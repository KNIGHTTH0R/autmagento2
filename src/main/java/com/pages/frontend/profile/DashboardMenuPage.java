package com.pages.frontend.profile;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.env.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

public class DashboardMenuPage extends AbstractPage {

	@FindBy(css = "div.block-content")
	private WebElement dashboardMenuContainer;

	public void clickOnProfileHistory() {
		element(dashboardMenuContainer).waitUntilVisible();
		List<WebElement> menuList = dashboardMenuContainer.findElements(By.cssSelector("li a"));

		theFor: for (WebElement elementNow : menuList) {
			String elementText = elementNow.getText();
			if (elementText.contains(ContextConstants.PROFILE_HISTORY)) {
				elementNow.click();
				break theFor;
			}
		}

	}
	public void clickOnJewelryBonusHistory() {
		element(dashboardMenuContainer).waitUntilVisible();
		List<WebElement> menuList = dashboardMenuContainer.findElements(By.cssSelector("li a"));
		
		theFor: for (WebElement elementNow : menuList) {
			String elementText = elementNow.getText();
			if (elementText.contains(ContextConstants.JEWELRY_HISTORY)) {
				elementNow.click();
				break theFor;
			}
		}
		
	}
}
