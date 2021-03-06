package com.pages.frontend.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class FacebookRegistrationFormPage extends AbstractPage {

	// mandatory fields
	@FindBy(id = "distribution_zip")
	private WebElement zipInput;

	@FindBy(id = "registration-distribution-country")
	private WebElement countrySelect;

	@FindBy(id = "password")
	private WebElement pass1Input;

	@FindBy(id = "confirmation")
	private WebElement pass2Input;

	// checkboxes
	@FindBy(id = "is_subscribed")
	private WebElement isSubscribedCheckbox;

	@FindBy(id = "flag_stylist_parties")
	private WebElement partiesCheckbox;

	@FindBy(id = "flag_stylist_member")
	private WebElement styleCoachCheckbox;

	// Apply to stylecoach
	@FindBy(id = "by_sc_name")
	private WebElement searchStyleRadio;

	@FindBy(id = "by_default")
	private WebElement noSearchStyleRadio;

	// confirm
	@FindBy(id = "accept-checkbox")
	private WebElement acceptButton;

	
	@FindBy(css = ".Personal-Fb p:nth-child(2)")
	private WebElement fbUserName;
	
	
	@FindBy(css = ".Personal-Fb p:nth-child(3)")
	private WebElement fbUserEmail;
	
	
//	@FindBy(css = "button[title='Senden']")
	@FindBy(css = "div.buttons-set.form-buttons.to-the-left button")     //int
	private WebElement submitButton;

	public void zipInput(String zipCode) {
		element(zipInput).waitUntilVisible();
		zipInput.sendKeys(zipCode);
	}

	/**
	 * This is a select web element. a fix value is required, that exists in the
	 * dropdown Accepted values: DE, IT, LU, AT, ES
	 * 
	 * @param selectOption
	 */
	public void countrySelect(String selectOption) {
		element(countrySelect).waitUntilVisible();
		element(countrySelect).selectByValue(selectOption);
	}

	/**
	 * Will write the password in both fields, password and confirmation fields.
	 * 
	 * @param passsword
	 */
	public void passwordInput(String passsword) {
		element(pass1Input).waitUntilVisible();
		pass1Input.sendKeys(passsword);
		element(pass2Input).waitUntilVisible();
		pass2Input.sendKeys(passsword);
	}

	public void setNewsletter() {
		element(isSubscribedCheckbox).waitUntilVisible();
		isSubscribedCheckbox.click();
	}

	/**
	 * Form will be displayed for contact details
	 */
	public void setHostParties() {
		element(partiesCheckbox).waitUntilVisible();
		partiesCheckbox.click();
	}

	public void setBeStyleCoach() {
		element(styleCoachCheckbox).waitUntilVisible();
		styleCoachCheckbox.click();
	}

	public void searchStyleCoach(String firstName, String lastName) {
		searchStyleRadio.click();

		getDriver().findElement(By.id("search_firstname")).sendKeys(firstName);
		getDriver().findElement(By.id("search_lastname")).sendKeys(lastName);
		getDriver().findElement(By.cssSelector("button[name='search_by_name_submit']")).click();
	}

	public void noSearchStyleCoach() {
		noSearchStyleRadio.click();
	}

	public void agreeAndConfirm() {
		acceptButton.click();
		submitButton.click();
	}

	public void verifyFbUserName(String fbName) {
		element(fbUserName).waitUntilVisible();
		CustomVerification.verifyTrue("Failure: The User Name is not displayed correctly in registration form", fbUserName.getText().contains(fbName));
	}
	
	public void verifyFbUserEmail(String fbEmail) {
		// TODO Auto-generated method stub
		element(fbUserEmail).waitUntilVisible();
		CustomVerification.verifyTrue("Failure: The User Email is not displayed correctly in registration form", fbUserEmail.getText().contains(fbEmail));
	}
}
