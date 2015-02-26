package com.pages.backend.promotion;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class ShoppingCartPriceRulesPage extends AbstractPage {

	@FindBy(id = "rule_is_active")
	private WebElement activationDropdown;
	
	@FindBy(css = "p.form-buttons button:nth-child(4)")
	private WebElement saveRule;

	public void activateRule() {
		evaluateJavascript("jQuery.noConflict();");
		element(activationDropdown).selectByVisibleText("Aktiv");
	}

	public void deactivateRule() {
		evaluateJavascript("jQuery.noConflict();");
		element(activationDropdown).selectByVisibleText("Nicht aktiv");
	}
	
	public void saveRule(){
		element(saveRule).waitUntilVisible();
		element(saveRule).click();
	}

}
