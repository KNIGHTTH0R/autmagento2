package com.pages.frontend.checkout.payment;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.assertj.core.api.InputStreamAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

/**
 * Main page from payment package
 * 
 * @author voicu.vac
 *
 */
public class PaymentPage extends AbstractPage {

	@FindBy(css = "input.imgB.pmB.pmBcard")
	private WebElement creditCardContainer;

	@FindBy(css = "input[value*='transfer']")
	private WebElement bankTransfer;

	@FindBy(css = "#paymentMethods li input.imgB.pmB.pmBbankTransfer_IBAN")
	private WebElement bankTransferEs;

	@FindBy(css = "#paymentMethods li input.imgB.pmB.pmBelv")
	private WebElement elvContainer;

	@FindBy(css = "input.paySubmit.paySubmitbankTransfer_DE")
	private WebElement confirmPayBankTransfer;

	@FindBy(css = "input.paySubmit.paySubmitbankTransfer_IBAN")
	private WebElement confirmPayBankTransferEs;

	@FindBy(id = "card.cardNumber")
	private WebElement cardNumberInput;

	@FindBy(id = "bankAccountNumber")
	private WebElement elvBankAccountInputInput;

	@FindBy(css = "#paymentMethods li input.imgB.pmB.pmBsepadirectdebit")
	private WebElement sepaContainer;

	@FindBy(id = "sepadirectdebit.ownerName")
	private WebElement sepaNameInput;

	@FindBy(css = "#paymentMethods li[data-variant*='klarna']")
	private WebElement klarnaContainer;

	@FindBy(id = "klarna.shopper.firstName")
	private WebElement klarnaInput;

	@FindBy(id = "mainSubmit")
	private WebElement weiterBtn;

	@FindBy(id = "mainBack")
	private WebElement backButton;

	@FindBy(css = ".payment-method._active div[name*='.company'] input")
	private WebElement inputCompany;

	@FindBy(css = ".payment-method._active div[name*='.street']:nth-child(1) input")
	private WebElement inputStreet;

	@FindBy(css = ".payment-method._active div[name*='.city'] input")
	private WebElement inputCity;

	@FindBy(css = ".payment-method._active div[name*='.region_id'] select")
	private WebElement regionDropdown;

	@FindBy(css = ".payment-method._active div[name*='.postcode'] input")
	private WebElement inputPostCode;

	@FindBy(css = ".payment-method._active div[name*='.country'] select")
	private WebElement contryDropdown;

	@FindBy(css = ".payment-method._active div[name*='.telephone'] input")
	private WebElement inputPhoneNumber;
	
	@FindBy(css = ".payment-method._active .action.action-update")
	private WebElement updateBtn;
	
	
	

	public void expandCreditCardForm() {
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		element(creditCardContainer).waitUntilVisible();
		creditCardContainer.click();
	}

	public boolean isCreditCardFormExpended() {
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		return element(cardNumberInput).isCurrentlyVisible();
	}

	public void goBack() {
		element(backButton).waitUntilVisible();
		backButton.click();
		// waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
		// ContextConstants.LOADING_MESSAGE));
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));

		waitABit(5000);
	}

	public void expandBankTransferForm() {
		element(bankTransfer).waitUntilVisible();
		bankTransfer.click();
	}

	public void expandBankTransferFormEs() {
		element(bankTransferEs).waitUntilVisible();
		bankTransferEs.click();
	}

	public void confirmPayBankTransfer() {
		element(confirmPayBankTransfer).waitUntilVisible();
		confirmPayBankTransfer.click();
	}

	public void confirmPayBankTransferEs() {
		element(confirmPayBankTransferEs).waitUntilVisible();
		confirmPayBankTransferEs.click();
	}

	public void expandElvForm() {
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		element(elvContainer).waitUntilVisible();
		elvContainer.click();
	}

	public boolean isElvFormExpended() {
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		return element(elvBankAccountInputInput).isCurrentlyVisible();
	}

	public void expandSepaForm() {
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		element(sepaContainer).waitUntilVisible();
		sepaContainer.click();
	}

	public boolean isSepaFormExpended() {
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		return element(sepaNameInput).isCurrentlyVisible();
	}

	public void expandKlarnaForm() {
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		element(klarnaContainer).waitUntilVisible();
		klarnaContainer.click();
	}

	public boolean isKlarnaFormExpended() {
		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
				ContextConstants.LOADING_MESSAGE));
		return element(klarnaInput).isCurrentlyVisible();
	}

	public boolean isKlarnaAvailable() {
		// if (element(klarnaContainer).isVisible())
		return element(klarnaContainer).isVisible();

	}

	public void clickOnWeiterBtn() {
		element(weiterBtn).waitUntilVisible();
		weiterBtn.click();
	}

	public void insertCompany(String company) {
		element(inputCompany).waitUntilVisible();
		inputCompany.sendKeys(company);
	}

	public void insertStreetAddress(String streetAddress) {
		element(inputStreet).waitUntilVisible();
		inputStreet.sendKeys(streetAddress);
	}

	public void insertCity(String city) {
		element(inputCity).waitUntilVisible();
		inputCity.sendKeys(city);
	}

	public void insertState(String state) {
		element(regionDropdown).waitUntilVisible();
		element(regionDropdown).selectByVisibleText(state);
	}

	public void insertPostalCode(String zip) {
		scrollPageDown();
		element(inputPostCode).waitUntilVisible();
		inputPostCode.sendKeys(zip);
	}

	public void insertPhoneNumber(String phoneNo) {
		element(inputPhoneNumber).waitUntilVisible();
		inputPhoneNumber.sendKeys(phoneNo);		
	}

	public void insertCountry(String country) {
		element(contryDropdown).waitUntilVisible();
		element(contryDropdown).selectByVisibleText(country);		
	}
	
	public void clickUpdateButton() {
		element(updateBtn).waitUntilVisible();
		updateBtn.click();
	}

}
