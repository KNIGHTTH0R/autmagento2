package com.pages.frontend;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.tools.CustomVerification;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class PartyHostGuestPage extends AbstractPage {

	@FindBy(css = "input[type*='password']")
	private WebElement inputPassword;

	@FindBy(id = "submit-login")
	private WebElement submitLoginBtn;

	@FindBy(css = "input[placeHolder*='Vorname']")
	private WebElement inputGuestFirstName;

	@FindBy(css = "input[placeHolder*='Nachname']")
	private WebElement inputGuestLastName;
	
	@FindBy(css = "input[placeHolder*='E-Mail Adresse']")
	private WebElement inputGuestEmail;

	@FindBy(css = ".guest-add-container a")
	private WebElement inviteGuestBtn;

	@FindBy(css = ".guest-add-container button")
	private WebElement saveBtn;
	
	@FindBy(css = ".guest-email-container a")
	private WebElement sendEmailBtn;
	
	@FindBy(css = ".guest-link-container a")
	private WebElement clickOnGuestPageBtn;
	
	@FindBy(css = ".col-md-6.text-center button:nth-child(1)")
	private WebElement clickAcceptInviteBtn;
	
	@FindBy(css = ".col-md-6.text-center button:nth-child(2)")
	private WebElement clickDeclineInviteBtn;
	
	
	@FindBy(css = ".btn-primary.pull-right")
	private WebElement saveGuestForm;
	
	
	@FindBy(css = ".jewelry a")
	private WebElement clickRegistrationLink;
	
	@FindBy(css = ".btn-fb-login")
	private WebElement clickFbBtn;
	
	
	
//	/
	
	
	

	public void inserPassword(String password) {
		element(inputPassword).waitUntilVisible();
		inputPassword.clear();
		inputPassword.sendKeys(password);
	}

	public void clickLoginBtn() {
		element(submitLoginBtn).waitUntilVisible();
		submitLoginBtn.click();
	}

	public void insertGuestFirstName(String firstname) {
		element(inputGuestFirstName).waitUntilVisible();
		inputGuestFirstName.clear();
		inputGuestFirstName.sendKeys(firstname);
	}
	

	public void insertGuestLastName(String lastname) {
		element(inputGuestLastName).waitUntilVisible();
		inputGuestLastName.clear();
		inputGuestLastName.sendKeys(lastname);
	}

	public void insertGuestEmail(String email) {
		element(inputGuestEmail).waitUntilVisible();
		inputGuestEmail.clear();
		inputGuestEmail.sendKeys(email);
		
	}
	
	public void expendInviteGuestForm() {
		scrollPageDown();
		scrollPageDown();
	//	element(inviteGuestBtn).waitUntilVisible();
		waitABit(2000);
		clickElement(inviteGuestBtn);
		//inviteGuestBtn.click();
		System.out.println("Am ajuns si aici");
	}

	public void clickSaveBtn() {
		element(saveBtn).waitUntilVisible();
		saveBtn.click();
	}

	public void verifyGuestIsDisplayedOnHostPage(String firstname, String lastname,boolean isApproved) {
		boolean found = false;
		List<WebElement> status=new ArrayList<WebElement>();
		scrollToPageTop();
		waitABit(3000);
		List<WebElement> guestList = getDriver().findElements(By.cssSelector(".name-list li"));
		for (WebElement guest : guestList) {
			status=guest.findElements(By.cssSelector("i.glyphicon.glyphicon-ok"));
			if (guest.findElement(By.cssSelector("span")).getText()
					.contentEquals(transformString(firstname, lastname))) {
				found = true;
				break;
			}
		}
		if(isApproved==false){
			CustomVerification.verifyTrue(
					"Failire: The guest Status is not coorect, expected isApproved= " + isApproved , status.size()==0);
		}
		if(isApproved==true){
			CustomVerification.verifyTrue(
					"Failire: The guest Status is not coorect, isApproved= " + isApproved , status.size()==1);
		}

		CustomVerification.verifyTrue(
				"Failire: The Guest was not find in guest list,expected:" + firstname + " " + lastname, found);

	}
	
	public void verifyGuestInviteStatusonGuestList(String firstname, String lastname) {
		boolean found = false;
		scrollToPageTop();
		List<WebElement> guestList = getDriver().findElements(By.cssSelector(".name-list li"));
		for (WebElement guest : guestList) {
			if (guest.findElement(By.cssSelector("span")).getText()
					.contentEquals(transformString(firstname, lastname))) {
				found = true;
			}
		}

		CustomVerification.verifyTrue(
				"Failire: The Guest was not find in guest list,expected:" + firstname + " " + lastname, found);

	}

	public static String transformString(String firstname, String lastname) {
		String guestName = "";

		String first = firstname.substring(0, 1).toUpperCase() + firstname.substring(1).toLowerCase();
		String last = lastname.substring(0, 1).toUpperCase() + lastname.substring(1).toLowerCase();
		guestName = first.concat(" " + last.substring(0, 1) + ".");
		System.out.println("guestName "+guestName);
		return guestName;

	}

	public static void main(String[] args) {
		System.out.println(transformString("vasile", "Laurentiy"));
	}

	public void hostDeclineGuestInvitation(String firstname, String lastname) {
		boolean found = false;
		scrollToPageTop();
		waitABit(3000);
		List<WebElement> guestList = getDriver().findElements(By.cssSelector(".name-list li"));
		for (WebElement guest : guestList) {
			if (guest.findElement(By.cssSelector("span")).getText()
					.contentEquals(transformString(firstname, lastname))) {
				found = true;
				guest.findElement(By.cssSelector("i")).click();
				break;
			}
		}
		CustomVerification.verifyTrue(
				"Failire: The Guest was not find in guest list,expected:" + firstname + " " + lastname, found);
	}

	public void sendGuestInvitationByEmail() {
		
		element(sendEmailBtn).waitUntilVisible();
		clickElement(sendEmailBtn );
		waitABit(3000);
		
		WebElement successMsg=getDriver().findElements(By.cssSelector(".guest-email-container div")).get(0);
		CustomVerification.verifyTrue("Failure: The send email success message is not displayed", successMsg.getText().contains("Die E-Mail wurde dir zugesendet. Du kannst sie nun weiterleiten."));
	}

	public void openGuestPartyPage() {
		element(clickOnGuestPageBtn).waitUntilVisible();
		clickOnGuestPageBtn.click();
	}

	public void acceptInvitation() {
		element(clickAcceptInviteBtn).waitUntilVisible();
		clickAcceptInviteBtn.click();
	}
	
	public void declineInvitation() {
		element(clickDeclineInviteBtn).waitUntilVisible();
		clickDeclineInviteBtn.click();		
	}

	public void selectGuestCountry(String country) {
		Select oSelect = new Select(getDriver().findElement(By.id("country")));		
		oSelect.selectByVisibleText(country);
	}

	public void clickOnSaveBtn() {
		element(saveGuestForm).waitUntilVisible();
		saveGuestForm.click();
		waitABit(3000);
	}

	public void clickOnCustomerRegistrationLink() {
		element(clickRegistrationLink).waitUntilVisible();
		clickRegistrationLink.click();
				
	}

	public void clickRegisterWithFB() {
		element(clickFbBtn).waitUntilVisible();
		clickFbBtn.click();
	}

	public void verifyFirstName(String firstName) {
		WebElement guestFirstName=getDriver().findElements(By.cssSelector("input[id*='firstname']")).get(0);
		CustomVerification.verifyTrue(
				"Failure: The Guest Last name is not displayed coretly exoected:" + firstName + " actual:"
						+ guestFirstName.getAttribute("value"),
						guestFirstName.getAttribute("value").contentEquals(firstName));
		
	}

	public void verifyLastName(String lastName) {
		WebElement guestLastName=getDriver().findElements(By.cssSelector("input[id*='lastname']")).get(0);
		CustomVerification.verifyTrue(
				"Failure: The Guest Last name is not displayed coretly exoected:" + lastName + " actual:"
						+ guestLastName.getAttribute("value"),
						guestLastName.getAttribute("value").contentEquals(lastName));		
	}

	public void verifyEmailName(String emailName) {
		WebElement guestEmail=getDriver().findElements(By.cssSelector("input[id*='email']")).get(0);
		CustomVerification.verifyTrue(
				"Failure: The Guest Email name is not displayed coretly exoected:" + emailName + " actual:"
						+ guestEmail.getAttribute("value"),
						guestEmail.getAttribute("value").contentEquals(emailName));
		
	}

	

	
	
}
