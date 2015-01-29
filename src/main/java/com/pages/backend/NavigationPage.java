package com.pages.backend;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class NavigationPage extends AbstractPage {

	@FindBy(id = "nav")
	private WebElement navigationBar;

	@FindBy(id = "message-popup-window")
	private WebElement popUpWindow;

	public void clickOnCustomers() {
		element(navigationBar).waitUntilVisible();
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> elementList = navigationBar.findElements(By.cssSelector("li > a"));

		for (WebElement elementNow : elementList) {
			if (elementNow.getText().contentEquals("Kunden")) {
				elementNow.click();
				break;
			}
		}
	}
	public void clickOnManageCustomers() {
		element(navigationBar).waitUntilVisible();
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> elementList = navigationBar.findElements(By.cssSelector("li > a"));
		
		for (WebElement elementNow : elementList) {
			if (elementNow.getText().contentEquals("Kunden verwalten")) {
				elementNow.click();
				break;
			}
		}
	}

	public void clickOnSales() {
		element(navigationBar).waitUntilVisible();
		evaluateJavascript("jQuery.noConflict();");

		List<WebElement> elementList = navigationBar.findElements(By.cssSelector("li > a"));

		for (WebElement elementNow : elementList) {
			if (elementNow.getText().contentEquals("Aufträge")) {
				elementNow.click();
				break;
			}
		}
	}

	public void dismissPopUp() {
		evaluateJavascript("jQuery.noConflict();");
		element(popUpWindow).waitUntilVisible();
		popUpWindow.findElement(By.cssSelector("div.message-popup-head > a")).click();
	}

	public void clickOrdersPage() {

		evaluateJavascript("jQuery.noConflict();");
		element(navigationBar).waitUntilVisible();
		List<WebElement> elementList = navigationBar.findElements(By.cssSelector("li > a"));

		for (WebElement elementNow : elementList) {
			if (elementNow.getText().contentEquals("Verkäufe")) {
				elementNow.click();
				break;
			}
		}

	}
}
