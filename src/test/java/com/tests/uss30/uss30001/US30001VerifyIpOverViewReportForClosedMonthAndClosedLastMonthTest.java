package com.tests.uss30.uss30001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.UserRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ReportsSteps;
import com.steps.frontend.reports.IpReportsSteps;
import com.steps.frontend.reports.StylistsCustomerOrdersReportSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
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
import com.workflows.frontend.reports.IpReportValidationWorkflow;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.UseTestDataFrom;

@WithTag(name = "US30.1 Verify Ip Overview Report", type = "Scenarios")
@Story(Application.IpReport.US30_1.class)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value="resources/commissionrundate.csv")

public class US30001VerifyIpOverViewReportForClosedMonthAndClosedLastMonthTest extends BaseTest {


	@Steps
	public StylistsCustomerOrdersReportSteps stylistsCustomerOrdersReportSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public ReportsSteps reportsSteps;
	@Steps
	public UserRegistrationSteps frontEndSteps;
	@Steps
	public IpReportsSteps ipReportsSteps;
	@Steps
	public IpReportValidationWorkflow ipReportValidationWorkflow;
	@Steps
	public CustomVerification customVerification;
	

	private String stylistUsername, stylistPassword;
	//private String reportMonth;
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
		//	reportMonth = prop.getProperty("reportMonth");

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
		
        //for february after is closing
	//	expectedIpOverviewModel = IpOverviewCalculations.calculateIpOverviewForClosedMonth("2513","2017-02-05 00:00:00","2017-01-09 13:07:29","2017-02-28 23:59:00","2017-03-10 17:00:00");
		expectedIpOverviewModel = IpOverviewCalculations.calculateIpOverviewForClosedMonth("6013","2017-10-05 00:00:00","2017-09-10 00:00:00","2017-10-10 00:00:00","2017-11-10 00:00:00");

//		System.out.println("expectedIpOverviewModel.getPaidOrdersThisMonth(): "+expectedIpOverviewModel.getPaidOrdersThisMonth());
//		System.out.println("expectedIpOverviewModel.getPaidOrdersThisMonth(): "+expectedIpOverviewModel.getIpTPOrdersThisMonth());
//		System.out.println("expectedIpOverviewModel.getPaidOrdersThisMonth(): "+expectedIpOverviewModel.getOpenChargebacks());
//		System.out.println("expectedIpOverviewModel.getPaidOrdersThisMonth(): "+expectedIpOverviewModel.getIpLastMonth());
		//for march after is closing
		
		expectedOrdersList = expectedIpOverviewModel.getPayedOrders(); //->pentru orders payed
	//	System.out.println("expectedOrdersList size"+ expectedOrdersList.size());
//		expectedReturns = expectedIpOverviewModel.getReturns();
	}

	@Test
	public void us30001VerifyIpOverViewReportForClosedMonthAndClosedLastMonthTest() throws Exception {
		frontEndSteps.performLogin(stylistUsername, stylistPassword);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		
		
//		headerSteps.redirectToStylistReports();
//		reportsSteps.clickOnIpReports();
		headerSteps.navigate("http://staging-aut.pippajean.com/de/stylereports/order/ipsreport/");
		headerSteps.waitABit(3000);
		//ipReportsSteps.selectMonth("OKT - 2017");
		//ipReportsSteps.selectMonth(DateUtils.parseDate(reportMonth, "yyyy-MM-dd", "MMM - yyyy", new Locale.Builder().setLanguage(MongoReader.getContext()).build()));
		
		//validate Ip overview report 
		IpOverViewSummaryModel  grabbedSummaryModel= ipReportsSteps.getIpOverviewSummaryModel();
		System.out.println("expected values" + expectedIpOverviewModel.toString());
		System.out.println("grabbed values" +grabbedSummaryModel.toString());
		ipReportValidationWorkflow.verifyIpOverviewReportDetails(grabbedSummaryModel, expectedIpOverviewModel);
		
		//validate Open ips summary
		IpOverViewOpenIpsModel grabbedOpenIpsModel = ipReportsSteps.getOpenIpsModelNotCurrentMonth();
		ipReportValidationWorkflow.verifyOpenIpFromOverviewReportDetailsNotCurrentMonth(grabbedOpenIpsModel, expectedIpOverviewModel);
		

		//validate payed orders list
		List<IpOverViewPayedOrdersModel> grabbedPayedOrdersModel = ipReportsSteps.getPayedOrdersModel();
		ipReportValidationWorkflow.verifyPayedOrdersList(expectedOrdersList, grabbedPayedOrdersModel);
		System.out.println("expected"+expectedOrdersList.size());
		System.out.println("grabbed"+grabbedPayedOrdersModel.size());
	   // System.out.println("order id "+grabbedPayedOrdersModel.removeAll(expectedOrdersList));
//		
//		
//        //validate returns orders 
//		List<IpOverViewReturnsListModel> grabbedReturnsListModel = ipReportsSteps.getReturnsListModel();
//		ipReportValidationWorkflow.verifyReturnedOrdersList(expectedReturns, grabbedReturnsListModel);
//		System.out.println("expected returns"+expectedReturns.size());
//		System.out.println("grabbed returns"+grabbedReturnsListModel.size());

		customVerification.printErrors();

	}
	
	
	
}
