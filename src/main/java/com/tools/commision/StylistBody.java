package com.tools.commision;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.tools.commision.Parties.Parties;

public class StylistBody {

	private String id;
	private String stylistId;
	private String parentStylistId;
	private String name;
	private String customerId;
	private String city;
	private String postcode;
	private String street;
	private String countryId;
	private String status;
	private String careerLevel;
	private String ip;
	private String ipUnsafe;
	private String ipMonth;
	private String ipNewFl;
	private String teamPoints;
	private String teamPointsUnsafe;
	private String retail;
	private String tob;
	private String paylevel;
	private String minimumPaylevel;
	private String minimumCareerLevel;
	private String joinedAt;
	private String createdAt;
	private String teamPointsLevel1;
	private String teamPointsLevel2;
	private String teamPointsLevel3;
	private String teamPointsLevel4;
	private String nrTeamMembersLevel1;
	private String nrTeamMembersLevel2;
	private String nrTeamMembersLevel3;
	private String nrTeamMembersLevel4;
	private String activatedAt;
	private String bankAccountOwnerName;
	private String bankAccountBankName;
	private String bankAccountNumber;
	private String bankAccountBlz;
	private String bankAccountVatPayer;
	private String bankAccountVatNumber;
	private String obtTeam;
	private String frontliners;
	private int frontlinersQualified;
	private String career;
	private int goldFrontliners;
	private int newFrontliners;
	private int ipForMyself;
	private int ipForCustomers;
	private String payoutForCustomers;
	private String payoutForMyself;
	private String payoutVia25;
	private String teamPointsTpr;
	private String teamPointsIpTpr;
	private String ppr;
	private String tpr;
	private String tprLevel1;
	private String tprLevel2;
	private String tpr_diff;
	@JsonIgnore
	private String diffPath;
	@JsonIgnore
	private String canceledAt;
	private String autoBonus;
	private String teamPointsLevel1Tpr;
	private String teamPointsLevel2Tpr;
	private String matchingBonus;
	private String tprDiff;
	private String stylistMatching;
	private Parties parties;
	private String ancestors;
	private int topNrNewStylists;
	private int topNrActiveStylist500Ip;
	private int topIpsNextTob;
	private int topIp;
	private int top30Ip;
	private String careerLevelName;
	private String paylevelName;

	public String getAncestors() {
		return ancestors;
	}

	public void setAncestors(String ancestors) {
		this.ancestors = ancestors;
	}

	public int getTopNrNewStylists() {
		return topNrNewStylists;
	}

	public void setTopNrNewStylists(int topNrNewStylists) {
		this.topNrNewStylists = topNrNewStylists;
	}

	public int getTopNrActiveStylist500Ip() {
		return topNrActiveStylist500Ip;
	}

	public void setTopNrActiveStylist500Ip(int topNrActiveStylist500Ip) {
		this.topNrActiveStylist500Ip = topNrActiveStylist500Ip;
	}

	public int getTopIpsNextTob() {
		return topIpsNextTob;
	}

	public void setTopIpsNextTob(int topIpsNextTob) {
		this.topIpsNextTob = topIpsNextTob;
	}

	public int getTopIp() {
		return topIp;
	}

	public void setTopIp(int topIp) {
		this.topIp = topIp;
	}

	public String getCareerLevelName() {
		return careerLevelName;
	}

	public void setCareerLevelName(String careerLevelName) {
		this.careerLevelName = careerLevelName;
	}

	public String getPaylevelName() {
		return paylevelName;
	}

	public void setPaylevelName(String paylevelName) {
		this.paylevelName = paylevelName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStylistId() {
		return stylistId;
	}

	public void setStylistId(String stylistId) {
		this.stylistId = stylistId;
	}

	public String getParentStylistId() {
		return parentStylistId;
	}

	public void setParentStylistId(String parentStylistId) {
		this.parentStylistId = parentStylistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCareerLevel() {
		return careerLevel;
	}

	public void setCareerLevel(String careerLevel) {
		this.careerLevel = careerLevel;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIpUnsafe() {
		return ipUnsafe;
	}

	public void setIpUnsafe(String ipUnsafe) {
		this.ipUnsafe = ipUnsafe;
	}

	public String getIpMonth() {
		return ipMonth;
	}

	public void setIpMonth(String ipMonth) {
		this.ipMonth = ipMonth;
	}

	public String getIpNewFl() {
		return ipNewFl;
	}

	public void setIpNewFl(String ipNewFl) {
		this.ipNewFl = ipNewFl;
	}

	public String getTeamPointsUnsafe() {
		return teamPointsUnsafe;
	}

	public void setTeamPointsUnsafe(String teamPointsUnsafe) {
		this.teamPointsUnsafe = teamPointsUnsafe;
	}

	public String getRetail() {
		return retail;
	}

	public void setRetail(String retail) {
		this.retail = retail;
	}

	public String getTob() {
		return tob;
	}

	public void setTob(String tob) {
		this.tob = tob;
	}

	public String getPaylevel() {
		return paylevel;
	}

	public void setPaylevel(String paylevel) {
		this.paylevel = paylevel;
	}

	public String getMinimumPaylevel() {
		return minimumPaylevel;
	}

	public void setMinimumPaylevel(String minimumPaylevel) {
		this.minimumPaylevel = minimumPaylevel;
	}

	public String getMinimumCareerLevel() {
		return minimumCareerLevel;
	}

	public void setMinimumCareerLevel(String minimumCareerLevel) {
		this.minimumCareerLevel = minimumCareerLevel;
	}

	public String getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(String joinedAt) {
		this.joinedAt = joinedAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getTeamPointsLevel1() {
		return teamPointsLevel1;
	}

	public void setTeamPointsLevel1(String teamPointsLevel1) {
		this.teamPointsLevel1 = teamPointsLevel1;
	}

	public String getTeamPointsLevel2() {
		return teamPointsLevel2;
	}

	public void setTeamPointsLevel2(String teamPointsLevel2) {
		this.teamPointsLevel2 = teamPointsLevel2;
	}

	public String getTeamPointsLevel3() {
		return teamPointsLevel3;
	}

	public void setTeamPointsLevel3(String teamPointsLevel3) {
		this.teamPointsLevel3 = teamPointsLevel3;
	}

	public String getTeamPointsLevel4() {
		return teamPointsLevel4;
	}

	public void setTeamPointsLevel4(String teamPointsLevel4) {
		this.teamPointsLevel4 = teamPointsLevel4;
	}

	public String getNrTeamMembersLevel1() {
		return nrTeamMembersLevel1;
	}

	public void setNrTeamMembersLevel1(String nrTeamMembersLevel1) {
		this.nrTeamMembersLevel1 = nrTeamMembersLevel1;
	}

	public String getNrTeamMembersLevel2() {
		return nrTeamMembersLevel2;
	}

	public void setNrTeamMembersLevel2(String nrTeamMembersLevel2) {
		this.nrTeamMembersLevel2 = nrTeamMembersLevel2;
	}

	public String getNrTeamMembersLevel3() {
		return nrTeamMembersLevel3;
	}

	public void setNrTeamMembersLevel3(String nrTeamMembersLevel3) {
		this.nrTeamMembersLevel3 = nrTeamMembersLevel3;
	}

	public String getNrTeamMembersLevel4() {
		return nrTeamMembersLevel4;
	}

	public void setNrTeamMembersLevel4(String nrTeamMembersLevel4) {
		this.nrTeamMembersLevel4 = nrTeamMembersLevel4;
	}

	public String getActivatedAt() {
		return activatedAt;
	}

	public void setActivatedAt(String activatedAt) {
		this.activatedAt = activatedAt;
	}

	public String getBankAccountOwnerName() {
		return bankAccountOwnerName;
	}

	public void setBankAccountOwnerName(String bankAccountOwnerName) {
		this.bankAccountOwnerName = bankAccountOwnerName;
	}

	public String getBankAccountBankName() {
		return bankAccountBankName;
	}

	public void setBankAccountBankName(String bankAccountBankName) {
		this.bankAccountBankName = bankAccountBankName;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankAccountBlz() {
		return bankAccountBlz;
	}

	public void setBankAccountBlz(String bankAccountBlz) {
		this.bankAccountBlz = bankAccountBlz;
	}

	public String getBankAccountVatPayer() {
		return bankAccountVatPayer;
	}

	public void setBankAccountVatPayer(String bankAccountVatPayer) {
		this.bankAccountVatPayer = bankAccountVatPayer;
	}

	public String getBankAccountVatNumber() {
		return bankAccountVatNumber;
	}

	public void setBankAccountVatNumber(String bankAccountVatNumber) {
		this.bankAccountVatNumber = bankAccountVatNumber;
	}

	public String getObtTeam() {
		return obtTeam;
	}

	public void setObtTeam(String obtTeam) {
		this.obtTeam = obtTeam;
	}

	public String getFrontliners() {
		return frontliners;
	}

	public void setFrontliners(String frontliners) {
		this.frontliners = frontliners;
	}

	public int getFrontlinersQualified() {
		return frontlinersQualified;
	}

	public void setFrontlinersQualified(int frontlinersQualified) {
		this.frontlinersQualified = frontlinersQualified;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public int getNewFrontliners() {
		return newFrontliners;
	}

	public void setNewFrontliners(int newFrontliners) {
		this.newFrontliners = newFrontliners;
	}

	public int getIpForMyself() {
		return ipForMyself;
	}

	public void setIpForMyself(int ipForMyself) {
		this.ipForMyself = ipForMyself;
	}

	public int getIpForCustomers() {
		return ipForCustomers;
	}

	public void setIpForCustomers(int ipForCustomers) {
		this.ipForCustomers = ipForCustomers;
	}

	public String getDiffPath() {
		return diffPath;
	}

	public void setDiffPath(String diffPath) {
		this.diffPath = diffPath;
	}

	public void setMatchingBonus(String matchingBonus) {
		this.matchingBonus = matchingBonus;
	}

	public String getMatchingBonus() {
		return matchingBonus;
	}

	public String getPpr() {
		return ppr;
	}

	public void setPpr(String ppr) {
		this.ppr = ppr;
	}

	public String getTpr() {
		return tpr;
	}

	public void setTpr(String tpr) {
		this.tpr = tpr;
	}

	public String getTprLevel1() {
		return tprLevel1;
	}

	public void setTprLevel1(String tprLevel1) {
		this.tprLevel1 = tprLevel1;
	}

	public String getTprLevel2() {
		return tprLevel2;
	}

	public void setTprLevel2(String tprLevel2) {
		this.tprLevel2 = tprLevel2;
	}

	public String getAutoBonus() {
		return autoBonus;
	}

	public void setAutoBonus(String autoBonus) {
		this.autoBonus = autoBonus;
	}

	public String getTeamPointsLevel1Tpr() {
		return teamPointsLevel1Tpr;
	}

	public void setTeamPointsLevel1Tpr(String teamPointsLevel1Tpr) {
		this.teamPointsLevel1Tpr = teamPointsLevel1Tpr;
	}

	public String getTeamPointsLevel2Tpr() {
		return teamPointsLevel2Tpr;
	}

	public void setTeamPointsLevel2Tpr(String teamPointsLevel2Tpr) {
		this.teamPointsLevel2Tpr = teamPointsLevel2Tpr;
	}

	public String getTpr_diff() {
		return tpr_diff;
	}

	public void setTpr_diff(String tpr_diff) {
		this.tpr_diff = tpr_diff;
	}

	public String getTprDiff() {
		return tprDiff;
	}

	public void setTprDiff(String tprDiff) {
		this.tprDiff = tprDiff;
	}

	public String getStylistMatching() {
		return stylistMatching;
	}

	public void setStylistMatching(String stylistMatching) {
		this.stylistMatching = stylistMatching;
	}

	public String getTeamPoints() {
		return teamPoints;
	}

	public void setTeamPoints(String teamPoints) {
		this.teamPoints = teamPoints;
	}

	public int getGoldFrontliners() {
		return goldFrontliners;
	}

	public void setGoldFrontliners(int goldFrontliners) {
		this.goldFrontliners = goldFrontliners;
	}

	public String getTeamPointsTpr() {
		return teamPointsTpr;
	}

	public void setTeamPointsTpr(String teamPointsTpr) {
		this.teamPointsTpr = teamPointsTpr;
	}

	public String getTeamPointsIpTpr() {
		return teamPointsIpTpr;
	}

	public void setTeamPointsIpTpr(String teamPointsIpTpr) {
		this.teamPointsIpTpr = teamPointsIpTpr;
	}

	public int getTop30Ip() {
		return top30Ip;
	}

	public void setTop30Ip(int top30Ip) {
		this.top30Ip = top30Ip;
	}

	public Parties getParties() {
		return parties;
	}

	public void setParties(Parties parties) {
		this.parties = parties;
	}

	public String getPayoutForCustomers() {
		return payoutForCustomers;
	}

	public void setPayoutForCustomers(String payoutForCustomers) {
		this.payoutForCustomers = payoutForCustomers;
	}

	public String getPayoutForMyself() {
		return payoutForMyself;
	}

	public void setPayoutForMyself(String payoutForMyself) {
		this.payoutForMyself = payoutForMyself;
	}

	public String getPayoutVia25() {
		return payoutVia25;
	}

	public void setPayoutVia25(String payoutVia25) {
		this.payoutVia25 = payoutVia25;
	}

	public String getCanceledAt() {
		return canceledAt;
	}

	public void setCanceledAt(String canceledAt) {
		this.canceledAt = canceledAt;
	}

}
