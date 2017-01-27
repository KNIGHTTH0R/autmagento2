package com.pages.backend.stylecoach;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.tools.constants.ContextConstants;
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
	
	@FindBy(css = "#customer_info_tabs_customer_edit_tab_view_content>.entry-edit:nth-child(2)")
	private List<WebElement> styleCoachProfilList;
	
	@FindBy(css = ".success-msg ul li span")
	private WebElement textSuccessContainer;
	

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
			Assert.assertTrue("The button was  found", isVisible);
			
		}
		else 
		{
			isVisible=false;
			Assert.assertFalse("The button was not found", isVisible);
		}
		

		return isVisible;
	}
	
	public boolean checkIsPresentResetContactButton(){
		boolean isVisible = false;
		if(element(resetContactButton).isVisible())
		{
			isVisible=true;
			Assert.assertTrue("The button was  found", isVisible);
			
		}
		else 
		{
			isVisible=false;
			Assert.assertFalse("The button was not found", isVisible);
		}
		

		return isVisible;
	}

	public String grabStyleCoachID() {
		
		WebElement styleCoachProfil=styleCoachProfilList.get(0);
		
		List<WebElement> stylistProfyleRows=styleCoachProfil.findElements(By.cssSelector("fieldset .box-left tbody tr"));
		WebElement searchedLine=getStyleCoachProfileRow(stylistProfyleRows,"Stylecoach ID:");
		
		String stylistID = searchedLine.findElement(By.cssSelector("td:nth-child(2)")).getText();
		
		return stylistID;
		
	}

	
	private WebElement getStyleCoachProfileRow(List<WebElement> rows,String sku) {
	
		List<WebElement> lines =  rows;
		WebElement searchedLine = null;

		boolean found = false;
		if (!lines.isEmpty()) {
			for (WebElement row : lines) {
				if (row.getText().contains(sku)) {
					searchedLine = row;
					found = true;
					break;
				}
			}
			Assert.assertTrue("The row was not found", found);
		} else {
			Assert.assertTrue("the table is empty and should not be", true);
			
		}

		return searchedLine;

	}

	public void clickOnResetContactsButton() {
		
		evaluateJavascript("jQuery.noConflict();");
		element(resetContactButton).waitUntilVisible();
		resetContactButton.click();
		
	}

	public void validateSuccessMessage() {
		waitForPageToLoad();
		element(textSuccessContainer).waitUntilVisible();
		Assert.assertTrue("Failure: Email notification text was not found. ", textSuccessContainer.getText().contains("The contacts of the SOS user were reseted!"));
		
		
		
	}

	public void validateResetAccountSuccessMessage() {
		// TODO Auto-generated method stub
		waitForPageToLoad();
		element(textSuccessContainer).waitUntilVisible();
		Assert.assertTrue("Failure: Email notification text was not found. ", textSuccessContainer.getText().contains("The SOS user of the customer was successfully reseted!"));
		
	}

}


