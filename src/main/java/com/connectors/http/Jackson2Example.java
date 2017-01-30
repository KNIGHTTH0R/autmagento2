package com.connectors.http;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tools.data.salesOnSpeed.CustomFields;
import com.tools.data.salesOnSpeed.Male;
import com.tools.data.salesOnSpeed.Phones;
import com.tools.data.salesOnSpeed.PrimaryPhone;
import com.tools.data.salesOnSpeed.SalesOnSpeedContactModel;

public class Jackson2Example {

	public static void main(String[] args) {
		Jackson2Example obj = new Jackson2Example();
		obj.run();
	}

	private void run() {
		ObjectMapper mapper = new ObjectMapper();

		//Staff staff = createDummyObject();
		String jsonInString = null;
		SalesOnSpeedContactModel contact= createContactDummyObject();

		try {
			// Convert object to JSON string and save into a file directly
			mapper.writeValue(new File("F:\\contactModel2.json"), contact);

			// Convert object to JSON string
			 jsonInString = mapper.writeValueAsString(contact);
			 
			System.out.println(jsonInString);
			JerseyClientSos.sendPost(jsonInString);
			

			// Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(contact);
			//JerseyClientSos.sendPost(jsonInString);
			System.out.println(jsonInString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	
	}

	
	private SalesOnSpeedContactModel createContactDummyObject() {

		SalesOnSpeedContactModel contactModel= new SalesOnSpeedContactModel();

		contactModel.setPrename("emilian");
		contactModel.setLastname("melian3");
	//	contactModel.setStreet("Bistritei 34");
		
	//	PrimaryPhone phoneNumber=new PrimaryPhone();
//		phoneNumber.setNumber("0748229261");
//		phoneNumber.setType("mobile");
//		
//		
//		
//		contactModel.setPrimaryPhone(phoneNumber);
		
		/// if the Phone is [] an array
//		ArrayList<Phones> listphone=new ArrayList<Phones>();
//		Phones[] array=new Phones[listphone.size()];
//		Phones phone=new Phones();
//		phone.setNumber("4343");
//		listphone.add(phone);
//		contactModel.setPhones(listphone.toArray(array));
//		
		///neeed a new model ...where type and set disabled are boolean 
	//	CustomFields customFields =new CustomFields();
//		
//		Male male=new Male();
//		male.setValue(true);
//		male.setLabel("Männlich");
//		male.setType("boolean");
//		male.setDisabled(false);
//		customFields.setMale(male);
//		
//		contactModel.setCustomFields(customFields);


		return contactModel;

	}

}

