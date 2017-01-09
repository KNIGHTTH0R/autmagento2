package com.connectors.http;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.tools.data.salesOnSpeed.Notes;
import com.tools.data.salesOnSpeed.Phones;
import com.tools.data.salesOnSpeed.SalesOnSpeedCustomerModel;
import com.tools.salesOnSpeed.SalesOnSpeedCustomerResponse;

public class SalesOnSpeedCalls {

	public static List<SalesOnSpeedCustomerModel>  getCustomerInfo(String contact) throws Exception {

		
		List<SalesOnSpeedCustomerModel> customer =new ArrayList<SalesOnSpeedCustomerModel>();
		
		String unparsedResponse = JerseyClientSos
				.sendGet("https://apidev.salesonspeed.de/contacts/"+contact+"/?requestedUserId=586f530766eeed5a1110c5a7");
		
		ObjectMapper mapper = new ObjectMapper();
		SalesOnSpeedCustomerResponse[] response = (SalesOnSpeedCustomerResponse[]) mapper.readValue(unparsedResponse,
				SalesOnSpeedCustomerResponse[].class);

		
		for (SalesOnSpeedCustomerResponse salesOnSpeedCustomerResponse : response) {
			SalesOnSpeedCustomerModel salesOnSpeedCustomerModel = new SalesOnSpeedCustomerModel();
			
			salesOnSpeedCustomerModel = populateModelFromResponse(salesOnSpeedCustomerModel, salesOnSpeedCustomerResponse);
			
			customer.add(salesOnSpeedCustomerModel);
			System.out.println("id customer => " + salesOnSpeedCustomerModel.get_id());
			System.out.println("phones  => " + salesOnSpeedCustomerModel.getPhones());
			System.out.println("phones => " + salesOnSpeedCustomerModel.getNotes());
			
		}
		return customer;

		
	}

	private static SalesOnSpeedCustomerModel populateModelFromResponse(SalesOnSpeedCustomerModel salesOnSpeedCustomerModel,
			SalesOnSpeedCustomerResponse response) {
//		System.out.println("din raspuns id "+response.get_id());
		salesOnSpeedCustomerModel.set_id(response.get_id());
		salesOnSpeedCustomerModel.set__v(response.get__v());
		salesOnSpeedCustomerModel.setStreet(response.getStreet());
		salesOnSpeedCustomerModel.setLastname(response.getLastname());
		salesOnSpeedCustomerModel.setPostcode(response.getPostcode());
		salesOnSpeedCustomerModel.setCity(response.getCity());
		salesOnSpeedCustomerModel.setCountry(response.getCountry());
		salesOnSpeedCustomerModel.setUpdated(response.getUpdated());
		salesOnSpeedCustomerModel.setCreated(response.getUpdated());
		salesOnSpeedCustomerModel.setEmail(response.getEmail());
		salesOnSpeedCustomerModel.setUserId(response.getUserId());
		salesOnSpeedCustomerModel.setLanguage(response.getLanguage());
		
		List<Phones> responsePhone=response.getPhones();
		List<Phones> phones=new ArrayList<Phones>();
 		
		for (Phones ph : responsePhone) {
			Phones extractedPhones =new Phones();
			extractedPhones.set_creator(ph.get_creator());
			extractedPhones.set_id(ph.get_id());
			extractedPhones.setCreated(ph.getCreated());
			extractedPhones.setUpdated(ph.getUpdated());
			extractedPhones.setCustomFields(ph.getCustomFields());
			extractedPhones.set__v(ph.get__v());
			extractedPhones.setNumber(ph.getNumber());
			extractedPhones.setType(ph.getType());
			
			phones.add(extractedPhones);
		}
		
		salesOnSpeedCustomerModel.setPhones(phones);
		
		List<Notes> responseNotes=response.getNotes();
		List<Notes> notes=new ArrayList<Notes>();
 		
		for (Notes note : responseNotes) {
			Notes extractedNotes =new Notes();
			extractedNotes.set_creator(note.get_creator());
			extractedNotes.set_id(note.get_id());
			extractedNotes.setCreated(note.getCreated());
			extractedNotes.setUpdated(note.getUpdated());
			extractedNotes.setCustomFields(note.getCustomFields());
			extractedNotes.set__v(note.get__v());
			extractedNotes.setUserId(note.getUserId());
			extractedNotes.setText(note.getText());
			
			notes.add(extractedNotes);
		}
		
		salesOnSpeedCustomerModel.setNotes(notes);
		
		
		
		return salesOnSpeedCustomerModel;
	}

	public static void main(String[] args) throws Exception {
		// SalesOnSpeedCalls cust =new SalesOnSpeedCalls();
		SalesOnSpeedCalls.getCustomerInfo("");
	}
}
