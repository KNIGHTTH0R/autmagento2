package com.tests.uss26.uss26001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ReportsSteps;
import com.steps.frontend.reports.StylistsCustomerOrdersReportSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.UrlConstants;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US26.1 Check products in availability report", type = "Scenarios")
@Story(Application.AvailabilityReport.US26_1.class)
@RunWith(SerenityRunner.class)
public class US26001VerifyProdNotAvailableForTheMomentInAvReportTest extends BaseTest {

	@Steps
	public StylistsCustomerOrdersReportSteps stylistsCustomerOrdersReportSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public ReportsSteps reportsSteps;
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public CustomVerification customVerifications;

	private String stylistUsername, stylistPassword;
	private ProductDetailedModel genProduct1;
	private String incrementId;

	@Before
	public void setUp() throws Exception {

		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setName("FOR_AVAILABILITY_REPORT_AUTOMATION_" + genProduct1.getName());
		genProduct1.setStockData(MagentoProductCalls.createNotAvailableForTheMomentStockData());
		incrementId = MagentoProductCalls.createApiProduct(genProduct1);

		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss26" + File.separator + "us26001.properties");
			prop.load(input);
			stylistUsername = prop.getProperty("stylistUsername");
			stylistPassword = prop.getProperty("stylistPassword");

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
		
		MongoConnector.cleanCollection(getClass().getSimpleName());

	}

	@Test
	public void us26001VerifyProdNotAvailableForTheMomentInAvReportTest() throws IOException {

		frontEndSteps.performLogin(stylistUsername, stylistPassword);
//		frontEndSteps.performLogin("irina.neagu@evozon.com", "irina1");
		if (!headerSteps.succesfullLogin()) {

			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.redirectToStylistReports();
		reportsSteps.downloadProductsOrderedBySku();
		reportsSteps.verifyThatProductHasNotAvailableForTheMomentStatus(genProduct1.getSku());
	}

	@After
	public void tearDown() {
		MongoWriter.saveProductDetailedModel(genProduct1, getClass().getSimpleName());
		MongoWriter.saveIncrementId(incrementId, getClass().getSimpleName());
	}
}
