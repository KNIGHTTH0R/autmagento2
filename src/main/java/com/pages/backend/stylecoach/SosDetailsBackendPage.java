package com.pages.backend.stylecoach;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.tools.requirements.AbstractPage;

public class SosDetailsBackendPage extends AbstractPage {

	@FindBy(id = "_sosinfosos_password")
	private WebElement sosPassword;

	@FindBy(css = "_sosinfosos_sync")
	private WebElement allowSosSyncOption;
	
	@FindBy(id = "_sosinforeset_account")
	private WebElement resetAccountButton;
	
	@FindBy(id = "_sosinforeset_sync")
	private WebElement resetContactButton;
	
	

	public void selectAllowSosSync(String syncSos) {
		evaluateJavascript("jQuery.noConflict();");
		Select dropdown = new Select(getDriver().findElement(By.id("_sosinfosos_sync")));
		dropdown.selectByVisibleText(syncSos);
		
		
	}
	
	public String checkPasswordField() {
		String pass = "";
		WebElement passInput = getDriver().findElement(By.cssSelector("#_sosinfosos_password"));
		String textFromPasswordInputBox=passInput.getAttribute("value");
			if (textFromPasswordInputBox.isEmpty()) {
				System.out.println("Input fields is empty");
			}else
			{
				pass=passInput.getAttribute("value");
				System.out.println("Password id:"+pass);
			}
		return pass;
	}
	
	
	public void clickOnResetAccountButton() {
		evaluateJavascript("jQuery.noConflict();");
		element(resetAccountButton).waitUntilVisible();
		resetAccountButton.click();
	}
	
	public boolean checkIsPresentResetAccountButton(){
		boolean isVisible = false;
		if(element(resetAccountButton).isVisible())
		{
			isVisible=true;
			System.out.println("butonul apareeeeeeee"+isVisible);
			Assert.assertTrue("The button was  found", isVisible);
			
		}
		else 
		{
			isVisible=false;
			System.out.println("butonul nu apare"+isVisible);
			Assert.assertFalse("The button was not found", isVisible);
		}
		

		return isVisible;
	}
	
	public boolean checkIsPresentResetContactButton(){
		boolean isVisible = false;
		if(element(resetContactButton).isVisible())
		{
			isVisible=true;
			System.out.println("butonul apareeeeeeee"+isVisible);
			Assert.assertTrue("The button was  found", isVisible);
			
		}
		else 
		{
			isVisible=false;
			System.out.println("butonul nu apare"+isVisible);
			Assert.assertFalse("The button was not found", isVisible);
		}
		

		return isVisible;
	}


}


