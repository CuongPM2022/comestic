package com.comestic.dao.implement;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.comestic.dao.IAbstractDAO;
import com.comestic.dao.mapper.IRowMapper;
import com.comestic.utils.Paging;

public class AbstractDAO<T> implements IAbstractDAO<T> {
	
	void deleteFile(String filePath) {
		File file = new File(filePath);
		if(!file.delete()) {
			System.out.println("Error delete file!");
		}
	}
	
	protected Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/ecom_store";
		String username = "root";
		String password = "Cuong@12345";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn = null;
		}
		return conn;
	}

	@Override
	public List<T> query(String sql, IRowMapper<T> rowMapper, Object... parameters) {
		Connection conn = getConnection();
		PreparedStatement state = null;
		ResultSet resultSet = null;
		List<T> list = null;
		if (conn != null) {
			list = new ArrayList<T>();
			try {
				state = conn.prepareStatement(sql);
				setParameter(state, parameters);
				resultSet = state.executeQuery();
				if (resultSet != null) {
					while (resultSet.next()) {
						list.add(rowMapper.mapRow(resultSet));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				list = null;
			} finally {
				closeAll(conn, state, resultSet);
			}
		}
		return list;
	}

	@Override
	public Integer count(String sql, Object... paremeters) {
		Connection conn = getConnection();
		PreparedStatement state = null;
		ResultSet resultSet = null;
		Integer count = 0;
		conn = getConnection();
		if (conn != null) {
			try {
				state = conn.prepareStatement(sql);
				setParameter(state, paremeters);
				resultSet = state.executeQuery();
				if (resultSet != null) {
					if (resultSet.next()) {
						count = resultSet.getInt(1);
					}
				}
			} catch (SQLException e) {
				count = 0;
				e.printStackTrace();
			} finally {
				closeAll(conn, state, resultSet);
			}
		}
		return count;
	}

	@Override
	public Object getRecord(String sql, Object... paremeters) {
		Object result = null;
		Connection conn = getConnection();
		PreparedStatement state = null;
		ResultSet resultSet = null;
		if (conn != null) {
			try {
				state = conn.prepareStatement(sql);
				setParameter(state, paremeters);
				resultSet = state.executeQuery();
				if (resultSet != null) {
					if (resultSet.next()) {
						result = resultSet.getObject(1);
					}
				}
			} catch (SQLException e) {
				result = null;
				e.printStackTrace();
			} finally {
				closeAll(conn, state, resultSet);
			}

		}
		return result;
	}

	@Override
	// tự đóng và mở Transaction
	public Long insert(String sql, Object... paremeters) {
		Long id = null;
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
				id = insertWithAvailableTran(conn, sql, paremeters);
				conn.commit();
			} catch (SQLException e) {
				if (conn != null) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				id = null;
				e.printStackTrace();
			} finally {
				closeConnection(conn);
			}
		}
		return id;
	}
	
	// sử dụng một Transaction có sẵn nào đó
	public Long insertWithAvailableTran(Connection conn, String sql, Object... paremeters) throws SQLException {
		Long id = null;
		PreparedStatement state = null;
		ResultSet resultSet = null;
		
		if (conn != null) {
			state = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameter(state, paremeters);
			state.executeUpdate();
			resultSet = state.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
		
			closeResultSet(resultSet);
			closeStatement(state);
		}
		return id;
	}
	
	
	@Override
	public void update(String sql, Object... paremeters) {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
				updateWithAvailableTran(conn, sql, paremeters);
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				if (conn != null) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			} finally {
				closeConnection(conn);
			}
		}

	}
	
	public void updateWithAvailableTran(Connection conn, String sql, Object... paremeters) throws SQLException {
		PreparedStatement state = null;
		if(conn != null) {
			state = conn.prepareStatement(sql);
			setParameter(state, paremeters);
			state.executeUpdate();
			closeStatement(state);
		}
	}

	protected void setPaging(StringBuilder sql, Paging paging) {
		if (paging != null) {
			setFilter(sql, paging);

			String sortName = paging.getSortName();
			if (sortName != null) {
				if(sortName.equals("price")) {
					sql.append(MessageFormat.format(" order by price*(1-percentdes/100) {0}", paging.getSortBy()));
				}
				else {
					sql.append(MessageFormat.format(" order by {0} {1}", sortName, paging.getSortBy()));
				}
			}

			if (paging.getOffset() != null && paging.getMaxItem() != null) {
				sql.append(MessageFormat.format(" limit {0},{1}", paging.getOffset(), paging.getMaxItem()));
			}
		}
	}

	protected void setFilter(StringBuilder sql, Paging paging) {
		if (paging != null) {
			if (paging.getFilter() != null) {
				if (!sql.toString().contains(" where")) {
					sql.append(" where 1");
				}

				Map<String, Object> temp = paging.getFilter();
				Set<String> set = temp.keySet();
				for (String key : set) {
					if (key.equals("manufactureid") || key.equals("categoryid") || key.equals("stateid")) {
						sql.append(MessageFormat.format(" and {0}={1}", key, temp.get(key).toString()));
					} else if (key.equals("pricemin")) {
						sql.append(MessageFormat.format(" and price*(1-percentdes/100)>={0}", temp.get(key).toString()));
					} else if (key.equals("pricemax")) {
						sql.append(MessageFormat.format(" and price*(1-percentdes/100)<={0}", temp.get(key).toString()));
					} else if (key.equals("sale")) {
						sql.append(" and percentdes!=0");
					}
				}
			}
		}
	}
	
	protected void setPagingHasFilterDate(StringBuilder sql, Paging paging, List<Object> listInput) {
		if(paging != null) {
			setPaging(sql, paging);
			if(paging.getFilter() != null) {
				setFilter(sql, paging);
				Map<String, Object> temp = paging.getFilter();
				Set<String> set = temp.keySet();
				for (String key : set) {
					if(key.equals("minday")) {
						sql.append(" and date>=?");
						listInput.add(temp.get(key).toString());
					}
					else if(key.equals("maxday")) {
						sql.append(" and date<= DATE_ADD(?,INTERVAL 1 DAY)");
						listInput.add(temp.get(key).toString());
					}
				}
			}
		}
	}

	private void setParameter(PreparedStatement state, Object... parameters) {
		if (parameters.length != 0) {
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) parameters[0];
			int length = list.size();
			try {
				for (int i = 0; i < length; i++) {
					if (list.get(i) instanceof Integer) {
						state.setInt(i + 1, (Integer) list.get(i));
					} else if (list.get(i) instanceof Long) {
						state.setLong(i + 1, (Long) list.get(i));
					} else if (list.get(i) instanceof Double) {
						state.setDouble(i + 1, (Double) list.get(i));
					} else if (list.get(i) instanceof String) {
						state.setString(i + 1, (String) list.get(i));
					} else if (list.get(i) instanceof Timestamp) {
						state.setTimestamp(i + 1, (Timestamp) list.get(i));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		

	}
	
	protected void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void closeStatement(PreparedStatement state) {
		try {
			if (state != null) {
				state.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void closeResultSet(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void closeAll(Connection conn, PreparedStatement state, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (state != null) {
				state.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
