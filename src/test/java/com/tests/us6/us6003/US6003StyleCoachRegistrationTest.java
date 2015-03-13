package com.tests.us6.us6003;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.StylistRegistrationSteps;
import com.tests.BaseTest;
import com.tools.requirements.Application;


@WithTag(name = "US6003", type = "frontend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US6003StyleCoachRegistrationTest extends BaseTest{
	
	@Steps
	public FooterSteps footerSteps;	
	@Steps
	public HeaderSteps headerSteps;	
	@Steps
	public HomeSteps homeSteps;	
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	
	private String username = "pN46P3vr@mailinator.com";
	private String password = "MXpUvCPtsIEEFq1";
	
	@Test
	public void us6003NavigateToStyleCoachRegisterPage() {
		
		headerSteps.navigateToRegisterForm();
		stylistRegistrationSteps.validateStylistRegisterPageTitle();
		
		headerSteps.navigateToRegisterFormFromStylistRegistrationLinkAndStarteJetzButton();
		stylistRegistrationSteps.validateStylistRegisterPageTitle();

		homeSteps.navigateToRegisterFormFromStyleCoachLinkAndJetzStarten();
		stylistRegistrationSteps.validateStylistRegisterPageTitle();

		homeSteps.navigateToRegisterFormFromStyleCoachLinkAndStarteJetzt();
		stylistRegistrationSteps.validateStylistRegisterPageTitle();		
		
		customerRegistrationSteps.navigateToLoginPageAndPerformLogin(username,password);
		
		homeSteps.navigateToRegisterFormFromStyleCoachLinkAndJetzStarten();
		stylistRegistrationSteps.validateStylistRegisterPageTitle();
	
		homeSteps.navigateToRegisterFormFromStyleCoachLinkAndStarteJetzt();
		stylistRegistrationSteps.validateStylistRegisterPageTitle();

		footerSteps.navigateToRegisterFormFromRegistrierungLink();
		stylistRegistrationSteps.validateStylistRegisterPageTitle();
		
		footerSteps.navigateToRegisterFormFromStarterSetLink();;
		stylistRegistrationSteps.validateStylistRegisterPageTitle();
			
		footerSteps.navigateToRegisterFormFromTrainingLink();
		stylistRegistrationSteps.validateStylistRegisterPageTitle();
			
		footerSteps.navigateToRegisterFormFromIncentivereisenLink();;
		stylistRegistrationSteps.validateStylistRegisterPageTitle();
			
		footerSteps.navigateToRegisterFormFromErfolgsgeschichtenLink();;
		stylistRegistrationSteps.validateStylistRegisterPageTitle();
			
		footerSteps.navigateToRegisterFormFromTraumkarriereStyleCoachLink();
		stylistRegistrationSteps.validateStylistRegisterPageTitle();

		
		
	
	
	}
	

}
