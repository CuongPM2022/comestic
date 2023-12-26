package com.comestic.model;

public class StatisticModel {
	private Integer newBill;
	private Integer totalBill;
	private Integer cancelBill;
	private Double totalMoney;
	private Long totalAccess;
	private Integer online;
	
	public Integer getNewBill() {
		return newBill;
	}
	public void setNewBill(Integer newBill) {
		this.newBill = newBill;
	}
	public Integer getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(Integer totalBill) {
		this.totalBill = totalBill;
	}
	public Integer getCancelBill() {
		return cancelBill;
	}
	public void setCancelBill(Integer cancelBill) {
		this.cancelBill = cancelBill;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Long getTotalAccess() {
		return totalAccess;
	}
	public void setTotalAccess(Long totalAccess) {
		this.totalAccess = totalAccess;
	}
	public Integer getOnline() {
		return online;
	}
	public void setOnline(Integer online) {
		this.online = online;
	}
}
