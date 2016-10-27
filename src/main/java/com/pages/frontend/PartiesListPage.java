
package com.pages.frontend;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class PartiesListPage extends AbstractPage {

	@FindBy(css = "div.party-list .party.clearfix:nth-child(1) a")
	private WebElement firstPartyLink;


	public String goToFirstParty() {
		element(firstPartyLink).waitUntilVisible();
		firstPartyLink.click();
		return getDriver().getCurrentUrl();
	}
	


}

