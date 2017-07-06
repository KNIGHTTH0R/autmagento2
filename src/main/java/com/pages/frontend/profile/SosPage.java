package com.pages.frontend.profile;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

public class SosPage extends AbstractPage {

	@FindBy(css = "div.my-account dl dd:nth-child(2)")
	private WebElement userEmail;

	@FindBy(css = "div.my-account dl dd:nth-child(4)")
	private WebElement sosPassword;
	
	@FindBy(className = "size14")
	private WebElement messageContainer;

	
	public String getUsername() {
		return userEmail.getText();
	}

	public String getSosPassword() {
		return sosPassword.getText();
	}
	
	public boolean checkUsernameField() {
		boolean isVisible = false;
		if(element(userEmail).isVisible())
		{
			isVisible=true;
			System.out.println("userEmail apare"+isVisible);
			Assert.assertTrue("userEmail was  found", isVisible);
			
		}
		else 
		{
			isVisible=false;
			System.out.println("userEmail nu apare"+isVisible);
			Assert.assertFalse("userEmail was not found", isVisible);
		}
		return isVisible;
	}
	
	public boolean checkSosPassword(){
		boolean isVisible = false;
		if(element(sosPassword).isVisible())
		{
			isVisible=true;
			System.out.println("sos password apare"+isVisible);
			Assert.assertTrue("Sos pass was  found", isVisible);
			
		}
		else 
		{
			isVisible=false;
			System.out.println("sos password nu apare"+isVisible);
			Assert.assertFalse("Sos pass was not found", isVisible);
		}
		

		return isVisible;
	}

	public void verifySosMessage() {

//		Wait<WebDriver> wait = new WebDriverWait(getDriver(), 30);
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
		waitFor(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));

		
		// withTimeoutOf(5,
		// TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
		element(messageContainer).waitUntilVisible();
		Assert.assertTrue("Failure: Success message has not been found.",
				messageContainer.getText().contains(ContextConstants.SOS_MESSAGE));
	}
	


}
