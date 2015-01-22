package com.pages.backend.customer.details;

import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.AbstractPage;
import com.tools.data.StylistDataModel;

public class LeadSettingsPage extends AbstractPage{

	
	@FindBy(id = "_scleadmanagetotal_sc_leads_received")
	private WebElement styleCoachLeads;
	
	
	@FindBy(id = "_scleadmanagetotal_host_leads_received")
	private WebElement hostessLeads;
	
	
	@FindBy(id = "_scleadmanagetotal_customers_received")
	private WebElement customerLeads;
	
	
	@FindBy(id = "_scleadmanagetotal_sc_leads_rec_curr_week")
	private WebElement styleCoachLeadsWeek;
	
	
	@FindBy(id = "_scleadmanagetotal_host_leads_rec_curr_week")
	private WebElement hostessLeadsWeek;
	
	
	public StylistDataModel grabValidationFields(){
		element(styleCoachLeads).waitUntilVisible();
		
		StylistDataModel stylistData = new StylistDataModel();
		
		stylistData.setStyleCoachLeads(styleCoachLeads.getAttribute("value"));
		stylistData.setHostessLeads(hostessLeads.getAttribute("value"));
		stylistData.setCustomerLeads(customerLeads.getAttribute("value"));
		stylistData.setStyleCoachLeadsWeek(styleCoachLeadsWeek.getAttribute("value"));
		stylistData.setHostessLeadsWeek(hostessLeadsWeek.getAttribute("value"));
		
		System.out.println("styleCoachLeads: " + stylistData.getStyleCoachLeads());
		System.out.println("hostessLeads: " + stylistData.getHostessLeads());
		System.out.println("customerLeads: " + stylistData.getCustomerLeads());
		System.out.println("styleCoachLeadsWeek: " + stylistData.getStyleCoachLeadsWeek());
		System.out.println("hostessLeadsWeek: " + stylistData.getHostessLeadsWeek());
		
		return stylistData;
		
	}
	
}
