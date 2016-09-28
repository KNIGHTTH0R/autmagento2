package com.tools.data.soap;

public class DBStylistPartyModel {

	private String party_id;
	private String stylist_id;
	private String deleted_at;
	private String closed_at;
	
	public String getDeleted_at() {
		return deleted_at;
	}
	public void setDeleted_at(String deleted_at) {
		this.deleted_at = deleted_at;
	}
	public String getClosed_at() {
		return closed_at;
	}
	public void setClosed_at(String closed_at) {
		this.closed_at = closed_at;
	}
	public String getParty_id() {
		return party_id;
	}
	public void setParty_id(String party_id) {
		this.party_id = party_id;
	}
	public String getStylist_id() {
		return stylist_id;
	}
	public void setStylist_id(String stylist_id) {
		this.stylist_id = stylist_id;
	}
}
