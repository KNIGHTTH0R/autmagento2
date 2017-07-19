package com.pages.external.ospm;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.tools.CustomVerification;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;

public class OnlineStylePartyGuestPage extends AbstractPage {

	@FindBy(css = ".guestresponse button:nth-child(1)")
	WebElement acceptBtn;

	@FindBy(css = ".btn-fb-login")
	WebElement facebookRegistrationBtn;

	@FindBy(id = "firstname")
	WebElement inputFirstName;

	@FindBy(id = "lastname")
	WebElement inputLastName;

	@FindBy(id = "email")
	WebElement inputEmailAddress;

	@FindBy(css = ".btn.btn-primary.pull-right")
	WebElement saveBtn;

	public void clickOnAcceptInvitationButton() {
		element(acceptBtn).waitUntilVisible();
		acceptBtn.click();

	}

	public void clickOnFbRegistrationBtn() {
		element(facebookRegistrationBtn).waitUntilVisible();
		facebookRegistrationBtn.click();

	}

	public void checkFbFirstName(String firstName) {
		waitABit(4000);
		element(inputFirstName).waitUntilVisible();
		
		CustomVerification.verifyTrue(
				"Failure: Invitee first name does not match expecte" + firstName + "grabbed"
						+ inputFirstName.getAttribute("value"),
				firstName.contentEquals(inputFirstName.getAttribute("value")));

	}

	public void checkFbLastName(String lastName) {
		element(inputLastName).waitUntilVisible();
		CustomVerification.verifyTrue(
				"Failure: Invitee first name does not match expecte" + lastName + "grabbed"
						+ inputLastName.getAttribute("value"),
						lastName.contentEquals(inputLastName.getAttribute("value")));

	}

	public void checkFbEmail(String email) {
		element(inputEmailAddress).waitUntilVisible();
		CustomVerification.verifyTrue(
				"Failure: Invitee first name does not match expecte" + email + "grabbed"
						+ inputEmailAddress.getAttribute("value"),
						email.contentEquals(inputEmailAddress.getAttribute("value")));
	}

	public void selectCountry(String country) {
		Select oSelect = new Select(getDriver().findElement(By.id("country")));
		oSelect.selectByVisibleText(country);
	}

	public void clickOnSaveButton() {
		element(saveBtn).waitUntilVisible();
		saveBtn.click();
	}

}
