package com.comestic.model;

public class VarietyImageModel extends AbstractModel {
	private Long varietyId;
	private Long imageId;
	private Integer isImageAvatar;
	
	public Long getVarietyId() {
		return varietyId;
	}
	public void setVarietyId(Long varietyId) {
		this.varietyId = varietyId;
	}
	public Long getImageId() {
		return imageId;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	public Integer getIsImageAvatar() {
		return isImageAvatar;
	}
	public void setIsImageAvatar(Integer isImageAvatar) {
		this.isImageAvatar = isImageAvatar;
	}
	
}
