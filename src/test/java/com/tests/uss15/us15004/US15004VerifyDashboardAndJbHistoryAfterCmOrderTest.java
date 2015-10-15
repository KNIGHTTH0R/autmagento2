package com.tests.uss15.us15004;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.DashboardSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.reports.JewelryBonusHistorySteps;
import com.tests.BaseTest;
import com.tools.SoapKeys;
import com.tools.data.backend.JewelryHistoryModel;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US15", type = "frontend")
@Story(Application.ZzzProducts.class)
@RunWith(ThucydidesRunner.class)
public class US15004VerifyDashboardAndJbHistoryAfterCmOrderTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public JewelryBonusHistorySteps jewelryBonusHistorySteps;
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
	public void us15004VerifyDashboardAndJbHistoryAfterCmOrderTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToProfile();
		String dashboardTotalJb = dashboardSteps.getJewelryBonus();

		jewelryBonusHistorySteps.navigateToJewelryHistory();
		actualJewelryHistoryModel = jewelryBonusHistorySteps.grabJewelryBonusHistory();

		dashboardSteps.validateDashboardTotalJewerlyBonus(expectedJewelryHistoryModel.getTotalPoints(), dashboardTotalJb);
		jewelryBonusHistorySteps.validateNewHistoryRegistration(expectedJewelryHistoryModel, actualJewelryHistoryModel);

	}

}
