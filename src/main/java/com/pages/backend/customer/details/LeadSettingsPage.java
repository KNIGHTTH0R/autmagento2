package com.pages.backend.customer.details;

import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.data.StylistDataModel;
import com.tools.requirements.AbstractPage;

public class LeadSettingsPage extends AbstractPage {

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

	public StylistDataModel grabValidationFields() {
		element(styleCoachLeads).waitUntilVisible();

		StylistDataModel stylistData = new StylistDataModel();

		stylistData.setStyleCoachLeads(styleCoachLeads.getAttribute("value"));
		stylistData.setHostessLeads(hostessLeads.getAttribute("value"));
		stylistData.setCustomerLeads(customerLeads.getAttribute("value"));
		stylistData.setStyleCoachLeadsWeek(styleCoachLeadsWeek.getAttribute("value"));
		stylistData.setHostessLeadsWeek(hostessLeadsWeek.getAttribute("value"));

		return stylistData;

	}

}
