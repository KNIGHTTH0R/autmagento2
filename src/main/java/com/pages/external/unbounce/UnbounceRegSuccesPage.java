package com.pages.external.unbounce;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

/**
 * @author mihai
 *
 */
public class UnbounceRegSuccesPage extends AbstractPage {

	@FindBy(css = ".roadshow h1")
	private WebElement succesMessageContainer;

	public String getSuccessMessage() {
		return succesMessageContainer.getText();
	}

}
