package com.steps.external;

import java.util.Set;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.Constants;
import com.tools.requirements.AbstractSteps;

public class FacebookRegistrationSteps extends AbstractSteps{

	private static final long serialVersionUID = -2649339632021723245L;

	
	@StepGroup
	public void goToFacebookLogin(String user, String pass) {
		clickOnFacebookLogin();
		findFrame("Facebook");
		performFacebookLogin(user, pass);
		waitABit(3000);
//		getDriver().switchTo().defaultContent();
	}
	
	@Step
	public void confirmAccessRequest(){
		facebookEMBLoginConfirmPage().clickOnSubmmit();
	}
	
	
	@Step
	public void clickOnFacebookLogin(){
		loginPage().clickOnFacebookSignIn();
	}
	
	@Step
	public void performFacebookLogin(String user, String pass){
		facebookEMBLoginPage().inputUser(user);
		facebookEMBLoginPage().inputPass(pass);
		facebookEMBLoginPage().clickLogin();
	}

	@Step
	public void fillFacebookRegistration(String zipCode, String selectOption, String passsword) {
		waitABit(Constants.TIME_CONSTANT);
		findFrame("Create new account");
		facebookRegistrationFormPage().zipInput(zipCode);
		facebookRegistrationFormPage().countrySelect(selectOption);
		facebookRegistrationFormPage().passwordInput(passsword);
		facebookRegistrationFormPage().noSearchStyleCoach();
		facebookRegistrationFormPage().agreeAndConfirm();
	}
}
