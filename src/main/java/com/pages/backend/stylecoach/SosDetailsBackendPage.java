package com.pages.backend.stylecoach;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

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

}


