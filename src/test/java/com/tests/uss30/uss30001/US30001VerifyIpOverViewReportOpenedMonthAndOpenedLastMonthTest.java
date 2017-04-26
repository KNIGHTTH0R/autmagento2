package com.tests.uss30.uss30001;

import static net.thucydides.core.steps.stepdata.StepData.withTestDataFrom;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApacheHttpHelper;
import com.steps.backend.BackEndSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ReportsSteps;
import com.steps.frontend.reports.IpReportsSteps;
import com.steps.frontend.reports.StylistsCustomerOrdersReportSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.EnvironmentConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.TimeConstants;
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
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.reports.IpReportValidationWorkflow;

@WithTag(name = "US30.1 Verify Ip Overview Report", type = "Scenarios")
@Story(Application.IpReport.US30_1.class)
@RunWith(SerenityRunner.class)
public class US30001VerifyIpOverViewReportOpenedMonthAndOpenedLastMonthTest extends BaseTest {

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
	@Steps
	public BackEndSteps backEndSteps;

	@Steps
	public CustomVerification customVerification;

	private String stylistUsername, stylistPassword;
	private String reportMonth;

	private String month,previousCommissionRun,lastCommissionRun,nextCommissionRun;
	IpOverviewModel expectedIpOverviewModel;
	List<IpOverViewPayedOrdersModel> expectedOrdersList;
	List<IpOverViewReturnsListModel> expectedReturns;

	@Before
	public void setUp() throws Exception {
		
//		backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
//		ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_ORDER_IMPORT,
//				EnvironmentConstants.USERNAME_JENKINS_COMM, EnvironmentConstants.PASSWORD_JENKINS_COMM);
//		backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
//		ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_CREDITMEMO_IMPORT,
//				EnvironmentConstants.USERNAME_JENKINS_COMM, EnvironmentConstants.PASSWORD_JENKINS_COMM);
//		backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
//		ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_SEND_IP_REPORT,
//				EnvironmentConstants.USERNAME_JENKINS_COMM, EnvironmentConstants.PASSWORD_JENKINS_COMM);

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
		
//		try {
//			withTestDataFrom("resources/.commissionrundate.csv");
//		} catch (IOException e) {
//			e.printStackTrace();
//			Assert.fail("Failed !!!");
//		}

		//for selecting March from Dropdown
		//march=Opened,february=Opened
	//	expectedIpOverviewModel = IpOverviewCalculations.calculateIpOverviewForOpenMonthAndOpenedLastMonth("2513","2017-03-05 00:00:00","2017-02-28 17:07:29","2017-03-31 23:59:00");
		expectedIpOverviewModel = IpOverviewCalculations.calculateIpOverviewForOpenMonthAndOpenedLastMonth("2513","2017-04-05 00:00:00","2017-03-10 17:00:00","2017-04-31 23:59:00");
	//	expectedIpOverviewModel = IpOverviewCalculations.calculateIpOverviewForOpenMonthAndClosedLastMonth("2513","2017-04-05 00:00:00","2017-02-28 23:59:00","2017-03-10 17:00:00","2017-04-10 17:00:00");

		
		expectedOrdersList = expectedIpOverviewModel.getPayedOrders(); //->pentru orders payed
//		expectedReturns = expectedIpOverviewModel.getReturns();

	}

	@Test
	public void us30001VerifyIpOverViewReportOpenedMonthAndOpenedLastMonthTest() throws Exception {
		frontEndSteps.performLogin(stylistUsername, stylistPassword);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		
		
		headerSteps.redirectToStylistReports();
		reportsSteps.clickOnIpReports();

//	System.out.println("asasasasasa"+ DateUtils.parseDate(reportMonth, "yyyy-MM-dd", "MMM - yyyy", new Locale.Builder().setLanguage(MongoReader.getContext()).build()));
//	
//	String str =DateUtils.parseDate(reportMonth, "yyyy-MM-dd", "MMM - yyyy", new Locale.Builder().setLanguage(MongoReader.getContext()).build());
//	ipReportsSteps.selectMonth(str.toUpperCase());
	
//		ipReportsSteps.selectMonth(DateUtils.parseDate(reportMonth, "yyyy-MM-dd", "MMM - yyyy", new Locale.Builder().setLanguage(MongoReader.getContext()).build()));
		//ipReportsSteps.selectMonth("MäR - 2017");
		headerSteps.navigate("http://aut-pippajean.evozon.com/de/ioa/stylereports/order/ipsreport/?month=2017-04");

		
		
		//validate Ip overview report -sunt OK
		IpOverViewSummaryModel  grabbedSummaryModel= ipReportsSteps.getIpOverviewSummaryModel();
		System.out.println("expected values" + expectedIpOverviewModel.toString());
		System.out.println("grabbed values" +grabbedSummaryModel.toString());
		ipReportValidationWorkflow.verifyIpOverviewReportDetails(grabbedSummaryModel, expectedIpOverviewModel);
		
		
		//validate Open ips summary - OK
		IpOverViewOpenIpsModel grabbedOpenIpsModel = ipReportsSteps.getOpenIpsModelCurrentMonth();
		ipReportValidationWorkflow.verifyOpenIpFromOverviewReportDetailsCurrentMonth(grabbedOpenIpsModel, expectedIpOverviewModel);
		

//		//validate payed orders list-OK
		List<IpOverViewPayedOrdersModel> grabbedPayedOrdersModel = ipReportsSteps.getPayedOrdersModel();
		ipReportValidationWorkflow.verifyPayedOrdersList(expectedOrdersList, grabbedPayedOrdersModel);
		System.out.println("expected"+expectedOrdersList.size());
		System.out.println("grabbed"+grabbedPayedOrdersModel.size());
	   // System.out.println("order id "+grabbedPayedOrdersModel.removeAll(expectedOrdersList));
//		
//		
//        //validate returns orders - shop=2,comm=6
//		List<IpOverViewReturnsListModel> grabbedReturnsListModel = ipReportsSteps.getReturnsListModel();
//		ipReportValidationWorkflow.verifyReturnedOrdersList(expectedReturns, grabbedReturnsListModel);
//		System.out.println("expected returns"+expectedReturns.size());
//		System.out.println("grabbed returns"+grabbedReturnsListModel.size());
		
		customVerification.printErrors();
		

        
	}

	
	
}
