package com.pages.frontend.registration.landing;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.requirements.AbstractPage;

/**
 * This page is the second page, after the registration form on the landing page
 * has been confirmed. Style coach allocation is selected on this page. You can
 * choose a specific StyleCoach or one that is provided by Pippa Jean (default)
 * 
 * @author voicu.vac
 *
 */
public class LandingCustomerAllocationPage extends AbstractPage {

	@CacheLookup
	@FindBy(id = "by_sc_name")
	private WebElement radioSelectByName;

	@FindBy(id = "search_firstname")
	private WebElement firstNameInput;

	@FindBy(id = "search_lastname")
	private WebElement lastNameInput;

	@FindBy(name = "search_by_name_submit")
	private WebElement searchSubmitButton;

	@CacheLookup
	@FindBy(id = "by_default")
	private WebElement radioSelectByDefault;
	
	@FindBy(id = "kostenlos-anmelden")
	private WebElement submitButton;

	public enum StyleMode {
		CustomStylist, DefaultStylist
	}

	public void selectStylistOption(StyleMode mode, String firstName, String lastName) {
		element(radioSelectByDefault).waitUntilVisible();

		switch (mode) {
		case CustomStylist:
			radioSelectByName.click();
			element(firstNameInput).waitUntilEnabled();
			if (!firstName.isEmpty() && !lastName.isEmpty()) {
				firstNameInput.sendKeys(firstName);
				lastNameInput.sendKeys(lastName);
			}
			searchSubmitButton.click();

			break;
		case DefaultStylist:
			radioSelectByDefault.click();
			break;

		default:
			break;
		}

	}
	
	public void submitAndContinue(){
		element(submitButton).waitUntilVisible();
		submitButton.click();	
	}
}
