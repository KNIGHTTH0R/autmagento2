package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tools.requirements.AbstractPage;

public class LoungePage extends AbstractPage {

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2)")
	private WebElement meinBusinessButton;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) a")
	private WebElement meinBusinessLink;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(1) > ul li:nth-child(2) a")
	private WebElement createPartyButton;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(1)")
	private WebElement stylePartiesLink;
	
	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(2)")
	private WebElement myContactsLink;

	@FindBy(css = "ul.main-nav.type-1.clearfix.logged-in > li:nth-child(2) > ul > li:nth-child(2) > ul li:nth-child(2) a")
	private WebElement addNewContactLink;

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
	
	public void clickAddContact() {
		element(addContact).waitUntilVisible();
		addContact.click();
	}

	public void typeContactName(String name) {
		element(contactInput).waitUntilVisible();
		contactInput.sendKeys(name);
		element(selectContact).waitUntilVisible();
		element(selectContact).click();
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

		Actions builder = new Actions(getDriver());

		builder.moveToElement(meinBusinessButton).build().perform();
		builder.moveToElement(stylePartiesLink).build().perform();

		element(createPartyButton).waitUntilVisible();
		createPartyButton.click();

	}

	public void clickOrderForCustomer() {

		Actions builder = new Actions(getDriver());

		builder.moveToElement(meinBusinessButton).build().perform();
		builder.moveToElement(customerOrderLink).build().perform();

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
	


}
