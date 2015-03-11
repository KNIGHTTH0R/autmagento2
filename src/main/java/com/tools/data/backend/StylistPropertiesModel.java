package com.tools.data.backend;

public class StylistPropertiesModel {

	private String type;
	private String status;
	private String jewelryreceived;

	public StylistPropertiesModel() {

	}

	public StylistPropertiesModel(String status, String jewString, String type) {
		this.type = type;
		this.status = status;
		this.jewelryreceived = jewString;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJewelryreceived() {
		return jewelryreceived;
	}

	public void setJewelryreceived(String jewelryreceived) {
		this.jewelryreceived = jewelryreceived;
	}

}
