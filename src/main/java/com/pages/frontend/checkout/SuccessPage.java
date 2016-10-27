package com.pages.frontend.checkout;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

//@DefaultUrl(UrlConstants.URL_CART_SUCCESS)
public class SuccessPage extends AbstractPage {

	@FindBy(className = "success-step-msg")
	private WebElement messageContainer;

	public void verifySuccessMessage() {

		Wait<WebDriver> wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
		// withTimeoutOf(5,
		// TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
		element(messageContainer).waitUntilVisible();
		Assert.assertTrue("Failure: Success message has not been found.",
				messageContainer.getText().contains(ContextConstants.SUCCES_MESSAGE));
	}

}
