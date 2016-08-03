package com.pages.frontend.profile;

import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import com.tools.requirements.AbstractPage;
import net.serenitybdd.core.annotations.findby.By;

public class ProfileNavPage extends AbstractPage {

	public void selectMenu(String menu) {
		List<WebElement> menuList = getDriver().findElements(By.cssSelector("div.block-content ul li a"));
		boolean found = false;
		for (WebElement webElement : menuList) {
			if (webElement.getText().contains(menu)) {
				webElement.click();
				found = true;
				break;
			}
		}
		Assert.assertTrue("The manu was not found", found);
	}

}
