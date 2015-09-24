package com.pages.frontend.profile;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractPage;

public class DashboardPage extends AbstractPage {

	@FindBy(css = "div.dashboard div:nth-child(5) div.col-1 dl dd:nth-child(2)")
	private WebElement jewelryContainer;

	@FindBy(css = "div.dashboard div:nth-child(5) div.col-1 dl dd:nth-child(2)")
	private WebElement marketingBonusContainer;

	public String getStyleCoachFirstNameFromProfile() {

		String styleCoachNameParts[] = null;
		List<WebElement> infoBoxList = getDriver().findElements(By.cssSelector(".info-box"));
		for (WebElement infoBox : infoBoxList) {
			if (infoBox.getText().contains(ContextConstants.MEIN_STYLE_COACH)) {
				styleCoachNameParts = infoBox.findElement(By.cssSelector("dl dd")).getText().split(" ");
				break;
			}
		}
		return styleCoachNameParts[0].toLowerCase();
	}

	public String getStyleCoachFullNameFromProfile() {

		String styleCoachName = "";
		List<WebElement> infoBoxList = getDriver().findElements(By.cssSelector(".info-box"));
		for (WebElement infoBox : infoBoxList) {
			if (infoBox.getText().contains(ContextConstants.MEIN_STYLE_COACH)) {
				styleCoachName = infoBox.findElement(By.cssSelector("dl dd")).getText();
				break;
			}
		}
		return styleCoachName;
	}

	public String getStyleCoachEmailFromProfile() {

		String styleCoachName = "";
		List<WebElement> infoBoxList = getDriver().findElements(By.cssSelector(".info-box"));
		for (WebElement infoBox : infoBoxList) {
			if (infoBox.getText().contains(ContextConstants.MEIN_STYLE_COACH)) {
				styleCoachName = infoBox.findElement(By.cssSelector("dl dd:nth-child(4)")).getText();
				break;
			}
		}
		return styleCoachName;
	}

	public String getJewelryBonus() {
		return jewelryContainer.getText().replace(".", "").replace(",", ".");
	}
	public String getJewelryBonusWithFourDecimals() {
		return jewelryContainer.getText().replace(".", "").replace(",", ".").concat("00");
	}

	public String getMarketingMaterialBonusWithFourDecimals() {
		return marketingBonusContainer.getText().replace(".", "").replace(",", ".").concat("00");
	}

}
