package com.tests.uss30.uss30001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
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
import com.tools.data.IpOverViewIpCorrectionModel;
import com.tools.data.IpOverViewOpenIpsModel;
import com.tools.data.IpOverViewPayedOrdersModel;
import com.tools.data.IpOverViewReturnsListModel;
import com.tools.data.IpOverViewSummaryModel;
import com.tools.data.IpOverviewModel;
import com.tools.generalCalculation.IpOverviewCalculations;
import com.tools.requirements.Application;
import com.workflows.frontend.reports.IpReportValidationWorkflow;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

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
	public UserRegistrationSteps frontEndSteps;
	@Steps
	public IpReportsSteps ipReportsSteps;
	@Steps
	public IpReportValidationWorkflow ipReportValidationWorkflow;
	@Steps
	public BackEndSteps backEndSteps;

	@Steps
	public CustomVerification customVerification;

	private String stylistUsername, stylistPassword;

	IpOverviewModel expectedIpOverviewModel;
	List<IpOverViewPayedOrdersModel> expectedOrdersList;
	List<IpOverViewReturnsListModel> expectedReturns;
	List<IpOverViewIpCorrectionModel> expectedIpCorrection= new ArrayList<IpOverViewIpCorrectionModel>();
	

	@Before
	public void setUp() throws Exception {
		
		// backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
		// ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_ORDER_IMPORT,
		// EnvironmentConstants.USERNAME_JENKINS_COMM,
		// EnvironmentConstants.PASSWORD_JENKINS_COMM);
		// backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
		// ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_CREDITMEMO_IMPORT,
		// EnvironmentConstants.USERNAME_JENKINS_COMM,
		// EnvironmentConstants.PASSWORD_JENKINS_COMM);
		// backEndSteps.waitCertainTime(TimeConstants.TIME_MEDIUM);
		// ApacheHttpHelper.sendGet(EnvironmentConstants.RUN_SEND_IP_REPORT,
		// EnvironmentConstants.USERNAME_JENKINS_COMM,
		// EnvironmentConstants.PASSWORD_JENKINS_COMM);
	
		IpOverViewIpCorrectionModel ipCorrected= new IpOverViewIpCorrectionModel();
		ipCorrected.setComment("test");
		ipCorrected.setDate("02.05.2018");
		ipCorrected.setIp("135");
		
		expectedIpCorrection.add(ipCorrected);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_30_FOLDER + File.separator + "us30001.properties");
			prop.load(input);
			stylistUsername = prop.getProperty("username");
			stylistPassword = prop.getProperty("password");

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

		
		
		//april open, march open
		/*expectedIpOverviewModel = IpOverviewCalculations.calculateIpOverviewForOpenMonthAndOpenedLastMonth("33",
				"2018-04-10 00:00:00", "2018-04-09 17:00:00", "2018-04-19 17:00:00");
*/
		
		expectedIpOverviewModel = IpOverviewCalculations.calculateIpOverviewForOpenMonthAndOpenedLastMonth("33",
				"2018-05-02 00:00:00", "2018-05-01 17:00:00", "2018-05-02 17:00:00");

		 expectedOrdersList = expectedIpOverviewModel.getPayedOrders();
		 expectedReturns =expectedIpOverviewModel.getReturns();
		

	}

	@Test
	public void us30001VerifyIpOverViewReportOpenedMonthAndOpenedLastMonthTest() throws Exception {

		frontEndSteps.performLogin(stylistUsername, stylistPassword);

		headerSteps.navigate("https://aut.atmyhome.com/de/emc/stylereports/order/ipsreport/");

		// validate Ip overview report -sunt OK
		IpOverViewSummaryModel grabbedSummaryModel = ipReportsSteps.getIpOverviewSummaryModel();
		ipReportValidationWorkflow.verifyIpOverviewReportDetails(grabbedSummaryModel, expectedIpOverviewModel);

		// validate Open ips summary - OK
		IpOverViewOpenIpsModel grabbedOpenIpsModel = ipReportsSteps.getOpenIpsModelCurrentMonth();
		ipReportValidationWorkflow.verifyOpenIpFromOverviewReportDetailsCurrentMonth(grabbedOpenIpsModel,
				expectedIpOverviewModel);

		// validate payed orders list-OK
		List<IpOverViewPayedOrdersModel> grabbedPayedOrdersModel = ipReportsSteps.getPayedOrdersModel();
		ipReportValidationWorkflow.verifyPayedOrdersList(expectedOrdersList, grabbedPayedOrdersModel);
	
		// validate returns orders - ok
		List<IpOverViewReturnsListModel> grabbedReturnsListModel = ipReportsSteps.getReturnsListModel();
		ipReportValidationWorkflow.verifyReturnedOrdersList(expectedReturns, grabbedReturnsListModel);
		
		// validate ipCorrection  - ok
		List<IpOverViewIpCorrectionModel> grabbedIpCorrectionModel=ipReportsSteps.getIpCorrectionModel();
		ipReportValidationWorkflow.verifyIpCorrectionList(expectedIpCorrection,grabbedIpCorrectionModel);
		customVerification.printErrors();

	}

}
