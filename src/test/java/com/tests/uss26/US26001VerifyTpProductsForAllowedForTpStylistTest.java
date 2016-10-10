package com.tests.uss26;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;

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
import com.tools.constants.UrlConstants;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.data.soap.StockDataModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US26.1 Check products in availability report", type = "Scenarios")
@Story(Application.AvailabilityReport.US26_1.class)
@RunWith(SerenityRunner.class)
public class US26001VerifyTpProductsForAllowedForTpStylistTest extends BaseTest {

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
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;

	@Before
	public void setUp() throws Exception {

		StockDataModel stockData;

		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setName("AV_REPORT_AUT_" + genProduct1.getName());
		stockData = MagentoProductCalls.createNotAvailableYetStockData(DateUtils.getNextMonthMiddle("yyyy-MM-dd"));
		stockData.setMinQty("-10");
		stockData.setIsInStock("1");
		stockData.setIsDiscontinued("0");
		genProduct1.setStockData(stockData);
		MagentoProductCalls.createApiProduct(genProduct1);

		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.setName("AV_REPORT_AUT_" + genProduct2.getName());
		stockData = MagentoProductCalls.createNotAvailableYetStockData(DateUtils.getNextMonthMiddle("yyyy-MM-dd"));
		stockData.setQty("-10");
		stockData.setMinQty("-10");
		stockData.setIsDiscontinued("0");
		genProduct2.setStockData(stockData);
		MagentoProductCalls.createApiProduct(genProduct2);

		genProduct3 = MagentoProductCalls.createProductModel();
		genProduct3.setName("AV_REPORT_AUT_" + genProduct3.getName());
		stockData = MagentoProductCalls.createNotAvailableYetStockData("");
		stockData.setQty("-5");
		stockData.setMinQty("-10");
		stockData.setIsInStock("0");
		stockData.setIsDiscontinued("0");
		genProduct3.setStockData(stockData);
		MagentoProductCalls.createApiProduct(genProduct3);

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
	}

	@Test
	public void us26001VerifyTpProductsForAllowedForTpStylistTest() throws IOException, ParseException {

		frontEndSteps.performLogin(stylistUsername, stylistPassword);
//		frontEndSteps.performLogin("irina.neagu@evozon.com", "irina1");
		if (!headerSteps.succesfullLogin()) {

			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.redirectToStylistReports();
		reportsSteps.downloadProductsOrderedBySku();
//		reportsSteps.verifyTpInfoMessage(true);
		reportsSteps.verifyAsteriscNextToAvDate(genProduct1);
		reportsSteps.verifyNoAsteriscNextToAvDate(genProduct2);
		reportsSteps.verifyNoAsteriscNextToWillBeAnnouncedStatus(genProduct3);
	}

}
