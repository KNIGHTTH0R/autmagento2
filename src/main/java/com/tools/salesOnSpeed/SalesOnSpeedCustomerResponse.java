package com.tools.salesOnSpeed;

import java.util.List;

import com.tools.data.salesOnSpeed.CustomFields;
import com.tools.data.salesOnSpeed.Notes;
import com.tools.data.salesOnSpeed.Phones;
import com.tools.data.salesOnSpeed.PrimaryPhone;

public class SalesOnSpeedCustomerResponse {
	private String prename;

	private String street;

	private String __v;

	private String lastname;

	private String postcode;

	private String city;

	private String country;

	private String _id;

	private String updated;

	private String created;

	private String email;

	private String userId;

	private String language;
	
	private List<Phones> phones;

	private List<Notes> notes;
	
	private CustomFields customFields;
	
	private PrimaryPhone primaryPhone;

	public PrimaryPhone getPrimaryPhone() {
		return primaryPhone;
	}

	public List<Phones> getPhones() {
		return phones;
	}

	public void setPhones(List<Phones> phones) {
		this.phones = phones;
	}

	public List<Notes> getNotes() {
		return notes;
	}

	public void setNotes(List<Notes> notes) {
		this.notes = notes;
	}

	public void setPrimaryPhone(PrimaryPhone primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	
	public String getPrename() {
		return prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

	public CustomFields getCustomFields() {
		return customFields;
	}

	public void setCustomFields(CustomFields customFields) {
		this.customFields = customFields;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String get__v() {
		return __v;
	}

	public void set__v(String __v) {
		this.__v = __v;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}



	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "SalesOnSpeedCustomerResponse [prename=" + prename + ", street=" + street + ", __v=" + __v
				+ ", lastname=" + lastname + ", postcode=" + postcode + ", city=" + city + ", country=" + country
				+ ", _id=" + _id + ", updated=" + updated + ", created=" + created + ", email=" + email + ", userId="
				+ userId + ", language=" + language + ", phones=" + phones + ", notes=" + notes + ", customFields="
				+ customFields + ", primaryPhone=" + primaryPhone + "]";
	}

	

	
	
	
}
