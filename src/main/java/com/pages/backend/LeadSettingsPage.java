package com.pages.backend;

import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.AbstractPage;
import com.tools.data.ValidationModel;

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
	
	
	public ValidationModel grabValidationFields(){
		element(styleCoachLeads).waitUntilVisible();
		
		ValidationModel stylistData = new ValidationModel();
		
		stylistData.styleCoachLeads = styleCoachLeads.getAttribute("value");
		stylistData.hostessLeads = hostessLeads.getAttribute("value");
		stylistData.customerLeads = customerLeads.getAttribute("value");
		stylistData.styleCoachLeadsWeek = styleCoachLeadsWeek.getAttribute("value");
		stylistData.hostessLeadsWeek = hostessLeadsWeek.getAttribute("value");
		
		System.out.println("styleCoachLeads: " + stylistData.styleCoachLeads);
		System.out.println("hostessLeads: " + stylistData.hostessLeads);
		System.out.println("customerLeads: " + stylistData.customerLeads);
		System.out.println("styleCoachLeadsWeek: " + stylistData.styleCoachLeadsWeek);
		System.out.println("hostessLeadsWeek: " + stylistData.hostessLeadsWeek);
		
		return stylistData;
		
	}
	
}
