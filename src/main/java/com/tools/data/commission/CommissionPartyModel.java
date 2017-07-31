package com.tools.data.commission;

import org.codehaus.jackson.annotate.JsonIgnore;

public class CommissionPartyModel {

	private String partyId;
	private int nrOfOrders;
	private int nrOfInvalidOrders;
	private double retailValue;
	private double grandRetailValue;
	private int ip;
	private String stylistId;
	private String contactId;
	private String hostCustomerId;
	private String partyDateTime;
	private String location;
	private String street;;
	private String postalCode;
	private String city;
	private String additional;
	private String createdAt;
	private String confirmedAt;
	private String updatedAt;
	private String deletedAt;
	@JsonIgnore
	private String status;
	
	
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public int getNrOfOrders() {
		return nrOfOrders;
	}
	public void setNrOfOrders(int nrOfOrders) {
		this.nrOfOrders = nrOfOrders;
	}
	public int getNrOfInvalidOrders() {
		return nrOfInvalidOrders;
	}
	public void setNrOfInvalidOrders(int nrOfInvalidOrders) {
		this.nrOfInvalidOrders = nrOfInvalidOrders;
	}
	public double getRetailValue() {
		return retailValue;
	}
	public void setRetailValue(double retailValue) {
		this.retailValue = retailValue;
	}
	public double getGrandRetailValue() {
		return grandRetailValue;
	}
	public void setGrandRetailValue(double grandRetailValue) {
		this.grandRetailValue = grandRetailValue;
	}
	public int getIp() {
		return ip;
	}
	public void setIp(int ip) {
		this.ip = ip;
	}
	public String getStylistId() {
		return stylistId;
	}
	public void setStylistId(String stylistId) {
		this.stylistId = stylistId;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getHostCustomerId() {
		return hostCustomerId;
	}
	public void setHostCustomerId(String hostCustomerId) {
		this.hostCustomerId = hostCustomerId;
	}
	public String getPartyDateTime() {
		return partyDateTime;
	}
	public void setPartyDateTime(String partyDateTime) {
		this.partyDateTime = partyDateTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAdditional() {
		return additional;
	}
	public void setAdditional(String additional) {
		this.additional = additional;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getConfirmedAt() {
		return confirmedAt;
	}
	public void setConfirmedAt(String confirmedAt) {
		this.confirmedAt = confirmedAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(String deletedAt) {
		this.deletedAt = deletedAt;
	}
	
	
}
