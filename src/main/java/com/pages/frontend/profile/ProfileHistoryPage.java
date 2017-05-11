package com.pages.frontend.profile;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
import com.tools.data.backend.OrderModel;
import com.tools.requirements.AbstractPage;

public class ProfileHistoryPage extends AbstractPage {

	@FindBy(css = "table#my-orders-table")
	private WebElement listContainer;

	@FindBy(css = "#my-orders-table tbody")
	private WebElement orderListContainer;

	@FindBy(css = ".block-content li:nth-child(5) a")
	private WebElement myOrdersLink;

	@FindBy(css = "a[href*='reorder']")
	private WebElement reorderLink;

	@FindBy(css = "#order_history")
	private WebElement tableOrderHistory;

	public List<OrderModel> grabOrderHistory() {
		waitABit(5000);
		element(tableOrderHistory).waitUntilVisible();
		List<WebElement> orderList = tableOrderHistory.findElements(By.cssSelector("tbody > tr"));

		List<OrderModel> result = new ArrayList<OrderModel>();

		for (WebElement elementNow : orderList) {
			OrderModel orderNow = new OrderModel();
			String orderId = elementNow.findElement(By.cssSelector("td:nth-child(1)")).getText();
			String date = elementNow.findElement(By.cssSelector("td:nth-child(2)")).getText();
			String invoiceTo = elementNow.findElement(By.cssSelector("td:nth-child(3)")).getText();
			String deliveryTo = elementNow.findElement(By.cssSelector("td:nth-child(4)")).getText();
			String totalSum = elementNow.findElement(By.cssSelector("td:nth-child(5)")).getText();
			String status = elementNow.findElement(By.cssSelector("td:nth-child(6)")).getText();

			orderNow.setOrderId(orderId);
			orderNow.setDate(date);
			orderNow.setInvoiceContact(invoiceTo);
			orderNow.setDeliveryContact(deliveryTo);
			orderNow.setTotalPrice(totalSum);
			orderNow.setStatus(status);

			result.add(orderNow);

		}

		return result;
	}

	public List<OrderModel> grabOrderDetails(String idOrder) {
		// waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
		// ContextConstants.LOADING_MESSAGE));
		waitABit(TimeConstants.TIME_MEDIUM);
		element(tableOrderHistory).waitUntilVisible();
		List<WebElement> orderList = tableOrderHistory.findElements(By.cssSelector("tbody > tr"));
		System.out.println("idOrder "+idOrder );
		List<OrderModel> result = new ArrayList<OrderModel>();
		boolean found = false;
		theFor: for (WebElement elementNow : orderList) {
			System.out.println(elementNow.getText());
			if (elementNow.getText().toLowerCase().contains(idOrder)) {
				found = true;
				OrderModel orderNow = new OrderModel();
				String orderId = elementNow.findElement(By.cssSelector("td:nth-child(1)")).getText();
				String date = elementNow.findElement(By.cssSelector("td:nth-child(2)")).getText();
				String invoiceTo = elementNow.findElement(By.cssSelector("td:nth-child(3)")).getText();
				String deliveryTo = elementNow.findElement(By.cssSelector("td:nth-child(4)")).getText();
				String totalSum = elementNow.findElement(By.cssSelector("td:nth-child(5)")).getText();
				String status = elementNow.findElement(By.cssSelector("td:nth-child(6)")).getText();

				orderNow.setOrderId(orderId);
				orderNow.setDate(date);
				orderNow.setInvoiceContact(invoiceTo);
				orderNow.setDeliveryContact(deliveryTo);
				orderNow.setTotalPrice(totalSum);
				orderNow.setStatus(status);

				result.add(orderNow);

				break theFor;
			}
		}
		Assert.assertTrue("The order was not found in the list !!!", found);
		
		return result;

	}

	public void clickOnOrder(String orderId) {
		// waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
		// ContextConstants.LOADING_MESSAGE));
		waitABit(TimeConstants.TIME_MEDIUM);
		element(tableOrderHistory).waitUntilVisible();
		List<WebElement> orderList = tableOrderHistory.findElements(By.cssSelector("tbody > tr"));
		boolean found = false;
		theFor: for (WebElement elementNow : orderList) {
			System.out.println(elementNow.getText());
			if (elementNow.getText().toLowerCase().contains(orderId)) {
				System.out.println("l-am gasit");
				found = true;
				elementNow.findElement(By.cssSelector("td:nth-child(1) a")).click();
				break theFor;
			}
		}
		Assert.assertTrue("The order was not found in the list !!!", found);

	}

	// public void clickReorderLink(String name) {
	// evaluateJavascript("jQuery.noConflict();");
	// element(orderListContainer).waitUntilVisible();
	// List<WebElement> listElements =
	// orderListContainer.findElements(By.tagName("tr"));
	// theFor: for (WebElement elementNow : listElements) {
	// if (elementNow.getText().contains(name)) {
	// elementNow.findElement(By.cssSelector("a[href*='reorder']")).click();
	// break theFor;
	// }
	// }
	// }

	public void clickReorderLink(String name) {
		evaluateJavascript("jQuery.noConflict();");
		System.out.println("Sunt aici");
		element(reorderLink).waitUntilVisible();
		reorderLink.click();

	}

}
