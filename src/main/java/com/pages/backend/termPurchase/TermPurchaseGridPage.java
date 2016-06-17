package com.pages.backend.termPurchase;

import java.text.ParseException;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.data.backend.TermPurchaseOrderModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.ContextConstants;
import com.tools.requirements.AbstractPage;
import com.tools.utils.DateUtils;

public class TermPurchaseGridPage extends AbstractPage {

	@FindBy(id = "scheduledOrders_filter_order_id")
	private WebElement orderIdInput;

	@FindBy(id = "scheduledOrders_massaction-select")
	private WebElement actionsListSelect;

	@FindBy(css = "td.filter-actions > button.task")
	private WebElement searchButton;

	@FindBy(css = "table#scheduledOrders_table tbody")
	private WebElement listContainer;

	@FindBy(id = "postpone_period")
	private WebElement postponePeriod;

	@FindBy(css = "button[onclick='scheduledOrders_massactionJsObject.apply()']")
	private WebElement submitMassAction;
	
	@FindBy(id = "messages")
	private WebElement message;
	

	public void inputOderId(String incrementId) {
		WebElement element = getDriver().findElement(By.id("scheduledOrders_filter_order_id"));
		element.clear();
		element(element).typeAndEnter(incrementId);
	}

	public void clickOnSearch() {
		evaluateJavascript("jQuery.noConflict();");
		element(searchButton).waitUntilVisible();
		searchButton.click();
		waitABit(2000);
	}

	public void submitMassAction() {
		evaluateJavascript("jQuery.noConflict();");
		element(submitMassAction).waitUntilVisible();
		submitMassAction.click();
		waitABit(5000);
	}

	public void selectOrder(String incrementId) {
		findOrder(incrementId).findElement(By.cssSelector("td input")).click();
	}

	public TermPurchaseOrderModel grabOrderDetails(String incrementId) throws ParseException {
		TermPurchaseOrderModel result = new TermPurchaseOrderModel();
		WebElement row = findOrder(incrementId);
		result.setIncrementId(row.findElement(By.cssSelector("td:nth-child(2)")).getText().trim());
		String execDate = row.findElement(By.cssSelector("td:nth-child(4)")).getText().trim();
		execDate = DateUtils.parseDate(execDate, "dd.MM.yyyy hh:mm:ss", "yyyy-MM-dd");
		result.setExecutionDate(execDate);
		String[] skuParts = row.findElement(By.cssSelector("td:nth-child(7) div h3")).getText().trim().split(" X ");
		result.setBoughtQty(skuParts[0]);
		result.setProductSku(skuParts[1]);
		String stockData = row.findElement(By.cssSelector("td:nth-child(7) div div")).getText().trim();
		result.setInStock(StringUtils.substringBetween(stockData, "In stock: ", "Is discontinued").trim());
		result.setIsDiscontinued(StringUtils.substringBetween(stockData, "Is discontinued: ", "Qty").trim());
		result.setProductQty(StringUtils.substringBetween(stockData, "Qty: ", "Minimum Qty").trim());
		result.setMinimumQty(StringUtils.substringBetween(stockData, "Minimum Qty: ", "Earliest availability").trim());
		String earliestAvailability = StringUtils.substringAfter(stockData, "Earliest availability: ").trim();
		earliestAvailability = DateUtils.parseDate(earliestAvailability, "dd.MM.yy", "yyyy-MM-dd");
		result.setEarliestAv(earliestAvailability);
		result.setScheduledPaymentStatus(row.findElement(By.cssSelector("td:nth-child(8)")).getText().trim());
		result.setOrderStatus(row.findElement(By.cssSelector("td:nth-child(9)")).getText().trim());
		result.setRecomandation(row.findElement(By.cssSelector("td:nth-child(10)")).getText().trim());
		result.setReason(row.findElement(By.cssSelector("td:nth-child(11)")).getText().trim());

		return result;
	}

	public void selectAction(String action) {
		evaluateJavascript("jQuery.noConflict();");
		element(actionsListSelect).waitUntilVisible();
		element(actionsListSelect).selectByVisibleText(action);
	}

	public void selectPostponePeriod(String period) {
		evaluateJavascript("jQuery.noConflict();");
		element(postponePeriod).waitUntilVisible();
		element(postponePeriod).selectByVisibleText(period);
	}

	public WebElement findOrder(String incrementId) {
		evaluateJavascript("jQuery.noConflict();");
		WebElement foundOrder = null;
		element(listContainer).waitUntilVisible();
		List<WebElement> listElements = listContainer.findElements(By.tagName("tr"));
		for (WebElement elementNow : listElements) {
			if (elementNow.getText().contains(incrementId)) {
				foundOrder = elementNow;
				break;
			}
		}
		return foundOrder;
	}

	public void verifyOrderIsNotInTheGrid(String incrementId) {
		evaluateJavascript("jQuery.noConflict();");
		element(listContainer).waitUntilVisible();
		boolean found = false;
		List<WebElement> listElements = listContainer.findElements(By.tagName("tr"));
		for (WebElement elementNow : listElements) {
			if (elementNow.getText().contains(incrementId)) {
				found = true;
				break;
			}
		}
		Assert.assertFalse("The order should not be present!!!", found);
	}
	
	public void validateMessage(String expectedMessage) {
		element(message).waitUntilVisible();
		Assert.assertTrue("The message from validation message is not the expected one!!", message.getText().contains(expectedMessage));
	}
	
//	public void validateCancelMessage() {
//		element(message).waitUntilVisible();
//		Assert.assertTrue("The message from validation message is not the expected one!!", message.getText().contains(ConfigConstants.CANCEL_SUCCESS_MESSAGE));
//	}
//	
//	public void validateReleaseMessage() {
//		element(message).waitUntilVisible();
//		Assert.assertTrue("The message from validation message is not the expected one!!", message.getText().contains(ConfigConstants.RELEASE_SUCCESS_MESSAGE));
//	}
//	
//	public void validateErrorMessage() {
//		element(message).waitUntilVisible();
//		Assert.assertTrue("The message from validation message is not the expected one!!", message.getText().contains(ConfigConstants.RELEASE_ERROR_MESSAGE));
//	}

}
