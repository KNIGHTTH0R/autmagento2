package com.tools.data.frontend;

public class BankTransferPaymentModel {

	private String company;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String phoneNo;

	public BankTransferPaymentModel() {
		this.company = "Evozon";
		this.streetAddress = "Titulescu";
		this.city = "Cluj-Napoca";
		this.state = "Cluj";
		this.zip = "45675";
		this.country = "Romania";
		this.phoneNo = "0943556433";
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	

}
