package com.tools.data;

public class TeamReportTakeOffPhaseModel {
	private String styleCoachId;
	private String stylistName;
	private String sponsorName;
	private String activationDate;
	private String takeOffPhaseEndDate;
	private String daysLeft;
	private String ip;
	private String ip30;
	private String newStylistTop;

	
	
	public String getIp30() {
		return ip30;
	}

	public void setIp30(String ip30) {
		this.ip30 = ip30;
	}

	public String getStyleCoachId() {
		return styleCoachId;
	}

	public void setStyleCoachId(String styleCoachId) {
		this.styleCoachId = styleCoachId;
	}

	public String getStylistName() {
		return stylistName;
	}

	public void setStylistName(String stylistName) {
		this.stylistName = stylistName;
	}

	public String getSponsorName() {
		return sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	public String getActivationDate() {
		return activationDate;
	}

	public String getNewStylistTop() {
		return newStylistTop;
	}

	public void setNewStylistTop(String newStylistTop) {
		this.newStylistTop = newStylistTop;
	}

	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}

	public String getTakeOffPhaseEndDate() {
		return takeOffPhaseEndDate;
	}

	public void setTakeOffPhaseEndDate(String takeOffPhaseEndDate) {
		this.takeOffPhaseEndDate = takeOffPhaseEndDate;
	}

	public String getDaysLeft() {
		return daysLeft;
	}

	public void setDaysLeft(String daysLeft) {
		this.daysLeft = daysLeft;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "TeamReportTakeOffPhaseModel [styleCoachId=" + styleCoachId + ", stylistName=" + stylistName
				+ ", sponsorName=" + sponsorName + ", activationDate=" + activationDate + ", takeOffPhaseEndDate="
				+ takeOffPhaseEndDate + ", daysLeft=" + daysLeft + ", ip=" + ip + ", ip30=" + ip30 + ", newStylistTop="
				+ newStylistTop + "]";
	}

	
	 

}
