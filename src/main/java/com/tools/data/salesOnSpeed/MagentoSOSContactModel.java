package com.tools.data.salesOnSpeed;

import java.util.List;

public class MagentoSOSContactModel {
	private String prename;
	
	private String contactId;

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

	//custom fields male , value, number, text 
	private String primaryPhoneNumber;

	private String underagedValue;
	private String not_interestedValue;

	private String contactedProgress_2Value;
	private String flagContactBoosterValue;
	private String contactedProgress_3Value;
	private String wrongDetailsValue;
	private String maleValue;
	private String signupIssuesValue;
	private String campaignNameValue;
	private String flagPartiesValue;

	private String isDistributedValue;

	private String flagMemberValue;

	private String roadshowCityValue;

	private String followUpDateValue;

	private String langIssuesValue;

	
	
	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getUnderagedValue() {
		return underagedValue;
	}

	public void setUnderagedValue(String underagedValue) {
		this.underagedValue = underagedValue;
	}

	public String getPrename() {
		return prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
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

	public String getPrimaryPhoneNumber() {
		return primaryPhoneNumber;
	}

	public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
		this.primaryPhoneNumber = primaryPhoneNumber;
	}

	

	public String getNot_interestedValue() {
		return not_interestedValue;
	}

	public void setNot_interestedValue(String not_interestedValue) {
		this.not_interestedValue = not_interestedValue;
	}

	public String getContactedProgress_2Value() {
		return contactedProgress_2Value;
	}

	public void setContactedProgress_2Value(String contactedProgress_2Value) {
		this.contactedProgress_2Value = contactedProgress_2Value;
	}

	public String getFlagContactBoosterValue() {
		return flagContactBoosterValue;
	}

	public void setFlagContactBoosterValue(String flagContactBoosterValue) {
		this.flagContactBoosterValue = flagContactBoosterValue;
	}

	public String getContactedProgress_3Value() {
		return contactedProgress_3Value;
	}

	public void setContactedProgress_3Value(String contactedProgress_3Value) {
		this.contactedProgress_3Value = contactedProgress_3Value;
	}

	public String getWrongDetailsValue() {
		return wrongDetailsValue;
	}

	public void setWrongDetailsValue(String wrongDetailsValue) {
		this.wrongDetailsValue = wrongDetailsValue;
	}

	public String getMaleValue() {
		return maleValue;
	}

	public void setMaleValue(String maleValue) {
		this.maleValue = maleValue;
	}

	public String getSignupIssuesValue() {
		return signupIssuesValue;
	}

	public void setSignupIssuesValue(String signupIssuesValue) {
		this.signupIssuesValue = signupIssuesValue;
	}

	public String getCampaignNameValue() {
		return campaignNameValue;
	}

	public void setCampaignNameValue(String campaignNameValue) {
		this.campaignNameValue = campaignNameValue;
	}

	public String getFlagPartiesValue() {
		return flagPartiesValue;
	}

	public void setFlagPartiesValue(String flagPartiesValue) {
		this.flagPartiesValue = flagPartiesValue;
	}

	public String getIsDistributedValue() {
		return isDistributedValue;
	}

	public void setIsDistributedValue(String isDistributedValue) {
		this.isDistributedValue = isDistributedValue;
	}

	public String getFlagMemberValue() {
		return flagMemberValue;
	}

	public void setFlagMemberValue(String flagMemberValue) {
		this.flagMemberValue = flagMemberValue;
	}

	public String getRoadshowCityValue() {
		return roadshowCityValue;
	}

	public void setRoadshowCityValue(String roadshowCityValue) {
		this.roadshowCityValue = roadshowCityValue;
	}

	public String getFollowUpDateValue() {
		return followUpDateValue;
	}

	public void setFollowUpDateValue(String followUpDateValue) {
		this.followUpDateValue = followUpDateValue;
	}

	public String getLangIssuesValue() {
		return langIssuesValue;
	}

	public void setLangIssuesValue(String langIssuesValue) {
		this.langIssuesValue = langIssuesValue;
	}

	@Override
	public String toString() {
		return "MagentoSOSContactModel [prename=" + prename + ", contactId=" + contactId + ", street=" + street
				+ ", __v=" + __v + ", lastname=" + lastname + ", postcode=" + postcode + ", city=" + city + ", country="
				+ country + ", _id=" + _id + ", updated=" + updated + ", created=" + created + ", email=" + email
				+ ", userId=" + userId + ", language=" + language + ", phones=" + phones + ", notes=" + notes
				+ ", primaryPhoneNumber=" + primaryPhoneNumber + ", underagedValue=" + underagedValue
				+ ", not_interestedValue=" + not_interestedValue + ", contactedProgress_2Value="
				+ contactedProgress_2Value + ", flagContactBoosterValue=" + flagContactBoosterValue
				+ ", contactedProgress_3Value=" + contactedProgress_3Value + ", wrongDetailsValue=" + wrongDetailsValue
				+ ", maleValue=" + maleValue + ", signupIssuesValue=" + signupIssuesValue + ", campaignNameValue="
				+ campaignNameValue + ", flagPartiesValue=" + flagPartiesValue + ", isDistributedValue="
				+ isDistributedValue + ", flagMemberValue=" + flagMemberValue + ", roadshowCityValue="
				+ roadshowCityValue + ", followUpDateValue=" + followUpDateValue + ", langIssuesValue="
				+ langIssuesValue + "]";
	}


	
	
	
}
