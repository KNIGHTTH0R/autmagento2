package com.pages.backend.orders.details;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.constants.ConfigConstants;
import com.tools.requirements.AbstractPage;

public class OrderNotificationPage extends AbstractPage {

	@FindBy(css = "table#adyenPayment_order_notifications_table")
	private WebElement notificationTable;
	

	public void verifyAuthorization(String shopperRefernce) {
		List<WebElement> menuList = notificationTable.findElements(By.cssSelector("tbody tr"));
		boolean found = false;
		for (WebElement webElement : menuList) {
			if (webElement.findElement(By.cssSelector("td:nth-child(3)")).getText().contains(ConfigConstants.ADYEN_AUTHORISATION)&&
					webElement.findElement(By.cssSelector("td:nth-child(5)")).getText().contains(shopperRefernce)) {
				found = true;
				break;
			}
		}
		Assert.assertTrue("The Authorisation was not found", found);
	}
	
	public void verifyCapture(String shopperRefernce) {
		List<WebElement> menuList = notificationTable.findElements(By.cssSelector("tbody tr"));
		boolean found = false;
		for (WebElement webElement : menuList) {
			if (webElement.findElement(By.cssSelector("td:nth-child(3)")).getText().contains(ConfigConstants.ADYEN_CAPTURE)&&
					webElement.findElement(By.cssSelector("td:nth-child(6)")).getText().contains(shopperRefernce)) {
				found = true;
				break;
			}
		}
		Assert.assertTrue("The capture was not found", found);
	}
	
}
