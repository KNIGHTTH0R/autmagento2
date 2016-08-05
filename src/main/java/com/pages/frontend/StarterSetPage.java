package com.pages.frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pages.frontend.checkout.cart.stylistRegistration.StylistRegistrationCartTotalModel;
import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
import com.tools.datahandler.StylistRegDataGrabber;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

import net.serenitybdd.core.annotations.findby.FindBy;

public class StarterSetPage extends AbstractPage {

	@FindBy(css = ".text-center >.big-btn.gold-btn.bordered")
	private WebElement jetztStyleCoachWerdenButton;

	@FindBy(css = "div.starter-sets.clearfix.gold-bg div:nth-child(4) a")
	private WebElement jetztStartenFromStarterSet;

	@FindBy(css = "input[name='shipping[house_number]']")
	private WebElement houseNumberInput;

	@FindBy(css = "input[name='shipping[postcode]']")
	private WebElement postcodeInput;

	@FindBy(css = "input[name='shipping[city]']")
	private WebElement cityInput;

	@FindBy(css = "input#same_as_billing")
	private WebElement sameAsBilling;

	@FindBy(css = "button#submit-step")
	private WebElement toPaymentButton;

	@FindBy(id = "submit-step")
	private WebElement submitStepButton;
	
	@FindBy(id = "coupon_code")
	private WebElement coucherInput;
	
	@FindBy(css = "#voucher-button-active button")
	private WebElement submitCodeButton;

	public void submitStep() {
		element(submitStepButton).waitUntilVisible();
		submitStepButton.click();
	}
	public void submitVoucherCode() {
		element(submitCodeButton).waitUntilVisible();
		submitCodeButton.click();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}
	
	public void inputVoucherCode(String code) {
		element(coucherInput).waitUntilVisible();
		coucherInput.clear();
		coucherInput.sendKeys(code);
	}

	public void clickGoToPaymentMethod() {
		element(toPaymentButton).waitUntilVisible();
		waitFor(ExpectedConditions.elementToBeClickable(toPaymentButton));
		toPaymentButton.click();
	}

	public void inputStreetNumber(String streetNumber) {
		element(houseNumberInput).waitUntilVisible();
		houseNumberInput.clear();
		houseNumberInput.sendKeys(streetNumber);
	}

	public void inputPostCode(String postCode) {
		element(postcodeInput).waitUntilVisible();
		postcodeInput.clear();
		postcodeInput.sendKeys(postCode);
	}

	public void inputHomeTown(String homeTown) {
		element(cityInput).waitUntilVisible();
		cityInput.clear();
		cityInput.sendKeys(homeTown);
	}

	public void setSameAsBilling(boolean checked) {

		boolean isSelected = sameAsBilling.isSelected();

		if ((checked && !isSelected) || (!checked && isSelected))
			sameAsBilling.click();

		waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

	public void clickOnJetztStyleCoachWerdenButton() {
		element(jetztStyleCoachWerdenButton).waitUntilVisible();
		jetztStyleCoachWerdenButton.click();
	}

	public void clickOnJetztStartenFromStarterSet() {
		element(jetztStartenFromStarterSet).waitUntilVisible();
		jetztStartenFromStarterSet.click();
	}

	public void selectStarterKit(String starterKitId) {
		waitFor(ExpectedConditions.presenceOfElementLocated(By.cssSelector(starterKitId)));
		elementjQueryClick(starterKitId);
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}

	public StylistRegistrationCartTotalModel grabCartTotal(boolean isVoucherApplied) {
		
		waitABit(TimeConstants.TIME_CONSTANT);

		StylistRegistrationCartTotalModel result = new StylistRegistrationCartTotalModel();

		result.setDelivery(FormatterUtils.parseValueToTwoDecimals(getDriver().findElement(By.cssSelector("#shipping-value")).getText()));
		result.setTotalPrice(FormatterUtils.parseValueToTwoDecimals(getDriver().findElement(By.cssSelector("#total-price-value")).getText()));
		if (isVoucherApplied) {
			result.setVoucher(FormatterUtils.parseValueToTwoDecimals(getDriver().findElement(By.cssSelector("#voucher-value")).getText()));
		}
		StylistRegDataGrabber.cartTotals = result;
		
		return result;

	}

}
