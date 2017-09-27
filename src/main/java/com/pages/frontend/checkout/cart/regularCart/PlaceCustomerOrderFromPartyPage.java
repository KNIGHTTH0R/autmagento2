package com.pages.frontend.checkout.cart.regularCart;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class PlaceCustomerOrderFromPartyPage extends AbstractPage {

	@FindBy(id = "contact")
	private WebElement contactInput;

	@FindBy(id = "create-order")
	private WebElement createOrder;

	@FindBy(css = "ul li.ui-menu-item a")
	private WebElement selectContact;

	@FindBy(css = "li.error-msg ul li span")
	private WebElement errorMessageContainer;

	public boolean typeContactName(String name) {
		boolean found=false;
		element(contactInput).waitUntilVisible();
		contactInput.clear();
		contactInput.sendKeys(name);
		waitABit(TimeConstants.TIME_MEDIUM);
		List<WebElement> contacts=getDriver().findElements(By.cssSelector("ul li.ui-menu-item a"));
		
		System.out.println("contacts.size()" +contacts.size());
		if(contacts.size()!=0){
			
			if (selectContact.getText().contentEquals(name)) {
				found=true;
				element(selectContact).click();
				waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
						ContextConstants.LOADING_MESSAGE));
				waitABit(TimeConstants.TIME_MEDIUM);
			} else {
				Assert.fail("The contact was not found");
			}
		}else{
			System.out.println("Failure : Searched contact does not exist");
			
		}
		return found;
		

	}

	public void startOrderForCustomer() {
		element(createOrder).waitUntilVisible();
		clickElement(createOrder);
//		createOrder.click();
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementWithText(
				By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
		waitABit(TimeConstants.TIME_MEDIUM);
	}

	
	public void verifyCustomerIsNotSuitableForTheOrderErrorMessage() {
			waitABit(5000);
			Assert.assertTrue("The error message should be present !!!",
					errorMessageContainer.getText().contains(ContextConstants.ORDER_FOR_WRONG_CUSTOMER_ERROR_MESSAGE));
		
		
	}
}
