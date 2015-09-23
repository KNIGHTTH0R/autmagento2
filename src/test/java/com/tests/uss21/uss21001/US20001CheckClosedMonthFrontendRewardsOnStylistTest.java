package com.tests.uss21.uss21001;

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
public class US20001CheckClosedMonthFrontendRewardsOnStylistTest extends BaseTest {

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

	@Test
	public void us15004VerifyDashboardAndJbHistoryCompleteOrderTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToProfile();
		String dashboardTotalJb = dashboardSteps.getJewelryBonus();
		String dashboardTotalMmb = dashboardSteps.getMarketingMaterialBonus();

		dashboardSteps.validateDashboardTotalJewerlyBonus(expectedJewelryHistoryModel.getTotalPoints(), dashboardTotalJb);
		dashboardSteps.validateDashboardTotalMarketingBonus(expectedJewelryHistoryModel.getTotalPoints(), dashboardTotalMmb);

	}

}
