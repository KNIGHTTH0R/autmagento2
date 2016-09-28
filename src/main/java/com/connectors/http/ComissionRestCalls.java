package com.connectors.http;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.tools.commision.CommisionPartyResponse;
import com.tools.commision.CommisionStylistListResponse;
import com.tools.commision.CommisionStylistResponse;
import com.tools.commision.StylistBody;
import com.tools.constants.Credentials;
import com.tools.constants.Separators;
import com.tools.constants.UrlConstants;
import com.tools.data.commission.CommissionPartyModel;
import com.tools.data.commission.CommissionStylistModel;
import com.tools.utils.DateUtils;
import com.tools.utils.MD5;
import com.tools.utils.PrintUtils;

public class ComissionRestCalls {

	public static void main(String[] args) throws Exception {

//		List<CommissionStylistModel> allStylists = ComissionRestCalls.getStylistListInfo();
//		List<CommissionStylistModel> level1List = CommissionService.getStylistsFromLevel(allStylists,"1030",1);
//		List<CommissionStylistModel> level2List = CommissionService.getStylistsFromLevel(allStylists,"1030",2);
//		List<CommissionStylistModel> level3List = CommissionService.getStylistsFromLevel(allStylists,"1030",3);
//		
//		System.out.println("-----------level 1----------------------------");
//		
//		for (CommissionStylistModel commissionStylistModel : level1List) {
//			System.out.println(commissionStylistModel.getName());
//			System.out.println(commissionStylistModel.getActivatedAt());
//			System.out.println(commissionStylistModel.getParentStylistId());
//		}
//		
//		System.out.println("-------------level 2--------------------------");
//		
//		for (CommissionStylistModel commissionStylistModel : level2List) {
//			System.out.println(commissionStylistModel.getName());
//			System.out.println(commissionStylistModel.getActivatedAt());
//			System.out.println(commissionStylistModel.getParentStylistId());
//		}
//		
//		System.out.println("--------------level 3-------------------------");
//		
//		for (CommissionStylistModel commissionStylistModel : level3List) {
//			System.out.println(commissionStylistModel.getName());
//			System.out.println(commissionStylistModel.getActivatedAt());
//			System.out.println(commissionStylistModel.getParentStylistId());
//		}
		ComissionRestCalls.getStylistInfo("1025");

	}

	public static String composeAuthenticationSuffix() throws Exception {

		String hash = MD5.getMd5(Credentials.API_KEY + Credentials.API_SECRET + DateUtils.getTimestamp() + "30");
		String suffix = "?page=1&per_page=5000&api_sig=" + hash + "&api_key=" + Credentials.API_KEY + "&api_ts="
				+ DateUtils.getTimestamp() + "&api_ttl=30";

		return suffix;
	}

	public static CommissionStylistModel getStylistInfo(String stylistId) throws Exception {

		String unparsedResponse = JerseyClient.sendGet(UrlConstants.COMMISION_WEB_BASE
				+ UrlConstants.COMMISION_STYLIST_SUFFIX + Separators.SLASH + stylistId + composeAuthenticationSuffix());
		CommissionStylistModel commissionStylistModel = new CommissionStylistModel();
		ObjectMapper mapper = new ObjectMapper();
		CommisionStylistResponse res = (CommisionStylistResponse) mapper.readValue(unparsedResponse,
				CommisionStylistResponse.class);
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

	public static List<CommissionStylistModel> getStylistListInfo() throws Exception {

		String unparsedResponse = JerseyClient.sendGet(UrlConstants.COMMISION_WEB_BASE
				+ UrlConstants.COMMISION_STYLIST_SUFFIX + composeAuthenticationSuffix());
		List<CommissionStylistModel> commissionStylistListModel = new ArrayList<CommissionStylistModel>();
		ObjectMapper mapper = new ObjectMapper();
		CommisionStylistListResponse res = (CommisionStylistListResponse) mapper.readValue(unparsedResponse,
				CommisionStylistListResponse.class);
		if (res.getStatus().equals("ok")) {

			for (StylistBody body : res.getBody()) {

				CommissionStylistModel commissionStylistModel = new CommissionStylistModel();

				commissionStylistModel.setId(body.getId());
				commissionStylistModel.setStylistId(body.getStylistId());
				commissionStylistModel.setParentStylistId(body.getParentStylistId());
				commissionStylistModel.setName(body.getName());
				commissionStylistModel.setCustomerId(body.getCustomerId());
				commissionStylistModel.setCity(body.getCity());
				commissionStylistModel.setPostcode(body.getPostcode());
				commissionStylistModel.setStreet(body.getStreet());
				commissionStylistModel.setCountryId(body.getCountryId());
				commissionStylistModel.setStatus(body.getStatus());
				commissionStylistModel.setCareerLevel(body.getCareerLevel());
				commissionStylistModel.setIp(body.getIp());
				commissionStylistModel.setIpUnsafe(body.getIpUnsafe());
				commissionStylistModel.setIpMonth(body.getIpMonth());
				commissionStylistModel.setIpNewFl(body.getIpNewFl());
				commissionStylistModel.setTeamPoints(body.getTeamPoints());
				commissionStylistModel.setTeamPointsUnsafe(body.getTeamPointsUnsafe());
				commissionStylistModel.setRetail(body.getRetail());
				commissionStylistModel.setTob(body.getTob());
				commissionStylistModel.setPaylevel(body.getPaylevel());
				commissionStylistModel.setMinimumPaylevel(body.getMinimumPaylevel());
				commissionStylistModel.setMinimumCareerLevel(body.getMinimumCareerLevel());
				commissionStylistModel.setJoinedAt(body.getJoinedAt());
				commissionStylistModel.setCreatedAt(body.getCreatedAt());
				commissionStylistModel.setTeamPointsLevel1(body.getTeamPointsLevel1());
				commissionStylistModel.setTeamPointsLevel2(body.getTeamPointsLevel2());
				commissionStylistModel.setTeamPointsLevel3(body.getTeamPointsLevel3());
				commissionStylistModel.setTeamPointsLevel4(body.getTeamPointsLevel4());
				commissionStylistModel.setNrTeamMembersLevel1(body.getNrTeamMembersLevel1());
				commissionStylistModel.setNrTeamMembersLevel2(body.getNrTeamMembersLevel2());
				commissionStylistModel.setNrTeamMembersLevel3(body.getNrTeamMembersLevel3());
				commissionStylistModel.setNrTeamMembersLevel4(body.getNrTeamMembersLevel4());
				commissionStylistModel.setActivatedAt(body.getActivatedAt());
				commissionStylistModel.setBankAccountOwnerName(body.getBankAccountOwnerName());
				commissionStylistModel.setBankAccountBankName(body.getBankAccountBankName());
				commissionStylistModel.setBankAccountNumber(body.getBankAccountNumber());
				commissionStylistModel.setBankAccountBlz(body.getBankAccountBlz());
				commissionStylistModel.setBankAccountVatPayer(body.getBankAccountVatPayer());
				commissionStylistModel.setBankAccountVatNumber(body.getBankAccountVatNumber());
				commissionStylistModel.setObtTeam(body.getObtTeam());
				commissionStylistModel.setFrontliners(body.getFrontliners());
				commissionStylistModel.setFrontlinersQualified(body.getFrontlinersQualified());
				commissionStylistModel.setCareer(body.getCareer());
				commissionStylistModel.setGoldFrontliners(body.getGoldFrontliners());
				commissionStylistModel.setNewFrontliners(body.getNewFrontliners());
				commissionStylistModel.setIpForMyself(body.getIpForMyself());
				commissionStylistModel.setIpForCustomers(body.getIpForCustomers());
				commissionStylistModel.setAncestors(body.getAncestors());

				commissionStylistListModel.add(commissionStylistModel);
			}

		} else {
			System.out.println(res.getStatus());
		}

		return commissionStylistListModel;
	}

	public static String getPartyPerformanceInfo(String partyId) throws Exception {

		String unparsedResponse = JerseyClient.sendGet(UrlConstants.COMMISION_WEB_BASE
				+ UrlConstants.COMMISION_PARTY_SUFFIX + partyId + composeAuthenticationSuffix());

		CommissionPartyModel commissionPartyModel = new CommissionPartyModel();

		ObjectMapper mapper = new ObjectMapper();
		CommisionPartyResponse res = (CommisionPartyResponse) mapper.readValue(unparsedResponse,
				CommisionPartyResponse.class);

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

	public static String getTeamPerformanceInfo(String stylistId) throws Exception {

		String unparsedResponse = JerseyClient.sendGet(UrlConstants.COMMISION_WEB_BASE
				+ UrlConstants.TEAM_PERFORMANCE_SUFFIX + stylistId + composeAuthenticationSuffix());

		return unparsedResponse;
	}

}
