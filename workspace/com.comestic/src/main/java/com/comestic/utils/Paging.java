package com.comestic.utils;

import java.util.Map;

public class Paging {
	private Integer offset;
	private Integer maxItem;
	private String sortName;
	private String sortBy;
	private Map<String, Object> filter;
	
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getMaxItem() {
		return maxItem;
	}
	public void setMaxItem(Integer maxItem) {
		this.maxItem = maxItem;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public Map<String, Object> getFilter() {
		return filter;
	}
	public void setFilter(Map<String, Object> filter) {
		this.filter = filter;
	}
	
	
}
