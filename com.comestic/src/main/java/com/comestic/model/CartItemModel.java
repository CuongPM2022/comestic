package com.comestic.model;

public class CartItemModel extends AbstractModel {
	private Long cartId;
	private Long productId;
	private String productCode;
	private String productName;
	private Long varietyId;
	private Double price;
	private Integer percentDes;
	private Integer number;
	private Integer userQuantity;
	private String image;
	private String state;
	private String varietyDetail;
	
	public String getVarietyDetail() {
		return varietyDetail;
	}
	public void setVarietyDetail(String varietyDetail) {
		this.varietyDetail = varietyDetail;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getVarietyId() {
		return varietyId;
	}
	public void setVarietyId(Long varietyId) {
		this.varietyId = varietyId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getPercentDes() {
		return percentDes;
	}
	public void setPercentDes(Integer percentDes) {
		this.percentDes = percentDes;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getUserQuantity() {
		return userQuantity;
	}
	public void setUserQuantity(Integer userQuantity) {
		this.userQuantity = userQuantity;
	}
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
}
