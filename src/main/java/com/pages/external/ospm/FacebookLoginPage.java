package com.pages.external.ospm;

import net.serenitybdd.core.annotations.findby.FindBy;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
import com.tools.requirements.AbstractPage;

/**
 * This page will contain mapping from the pop-up facebook login window.
 * 
 * @author voicu.vac
 *
 */
public class FacebookLoginPage extends AbstractPage {

	@FindBy(id = "email")
	private WebElement userNameInput;

	@FindBy(id = "pass")
	private WebElement userPassInput;

	@FindBy(id = "loginbutton")
	private WebElement loginButton;

	@FindBy(id = "app-id-390416157958904")
	private List<WebElement> appPfDev;


	
	
	@FindBy(css = "._pig div label span span")
	private List<WebElement> accessGroupYouManageOption;
//	@FindBy(css = "input[value*='user_managed_groups']")
//	private WebElement accessGroupYouManageOption;
	
	
	@FindBy(css = "input[value*='Remove']")
	private WebElement confirmRemoveApp;
	
	
	@FindBy(css = ".uiOverlayFooterButtons button")
	private WebElement saveButton;

	
	
	public void inputUser(String user) {
		element(userNameInput).waitUntilVisible();
		userNameInput.sendKeys(user);
	}

	public void inputPass(String pass) {
		element(userPassInput).waitUntilVisible();
		userPassInput.sendKeys(pass);
	}

	public void clickLogin() {
		element(loginButton).waitUntilVisible();
		loginButton.click();
	}

	public void removeThePJDevApp(String appId) {
		waitABit(3000);
		if (appPfDev.size()==1) {
			appPfDev.get(0).findElement(By.cssSelector("div a[data-tooltip-content*='Remove']")).click();
			System.out.println("da sunt aici");
			clickOnRemoveButton();
		}else{
			System.out.println("the app is not installed yet");
		}

	}
	
	public void clickOnRemoveButton(){
		waitABit(3000);
		System.out.println("textsassa "+confirmRemoveApp.getText());
		element(confirmRemoveApp).waitUntilVisible();
		confirmRemoveApp.click();
	}

	public void editpermissionsForPJDevApp(String appId) {
		// TODO Auto-generated method stub
		waitABit(3000);
		if (appPfDev.size()==1) {
			appPfDev.get(0).findElement(By.cssSelector("div a[data-tooltip-content*='Edit Settings']")).click();
			System.out.println("da sunt aici");
			uncheckAccessTheGroupYouManageOption();
			savePermmissions();
		}
	}

	private void savePermmissions() {
		element(saveButton).waitUntilVisible();
		saveButton.click();
		
	}

	private void uncheckAccessTheGroupYouManageOption() {
		boolean found=false;
		boolean isChecked = getDriver().findElement(By.cssSelector("._498k label input[value*='user_managed_groups']")).isSelected();
		for (WebElement option : accessGroupYouManageOption) {
			if(option.getText().contains("Access the groups you manage")){
				found=true;
				if(isChecked){
					option.click();
				}
				break;
			}
			
		}
		waitABit(5000);
		CustomVerification.verifyTrue(" nu s-a gasit optiunea ", found);
		
		

	}

	
}