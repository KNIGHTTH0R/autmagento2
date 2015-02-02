package com.pages.frontend.profile;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.thucydides.core.annotations.findby.FindBy;

import com.tools.data.backend.OrderModel;
import com.tools.requirements.AbstractPage;

public class ProfileHistoryPage extends AbstractPage{

	@FindBy(css = "table#my-orders-table")
	private WebElement listContainer;
	
	public List<OrderModel> grabOrderHistory() {

		System.out.println("------------ Order History");
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
			
//			System.out.println("------------");
//			System.out.println(orderId);
//			System.out.println(date);
//			System.out.println(invoiceTo);
//			System.out.println(deliveryTo);
//			System.out.println(totalSum);
//			System.out.println(status);
		}
		
		return result;
	}

}
