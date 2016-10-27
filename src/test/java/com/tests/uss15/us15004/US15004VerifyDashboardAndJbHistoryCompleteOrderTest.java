package com.tests.uss15.us15004;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.DashboardSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.profile.ProfileNavSteps;
import com.steps.frontend.reports.JewelryBonusHistorySteps;
import com.tests.BaseTest;
import com.tools.constants.ContextConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.JewelryHistoryModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTags({ @WithTag(name = "US15.4 Validate Zzz Product JB for all order states", type = "Scenarios"),
	@WithTag(name = "US15.4 Check place a customer order details in mailchimp", type = "Scenarios") })
@Story(Application.Newsletter.US15_4.class)
@RunWith(SerenityRunner.class)
public class US15004VerifyDashboardAndJbHistoryCompleteOrderTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public JewelryBonusHistorySteps jewelryBonusHistorySteps;
	@Steps
	public ProfileNavSteps profileNavSteps;
	@Steps
	public DashboardSteps dashboardSteps;

	private String username, password;
	private JewelryHistoryModel expectedJewelryHistoryModel = new JewelryHistoryModel();
	private JewelryHistoryModel actualJewelryHistoryModel = new JewelryHistoryModel();

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			expectedJewelryHistoryModel = MongoReader.grabJewerlyHistoryModels("US15004OrderZzzProductsForCustomerTest" + SoapKeys.COMPLETE).get(0);

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss15" + File.separator + "us15004.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


	}

	@Test
	public void us15004VerifyDashboardAndJbHistoryCompleteOrderTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToProfile();
		String dashboardTotalJb = dashboardSteps.getJewelryBonus();

		profileNavSteps.selectMenu(ContextConstants.JEWELRY_HISTORY);
		actualJewelryHistoryModel = jewelryBonusHistorySteps.grabJewelryBonusHistory();

		dashboardSteps.validateDashboardTotalJewerlyBonus(expectedJewelryHistoryModel.getTotalPoints(), dashboardTotalJb);
		jewelryBonusHistorySteps.validateNewHistoryRegistration(expectedJewelryHistoryModel, actualJewelryHistoryModel);

	}

}
