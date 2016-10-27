package com.pages.frontend.registration.connectWithMe;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

public class ConnectSuccesPage extends AbstractPage {

	@FindBy(css = "div.page-title h1")
	private WebElement succesMessageContainer;
	
	@FindBy(css = "div.registration-info.clearfix")
	private WebElement succesRegistrationDataContainer;

	public void verifyConnectWithMeRegistrationSuccesMessage() {
		Assert.assertTrue("The succes registration message is not correct !!! ", succesMessageContainer.getText().contains(ContextConstants.CONNECT_SUCCES_MESSAGE));
	}

	public void verifyConnectWithMeSuccesRegistrationContainsScName(String stylecoachName) {
		Assert.assertTrue("Style coach not found !!! ", succesRegistrationDataContainer.getText().contains(stylecoachName));
	}

}
