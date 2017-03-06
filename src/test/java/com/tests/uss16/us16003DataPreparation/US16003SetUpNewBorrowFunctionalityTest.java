package com.tests.uss16.us16003DataPreparation;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.borrow.BorrowSystemConfigurationSteps;
import com.tests.BaseTest;
import com.tools.constants.Credentials;
import com.tools.constants.Separators;
import com.tools.utils.DateUtils;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class US16003SetUpNewBorrowFunctionalityTest extends BaseTest{

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public BorrowSystemConfigurationSteps borrowSystemConfigurationSteps;
	
	
	
	@Test
	public void us16003SetUpNewBorrowFunctionalityTest() {
		
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSystemConfiguration();
		borrowSystemConfigurationSteps.goToBorrowTab();
		borrowSystemConfigurationSteps.selectDisabledBorrowOption("Nein");
		borrowSystemConfigurationSteps.selectBorrowProcessType("(New process) Allow defined products to be borrowed");
		borrowSystemConfigurationSteps.saveConfiguration();
		borrowSystemConfigurationSteps.selectCountries();
		borrowSystemConfigurationSteps.selectProductsForStylistwithExtendedOption();
		borrowSystemConfigurationSteps.saveConfiguration();
	}
}
