package com.pages.backend;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tools.constants.TimeConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

/**
 * @author mihai
 *
 */
public class NavigationPage extends AbstractPage {

	@FindBy(id = "nav")
	private WebElement navigationBar;

	@FindBy(id = "message-popup-window")
	private WebElement popUpWindow;

	@FindBy(css = ".header-top img[src*='logo']")
	private WebElement adminHomePage;
	
	@FindBy(css = "#nav li.parent.level0:nth-child(9)  ")
	private WebElement systemTab;
	
	

	public void dismissPopUp() {
		
		evaluateJavascript("jQuery.noConflict();");
		element(popUpWindow).waitUntilVisible();
		popUpWindow.findElement(By.cssSelector("div.message-popup-head > a")).click();
		waitABit(1000);
	}

	public void selectMenuFromNavbar(String menu, String submenu) {
		evaluateJavascript("jQuery.noConflict();");
		element(navigationBar).waitUntilVisible();
		List<WebElement> menuList = navigationBar.findElements(By.cssSelector("li.parent.level0"));
		Actions action = new Actions(getDriver());
		for (WebElement menuNow : menuList) {

			if (menuNow.findElement(By.cssSelector("a")).getText().contentEquals(menu)) {

				action.moveToElement(menuNow).build().perform();

				List<WebElement> submenuList = menuNow.findElements(By.cssSelector("ul > li.level1"));

				for (WebElement submenuNow : submenuList) {
					if (submenuNow.findElement(By.cssSelector("a > span")).getText().contentEquals(submenu)) {
						submenuNow.click();
						waitABit(TimeConstants.WAIT_TIME_SMALL);
						break;
					}
				}
				break;
			}
		}
	}
	

	public void selectSubmenu(String submenu) {
		evaluateJavascript("jQuery.noConflict();");
		element(navigationBar).waitUntilVisible();
		List<WebElement> menuList = navigationBar.findElements(By.cssSelector("li.parent.level0 ul > li.level1 > a"));
		for (WebElement menuNow : menuList) {
			if (menuNow.getAttribute("href").contains(submenu)) {
				getDriver().get(menuNow.getAttribute("href"));
				break;
			}
		}
	}

	public void selectSubmenuEm(String submenu) {
		evaluateJavascript("jQuery.noConflict();");
		element(navigationBar).waitUntilVisible();
		List<WebElement> menuList = navigationBar.findElements(By.cssSelector("li.parent.level0 ul > li.level1 > a"));
		
		for (WebElement menuNow : menuList) {
			if (menuNow.getAttribute("href").contains(submenu)) {
				menuNow.click();
				break;
			}
		}
	}
	
	
	public void goToHomePage() {
		element(adminHomePage).waitUntilVisible();
		adminHomePage.click();

	}

	public boolean isDismissPopPresent() {
		// TODO Auto-generated method stub
		return getDriver().findElements(By.cssSelector("#message-popup-window")).size() > 0;
	}

	public void clickOnSystemTab() {
		evaluateJavascript("jQuery.noConflict();");
		element(systemTab).waitUntilVisible();
		systemTab.click();
	}
}
