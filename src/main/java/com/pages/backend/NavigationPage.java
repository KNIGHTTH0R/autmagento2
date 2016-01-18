package com.pages.backend;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tools.requirements.AbstractPage;

public class NavigationPage extends AbstractPage {

	@FindBy(id = "nav")
	private WebElement navigationBar;

	@FindBy(id = "message-popup-window")
	private WebElement popUpWindow;

	// TODO refactoring here

	public void clickOnPromotions() {
		element(navigationBar).waitUntilVisible();
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> elementList = navigationBar.findElements(By.cssSelector("li > a"));

		for (WebElement elementNow : elementList) {
			if (elementNow.getText().contentEquals("Promotionen")) {
				elementNow.click();
				break;
			}
		}
	}

	public void clickOnShoppingCartPriceRules() {
		element(navigationBar).waitUntilVisible();
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> elementList = navigationBar.findElements(By.cssSelector("li > a"));

		for (WebElement elementNow : elementList) {
			if (elementNow.getText().contentEquals("Warenkorb Preisgebote")) {
				elementNow.click();
				break;
			}
		}
	}

	public void clickOnStyleCoach() {
		element(navigationBar).waitUntilVisible();
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> elementList = navigationBar.findElements(By.cssSelector("li > a"));

		for (WebElement elementNow : elementList) {
			if (elementNow.getText().contentEquals("Stylecoach")) {
				elementNow.click();
				break;
			}
		}
	}

	public void clickOnStyleParties() {
		element(navigationBar).waitUntilVisible();
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> elementList = navigationBar.findElements(By.cssSelector("li > a"));

		for (WebElement elementNow : elementList) {
			if (elementNow.getText().contentEquals("Style Parties")) {
				elementNow.click();
				break;
			}
		}
	}

	public void clickOnStylecoachList() {
		element(navigationBar).waitUntilVisible();
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> elementList = navigationBar.findElements(By.cssSelector("li > a"));

		for (WebElement elementNow : elementList) {
			if (elementNow.getText().contentEquals("Stylecoach List")) {
				elementNow.click();
				break;
			}
		}
	}

	public void clickOnContactList() {
		element(navigationBar).waitUntilVisible();
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> elementList = navigationBar.findElements(By.cssSelector("li > a"));

		for (WebElement elementNow : elementList) {
			if (elementNow.getText().contentEquals("Kontakte")) {
				elementNow.click();
				break;
			}
		}
	}

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

	public void goToNewsletter() {
		element(navigationBar).waitUntilVisible();
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> elementList = navigationBar.findElements(By.cssSelector("li > a"));

		for (WebElement elementNow : elementList) {
			if (elementNow.getText().contentEquals("Newsletter")) {
				elementNow.click();
				break;
			}
		}
	}

	public void goToNewsletterSubribers() {
		element(navigationBar).waitUntilVisible();
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> elementList = navigationBar.findElements(By.cssSelector("li > a"));

		for (WebElement elementNow : elementList) {
			if (elementNow.getText().contentEquals("Newsletter Bezieher")) {
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

	public void clickOnCreditMemo() {
		element(navigationBar).waitUntilVisible();
		evaluateJavascript("jQuery.noConflict();");

		List<WebElement> elementList = navigationBar.findElements(By.cssSelector("li > a"));

		for (WebElement elementNow : elementList) {
			if (elementNow.getText().contentEquals("Gutschriften")) {
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

	public void clickOnProducts() {
		element(navigationBar).waitUntilVisible();
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> elementList = navigationBar.findElements(By.cssSelector("li > a"));

		for (WebElement elementNow : elementList) {
			if (elementNow.getText().contentEquals("Katalog")) {
				elementNow.click();
				break;
			}
		}
	}

	public void clickOnManageProducts() {
		element(navigationBar).waitUntilVisible();
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> elementList = navigationBar.findElements(By.cssSelector("li > a"));

		for (WebElement elementNow : elementList) {
			if (elementNow.getText().contentEquals("Produkte verwalten")) {
				elementNow.click();
				break;
			}
		}
	}

	public void selectMenuFromNavbar(String menu, String submenu) {

		Actions builder = new Actions(getDriver());

		element(navigationBar).waitUntilVisible();
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> menuList = navigationBar.findElements(By.cssSelector("li > a"));

		for (WebElement menuNow : menuList) {
			if (menuNow.getText().contentEquals(menu)) {
				System.out.println("menu found");
				builder.moveToElement(menuNow).build().perform();
				List<WebElement> submenuList = menuNow.findElements(By.cssSelector("ul > li > a"));

				for (WebElement submenuNow : submenuList) {
					if (submenuNow.getText().contentEquals(submenu)) {
						System.out.println("submenu found");
						submenuNow.click();
						break;
					}

				}
				break;
			}

		}
	}
}
