package com.pages.external.navision;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class NavisionImportPage extends AbstractPage {
	
	@FindBy(id = "months")
	private WebElement closedMonthSelect;

}
