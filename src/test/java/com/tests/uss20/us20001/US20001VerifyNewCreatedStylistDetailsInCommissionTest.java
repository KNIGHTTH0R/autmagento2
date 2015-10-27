package com.tests.uss20.us20001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApacheHttpHelper;
import com.connectors.http.ApiCalls;
import com.connectors.http.ComissionRestCalls;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.commission.CommissionStylistModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.env.constants.JenkinsConstants;
import com.tools.env.constants.SoapConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.commission.CommissionStylistValidationWorkflows;

@WithTag(name = "US20.1 Verify new SC and updated SC details in Commission", type = "Scenarios")
@Story(Application.StylecoachInfo.US20_1.class)
@RunWith(ThucydidesRunner.class)
public class US20001VerifyNewCreatedStylistDetailsInCommissionTest extends BaseTest {

	@Steps
	public CommissionStylistValidationWorkflows commissionValidationWorkflows;
	@Steps
	public CustomVerification customVerifications;

	CommissionStylistModel commissionStylistModel;
	DBStylistModel dBStylistModel;

	@Before
	public void setUp() throws Exception {

		String incrementId = MongoReader.grabIncrementId("US20001GetStylistIncrementIdTest");
		
		commissionStylistModel = ComissionRestCalls.getStylistInfo(incrementId);
		dBStylistModel = ApiCalls.getStylistList(SoapConstants.STYLIST_ID_FILTER, SoapConstants.EQUAL, incrementId).get(0);
		
		//TODO this is temporarily placed here.it only has to run before calculating the current month bonuses
		ApacheHttpHelper.sendGet(JenkinsConstants.IMPORT_ALL_JOB);
		ApacheHttpHelper.sendGet(JenkinsConstants.REOPEN_MONTH_JOB);
	}

	@Test
	public void us20001VerifyNewCreatedStylistDetailsInCommissionTest() {

		commissionValidationWorkflows.validateCommssionStylistProperties(commissionStylistModel, dBStylistModel);
		customVerifications.printErrors();

	}

}
