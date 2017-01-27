package com.tests.uss36.uss36001;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoSosUserInfo;
import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.backend.SalesOnSpeed.MagentoToSosContactsSyncSteps;
import com.steps.backend.customer.CustomerDetailsBackendSteps;
import com.steps.backend.stylecoach.StylecoachSalesOnSpeedBackendSteps;
import com.steps.frontend.ContactDetailsSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.profile.ProfileNavSteps;
import com.steps.frontend.profile.ProfileSteps;
import com.steps.frontend.profile.SosSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.Credentials;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.SosContactModel;
import com.tools.data.salesOnSpeed.MagentoSOSUserInfoModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;


@RunWith(SerenityRunner.class)
public class US36001ResetAnActiveAccountTest extends BaseTest {
	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public CustomerDetailsBackendSteps customerDetailsBackendSteps;
	@Steps 
	public ProfileNavSteps profileNavSteps;
	@Steps 
	public LoungeSteps loungeSteps;
	@Steps 
	public SosSteps sosSteps;
	@Steps 
	public ContactDetailsSteps contactDetailsSteps;
	@Steps 
	public StylecoachSalesOnSpeedBackendSteps stylecoachSalesOnSpeedBackendSteps;
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	
	@Steps
	MagentoToSosContactsSyncSteps magentoToSosSync;
	
	
	

	
	SosContactModel sosContact=new SosContactModel();
	String sosPassword;
	String email, password;
	public CustomerFormModel stylistRegistrationData;
	MagentoSOSUserInfoModel sosUserInfoBeforeReset = new MagentoSOSUserInfoModel();
	MagentoSOSUserInfoModel sosUserInfoAfterReset = new MagentoSOSUserInfoModel();
	@Before
	public void setUp() {

		
		int size = MongoReader.grabCustomerFormModels("US36001StyleCoachRegistrationTest").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US36001StyleCoachRegistrationTest").get(0);
		} else
			System.out.println("The database has no entries");

		email=stylistRegistrationData.getEmailName();
		password=stylistRegistrationData.getPassword();
	
		MongoConnector.cleanCollection(getClass().getSimpleName());

		


	}

	/**
	 * BackEnd steps in this test
	 * @throws Exception 
	 */
	@Test
	public void us36001ResetAnActiveAccountTest() throws Exception {
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(email);
		backEndSteps.openCustomerDetails(email);
		
		String stylistId=stylecoachSalesOnSpeedBackendSteps.grabStyleCoachID();
		sosUserInfoBeforeReset=MagentoSosUserInfo.getUserInfo(stylistId);
		String stylistSosId=sosUserInfoBeforeReset.getSosId();
		
		System.out.println("sosUserInfoBeforeReset "+ stylistSosId );
		
		customerDetailsBackendSteps.clickOnSalesOnSpeedInfo();
		sosPassword=stylecoachSalesOnSpeedBackendSteps.checkSosPassword();
		stylecoachSalesOnSpeedBackendSteps.clickOnResetAccountButton();
		
		stylecoachSalesOnSpeedBackendSteps.checkIsPresentResetAccountButton();
		stylecoachSalesOnSpeedBackendSteps.checkIsPresentResetContactButton();
		stylecoachSalesOnSpeedBackendSteps.validateResetAccountSuccessMessage();
		
		String sosNewPassword=stylecoachSalesOnSpeedBackendSteps.checkSosPassword();
		sosUserInfoAfterReset=MagentoSosUserInfo.getUserInfo(stylistId);
		String stylistNewSosId=sosUserInfoAfterReset.getSosId();
//
		System.out.println("sosUserInfoAfterReset "+stylistNewSosId);
		sosContact.setSosPassword(sosNewPassword);
		sosContact.setSosUserEmail(email);
		sosContact.setStylistId(stylistId);
		sosContact.setStylistSosId(stylistNewSosId);
		
		magentoToSosSync.validateStylistSosIdAfterReset(stylistSosId, stylistNewSosId);
		magentoToSosSync.validateSosPasswordIsChanged(sosPassword,sosNewPassword);
		magentoToSosSync.validateOldCredential(stylistSosId,email,sosPassword);
		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveSosCustomerFormModel(sosContact, getClass().getSimpleName());
		
	}
}