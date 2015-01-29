package com.pages.backend.customer.details;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.requirements.AbstractPage;

public class CustomerDetailsPage extends AbstractPage {

	@FindBy(id = "customer_info_tabs_stylecoach_lead_manage")
	private WebElement leadSettingsButton;

	@FindBy(id = "customer_info_tabs_customer_edit_tab_view_content")
	private WebElement detailsContainer;

	public void clickOnLeadSettings() {
		evaluateJavascript("jQuery.noConflict();");
		element(leadSettingsButton).waitUntilVisible();
		leadSettingsButton.click();
	}

	public String extractEmailConfirmationStatus() {
		
		String status = "";
		element(detailsContainer).waitUntilVisible();
		List<WebElement> elementList = detailsContainer.findElements(By
				.cssSelector("table.box-left tr"));
		
		detailsContainer.findElement(By.cssSelector("table.box-left tr:nth-child(2) td:last-child"));

		for (WebElement elemNow : elementList) {
			if(elemNow.getText().contains("E-Mail-Adresse"))
				status = elemNow.getText();
			System.out.println("EL: " + elemNow.getText());
		}
		
//		System.out.println(isActive.getText());

		return status;
	}

}
