package com.pages.frontend.registration.landing;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class ContactLandingPage extends AbstractPage{
	
	@FindBy(css = "input[value='frau']")
	private WebElement frauRadioButton;
	
	@FindBy(css = "input[value='herr']")
	private WebElement herrRadioButton;
	
	@FindBy(name = "vorname")
	private WebElement firstNameInput;
	
	@FindBy(name = "nachname")
	private WebElement lastNameInput;
	
	@FindBy(name = "email")
	private WebElement emailInput;
	
	@FindBy(name = "plz")
	private WebElement plzInput;
	
	@FindBy(name = "ort")
	private WebElement ortInput;
	
	@FindBy(id = "land")
	private WebElement countrySelect;
	
	@FindBy(name = "tel_ort")
	private WebElement telefoneCodeInput;
	
	@FindBy(name = "tel_ziel")
	private WebElement telefoneInput;
	
	@FindBy(id = "terms")
	private WebElement iAgreeCheckbox;
	
	@FindBy(id = "kostenlos-anmelden")
	private WebElement okButton;
	
	
	public void selectGender(boolean isMale){
		element(frauRadioButton).waitUntilVisible();
		if(isMale){
			frauRadioButton.click();
		}else{
			herrRadioButton.click();
		}
	}
	
	public void inputFirstName(String firstName){
		element(firstNameInput).waitUntilVisible();
		firstNameInput.sendKeys(firstName);
	}

	public void inputLastName(String lastName){
		element(lastNameInput).waitUntilVisible();
		lastNameInput.sendKeys(lastName);
	}
	
	public void inputEmail(String email){
		element(emailInput).waitUntilVisible();
		emailInput.sendKeys(email);
	}
	
	public void inputCity(String city){
		element(ortInput).waitUntilVisible();
		ortInput.sendKeys(city);
	}
	
	public void inputPostCode(String postCode){
		element(plzInput).waitUntilVisible();
		plzInput.sendKeys(postCode);
	}

	public void selectCountry(String country){
		element(countrySelect).waitUntilVisible();
		selectFromDropdown(countrySelect, country);
	}
	
	public void inputTelephoneAreaCode(String telCode){
		element(telefoneCodeInput).waitUntilVisible();
		telefoneCodeInput.sendKeys(telCode);
	}
	
	public void inputTelephone(String telNr){
		element(telefoneInput).waitUntilVisible();
		telefoneInput.sendKeys(telNr);
	}
	
	public void iAgreeCheckbox(){
		element(iAgreeCheckbox).waitUntilVisible();
		iAgreeCheckbox.click();
	}
	
	public void clickOk(){
		element(okButton).waitUntilVisible();
		okButton.click();
	}
	
}
