package com.comestic.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductModel extends AbstractModel {
	private Long categoryId;
	private String categoryName;
	private Long manufactureId;
	private String manufactureName;
	private Long typeProductId;
	private String code;
	private String name;
	private String image;
	private Timestamp date;
	private Integer totalComment;
	private Integer totalPreview;
	private String shortDescription;
	private String longDescription;
	private Integer view;
	private Integer state;
	private Integer isHot;
	private Integer numberLimit;
	private Double price;
	private double star;
	private Integer numberVariety;
	private List<VarietyModel> listVariety = new ArrayList<>();
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getManufactureId() {
		return manufactureId;
	}
	public void setManufactureId(Long manufactureId) {
		this.manufactureId = manufactureId;
	}
	public String getManufactureName() {
		return manufactureName;
	}
	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}
	public Long getTypeProductId() {
		return typeProductId;
	}
	public void setTypeProductId(Long typeProductId) {
		this.typeProductId = typeProductId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Integer getTotalComment() {
		return totalComment;
	}
	public void setTotalComment(Integer totalComment) {
		this.totalComment = totalComment;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	public Integer getView() {
		return view;
	}
	public void setView(Integer view) {
		this.view = view;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getIsHot() {
		return isHot;
	}
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Integer getNumberLimit() {
		return numberLimit;
	}
	public void setNumberLimit(Integer numberLimit) {
		this.numberLimit = numberLimit;
	}
	public double getStar() {
		return star;
	}
	public void setStar(double star) {
		this.star = star;
	}
	public Integer getNumberVariety() {
		return numberVariety;
	}
	public void setNumberVariety(Integer numberVariety) {
		this.numberVariety = numberVariety;
	}
	public List<VarietyModel> getListVariety() {
		return listVariety;
	}
	public void setListVariety(List<VarietyModel> listVariety) {
		this.listVariety = listVariety;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getTotalPreview() {
		return totalPreview;
	}
	public void setTotalPreview(Integer totalPreview) {
		this.totalPreview = totalPreview;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
