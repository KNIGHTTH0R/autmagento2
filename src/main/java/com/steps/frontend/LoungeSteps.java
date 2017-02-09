package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.data.frontend.LoungeIpPerformanceModel;
import com.tools.requirements.AbstractSteps;

public class LoungeSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void goToMyBusiness() {
		loungePage().goToMyBusiness();
	}

	@Step
	public void clickCreateParty() {
		loungePage().clickCreateParty();
	}

	@Step
	public void clickOrderForCustomer() {
		loungePage().clickOrderForCustomer();
	}

	@Step
	public void clickGoToBorrowCart() {
		loungePage().clickGoToBorrowCart();
	}

	@Step
	public void goToToAddNewContact() {
		loungePage().goToToAddNewContact();
	}

	@Step
	public void goToContactsList() {
		loungePage().goToContactsList();
	}

	@Step
	public void selectCustomerToOrderFor(String name) {
		loungePage().typeContactName(name);
		loungePage().startOrderForCustomer();
	}

	@Step
	public void orderForNewCustomer() {
		loungePage().clickOrderForCustomer();
		loungePage().clickAddContact();
	}

	@Step
	public LoungeIpPerformanceModel grabSCPerformanceIpLogic() {
		return loungePage().grabSCPerformanceIpLogic();
	}
	
	@Step
	public void checkIfBorrowLinkIsDisplayed(boolean isDisplayed) {
		loungePage().checkIfBorrowLinkIsDisplayed(isDisplayed);

	}

	@Step
	public void goToLoungePage() {
		loungePage().goToLoungeList();
		
	}
	
	@Step
	public void checkIfBorrowBoxIsdisplayed(boolean isDisplayed) {
		loungePage().checkIfBorrowBoxIsDisplayed(isDisplayed);
		
	}

	@Step
	public void verifyBorrowBlockStatus(String status) {
		loungePage().verifyBorrowBlockStatus(status);
		
	}

	public void verifyBorrowBlockMessage(String allowedMessage) {
		loungePage().verifyBorrowBlockMessage(allowedMessage);
		
	}

	public void clickGoToBorrowCartFromLounge() {
		loungePage().clickGoToBorrowCartFromLounge();
		
	}

	public void goToMyBusinessFromCart() {
		loungePage().goToMyBusinessFromCart();
		
	}

	
}
