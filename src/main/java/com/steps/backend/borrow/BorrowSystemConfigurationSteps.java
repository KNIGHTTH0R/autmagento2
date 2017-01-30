package com.steps.backend.borrow;

import java.text.ParseException;

import com.pages.backend.borrow.BorrowPage;
import com.tools.constants.ConfigConstants;
import com.tools.data.backend.TermPurchaseOrderModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class BorrowSystemConfigurationSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void selectDisabledBorrowOption(String borrowOption) {
		systemConfigurationPage().selectBorrowOption(borrowOption);
		systemConfigurationPage().saveConfiguration();
	}
	
	
	
	@Step
	public void goToBorrowTab() {
		systemConfigurationPage().clickOnDesiredTab("Borrow");
	}
	
	
	@Step
	public void selectBorrowProcessType(String borrowType) {
		borrowPage().selectBorrowProcessType(borrowType);
	}

	
	@Step
	public void saveConfiguration() {
		systemConfigurationPage().saveConfiguration();
	}
	
	@Step
	public void selectCountries() {
		borrowPage().selectCountries();
	}
	
	@Step
	public void selectProductsForStylistwithExtendedOption() {
		borrowPage().selectProductsForStylistwithExtendedOption();
	}

	
}
