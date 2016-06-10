package com.pages.backend;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class NavigationPage extends AbstractPage {

	@FindBy(id = "nav")
	private WebElement navigationBar;

	@FindBy(id = "message-popup-window")
	private WebElement popUpWindow;

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
				
				waitFor(ExpectedConditions.visibilityOfAllElements(menuNow.findElements(By.cssSelector("ul > li.level1 > a > span"))));
				
				List<WebElement> submenuList = menuNow.findElements(By.cssSelector("ul > li.level1"));

				for (WebElement submenuNow : submenuList) {
					if (submenuNow.getText().contentEquals(submenu)) {
						submenuNow.click();
						waitABit(TimeConstants.WAIT_TIME_SMALL);
						break;
					}
				}
				break;
			}
		}
	}
}
