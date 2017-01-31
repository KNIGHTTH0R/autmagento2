package com.tests.uss30.uss30001;

import static net.thucydides.core.steps.stepdata.StepData.withTestDataFrom;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.UseTestDataFrom;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ReportsSteps;
import com.steps.frontend.reports.IpReportsSteps;
import com.steps.frontend.reports.StylistsCustomerOrdersReportSteps;
import com.tests.BaseTest;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.IpOverViewOpenIpsModel;
import com.tools.data.IpOverViewPayedOrdersModel;
import com.tools.data.IpOverViewReturnsListModel;
import com.tools.data.IpOverViewSummaryModel;
import com.tools.data.IpOverviewModel;
import com.tools.generalCalculation.IpOverviewCalculations;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.workflows.frontend.reports.IpReportValidationWorkflow;

@WithTag(name = "US30.1 Verify Ip Overview Report", type = "Scenarios")
@Story(Application.IpReport.US30_1.class)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value="resources/commissionrundate.csv")

public class US30001VerifyIpOverViewReportAfterMonthClosingTest extends BaseTest {


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
	public IpReportsSteps ipReportsSteps;
	@Steps
	public IpReportValidationWorkflow ipReportValidationWorkflow;

	private String stylistUsername, stylistPassword;
	private String reportMonth;
	String month,previousCommissionRun,lastCommissionRun,nextCommissionRun;

	IpOverviewModel expectedIpOverviewModel;
	List<IpOverViewPayedOrdersModel> expectedOrdersList;
	List<IpOverViewReturnsListModel> expectedReturns;

	@Before
	public void setUp() throws Exception {
		
		System.out.println("luna " + month);
		System.out.println("luna " + previousCommissionRun);
		System.out.println("luna " + lastCommissionRun);
		System.out.println("luna " + nextCommissionRun);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_30_FOLDER + File.separator + "us30001.properties");
			prop.load(input);
			stylistUsername = prop.getProperty("username");
			stylistPassword = prop.getProperty("password");
			reportMonth = prop.getProperty("reportMonth");

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
		

	//	expectedIpOverviewModel = IpOverviewCalculations.calculateIpOverview("2513","2016-11-20 12:00:00",previousCommissionRun,lastCommissionRun,nextCommissionRun);
		expectedOrdersList = expectedIpOverviewModel.getPayedOrders();
//		expectedReturns = expectedIpOverviewModel.getReturns();
		System.out.println("expecteeeeed!!!!!!!!!!!!!!" +expectedIpOverviewModel.getPaidOrdersPreviosMonth());
//		System.out.println("expecteeeeed!!!!!!!!!!!!!!" +expectedIpOverviewModel.getIpLastMonth().toString());
//		System.out.println("expecteeeeed!!!!!!!!!!!!!!" +expectedIpOverviewModel.getIpThisMonth().toString());
//		System.out.println("expecteeeeed!!!!!!!!!!!!!!" +expectedIpOverviewModel.getPaidOrdersPreviosMonth());
		System.out.println("expecteeeeed!!!!!!!!!!!!!!" +expectedOrdersList);

	}

	@Test
	public void us30001VerifyIpOverViewReportAfterMonthClosingTest() throws Exception {
		frontEndSteps.performLogin(stylistUsername, stylistPassword);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		
		
		headerSteps.redirectToStylistReports();
		reportsSteps.clickOnIpReports();
//		
//		System.out.println("asasasasasa"+ DateUtils.parseDate(reportMonth, "yyyy-MM-dd", "MMM - yyyy", new Locale.Builder().setLanguage(MongoReader.getContext()).build()));
//		
//		String str =DateUtils.parseDate(reportMonth, "yyyy-MM-dd", "MMM - yyyy", new Locale.Builder().setLanguage(MongoReader.getContext()).build());
//		ipReportsSteps.selectMonth(str.toUpperCase());

//		ipReportsSteps.selectMonth(DateUtils.parseDate(reportMonth, "yyyy-MM-dd HH:mm:ss", "yyyy-MMM", new Locale.Builder().setLanguage(MongoReader.getContext()).build()));

//		
//		IpOverViewSummaryModel grabbedSummaryModel = ipReportsSteps.getIpOverviewSummaryModel();
//
//		IpOverViewOpenIpsModel grabbedOpenIpsModel = ipReportsSteps.getOpenIpsModel();
//
//		List<IpOverViewPayedOrdersModel> grabbedPayedOrdersModel = ipReportsSteps.getPayedOrdersModel();
//
//		List<IpOverViewReturnsListModel> grabbedReturnsListModel = ipReportsSteps.getReturnsListModel();
//
//
//		ipReportsSteps.validateIpOverViewSummaryModel(expectedIpOverviewModel, grabbedSummaryModel);
//		ipReportsSteps.validateOpenIps(expectedIpOverviewModel, grabbedOpenIpsModel);
//		ipReportsSteps.validateIpOverViewPayedOrdersModelList(expectedOrdersList, grabbedPayedOrdersModel);
//		ipReportsSteps.validateIpOverViewRefundAndReturnsModelList(expectedReturns, grabbedReturnsListModel);

	}
	
	
}