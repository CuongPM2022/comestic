package com.comestic.model;

import java.util.ArrayList;
import java.util.List;

public class VarietyModel extends AbstractModel {
	private Long productId;
	private String image;
	private Long imageId;
	private Double price;
	private Integer percentDes;
	private Integer number;
	private Integer isAvatar;
	private List<VarietyAttributeModel> listAttribute = new ArrayList<>();
	
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
	public List<VarietyAttributeModel> getListAttribute() {
		return listAttribute;
	}
	public void setListAttribute(List<VarietyAttributeModel> listAttribute) {
		this.listAttribute = listAttribute;
	}
	
	public String findStrListAttribute() {
		String temp = "";
		for(VarietyAttributeModel item:listAttribute) {
			temp += item.getName() + ": " + item.getValue() + ", ";
		}
		if(!temp.isEmpty()) {
			temp = temp.substring(0, temp.length() - 2);
		}
		return temp;
	}
	public Integer getPercentDes() {
		return percentDes;
	}
	public void setPercentDes(Integer percentDes) {
		this.percentDes = percentDes;
	}
	public Long getImageId() {
		return imageId;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	
}
