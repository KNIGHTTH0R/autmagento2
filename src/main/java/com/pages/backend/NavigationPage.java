package com.pages.backend;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractPage;

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
		List<WebElement> menuList = navigationBar.findElements(By.cssSelector("li"));

		Actions builder = new Actions(getDriver());

		for (WebElement menuNow : menuList) {

			if (menuNow.findElement(By.cssSelector("a")).getText().contentEquals(menu)) {
				builder.moveToElement(menuNow).build().perform();
				waitABit(TimeConstants.TIME_CONSTANT);

				List<WebElement> submenuList = menuNow.findElements(By.cssSelector("ul > li > a"));

				for (WebElement submenuNow : submenuList) {
					System.out.println(submenuNow.getText());
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
