package com.pages.frontend;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
import com.tools.data.frontend.LoungeIpPerformanceModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.PrintUtils;

import net.serenitybdd.core.annotations.findby.FindBy;

public class LoungePage extends AbstractPage {

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2)")
	private WebElement meinBusinessButton;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) a")
	private WebElement meinBusinessLink;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(1) > ul li:nth-child(2) a")
	private WebElement createPartyButton;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(1) > ul li:nth-child(1) a")
	private WebElement partyListButton;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(1)")
	private WebElement stylePartiesLink;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(2)")
	private WebElement myContactsLink;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(2) > ul li:nth-child(2) a")
	private WebElement addNewContactLink;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(2) > ul li:nth-child(1) a")
	private WebElement contactsListLink;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(3)")
	private WebElement customerOrderLink;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(6)")
	private WebElement loanedLink;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(3) > ul li:nth-child(1) a")
	private WebElement startOrderForCustomerLink;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(6) > ul li:nth-child(1) a")
	private WebElement borrowCartLink;

	@FindBy(id = "contact")
	private WebElement contactInput;

	@FindBy(id = "create-order")
	private WebElement createOrder;

	@FindBy(css = "ul li.ui-menu-item a")
	private WebElement selectContact;

	@FindBy(css = "li.error-msg ul li span")
	private WebElement errorMessageContainer;

	@FindBy(id = "addContact")
	private WebElement addContact;

	@FindBy(css = ".performance-table")
	private WebElement performanceTable;

	public void clickAddContact() {
		element(addContact).waitUntilVisible();
		addContact.click();
	}

	public void typeContactName(String name) {
		element(contactInput).waitUntilVisible();
		contactInput.sendKeys(name);
		waitABit(TimeConstants.TIME_CONSTANT);
		element(selectContact).waitUntilVisible();
		if (selectContact.getText().contentEquals(name)) {
			element(selectContact).click();
			waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
					ContextConstants.LOADING_MESSAGE));
			waitABit(TimeConstants.WAIT_TIME_SMALL);
		} else {
			Assert.fail("The contact was not found");
		}
	}

	public void startOrderForCustomer() {
		element(createOrder).waitUntilVisible();
		createOrder.click();
	}

	public void goToMyBusiness() {
		element(meinBusinessButton).waitUntilVisible();
		meinBusinessLink.click();
	}

	public void clickCreateParty() {
		
		scrollToPageTop();

		Actions builder = new Actions(getDriver());

		builder.moveToElement(meinBusinessButton).build().perform();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		builder.moveToElement(stylePartiesLink).build().perform();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		element(createPartyButton).waitUntilVisible();
		
	//	waitABit(3000);
		createPartyButton.sendKeys(Keys.RETURN);
		//createPartyButton.click();
		
		
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		
		
	}

	public void clickGoToPartyList() {

		Actions builder = new Actions(getDriver());

		builder.moveToElement(meinBusinessButton).build().perform();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		builder.moveToElement(stylePartiesLink).build().perform();
		waitABit(TimeConstants.WAIT_TIME_SMALL);

		element(partyListButton).waitUntilVisible();
		partyListButton.click();

	}

	public void clickOrderForCustomer() {

		Actions builder = new Actions(getDriver());

		builder.moveToElement(meinBusinessButton).build().perform();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		builder.moveToElement(customerOrderLink).build().perform();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		element(startOrderForCustomerLink).waitUntilVisible();
		startOrderForCustomerLink.click();

	}

	public void clickGoToBorrowCart() {

		Actions builder = new Actions(getDriver());

		builder.moveToElement(meinBusinessButton).build().perform();
		builder.moveToElement(loanedLink).build().perform();

		element(borrowCartLink).waitUntilVisible();
		borrowCartLink.click();

	}

	public void goToToAddNewContact() {

		Actions builder = new Actions(getDriver());

		builder.moveToElement(meinBusinessButton).build().perform();
		builder.moveToElement(myContactsLink).build().perform();

		element(addNewContactLink).waitUntilVisible();
		addNewContactLink.click();

	}

	public void goToContactsList() {

		Actions builder = new Actions(getDriver());

		builder.moveToElement(meinBusinessButton).build().perform();
		builder.moveToElement(myContactsLink).build().perform();

		element(contactsListLink).waitUntilVisible();
		contactsListLink.click();

	}

	public LoungeIpPerformanceModel grabSCPerformanceIpLogic() {

		LoungeIpPerformanceModel result = new LoungeIpPerformanceModel();

		WebElement performanceTable = getDriver().findElement(By.cssSelector(".performance-table tbody"));
		result.setCareerLevel(performanceTable.findElement(By.cssSelector("tr:nth-child(1) td:nth-child(2)")).getText());
		result.setPayLevel(performanceTable.findElement(By.cssSelector("tr:nth-child(2) td:nth-child(2)")).getText());
		result.setIndividualPoints(performanceTable.findElement(By.cssSelector("tr:nth-child(3) td:nth-child(2)")).getText());
		result.setUnsafeIndividualPoints(performanceTable.findElement(By.cssSelector("tr:nth-child(4) td:nth-child(2)")).getText());
		result.setTeamPoints(performanceTable.findElement(By.cssSelector("tr:nth-child(5) td:nth-child(2)")).getText());
 
		PrintUtils.printLoungeIpPerformanceModel(result);
		
		return result;
	}

}
