package com.comestic.model;

import java.util.List;

public class VarietyModel extends AbstractModel {
	private Long productId;
	private String image;
	private Double price;
	private Integer number;
	private Integer isAvatar;
	private List<AttributeOfVarietyModel> listAttribute;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getIsAvatar() {
		return isAvatar;
	}
	public void setIsAvatar(Integer isAvatar) {
		this.isAvatar = isAvatar;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<AttributeOfVarietyModel> getListAttribute() {
		return listAttribute;
	}
	public void setListAttribute(List<AttributeOfVarietyModel> listAttribute) {
		this.listAttribute = listAttribute;
	}
	
	
}
