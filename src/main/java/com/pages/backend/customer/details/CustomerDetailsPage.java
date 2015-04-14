package com.pages.backend.customer.details;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.env.stagingaut.Constants;
import com.tools.requirements.AbstractPage;

public class CustomerDetailsPage extends AbstractPage {

	@FindBy(id = "customer_info_tabs_stylecoach_lead_manage")
	private WebElement leadSettingsButton;

	@FindBy(css = "#rewardPointsBalanceGrid_table tbody tr:nth-child(2) td:nth-child(3)")
	private WebElement jewerlyContainer;

	@FindBy(css = "table.box-left tr:nth-child(5) td:last-child")
	private WebElement customerTypeContainer;

	@FindBy(css = "table.box-left tr:nth-child(2) td:last-child")
	private WebElement statusContainer;

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
		List<WebElement> elementList = detailsContainer.findElements(By.cssSelector("table.box-left tr"));

		for (WebElement elemNow : elementList) {
			if (elemNow.getText().contains("E-Mail-Adresse"))
				status = elemNow.getText();
			status = status.replace("Bestätigte E-Mail-Adresse:", "").trim();
		}

		return status;
	}

	public String extractRegistrationDate() {
		String status = "";
		element(detailsContainer).waitUntilVisible();
		List<WebElement> elementList = detailsContainer.findElements(By.cssSelector("table.box-left tr"));

		for (WebElement elemNow : elementList) {
			if (elemNow.getText().contains("Konto erstellt am"))
				status = elemNow.getText();
		}

		return status;
	}

	public String extractConfirmationDate() {
		String status = "";
		element(detailsContainer).waitUntilVisible();
		List<WebElement> elementList = detailsContainer.findElements(By.cssSelector("table.box-left tr"));

		for (WebElement elemNow : elementList) {
			if (elemNow.getText().contains("Registrierungsdatum als SC"))
				status = elemNow.getText();
		}

		return status;
	}

	public String extractEmailConfirmationStatusWithoutLabel() {
		return statusContainer.getText();
	}

	public String extractCustomerType() {
		element(customerTypeContainer).waitUntilVisible();
		return customerTypeContainer.getText();
	}

	/**
	 * Grab customer id from url.
	 */
	public String grabCustomerId() {
		String url = getDriver().getCurrentUrl();
		String userId[] = url.split("/");
		return userId[8];
	}

	public void deleteCustomer() {
		List<WebElement> deleteButtons = getDriver().findElements(By.cssSelector("div.main-col div.main-col-inner button.delete"));

		for (WebElement buttonNow : deleteButtons) {
			buttonNow.click();
			waitABit(Constants.TIME_CONSTANT);
			String alertText = getDriver().switchTo().alert().getText();
			getDriver().switchTo().alert().accept();
			if (alertText.contains("Vorgang")) {
				break;
			}
		}
	}

	public String extractJewelryBonusValue() {
		String jb = "";
		List<WebElement> list = getDriver().findElements(By.cssSelector("#rewardPointsBalanceGrid_table tbody tr"));
		for (WebElement item : list) {
			if (item.getText().contains("Schmuckbonus")) {
				jb = item.findElement(By.cssSelector("td:nth-child(3)")).getText();
				break;
			}
		}

		return jb;
	}

}
