package com.tools.data.frontend;

public class ContactModel {

	private String name;
	private String street;
	private String number;
	private String zip;
	private String town;
	private String country;
	private String createdAt;
	private boolean hasPartyHostInterrest;
	private boolean hasStyleCoachInterrest;
	private boolean isNewsletterSubscribed;
	private String lastHistoryRegistration;

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	public boolean getHasPartyHostInterrest() {
		return hasPartyHostInterrest;
	}

	public void setHasPartyHostInterrest(boolean hasPartyHostInterrest) {
		this.hasPartyHostInterrest = hasPartyHostInterrest;
	}

	public boolean getHasStyleCoachInterrest() {
		return hasStyleCoachInterrest;
	}

	public void setHasStyleCoachInterrest(boolean hasStyleCoachInterrest) {
		this.hasStyleCoachInterrest = hasStyleCoachInterrest;
	}

	public boolean getIsNewsletterSubscribed() {
		return isNewsletterSubscribed;
	}

	public void setIsNewsletterSubscribed(boolean isNewsletterSubscribed) {
		this.isNewsletterSubscribed = isNewsletterSubscribed;
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

	public String getLastHistoryRegistration() {
		return lastHistoryRegistration;
	}

	public void setLastHistoryRegistration(String lastHistoryRegistration) {
		this.lastHistoryRegistration = lastHistoryRegistration;
	}

}
