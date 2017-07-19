package com.tools.data.frontend;

public class OnlineStylePartyModel {

	String hostUserName;
	String hostPassword;
	String hostPageUrl;
	String partyId;

	public String getHostPageUrl() {
		return hostPageUrl;
	}

	public void setHostPageUrl(String hostPageUrl) {
		this.hostPageUrl = hostPageUrl;
	}

	public String getHostUserName() {
		return hostUserName;
	}

	public void setHostUserName(String hostUserName) {
		this.hostUserName = hostUserName;
	}

	public String getHostPassword() {
		return hostPassword;
	}

	public void setHostPassword(String hostPassword) {
		this.hostPassword = hostPassword;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	@Override
	public String toString() {
		return "OnlineStylePartyModel [hostUserName=" + hostUserName + ", hostPassword=" + hostPassword
				+ ", hostPageUrl=" + hostPageUrl + ", partyId=" + partyId + "]";
	}

	
}
