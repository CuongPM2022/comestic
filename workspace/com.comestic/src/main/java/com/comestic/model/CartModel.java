package com.comestic.model;

import java.sql.Timestamp;
import java.util.List;

public class CartModel extends AbstractModel {
	private String code;
	private String nameCustomer;
	private String gender;
	private String phone;
	private String email;
	private String address;
	private String method;
	private String note;
	private Double totalMoney;
	private Double importMoney;
	private Timestamp date;
	private String strDate;
	private Long userId;
	private Long stateId;
	private String stateName;
	private List<CartItemModel> listCartItem;
	
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public String getNameCustomer() {
		return nameCustomer;
	}
	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Double getImportMoney() {
		return importMoney;
	}
	public void setImportMoney(Double importMoney) {
		this.importMoney = importMoney;
	}
	public List<CartItemModel> getListCartItem() {
		return listCartItem;
	}
	public void setListCartItem(List<CartItemModel> listCartItem) {
		this.listCartItem = listCartItem;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStrDate() {
		return strDate;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	
}
