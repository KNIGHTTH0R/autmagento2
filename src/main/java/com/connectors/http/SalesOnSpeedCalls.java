package com.connectors.http;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.tools.constants.SalesOnSpeedConstants;
import com.tools.data.salesOnSpeed.Campaign_name;
import com.tools.data.salesOnSpeed.Contacted_progress_0;
import com.tools.data.salesOnSpeed.Contacted_progress_1;
import com.tools.data.salesOnSpeed.Contacted_progress_2;
import com.tools.data.salesOnSpeed.Contacted_progress_3;
import com.tools.data.salesOnSpeed.Contacted_progress_4;
import com.tools.data.salesOnSpeed.CustomFields;
import com.tools.data.salesOnSpeed.Flag_contact_booster;
import com.tools.data.salesOnSpeed.Flag_member;
import com.tools.data.salesOnSpeed.Flag_parties;
import com.tools.data.salesOnSpeed.Follow_up_date;
import com.tools.data.salesOnSpeed.Is_distributed;
import com.tools.data.salesOnSpeed.Lang_issues;
import com.tools.data.salesOnSpeed.MagentoSOSContactModel;
import com.tools.data.salesOnSpeed.Male;
import com.tools.data.salesOnSpeed.Not_interested;
import com.tools.data.salesOnSpeed.Notes;
import com.tools.data.salesOnSpeed.Phones;
import com.tools.data.salesOnSpeed.PrimaryPhone;
import com.tools.data.salesOnSpeed.Roadshow_city;
import com.tools.data.salesOnSpeed.Signup_issues;
import com.tools.data.salesOnSpeed.Underaged;
import com.tools.data.salesOnSpeed.Wrong_details;
import com.tools.salesOnSpeed.SalesOnSpeedContactResponse;
import com.tools.utils.DateUtils;

public class SalesOnSpeedCalls {
	
	

	public static List<MagentoSOSContactModel> getListCustomerInfo(String userSosId, String email, String password)
			throws Exception {

		List<MagentoSOSContactModel> contactList = new ArrayList<MagentoSOSContactModel>();

		String unparsedResponse = JerseyClientSos
				.sendGet("https://apidev.salesonspeed.de/contacts/?requestedUserId=" + userSosId, email, password);

		ObjectMapper mapper = new ObjectMapper();
		SalesOnSpeedContactResponse[] response = (SalesOnSpeedContactResponse[]) mapper.readValue(unparsedResponse,
				SalesOnSpeedContactResponse[].class);

		for (SalesOnSpeedContactResponse salesOnSpeedCustomerResponse : response) {
			MagentoSOSContactModel magentoCustomerModel = new MagentoSOSContactModel();

			magentoCustomerModel = populateModelFromResponse(magentoCustomerModel, salesOnSpeedCustomerResponse);

			contactList.add(magentoCustomerModel);

		}
		return contactList;

	}

	public static MagentoSOSContactModel getCustomerInfo(String contactSosId, String userSosId, String email,
			String password) throws Exception {

		String unparsedResponse = JerseyClientSos.sendGet(
				"https://apidev.salesonspeed.de/contacts/" + contactSosId + "?requestedUserId=" + userSosId, email,
				password);

		ObjectMapper mapper = new ObjectMapper();
		SalesOnSpeedContactResponse response = (SalesOnSpeedContactResponse) mapper.readValue(unparsedResponse,
				SalesOnSpeedContactResponse.class);

		MagentoSOSContactModel salesOnSpeedContactModel = new MagentoSOSContactModel();

		salesOnSpeedContactModel = populateModelFromResponse(salesOnSpeedContactModel, response);

		System.out.println(salesOnSpeedContactModel.toString());

		return salesOnSpeedContactModel;

	}

	private static MagentoSOSContactModel populateModelFromResponse(MagentoSOSContactModel salesOnSpeedContactModel,
			SalesOnSpeedContactResponse response) {

		List<String> contacted_progress = new ArrayList<String>();

		salesOnSpeedContactModel.set_id(response.get_id());
		salesOnSpeedContactModel.set__v(response.get__v());
		salesOnSpeedContactModel.setStreet(response.getStreet());
		salesOnSpeedContactModel.setPrename(response.getPrename());
		salesOnSpeedContactModel.setLastname(response.getLastname());
		salesOnSpeedContactModel.setPostcode(response.getPostcode());
		salesOnSpeedContactModel.setCity(response.getCity());
		salesOnSpeedContactModel.setCountry(response.getCountry());
		salesOnSpeedContactModel.setUpdated(DateUtils.parseMilisDate(response.getUpdated(), "yyyy-MM-dd"));
		salesOnSpeedContactModel.setCreated(DateUtils.parseMilisDate(response.getCreated(), "yyyy-MM-dd"));
		salesOnSpeedContactModel.setEmail(response.getEmail());
		salesOnSpeedContactModel.setUserId(response.getUserId());
		salesOnSpeedContactModel.setLanguage(response.getLanguage());

		List<Phones> phones = new ArrayList<Phones>();

		for (Phones ph : response.getPhones()) {

			phones.add(ph);
		}

		salesOnSpeedContactModel.setPhones(phones);

		List<Notes> notes = new ArrayList<Notes>();

		for (Notes note : response.getNotes()) {

			notes.add(note);
		}

		salesOnSpeedContactModel.setNotes(notes);

		CustomFields customfields = response.getCustomFields();

		Underaged underaged = customfields.getUnderaged();
		salesOnSpeedContactModel.setUnderagedValue(underaged.getValue());

		Not_interested notInterested = customfields.getNot_interested();
		salesOnSpeedContactModel.setNot_interestedValue(notInterested.getValue());

		Flag_contact_booster flagContactBooster = customfields.getFlag_contact_booster();
		salesOnSpeedContactModel.setFlagContactBoosterValue(flagContactBooster.getValue());

		Wrong_details wrongDetails = customfields.getWrong_details();
		salesOnSpeedContactModel.setWrongDetailsValue(wrongDetails.getValue());

		Male male = customfields.getMale();
	//	salesOnSpeedContactModel.setMaleValue(male.getValue());

		Signup_issues signupIssues = customfields.getSignup_issues();
		salesOnSpeedContactModel.setSignupIssuesValue(signupIssues.getValue());

		Campaign_name campaignName = customfields.getCampaign_name();

		String campaignValue = campaignName.getValue();
		String campaign = SalesOnSpeedConstants.CAMPAIGN_NAME_VALUES.contains(campaignValue) ? "" : campaignValue;
		salesOnSpeedContactModel.setCampaignNameValue(campaign);

		Flag_parties flagParties = customfields.getFlag_parties();
		salesOnSpeedContactModel.setFlagPartiesValue(flagParties.getValue());

		Is_distributed isDistributed = customfields.getIs_distributed();
		salesOnSpeedContactModel.setIsDistributedValue(isDistributed.getValue());

		Flag_member flagMember = customfields.getFlag_member();
		salesOnSpeedContactModel.setFlagMemberValue(flagMember.getValue());

		Roadshow_city roadShowCity = customfields.getRoadshow_city();

		String roadShowCityValue = roadShowCity.getValue();
		// if roadshow in mag is empty and in sos contains a specific message
		// message
		String roadShowCityValue2 = SalesOnSpeedConstants.ROAD_SHOW_VALUES.contains(roadShowCityValue) ? ""
				: roadShowCityValue;

		salesOnSpeedContactModel.setRoadshowCityValue(roadShowCityValue2);

		Follow_up_date followUpDate = customfields.getFollow_up_date();
		salesOnSpeedContactModel.setFollowUpDateValue(followUpDate.getValue());

		Lang_issues langIssue = customfields.getLang_issues();
		salesOnSpeedContactModel.setLangIssuesValue(langIssue.getValue());
		
		//cause in mag each contactProgress is stored with a corresponding integer 
		Contacted_progress_2 contactedProgress2 = customfields.getContacted_progress_2();
		contacted_progress.add(contactedProgress2.getValue() == "true" ? "2" : "false");

		Contacted_progress_1 contactedProgress1 = customfields.getContacted_progress_1();
		contacted_progress.add(contactedProgress1.getValue() == "true" ? "1" : "false");

		Contacted_progress_0 contactedProgress0 = customfields.getContacted_progress_0();
		contacted_progress.add(contactedProgress0.getValue() == "true" ? "0" : "false");

		Contacted_progress_3 contactedProgress3 = customfields.getContacted_progress_3();
		contacted_progress.add(contactedProgress3.getValue() == "true" ? "3" : "false");

		Contacted_progress_4 contactedProgress4 = customfields.getContacted_progress_4();
		contacted_progress.add(contactedProgress4.getValue() == "true" ? "4" : "false");

		// a work around for the impossibility to grab the -1 progress
		for (String progress : contacted_progress) {
			if (progress != "false") {
				salesOnSpeedContactModel.setContacted_progress(progress);
				break;
			}
		}

		if (salesOnSpeedContactModel.getContacted_progress() == null) {
			salesOnSpeedContactModel.setContacted_progress("-1");
		}

		// to avoid null pointer exception we set string "null" if the contacts
		// does not have a phone set
		if (response.getPrimaryPhone() != null) {
			PrimaryPhone primaryPhone = response.getPrimaryPhone();
			salesOnSpeedContactModel.setPrimaryPhoneNumber(primaryPhone.getNumber());
		} else {
			salesOnSpeedContactModel.setPrimaryPhoneNumber("null");
		}

		return salesOnSpeedContactModel;
	}

	public static void main(String[] args) throws Exception {
		SalesOnSpeedCalls.getListCustomerInfo("5888de32b8cc4b0536c7ebad", "Q6zi4j4nT8Du@mailinator.com", "evmyrpjz");
		/*
		 * SalesOnSpeedCalls.getCustomerInfo("587f2a4eb8cc4b0536c7e9fa",
		 * "587cb70fb8cc4b0536c7e9a1", "pippajean", "Minerilor62!");
		 */
	}
}
