package com.tests.uss20.us20001;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApacheHttpHelper;
import com.connectors.http.ComissionRestCalls;
import com.connectors.http.StylistListMagentoCalls;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.EnvironmentConstants;
import com.tools.constants.SoapConstants;
import com.tools.data.commission.CommissionStylistModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.commission.CommissionStylistValidationWorkflows;

@WithTag(name = "US20.1 Verify new SC and updated SC details in Commission", type = "Scenarios")
@Story(Application.StylecoachInfo.US20_1.class)
@RunWith(SerenityRunner.class)
public class US20001VerifyNewCreatedStylistDetailsInCommissionTest extends BaseTest {

	@Steps
	public CommissionStylistValidationWorkflows commissionValidationWorkflows;
	@Steps
	public CustomVerification customVerifications;

	CommissionStylistModel commissionStylistModel;
	DBStylistModel dBStylistModel;

	@Before
	public void setUp() throws Exception {

		String incrementId = MongoReader.grabIncrementId("US20001StylistActivationTest");
		System.out.println(incrementId);

		commissionStylistModel = ComissionRestCalls.getStylistInfo(incrementId);
		dBStylistModel = StylistListMagentoCalls
				.getStylistList(SoapConstants.STYLIST_ID_FILTER, SoapConstants.EQUAL, incrementId).get(0);

		ApacheHttpHelper.sendGet(EnvironmentConstants.IMPORT_ALL_JOB, EnvironmentConstants.USERNAME_JENKINS_COMM,
				EnvironmentConstants.PASSWORD_JENKINS_COMM);
		ApacheHttpHelper.sendGet(EnvironmentConstants.REOPEN_MONTH_JOB, EnvironmentConstants.USERNAME_JENKINS_COMM,
				EnvironmentConstants.PASSWORD_JENKINS_COMM);
	}

	@Test
	public void us20001VerifyNewCreatedStylistDetailsInCommissionTest() {

		commissionValidationWorkflows.validateCommssionStylistProperties(commissionStylistModel, dBStylistModel);
		customVerifications.printErrors();

	}

}