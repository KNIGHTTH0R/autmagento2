package com.pages.frontend;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractPage;

public class StylistContextPage extends AbstractPage {
	
	@FindBy(id = "stylistref")
	private WebElement stylistref;

	public void inputStylistRef(String ref) {
		element(stylistref).waitUntilVisible();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		stylistref.clear();
		waitABit(TimeConstants.WAIT_TIME_SMALL);
		element(stylistref).sendKeys(ref);
		waitABit(TimeConstants.WAIT_TIME_SMALL);
	}

}
