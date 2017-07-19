package com.pages.external.ospm;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class OnlineStylePartyStylistPage extends AbstractPage{

	@FindBy(css = "#login-form input")
	private WebElement inputPassword;
	
	@FindBy(id = "submit-login")
	private WebElement loginBtn;
	
	
	public void insertHostPassword(String hostPass) {
		element(inputPassword).waitUntilVisible();
		inputPassword.clear();
		inputPassword.sendKeys(hostPass);
		
	}

	public void clickOnLoginBtn(){
		element(loginBtn).waitUntilVisible();
		loginBtn.click();
	}

	public void verifyIfInvitedGuestAppersInList(String guestName) {
		boolean found=false;
		List<WebElement> guestList=getDriver().findElements(By.cssSelector(".name-list li"));
		if(guestList.size()!=0){
			if(guestList.get(0).getText().contains(guestName)){
				found=true;
			}
		}
		CustomVerification.verifyTrue("Failure: the guest was not found in guests list", found);
	}
	
	public void verifyInvitationStatus(){
		List<WebElement> status=getDriver().findElements(By.cssSelector(".name-list li .glyphicon-ok"));
		if(status.size()!=0){
			CustomVerification.verifyTrue("Failure: the status is not correct displayed", status.get(0)!=null);
		}else{
			CustomVerification.verifyTrue("Failure: no entries in guest list", false);
		}
		
	}
}
