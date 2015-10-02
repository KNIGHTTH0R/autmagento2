package com.tools.data.frontend;

public class ContactModel {

	private String name;
	private String street;
	private String number;
	private String zip;
	private String town;
	private String country;
	private String createdAt;
	private String partyHostStatus;
	private String styleCoachStatus;
	private String newsletterStatus;

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getPartyHostStatus() {
		return partyHostStatus;
	}

	public void setPartyHostStatus(String partyHostStatus) {
		this.partyHostStatus = partyHostStatus;
	}

	public String getStyleCoachStatus() {
		return styleCoachStatus;
	}

	public void setStyleCoachStatus(String styleCoachStatus) {
		this.styleCoachStatus = styleCoachStatus;
	}

	public String getNewsletterStatus() {
		return newsletterStatus;
	}

	public void setNewsletterStatus(String newsletterStatus) {
		this.newsletterStatus = newsletterStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
