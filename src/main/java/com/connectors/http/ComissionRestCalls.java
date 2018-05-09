package com.connectors.http;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.tools.commision.CommisionPartyResponse;
import com.tools.commision.CommisionStylistListResponse;
import com.tools.commision.CommisionStylistResponse;
import com.tools.commision.StylistBody;
import com.tools.constants.Credentials;
import com.tools.constants.EnvironmentConstants;
import com.tools.constants.Separators;
import com.tools.constants.UrlConstants;
import com.tools.data.commission.CommissionPartyModel;
import com.tools.data.commission.CommissionStylistModel;
import com.tools.utils.DateUtils;
import com.tools.utils.MD5;
import com.tools.utils.PrintUtils;

public class ComissionRestCalls {

	public static void main(String[] args) throws Exception {

	//	ComissionRestCalls.getStylistInfo("1030");
	//	System.setProperty("http.proxyHost", "127.0.0.1");
	//	System.setProperty("http.proxyPort", "8080");
		
	/*	CommissionStylistModel x=ComissionRestCalls.getStylistInfo("2", "2018", "4");
		System.out.println(x);*/
	//	List<CommissionStylistModel> x1=	ComissionRestCalls.getStylistListInfo("2018", "04");
		
		List<CommissionStylistModel> listaTotala= new ArrayList<CommissionStylistModel>();
		
		for(int i=1;i<=1;i++){
			List<CommissionStylistModel> x=	ComissionRestCalls.getStylistListInfo("2018", "03",String.valueOf(i));
			if(x.isEmpty()){
				break;
			}else{
				Thread.sleep(3000);
				listaTotala.addAll(x);
			}
			
		}
		
	
		System.out.println(listaTotala.size());
		
		listaTotala.sort(Comparator.comparing(CommissionStylistModel::getStylistId));
		
		for (CommissionStylistModel commissionStylistModel : listaTotala) {
			System.out.println(commissionStylistModel.getStylistId());
		}
		
		
	}
	
	
	
	

	public static String composeAuthenticationSuffix() throws Exception {

		String hash = MD5.getMd5(Credentials.API_KEY + Credentials.API_SECRET + DateUtils.getTimestamp() + "30");
		String suffix = "&api_sig=" + hash + "&api_key=" + Credentials.API_KEY + "&api_ts=" + DateUtils.getTimestamp()
				+ "&api_ttl=30";

		return suffix;
	}

	public static String composePeriodAndPaginationSuffix(String year, String month) throws Exception {
		String suffix ="?year=" + year + "&month="+month + "&page=1&per_page=200";
		
		return suffix;
	}

	/*public static String composePeriodAndPaginationSuffix(String year, String month) throws Exception {
		String suffix ="?year=" + year + "&month="+month + "&stylistId[lt]=100";
		
		return suffix;
	}*/
	
	
	public static CommissionStylistModel getStylistInfo(String stylistId) throws Exception {

		String unparsedResponse = JerseyClient
				.sendGet(EnvironmentConstants.COMMISSION_URL + UrlConstants.COMMISION_STYLIST_SUFFIX + Separators.SLASH
						+ stylistId + "?page=1&per_page=5000" + composeAuthenticationSuffix());
		
		System.out.println(unparsedResponse);
		CommissionStylistModel commissionStylistModel = new CommissionStylistModel();
		ObjectMapper mapper = new ObjectMapper();
		CommisionStylistResponse res = (CommisionStylistResponse) mapper.readValue(unparsedResponse,
				CommisionStylistResponse.class);
		if (res.getStatus().equals("ok")) {

			StylistBody body = res.getBody();
			commissionStylistModel = populateModelFromResponse(commissionStylistModel, body);

		} else {
			System.out.println(res.getStatus());
		}

		return commissionStylistModel;
	}

	public static CommissionStylistModel getStylistInfo(String stylistId, String year, String month) throws Exception {

		String unparsedResponse = JerseyClient
				.sendGet(EnvironmentConstants.COMMISSION_URL + UrlConstants.COMMISION_STYLIST_SUFFIX + Separators.SLASH
						+ stylistId + composePeriodAndPaginationSuffix(year, month) + composeAuthenticationSuffix());
		CommissionStylistModel commissionStylistModel = new CommissionStylistModel();
		ObjectMapper mapper = new ObjectMapper();
		CommisionStylistResponse res = (CommisionStylistResponse) mapper.readValue(unparsedResponse,
				CommisionStylistResponse.class);
		if (res.getStatus().equals("ok")) {

			StylistBody body = res.getBody();
			commissionStylistModel = populateModelFromResponse(commissionStylistModel, body);

		} else {
			System.out.println(res.getStatus());
		}

		return commissionStylistModel;
	}

	
	public static List<CommissionStylistModel> getStylistListInfo(String year, String month, String page) throws Exception {

		String unparsedResponse = JerseyClient
				.sendGet(EnvironmentConstants.COMMISSION_URL + UrlConstants.COMMISION_STYLIST_SUFFIX
						+ "?year=" + year + "&month="+month + "&page="+page+"&per_page=400" + composeAuthenticationSuffix());
		
		
	/*	String unparsedResponse = JerseyClient
				.sendGet(EnvironmentConstants.COMMISSION_URL + UrlConstants.COMMISION_STYLIST_SUFFIX + Separators.SLASH
						+ stylistId + "?page=1&per_page=5000" + composeAuthenticationSuffix());*/
		
		System.out.println(unparsedResponse);
		List<CommissionStylistModel> commissionStylistListModel = new ArrayList<CommissionStylistModel>();
		ObjectMapper mapper = new ObjectMapper();
		CommisionStylistListResponse res = (CommisionStylistListResponse) mapper.readValue(unparsedResponse,
				CommisionStylistListResponse.class);
		if (res.getStatus().equals("ok")) {

			for (StylistBody body : res.getBody()) {

				CommissionStylistModel commissionStylistModel = new CommissionStylistModel();
				commissionStylistModel = populateModelFromResponse(commissionStylistModel, body);
				commissionStylistListModel.add(commissionStylistModel);
			}

		} else {
			System.out.println(res.getStatus());
		}

		return commissionStylistListModel;
	}
	
	
	public static List<CommissionStylistModel> getStylistListInfo(String year, String month) throws Exception {

		String unparsedResponse = JerseyClient
				.sendGet(EnvironmentConstants.COMMISSION_URL + UrlConstants.COMMISION_STYLIST_SUFFIX
						+ composePeriodAndPaginationSuffix(year, month) + composeAuthenticationSuffix());
		
		
		
	/*	String unparsedResponse = JerseyClient
				.sendGet(EnvironmentConstants.COMMISSION_URL + UrlConstants.COMMISION_STYLIST_SUFFIX + Separators.SLASH
						+ stylistId + "?page=1&per_page=5000" + composeAuthenticationSuffix());*/
		
		System.out.println(unparsedResponse);
		List<CommissionStylistModel> commissionStylistListModel = new ArrayList<CommissionStylistModel>();
		ObjectMapper mapper = new ObjectMapper();
		CommisionStylistListResponse res = (CommisionStylistListResponse) mapper.readValue(unparsedResponse,
				CommisionStylistListResponse.class);
		if (res.getStatus().equals("ok")) {

			for (StylistBody body : res.getBody()) {

				CommissionStylistModel commissionStylistModel = new CommissionStylistModel();
				commissionStylistModel = populateModelFromResponse(commissionStylistModel, body);
				commissionStylistListModel.add(commissionStylistModel);
			}

		} else {
			System.out.println(res.getStatus());
		}

		return commissionStylistListModel;
	}

	public static String getPartyPerformanceInfo(String partyId) throws Exception {

		String unparsedResponse = JerseyClient.sendGet(EnvironmentConstants.COMMISSION_URL
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

		String unparsedResponse = JerseyClient.sendGet(EnvironmentConstants.COMMISSION_URL
				+ UrlConstants.TEAM_PERFORMANCE_SUFFIX + stylistId + composeAuthenticationSuffix());

		return unparsedResponse;
	}

	public static CommissionStylistModel populateModelFromResponse(CommissionStylistModel commissionStylistModel,
			StylistBody body) throws ParseException {

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
		commissionStylistModel.setCareerLevelName(getShortedValue(body.getCareerLevelName()));
		
		commissionStylistModel.setPayLevel(body.getPaylevel());
		commissionStylistModel.setCanceledAt(getShortedValue(body.getCanceledAt()));

		commissionStylistModel.setIp(body.getIp());
		commissionStylistModel.setIpTop(String.valueOf(body.getTopIp()));
		commissionStylistModel.setIpUnsafe(body.getIpUnsafe());
		commissionStylistModel.setIpMonth(body.getIpMonth());
		commissionStylistModel.setIpNewFl(body.getIpNewFl());
		commissionStylistModel.setTeamPoints(body.getTeamPoints());
		commissionStylistModel.setTeamPointsUnsafe(body.getTeamPointsUnsafe());
		commissionStylistModel.setRetail(body.getRetail());
		commissionStylistModel.setTob(body.getTob());
		commissionStylistModel.setPayLevelName(getShortedValue(body.getPaylevelName()));
		commissionStylistModel.setMinimumPaylevel(body.getMinimumPaylevel());
		commissionStylistModel.setMinimumCareerLevel(body.getMinimumCareerLevel());
		commissionStylistModel.setJoinedAt(body.getJoinedAt());
		commissionStylistModel.setCreatedAt(DateUtils.parseDate(body.getCreatedAt(), "yyyy-mm-dd", "dd.mm.yy"));
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
		commissionStylistModel.setIp30Top(body.getTop30Ip());
		commissionStylistModel.setTopNrNewStylists(String.valueOf(body.getTopNrNewStylists()));

		return commissionStylistModel;

	}
	
	private static String getShortedValue(String value){
		String shortedValue="";
		
		if (value == null) {
            value="null";
        }
    
        switch (value) {
        case "Team leader":
        	shortedValue = "TL";
            break;
        case "Field manager":
        	shortedValue = "FL";
            break;
        case "null":
        	shortedValue = "n/a";
            break;
        case "Stylist":
        	shortedValue = "S";
            break;
        
        default: 
        	shortedValue=value;
            break;
    }
    
		return shortedValue;
		
	}
	 

	
}



