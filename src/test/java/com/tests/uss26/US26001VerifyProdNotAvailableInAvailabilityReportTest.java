package com.tests.uss26;

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

import com.connectors.http.MagentoProductCalls;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ReportsSteps;
import com.steps.frontend.reports.StylistsCustomerOrdersReportSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US26.1 Check products in availability report", type = "Scenarios")
@Story(Application.AvailabilityReport.US26_1.class)
@RunWith(ThucydidesRunner.class)
public class US26001VerifyProdNotAvailableInAvailabilityReportTest extends BaseTest {

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

	private static ProductDetailedModel generatedProduct = new ProductDetailedModel();
	private String stylistUsername, stylistPassword;
	private ProductDetailedModel updatedProduct;
	private String incrementId;

	@Before
	public void setUp() throws Exception {

		generatedProduct = MongoReader.grabProductDetailedModel("US26001VerifyProdNotAvailableForTheMomentInAvReportTest").get(0);
		incrementId = MongoReader.grabIncrementId("US26001VerifyProdNotAvailableForTheMomentInAvReportTest");

		updatedProduct = MagentoProductCalls.updateProductStockModel();
		updatedProduct.setStockData(MagentoProductCalls.createNotAvailableStockData());
		MagentoProductCalls.updateApiProduct(updatedProduct, incrementId);

		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us8" + File.separator + "us8003.properties");
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

	}

	@Test
	public void us26001VerifyProdNotAvailableInAvailabilityReportTest() throws IOException {

		frontEndSteps.performLogin(stylistUsername, stylistPassword);
		if (!headerSteps.succesfullLogin()) {

			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.redirectToStylistReports();
		reportsSteps.downloadProductsOrderedBySku();
		reportsSteps.verifyThatProductHasNotAvailableStatus(generatedProduct.getSku());
	}
}
