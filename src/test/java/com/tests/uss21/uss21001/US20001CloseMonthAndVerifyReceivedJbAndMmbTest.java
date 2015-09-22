package com.tests.uss21.uss21001;

import java.text.ParseException;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.external.commission.CommissionReportSteps;
import com.tests.BaseTest;
import com.tools.calculation.ClosedMonthBonusCalculation;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;

@WithTag(name = "US21", type = "frontend")
@Story(Application.KoboSubscription.class)
@RunWith(ThucydidesRunner.class)
public class US20001CloseMonthAndVerifyReceivedJbAndMmbTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CommissionReportSteps commissionReportSteps;

	@After
	public void setUp() throws NumberFormatException, ParseException {
		
		ClosedMonthBonusCalculation.calculateClosedMonthBonuses("1835", DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"), "2015-09-01 00:00:00");
	}

	@Test
	public void us20001StyleCoachRegistrationTest() throws NumberFormatException, ParseException {
		backEndSteps.navigate("https://commission-staging-aut.pippajean.com/report");
		commissionReportSteps.closeMonth();

	}
}
