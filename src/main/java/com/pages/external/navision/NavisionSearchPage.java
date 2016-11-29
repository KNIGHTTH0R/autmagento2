package com.pages.external.navision;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class NavisionSearchPage extends AbstractPage {

	@FindBy(css = "div.ms-nav-pagesearch div.edit-container input")
	private WebElement searchInput;

	public void inputSearchTerm(String search) {
		element(searchInput).waitUntilVisible();
		searchInput.sendKeys(search);
	}

	public void clickOnMenu(String menu) {
		List<WebElement> menuList = getDriver().findElements(By.cssSelector("div.task-dialog-content-container tbody tr td a"));
		boolean found = false;
		for (WebElement menuItem : menuList) {
			if (menuItem.getText().contentEquals(menu)) {
				found = true;
				menuItem.click();
			}
		}
		Assert.assertTrue("The menu was not found", found);
	}

}
