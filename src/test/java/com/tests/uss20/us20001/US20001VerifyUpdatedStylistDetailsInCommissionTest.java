package com.tests.uss20.us20001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApiCalls;
import com.connectors.http.ComissionRestCalls;
import com.tests.BaseTest;
import com.tools.data.commission.CommissionStylistModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.env.constants.SoapConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.commission.CommissionValidationWorkflows;

@WithTag(name = "US20", type = "external")
@Story(Application.MassAction.class)
@RunWith(ThucydidesRunner.class)
public class US20001VerifyUpdatedStylistDetailsInCommissionTest extends BaseTest {

	@Steps
	public CommissionValidationWorkflows commissionValidationWorkflows;

	CommissionStylistModel commissionStylistModel;
	DBStylistModel dBStylistModel;

	@Before
	public void setUp() throws Exception {

		String incrementId = MongoReader.grabIncrementId("US20001GetStylistIncrementIdTest");

		commissionStylistModel = ComissionRestCalls.getStylistInfo(incrementId);
		dBStylistModel = ApiCalls.getStylistList(SoapConstants.STYLIST_ID_FILTER, SoapConstants.EQUAL, incrementId).get(0);
	}

	@Test
	public void us20001VerifyNewCreatedStylistDetailsInCommissionTest() {

		commissionValidationWorkflows.validateCommssionStylistProperties(commissionStylistModel, dBStylistModel);

	}

}
