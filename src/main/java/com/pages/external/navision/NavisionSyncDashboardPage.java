package com.pages.external.navision;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class NavisionSyncDashboardPage extends AbstractPage {
	
	@FindBy(css = ".ms-nav-band-caption a[title='Lines']")
	private WebElement linesOption;
	
	@FindBy(css = ".ms-nav-ctxmenu-item a[title='Filters']")
	private WebElement filtersOption;
	
	public void clickLinesLink() {
		element(linesOption).waitUntilVisible();
		linesOption.click();
	}
	
	public void clickFilterLink() {
		element(filtersOption).waitUntilVisible();
		filtersOption.click();
	}
	

	public void clickPJOrderList(String tableName) {
		List<WebElement> tableList = getDriver().findElements(By.cssSelector(".ms-nav-scrollable tbody tr"));
		boolean found = false;
		for (WebElement tableItem : tableList) {
			if (tableItem.findElement(By.cssSelector("td:nth-child(1)")).getText().contentEquals(tableName)) {
				found = true;
				tableItem.click();
				
			}
		}
		Assert.assertTrue("The table was not found", found);
	}
}
