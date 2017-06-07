package com.steps.external;

import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import java.util.ArrayList;

import org.openqa.selenium.Keys;

import com.tools.constants.TimeConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.AbstractSteps;

public class FacebookRegistrationSteps extends AbstractSteps {

	private static final long serialVersionUID = -2649339632021723245L;

	@StepGroup
	public void goToFacebookLogin(String user, String pass) {
		clickOnFacebookLogin();
		findFrame("Facebook");
		performFacebookLogin(user, pass);
		waitABit(3000);
	}

	@Step
	public void confirmAccessRequest() {
		facebookEMBLoginConfirmPage().clickOnSubmmit();
	}

	@Step
	public void clickOnFacebookLogin() {
		getDriver().get(MongoReader.getBaseURL());
		headerPage().clickAnmeldenButton();
		loginPage().clickOnFacebookSignIn();
	}

	@Step
	public void performFacebookLogin(String user, String pass) {
		facebookEMBLoginPage().inputUser(user);
		facebookEMBLoginPage().inputPass(pass);
		facebookEMBLoginPage().clickLogin();
	}

	@Step
	public void fillFacebookRegistration(String zipCode, String selectOption, String passsword) {
		waitABit(TimeConstants.TIME_CONSTANT);
		findFrame("Create new account");
		facebookRegistrationFormPage().zipInput(zipCode);
		facebookRegistrationFormPage().countrySelect(selectOption);
		facebookRegistrationFormPage().passwordInput(passsword);
		facebookRegistrationFormPage().noSearchStyleCoach();
		facebookRegistrationFormPage().agreeAndConfirm();
	}
	
	@Step 
	public void loginToFacebookApp(String user,String pass){
		
//		getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
//		ArrayList<String> tabs = new ArrayList<String> (getDriver().getWindowHandles());
//		getDriver().switchTo().window(tabs.get(1)); //switches to new tab
//	
//		getDriver().get("http:\\www.facebook.com");
//		getDriver().manage().window().maximize();
		facebookLoginPage().inputUser(user);
		facebookLoginPage().inputPass(pass);
		facebookLoginPage().clickLogin();
		
	//	getDriver().switchTo().window(tabs.get(0));
	}
	
	@Step 
	public void loginToFacebook(String user,String pass){
		

//	
		getDriver().get("http:\\www.facebook.com");
		getDriver().manage().window().maximize();
		facebookLoginPage().inputUser(user);
		facebookLoginPage().inputPass(pass);
		facebookLoginPage().clickLogin();
		onlineStylePartyManagerPage().closePopUp();
	//	getDriver().switchTo().window(tabs.get(0));
	}
	
	@Step 
	public void accessSettingsOnFacebookDesktopApp(){
		
		getDriver().get("https://www.facebook.com/settings?tab=applications");
		
	//	getDriver().switchTo().window(tabs.get(0));
	}

	@Step
	public void removeThePJDevApp(String appId) {
		waitABit(3000);
		//onlineStylePartyManagerPage().closePopUp();
		facebookLoginPage().removeThePJDevApp(appId);
		
	}

	public void editpermissionsForPJDevApp(String appId) {
		// TODO Auto-generated method stub
		waitABit(3000);
		//onlineStylePartyManagerPage().closePopUp();
		facebookLoginPage().editpermissionsForPJDevApp(appId);
	}
}
