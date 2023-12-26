package com.comestic.dao.implement;

import javax.inject.Inject;

import com.comestic.dao.ICartDAO;
import com.comestic.dao.IStatisticDAO;
import com.comestic.model.StatisticModel;
import com.comestic.utils.Paging;

public class StatisticDAO extends AbstractDAO<StatisticModel> implements IStatisticDAO {

	@Inject
	private ICartDAO cartDAO;
	
	@Override
	public StatisticModel getStatistic(Paging paging) {
		StatisticModel statisticModel = new StatisticModel();
		statisticModel.setNewBill(cartDAO.getTotalNewBill(paging));
		statisticModel.setTotalBill(cartDAO.getTotalBill(paging));
		statisticModel.setTotalMoney(cartDAO.getTotalMoney(paging));
		statisticModel.setCancelBill(cartDAO.getTotalCancelBill(paging));
		statisticModel.setTotalAccess(getTotalAccess());
		return statisticModel;
	}

	@Override
	public Long getTotalAccess() {
		StringBuilder sql = new StringBuilder("select value from info where id=1");
		Long totalAccess = Long.parseLong(getRecord(sql.toString()).toString());
		return totalAccess;
	}

	@Override
	public void updateTotalAccess() {
		StringBuilder sql = new StringBuilder("Update info set value = convert(value, unsigned) + 1 where id=1");
		update(sql.toString());
	}

	

}
