package com.connectors.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.NodeList;

import com.tools.data.soap.DBStylistModel;

/**
 * @author mihaibarta
 *
 */

public class StylistListMagentoCalls {

	public static List<DBStylistModel> getStylistList(String filter, String operand, String filterValue) {

		List<DBStylistModel> stylistList = new ArrayList<DBStylistModel>();

		try {
			SOAPMessage response = HttpSoapConnector.soapGetStylistList(filter, operand, filterValue);
			System.out.println(response);
			try {
				stylistList = extractStylistData(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stylistList;
	}
	
	public static List<DBStylistModel> getStylistListInRange(String filter, String operand1,String operand2,String filterValue1,String filterValue2) {

		List<DBStylistModel> stylistList = new ArrayList<DBStylistModel>();

		try {
			SOAPMessage response = HttpSoapConnector.soapGetStylistListInRange(filter, operand1,operand2, filterValue1,filterValue2);
			System.out.println(response);
			try {
				stylistList = extractStylistData(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stylistList;
	}

	private static List<DBStylistModel> extractStylistData(SOAPMessage response) throws Exception {

		List<DBStylistModel> stylistModelList = new ArrayList<DBStylistModel>();

		NodeList stylistList = response.getSOAPBody().getElementsByTagName("complexObjectArray");
		for (int i = 0; i < stylistList.getLength(); i++) {
			DBStylistModel model = new DBStylistModel();
			NodeList childNodes = stylistList.item(i).getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {

				if (childNodes.item(j).getNodeName().equalsIgnoreCase("stylist_id")) {
					model.setStylistId(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_id")) {
					model.setCustomerId(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("parent_id")) {
					model.setParentId(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("activated_at")) {
					model.setActivatedAt(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("created_at")) {
					model.setCreatedAt(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("updated_at")) {
					model.setUpdatedAt(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_firstname")) {
					model.setFirstName(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_lastname")) {
					model.setLastName(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_street")) {
					model.setStreet(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_house_number")) {
					model.setHouseNumber(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_postcode")) {
					model.setPostCode(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("bank_account_vat_payer")) {
					model.setVatPayer(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("bank_account_vat_number")) {
					model.setVatNumber(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_preferred_website")) {
					model.setWebsite(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("status")) {
					model.setStatus(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("customer_email")) {
					model.setEmail(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("billing_latitude")) {
					model.setLattitude(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("billing_longitude")) {
					model.setLongitude(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("qualified_sc_lead_retrieval")) {
					model.setQualifiedSC(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("qualified_host_lead_retrieval")) {
					model.setQualifiedHost(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("qualified_customer_retrieval")) {
					model.setQualifiedCustomer(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("total_sc_leads_received")) {
					model.setTotalSCReceived(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("total_host_leads_received")) {
					model.setTotalHostReceived(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("total_customers_received")) {
					model.setTotalCustomerReceived(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("total_sc_leads_rec_curr_week")) {
					model.setTotalSCCurrentWeek(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("total_host_leads_rec_curr_week")) {
					model.setTotalHostCurrentWeek(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("max_sc_leads_per_week")) {
					model.setMaxSCPerWeek(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("lead_retrieval_paused")) {
					model.setLeadRetrievalPaused(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("sc_lead_range")) {
					model.setStyleCoachLeadRange(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("host_lead_range")) {
					model.setHostLeadRange(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("sc_quite_date")) {
					model.setStylistQuiteDate(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("sc_contract_status")) {
					model.setStylistContractStatus(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("paused_from")) {
					model.setPausedFrom(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("paused_to")) {
					model.setPausedTo(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("date_of_birth")) {
					String[] parts = childNodes.item(j).getTextContent().split(" ");
					model.setBirthDate(parts[0]);
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("has_slogan")) {
					model.setSlogan(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("has_avatar")) {
					model.setAvatar(childNodes.item(j).getTextContent());
				}
				if (childNodes.item(j).getNodeName().equalsIgnoreCase("is_confirmed")) {
					model.setConfirmed(childNodes.item(j).getTextContent());
				}
			}
			stylistModelList.add(model);
		}
		return stylistModelList;

	}

}
