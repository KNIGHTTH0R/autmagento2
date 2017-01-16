package com.connectors.http;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.tools.data.salesOnSpeed.CustomFields;

import com.tools.data.salesOnSpeed.Notes;
import com.tools.data.salesOnSpeed.Phones;
import com.tools.data.salesOnSpeed.SalesOnSpeedContactModel;
import com.tools.salesOnSpeed.SalesOnSpeedContactResponse;

public class SalesOnSpeedCalls {

	public static List<SalesOnSpeedContactModel> getListCustomerInfo(String userSosId) throws Exception {

		List<SalesOnSpeedContactModel> contact = new ArrayList<SalesOnSpeedContactModel>();

		String unparsedResponse = JerseyClientSos
				.sendGet("https://apidev.salesonspeed.de/contacts/?requestedUserId=" + userSosId);

		ObjectMapper mapper = new ObjectMapper();
		SalesOnSpeedContactResponse[] response = (SalesOnSpeedContactResponse[]) mapper.readValue(unparsedResponse,
				SalesOnSpeedContactResponse[].class);

		for (SalesOnSpeedContactResponse salesOnSpeedCustomerResponse : response) {
			SalesOnSpeedContactModel salesOnSpeedCustomerModel = new SalesOnSpeedContactModel();

			salesOnSpeedCustomerModel = populateModelFromResponse(salesOnSpeedCustomerModel,
					salesOnSpeedCustomerResponse);

			contact.add(salesOnSpeedCustomerModel);

//			CustomFields customF = salesOnSpeedCustomerModel.getCustomFields();
//			Underaged under = customF.getUnderaged();
//			System.out.println("underaged type " + under.getType());
//			System.out.println("underaged disabled " + under.getDisabled());
//			System.out.println("id customer => " + salesOnSpeedCustomerModel.get_id());
//			System.out.println("phones  => " + salesOnSpeedCustomerModel.getPhones());
//			System.out.println("notes => " + salesOnSpeedCustomerModel.getNotes());
//
//			List<Notes> note = salesOnSpeedCustomerModel.getNotes();
//			for (Notes notes1 : note) {
//				System.out.println("note text " + notes1.getText());
//			}

		}
		return contact;

	}

	public static SalesOnSpeedContactModel getCustomerInfo(String contactSosId, String userSosId) throws Exception {

		// List<SalesOnSpeedCustomerModel> contact = new
		// ArrayList<SalesOnSpeedCustomerModel>();

		String unparsedResponse = JerseyClientSos
				.sendGet("https://apidev.salesonspeed.de/contacts/" + contactSosId + "?requestedUserId=" + userSosId);

		ObjectMapper mapper = new ObjectMapper();
		SalesOnSpeedContactResponse response = (SalesOnSpeedContactResponse) mapper.readValue(unparsedResponse,
				SalesOnSpeedContactResponse.class);

		SalesOnSpeedContactModel salesOnSpeedContactModel = new SalesOnSpeedContactModel();

		salesOnSpeedContactModel = populateModelFromResponse(salesOnSpeedContactModel, response);

//		CustomFields customF = salesOnSpeedContactModel.getCustomFields();
//		Underaged under = customF.getUnderaged();
//		System.out.println("underaged type " + under.getType());
//		System.out.println("underaged disabled " + under.getDisabled());
//		System.out.println("id customer => " + salesOnSpeedContactModel.get_id());
//		System.out.println("phones  => " + salesOnSpeedContactModel.getPhones());
//		System.out.println("notes => " + salesOnSpeedContactModel.getNotes());
//
//		List<Notes> note = salesOnSpeedContactModel.getNotes();
//		for (Notes notes1 : note) {
//			System.out.println("note text " + notes1.getText());
//		}

		return salesOnSpeedContactModel;

	}

	private static SalesOnSpeedContactModel populateModelFromResponse(SalesOnSpeedContactModel salesOnSpeedContactModel,
			SalesOnSpeedContactResponse response) {

		salesOnSpeedContactModel.set_id(response.get_id());
		salesOnSpeedContactModel.set__v(response.get__v());
		salesOnSpeedContactModel.setStreet(response.getStreet());
		salesOnSpeedContactModel.setLastname(response.getLastname());
		salesOnSpeedContactModel.setPostcode(response.getPostcode());
		salesOnSpeedContactModel.setCity(response.getCity());
		salesOnSpeedContactModel.setCountry(response.getCountry());
		salesOnSpeedContactModel.setUpdated(response.getUpdated());
		salesOnSpeedContactModel.setCreated(response.getUpdated());
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

		CustomFields extractedCustomFields = new CustomFields();

		extractedCustomFields.setUnderaged(customfields.getUnderaged());
		extractedCustomFields.setNot_interested(customfields.getNot_interested());
		extractedCustomFields.setFlag_contact_booster(customfields.getFlag_contact_booster());
		extractedCustomFields.setWrong_details(customfields.getWrong_details());
		extractedCustomFields.setMale(customfields.getMale());
		extractedCustomFields.setSignup_issues(customfields.getSignup_issues());
		extractedCustomFields.setCampaign_name(customfields.getCampaign_name());
		extractedCustomFields.setFlag_parties(customfields.getFlag_parties());
		extractedCustomFields.setIs_distributed(customfields.getIs_distributed());
		extractedCustomFields.setFlag_member(customfields.getFlag_member());
		extractedCustomFields.setRoadshow_city(customfields.getRoadshow_city());
		extractedCustomFields.setFollow_up_date(customfields.getFollow_up_date());
		extractedCustomFields.setLang_issues(customfields.getLang_issues());
		extractedCustomFields.setContacted_progress_0(customfields.getContacted_progress_0());
		extractedCustomFields.setContacted_progress_1(customfields.getContacted_progress_1());
		extractedCustomFields.setContacted_progress_2(customfields.getContacted_progress_2());
		extractedCustomFields.setContacted_progress_3(customfields.getContacted_progress_3());
		extractedCustomFields.setContacted_progress_4(customfields.getContacted_progress_4());

		salesOnSpeedContactModel.setCustomFields(extractedCustomFields);

		salesOnSpeedContactModel.setPrimaryPhone(response.getPrimaryPhone());

		return salesOnSpeedContactModel;
	}

	public static void main(String[] args) throws Exception {
		// SalesOnSpeedCalls.getListCustomerInfo("586f530766eeed5a1110c5a7");
		SalesOnSpeedCalls.getCustomerInfo("586f56ff66eeed5a1110c5d5", "586f530766eeed5a1110c5a7");
	}
}
