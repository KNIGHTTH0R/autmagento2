package com.tests.us6.us6003;

import java.io.IOException;

import javax.xml.soap.SOAPException;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.external.EmailClientSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.StylistCampaignSteps;
import com.steps.frontend.StylistRegistrationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.requirements.Application;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;


@WithTag(name = "US6003", type = "frontend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US6003StyleCoachRegistrationTest extends BaseTest{
	
	@Steps
	public HeaderSteps headerSteps;	
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public StylistCampaignSteps stylistCampaignSteps;
	@Steps
	public EmailClientSteps emailClientSteps;	
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps 
	public CustomVerification customVerifications;
	@Steps 
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps 
	public CustomerAndStylistRegistrationWorkflows customerAndStylistRegistrationWorkflows;	
	
	private String username = "pN46P3vr@mailinator.com";
	private String password = "MXpUvCPtsIEEFq1";
	


	
	@Test
	public void us6003StyleCoachRegistrationTest() throws SOAPException, IOException{
		
//		headerSteps.navigateToRegisterForm();
//		String title1 = stylistRegistrationSteps.getStylistRegisterPageTitle();
//		System.out.println(title1);
//		headerSteps.navigateToRegisterForm2();
//		String title2 = stylistRegistrationSteps.getStylistRegisterPageTitle();
//		System.out.println(title2);
//		headerSteps.navigateToRegisterForm3();
//		String title3 = stylistRegistrationSteps.getStylistRegisterPageTitle();
//		System.out.println(title3);
//		headerSteps.navigateToRegisterForm4();
//		String title4 = stylistRegistrationSteps.getStylistRegisterPageTitle();
//		System.out.println(title4);	
		
		customerRegistrationSteps.performLogin(username,password);
		
//		headerSteps.navigateToRegisterForm3();
//		String title3 = stylistRegistrationSteps.getStylistRegisterPageTitle();
//		System.out.println(title3);
//		headerSteps.navigateToRegisterForm4();
//		String title4 = stylistRegistrationSteps.getStylistRegisterPageTitle();
//		System.out.println(title4);
//		headerSteps.navigateToRegisterForm5();
//		String title5 = stylistRegistrationSteps.getStylistRegisterPageTitle();
//		System.out.println(title5);
//		headerSteps.navigateToRegisterForm6();
//		String title6 = stylistRegistrationSteps.getStylistRegisterPageTitle();
//		System.out.println(title6);		
//		headerSteps.navigateToRegisterForm7();
//		String title7 = stylistRegistrationSteps.getStylistRegisterPageTitle();
//		System.out.println(title7);		
		headerSteps.navigateToRegisterForm8();
		String title8 = stylistRegistrationSteps.getStylistRegisterPageTitle();
		System.out.println(title8);		
	
	
	}
	

}
