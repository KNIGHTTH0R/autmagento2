package com.tools.data;

import java.util.List;

public class TeamReportModel {
	private String styleCoachId;
	private String styleCoachName;
	private String activationDate;
	private String canceledDate;
	private String ip;
	private String ipTop;
	private String tqv;
	private String carrerLevelThisMonth;
	private String carrerLevelLastMonth;
	private String payLevel;
	private String ipNewRecruited;
	private String newStylist;
	private String newStylistTop;
	private String ipThisMonth;
	private String ipLastMonth;
	private String partiesHeld;
	private String partiesPlanned;
	private String partiesUpcoming;
	private String revenuePerParty;
	private String takeOffPhaseEndDate;
	private String daysLeft;
	private String level;
	private String ip30Top;
	private String topNrNewStylists;
	private List<IpOverViewReturnsListModel> returns;
	private String manualIpCorrection;
	private String manualIpCorrectionTop;
	
	
	

	public String getManualIpCorrectionTop() {
		return manualIpCorrectionTop;
	}

	public void setManualIpCorrectionTop(String manualIpCorrectionTop) {
		this.manualIpCorrectionTop = manualIpCorrectionTop;
	}

	public String getManualIpCorrection() {
		return manualIpCorrection;
	}

	public void setManualIpCorrection(String manualIpCorrection) {
		this.manualIpCorrection = manualIpCorrection;
	}

	public List<IpOverViewReturnsListModel> getReturns() {
		return returns;
	}

	public void setReturns(List<IpOverViewReturnsListModel> returns) {
		this.returns = returns;
	}

	public String getTopNrNewStylists() {
		return topNrNewStylists;
	}

	public void setTopNrNewStylists(String topNrNewStylists) {
		this.topNrNewStylists = topNrNewStylists;
	}

	public String getIp30Top() {
		return ip30Top;
	}

	public void setIp30Top(String ip30Top) {
		this.ip30Top = ip30Top;
	}

	public String getCanceledDate() {
		return canceledDate;
	}

	public void setCanceledDate(String canceledDate) {
		this.canceledDate = canceledDate;
	}

	public String getStyleCoachId() {
		return styleCoachId;
	}

	public void setStyleCoachId(String styleCoachId) {
		this.styleCoachId = styleCoachId;
	}

	public String getNewStylist() {
		return newStylist;
	}

	public void setNewStylist(String newStylist) {
		this.newStylist = newStylist;
	}

	public String getStyleCoachName() {
		return styleCoachName;
	}

	public void setStyleCoachName(String styleCoachName) {
		this.styleCoachName = styleCoachName;
	}

	public String getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}

	public String getNewStylistTop() {
		return newStylistTop;
	}

	public void setNewStylistTop(String newStylistTop) {
		this.newStylistTop = newStylistTop;
	}

	public String getRevenuePerParty() {
		return revenuePerParty;
	}

	public void setRevenuePerParty(String revenuePerParty) {
		this.revenuePerParty = revenuePerParty;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIpTop() {
		return ipTop;
	}

	public void setIpTop(String ipTop) {
		this.ipTop = ipTop;
	}

	public String getTqv() {
		return tqv;
	}

	public void setTqv(String tqv) {
		this.tqv = tqv;
	}

	public String getCarrerLevelThisMonth() {
		return carrerLevelThisMonth;
	}

	public void setCarrerLevelThisMonth(String carrerLevelThisMonth) {
		this.carrerLevelThisMonth = carrerLevelThisMonth;
	}

	public String getCarrerLevelLastMonth() {
		return carrerLevelLastMonth;
	}

	public void setCarrerLevelLastMonth(String carrerLevelLastMonth) {
		this.carrerLevelLastMonth = carrerLevelLastMonth;
	}

	public String getPayLevel() {
		return payLevel;
	}

	public void setPayLevel(String payLevel) {
		this.payLevel = payLevel;
	}

	public String getIpNewRecruited() {
		return ipNewRecruited;
	}

	public void setIpNewRecruited(String ipNewRecruited) {
		this.ipNewRecruited = ipNewRecruited;
	}

	public String getIpThisMonth() {
		return ipThisMonth;
	}

	public void setIpThisMonth(String ipThisMonth) {
		this.ipThisMonth = ipThisMonth;
	}

	public String getIpLastMonth() {
		return ipLastMonth;
	}

	public void setIpLastMonth(String ipLastMonth) {
		this.ipLastMonth = ipLastMonth;
	}

	public String getPartiesHeld() {
		return partiesHeld;
	}

	public void setPartiesHeld(String partiesHeld) {
		this.partiesHeld = partiesHeld;
	}

	public String getPartiesPlanned() {
		return partiesPlanned;
	}

	public void setPartiesPlanned(String partiesPlanned) {
		this.partiesPlanned = partiesPlanned;
	}

	public String getPartiesUpcoming() {
		return partiesUpcoming;
	}

	public void setPartiesUpcoming(String partiesUpcoming) {
		this.partiesUpcoming = partiesUpcoming;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "TeamReportModel [styleCoachId=" + styleCoachId + ", styleCoachName=" + styleCoachName
				+ ", activationDate=" + activationDate + ", canceledDate=" + canceledDate + ", ip=" + ip + ", ipTop="
				+ ipTop + ", tqv=" + tqv + ", carrerLevelThisMonth=" + carrerLevelThisMonth + ", carrerLevelLastMonth="
				+ carrerLevelLastMonth + ", payLevel=" + payLevel + ", ipNewRecruited=" + ipNewRecruited
				+ ", newStylist=" + newStylist + ", newStylistTop=" + newStylistTop + ", ipThisMonth=" + ipThisMonth
				+ ", ipLastMonth=" + ipLastMonth + ", partiesHeld=" + partiesHeld + ", partiesPlanned=" + partiesPlanned
				+ ", partiesUpcoming=" + partiesUpcoming + ", revenuePerParty=" + revenuePerParty
				+ ", takeOffPhaseEndDate=" + takeOffPhaseEndDate + ", daysLeft=" + daysLeft + ", level=" + level
				+ ", ip30Top=" + ip30Top + ", topNrNewStylists=" + topNrNewStylists + "]";
	}

	
	
}
