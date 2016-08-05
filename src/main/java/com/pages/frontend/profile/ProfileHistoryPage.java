package com.pages.frontend.profile;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.data.backend.OrderModel;
import com.tools.requirements.AbstractPage;

public class ProfileHistoryPage extends AbstractPage {

	@FindBy(css = "table#my-orders-table")
	private WebElement listContainer;

	@FindBy(css = "#my-orders-table tbody")
	private WebElement orderListContainer;

	@FindBy(css = ".block-content li:nth-child(5) a")
	private WebElement myOrdersLink;

	public List<OrderModel> grabOrderHistory() {

		element(listContainer).waitUntilVisible();
		List<WebElement> orderList = listContainer.findElements(By.cssSelector("tbody > tr"));

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

	public void clickReorderLink(String name) {
		evaluateJavascript("jQuery.noConflict();");
		element(orderListContainer).waitUntilVisible();
		List<WebElement> listElements = orderListContainer.findElements(By.tagName("tr"));
		theFor: for (WebElement elementNow : listElements) {
			if (elementNow.getText().contains(name)) {
				elementNow.findElement(By.cssSelector("a[href*='reorder']")).click();
				break theFor;
			}
		}
	}

}
