package com.steps.external.academy;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class LoginAcademySteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	
	@Step
	public void performAcademyLogin(String name,String password){
		navigate("https://staging-academy.pippajean.com");
		loginAcademyPage().clickLoginWithPippajeanAccount();
		loginAcademyPage().inputEmail(name);
		loginAcademyPage().inputPassword(password);
		loginAcademyPage().clickLoginButton();
	}


	public void performLogoutFromAcademy() {
		// TODO Auto-generated method stub
		navigate("https://staging-academy.pippajean.com");
		loginAcademyPage().clickOnLogOut();
	}
}
