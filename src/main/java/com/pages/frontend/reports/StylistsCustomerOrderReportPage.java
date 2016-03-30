package com.pages.frontend.reports;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.data.backend.OrderModel;
import com.tools.requirements.AbstractPage;

public class StylistsCustomerOrderReportPage extends AbstractPage {

	@FindBy(css = "table#reports-table-default")
	private WebElement listContainer;

	public List<OrderModel> grabCustomerOrdersReport() {

		System.out.println("------------ Order History");
		element(listContainer).waitUntilVisible();
		List<WebElement> orderList = listContainer.findElements(By.cssSelector("tbody > tr"));

		List<OrderModel> result = new ArrayList<OrderModel>();

		for (WebElement elementNow : orderList) {
			OrderModel orderNow = new OrderModel();
			String orderId = elementNow.findElement(By.cssSelector("td:nth-child(2)")).getText();
			String date = elementNow.findElement(By.cssSelector("td:nth-child(3)")).getText();
			String totalSum = elementNow.findElement(By.cssSelector("td:nth-child(5)")).getText();
			String status = elementNow.findElement(By.cssSelector("td:nth-child(4)")).getText();

			orderNow.setOrderId(orderId);
			orderNow.setDate(date);
			orderNow.setInvoiceContact("empty");
			orderNow.setDeliveryContact("empty");
			orderNow.setTotalPrice(totalSum);
			orderNow.setStatus(status);

			result.add(orderNow);
		}

		return result;
	}

}
