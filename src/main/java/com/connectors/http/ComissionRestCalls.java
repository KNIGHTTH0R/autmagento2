package com.connectors.http;

import org.codehaus.jackson.map.ObjectMapper;

import com.tools.commision.CommisionPartyResponse;
import com.tools.commision.CommisionStylistResponse;
import com.tools.data.commission.CommissionPartyModel;
import com.tools.data.commission.CommissionStylistModel;
import com.tools.env.variables.Credentials;
import com.tools.env.variables.UrlConstants;
import com.tools.utils.DateUtils;
import com.tools.utils.MD5;
import com.tools.utils.PrintUtils;

public class ComissionRestCalls {

	public static void main(String[] args) throws Exception {

		ComissionRestCalls.getStylistInfo("1835");

	}

	public static String composeAuthenticationSuffix() throws Exception {

		String hash = MD5.getMd5(Credentials.API_KEY + Credentials.API_SECRET + DateUtils.getTimestamp() + "300");
		String suffix = "?api_sig=" + hash + "&api_key=" + Credentials.API_KEY + "&api_ts=" + DateUtils.getTimestamp() + "&api_ttl=300";

		return suffix;
	}

	public static CommissionStylistModel getStylistInfo(String stylistId) throws Exception {

		String unparsedResponse = JerseyClient.sendGet(UrlConstants.COMMISION_WEB_BASE + UrlConstants.COMMISION_STYLIST_SUFFIX + stylistId + composeAuthenticationSuffix());
		CommissionStylistModel commissionStylistModel = new CommissionStylistModel();
		ObjectMapper mapper = new ObjectMapper();
		CommisionStylistResponse res = (CommisionStylistResponse) mapper.readValue(unparsedResponse, CommisionStylistResponse.class);
		if (res.getStatus().equals("ok")) {

			commissionStylistModel.setId(res.getBody().getId());
			commissionStylistModel.setStylistId(res.getBody().getStylistId());
			commissionStylistModel.setParentStylistId(res.getBody().getParentStylistId());
			commissionStylistModel.setName(res.getBody().getName());
			commissionStylistModel.setCustomerId(res.getBody().getCustomerId());
			commissionStylistModel.setCity(res.getBody().getCity());
			commissionStylistModel.setPostcode(res.getBody().getPostcode());
			commissionStylistModel.setStreet(res.getBody().getStreet());
			commissionStylistModel.setCountryId(res.getBody().getCountryId());
			commissionStylistModel.setStatus(res.getBody().getStatus());
			commissionStylistModel.setCareerLevel(res.getBody().getCareerLevel());
			commissionStylistModel.setIp(res.getBody().getIp());
			commissionStylistModel.setIpUnsafe(res.getBody().getIpUnsafe());
			commissionStylistModel.setIpMonth(res.getBody().getIpMonth());
			commissionStylistModel.setIpNewFl(res.getBody().getIpNewFl());
			commissionStylistModel.setTeamPoints(res.getBody().getTeamPoints());
			commissionStylistModel.setTeamPointsUnsafe(res.getBody().getTeamPointsUnsafe());
			commissionStylistModel.setRetail(res.getBody().getRetail());
			commissionStylistModel.setTob(res.getBody().getTob());
			commissionStylistModel.setPaylevel(res.getBody().getPaylevel());
			commissionStylistModel.setMinimumPaylevel(res.getBody().getMinimumPaylevel());
			commissionStylistModel.setMinimumCareerLevel(res.getBody().getMinimumCareerLevel());
			commissionStylistModel.setJoinedAt(res.getBody().getJoinedAt());
			commissionStylistModel.setCreatedAt(res.getBody().getCreatedAt());
			commissionStylistModel.setTeamPointsLevel1(res.getBody().getTeamPointsLevel1());
			commissionStylistModel.setTeamPointsLevel2(res.getBody().getTeamPointsLevel2());
			commissionStylistModel.setTeamPointsLevel3(res.getBody().getTeamPointsLevel3());
			commissionStylistModel.setTeamPointsLevel4(res.getBody().getTeamPointsLevel4());
			commissionStylistModel.setNrTeamMembersLevel1(res.getBody().getNrTeamMembersLevel1());
			commissionStylistModel.setNrTeamMembersLevel2(res.getBody().getNrTeamMembersLevel2());
			commissionStylistModel.setNrTeamMembersLevel3(res.getBody().getNrTeamMembersLevel3());
			commissionStylistModel.setNrTeamMembersLevel4(res.getBody().getNrTeamMembersLevel4());
			commissionStylistModel.setActivatedAt(res.getBody().getActivatedAt());
			commissionStylistModel.setBankAccountOwnerName(res.getBody().getBankAccountOwnerName());
			commissionStylistModel.setBankAccountBankName(res.getBody().getBankAccountBankName());
			commissionStylistModel.setBankAccountNumber(res.getBody().getBankAccountNumber());
			commissionStylistModel.setBankAccountBlz(res.getBody().getBankAccountBlz());
			commissionStylistModel.setBankAccountVatPayer(res.getBody().getBankAccountVatPayer());
			commissionStylistModel.setBankAccountVatNumber(res.getBody().getBankAccountVatNumber());
			commissionStylistModel.setObtTeam(res.getBody().getObtTeam());
			commissionStylistModel.setFrontliners(res.getBody().getFrontliners());
			commissionStylistModel.setFrontlinersQualified(res.getBody().getFrontlinersQualified());
			commissionStylistModel.setCareer(res.getBody().getCareer());
			commissionStylistModel.setGoldFrontliners(res.getBody().getGoldFrontliners());
			commissionStylistModel.setNewFrontliners(res.getBody().getNewFrontliners());
			commissionStylistModel.setIpForMyself(res.getBody().getIpForMyself());
			commissionStylistModel.setIpForCustomers(res.getBody().getIpForCustomers());

		} else {
			System.out.println(res.getStatus());
		}

		return commissionStylistModel;
	}

	public static String getPartyPerformanceInfo(String stylistId) throws Exception {

		String unparsedResponse = JerseyClient.sendGet(UrlConstants.COMMISION_WEB_BASE + UrlConstants.COMMISION_PARTY_SUFFIX + stylistId + composeAuthenticationSuffix());

		CommissionPartyModel commissionPartyModel = new CommissionPartyModel();

		ObjectMapper mapper = new ObjectMapper();
		CommisionPartyResponse res = (CommisionPartyResponse) mapper.readValue(unparsedResponse, CommisionPartyResponse.class);

		commissionPartyModel.setPartyId(res.getPartyId());
		commissionPartyModel.setNrOfOrders(res.getNrOfOrders());
		commissionPartyModel.setNrOfInvalidOrders(res.getNrOfInvalidOrders());
		commissionPartyModel.setRetailValue(res.getRetailValue());
		commissionPartyModel.setGrandRetailValue(res.getGrandRetailValue());
		commissionPartyModel.setIp(res.getIp());
		commissionPartyModel.setStylistId(res.getStylistId());
		commissionPartyModel.setContactId(res.getContactId());
		commissionPartyModel.setHostCustomerId(res.getHostCustomerId());
		commissionPartyModel.setPartyDateTime(res.getPartyDateTime());
		commissionPartyModel.setLocation(res.getLocation());
		commissionPartyModel.setStreet(res.getStreet());
		commissionPartyModel.setPostalCode(res.getPostalCode());
		commissionPartyModel.setCity(res.getCity());
		commissionPartyModel.setAdditional(res.getAdditional());
		commissionPartyModel.setCreatedAt(res.getCreatedAt());
		commissionPartyModel.setConfirmedAt(res.getConfirmedAt());
		commissionPartyModel.setUpdatedAt(res.getUpdatedAt());
		commissionPartyModel.setDeletedAt(res.getDeletedAt());

		PrintUtils.printCommisionPartyModel(commissionPartyModel);

		return unparsedResponse;
	}
}
