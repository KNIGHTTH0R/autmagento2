package com.pages.frontend;


import java.util.concurrent.TimeUnit;



import net.serenitybdd.core.annotations.findby.FindBy;

import org.jruby.RubyProcess.Sys;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.data.frontend.ContactModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.PrintUtils;


public class ContactDetailsPage extends AbstractPage {
	
	@FindBy(id = "sosMassSyncContacts")
	private WebElement sosButton;
	
	@FindBy(css = "#sosFormMassSyncContacts button.blue-button")
	private WebElement submitButton;
	
	@FindBy(css = "	a[class='fancybox-item fancybox-close']")
	private WebElement closeModal;
	
	@FindBy(css= "customer-infos-accordion ui-accordion")
	private WebElement customerBlock;
	
	
	public ContactModel grabContactDetails() {

		ContactModel result = new ContactModel();

		result.setName(getDriver().findElement(By.cssSelector("div.col1-set.page-title .page-title-inner h1")).getText());
		result.setCreatedAt(getDriver().findElement(By.cssSelector("#contact-source span")).getText().trim());
		result.setHasPartyHostInterrest(getDriver().findElement(By.id("flag_parties")).isSelected());
		result.setHasStyleCoachInterrest(getDriver().findElement(By.id("flag_member")).isSelected());
//		result.setIsNewsletterSubscribed(getDriver().findElement(By.id("#contact-email-signup p")).isSelected());
		result.setStreet(getDriver().findElement(By.xpath("//*[@id='contact-information']/p[2]")).getText());
		result.setNumber(getDriver().findElement(By.xpath("//*[@id='contact-information']/p[2]")).getText());
		result.setZip(getDriver().findElement(By.xpath("//*[@id='contact-information']/p[3]")).getText().replace(",", ""));
		result.setTown(getDriver().findElement(By.xpath("//*[@id='contact-information']/p[4]")).getText());
		result.setCountry(getDriver().findElement(By.cssSelector("#contact-information p.dp-bl.contact-city")).getText());
		result.setLastHistoryRegistration(getDriver().findElement(By.cssSelector("#reports-table-default tbody tr:nth-child(1)")).getText());
		
		
		
		PrintUtils.printContactModel(result);

		return result;

	}
	
	public boolean checkIsPresentSosButton(){
		boolean isVisible = false;
		if(element(sosButton).isVisible())
		{
			isVisible=true;
			Assert.assertTrue("The menu was  found", isVisible);
			
		}
		else 
		{
			isVisible=false;
			Assert.assertFalse("The menu was not found", isVisible);
		}
		

		return isVisible;
	}
	
	public void clickOnSosSyncButton() {
		element(sosButton).waitUntilVisible();
		sosButton.click();
		
	}
	
	public void clickOnSubmitSosButton() {
		element(submitButton).waitUntilVisible();
		submitButton.click();
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementWithText(
				By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.SOS_LOADING_MESSAGE));
	}
	
	public void closeModalWindow() {
		element(closeModal).waitUntilVisible();
		closeModal.click();
		waitABit(5000);
		
	}

	public void checkPresenceOfCustomerInfosBlock(boolean isDisplayed) {
		if (isDisplayed)
			Assert.assertTrue("The Customer block should be present and it's not !!!",
					customerBlock.isDisplayed());

		else
			Assert.assertTrue("The Customer block is present and it shouldn't !!!",
					!customerBlock.isDisplayed());
		
	}
	

}
//*[@id="contact-information"]/p[4]
