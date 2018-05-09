package com.pages.backend.orders.details;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.data.backend.OrderItemModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

public class OrderItemsPage extends AbstractPage {

	@FindBy(css = "table.order-tables")
	private WebElement tableContainer;

	public List<OrderItemModel> grabOrderItems() {
		List<OrderItemModel> result = new ArrayList<OrderItemModel>();
        waitForPageToLoad();
		element(tableContainer).waitUntilVisible();

		List<WebElement> listEntries = getDriver().findElements(By.cssSelector("table.order-tables > tbody > tr"));

		waitFor(ExpectedConditions.visibilityOfAllElements(listEntries));

		for (WebElement elementNow : listEntries) {
			OrderItemModel orderNow = new OrderItemModel();

			String productName = elementNow.findElement(By.cssSelector("span[id*='order_item']")).getText();
			String productCode = elementNow.findElement(By.cssSelector("div.item-text div")).getText();
			String propertyStatus = elementNow.findElement(By.cssSelector("td:nth-child(2)")).getText();
			String originalPrice = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(3)")).getText());
			String price = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(4) span.price")).getText());
			String number = elementNow.findElement(By.cssSelector("td:nth-child(5) td:last-child")).getText();
			String subtotal = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText());
			String taxAmount = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(7) span.price")).getText());
			String taxPercentage = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(8)")).getText());
			String discountAmount = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(9) span.price")).getText());
			String ip = elementNow.findElement(By.cssSelector("td:nth-child(10)")).getText();
			String jb = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(11) span.price")).getText());
			String mb = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(12) span.price")).getText());
			String fd = elementNow.findElement(By.cssSelector("td:nth-child(13)")).getText();
			String rowSum = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(14)")).getText());

			orderNow.setProductName(productName);
			orderNow.setProductCode(productCode.replace("Artikelposition: ", ""));
			orderNow.setPropertyStatus(propertyStatus);
			orderNow.setOriginalPrice(originalPrice);
			orderNow.setPrice(price);
			orderNow.setNumber(number);
			orderNow.setSubtotal(subtotal);
			orderNow.setTaxAmount(taxAmount);
			orderNow.setTaxPercentage(taxPercentage);
			orderNow.setDiscountAmount(discountAmount);
			orderNow.setiP(ip);
			orderNow.setjB(jb);
			orderNow.setmB(mb);
			orderNow.setfD(fd);
			orderNow.setRowSum(rowSum);

			result.add(orderNow);
		}
		return result;
	}

	public List<OrderItemModel> grabInvoiceOrderItems() {
		List<OrderItemModel> result = new ArrayList<OrderItemModel>();
        waitForPageToLoad();
		element(tableContainer).waitUntilVisible();

		List<WebElement> listEntries = getDriver().findElements(By.cssSelector("table.order-tables > tbody > tr"));

		waitFor(ExpectedConditions.visibilityOfAllElements(listEntries));

		for (WebElement elementNow : listEntries) {
			OrderItemModel orderNow = new OrderItemModel();

			String productName = elementNow.findElement(By.cssSelector("span[id*='order_item']")).getText();
			String productCode = elementNow.findElement(By.cssSelector("div")).getText();
			String propertyStatus = elementNow.findElement(By.cssSelector("td:nth-child(3)")).getText();
			String price = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(2) span.price")).getText());
			String number = elementNow.findElement(By.cssSelector("td:nth-child(3) td:last-child")).getText();
			String subtotal = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(5) span.price")).getText());
			String taxAmount = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText());
			String discountAmount = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(7) span.price")).getText());
			String rowSum = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(8)")).getText());

			orderNow.setProductName(productName);
			orderNow.setProductCode(productCode.replace("Artikelposition: ", ""));
			orderNow.setPropertyStatus(propertyStatus);
			orderNow.setPrice(price);
			orderNow.setNumber(number);
			orderNow.setSubtotal(subtotal);
			orderNow.setTaxAmount(taxAmount);
			orderNow.setDiscountAmount(discountAmount);
			orderNow.setRowSum(rowSum);

			result.add(orderNow);
		}
		return result;
	}

	public List<OrderItemModel> grabShipmentOrderItems() {
		List<OrderItemModel> result = new ArrayList<OrderItemModel>();
        waitForPageToLoad();
		element(tableContainer).waitUntilVisible();

		List<WebElement> listEntries = getDriver().findElements(By.cssSelector("table.order-tables > tbody > tr"));

		waitFor(ExpectedConditions.visibilityOfAllElements(listEntries));

		for (WebElement elementNow : listEntries) {
			OrderItemModel orderNow = new OrderItemModel();

			String productName = elementNow.findElement(By.cssSelector("span[id*='order_item']")).getText();
			String productCode = elementNow.findElement(By.cssSelector("div")).getText();
			String propertyStatus = elementNow.findElement(By.cssSelector("td:nth-child(2)")).getText();
			String number = elementNow.findElement(By.cssSelector("td:nth-child(2) td:last-child")).getText();

			orderNow.setProductName(productName);
			orderNow.setProductCode(productCode.replace("Artikelposition: ", ""));
			orderNow.setPropertyStatus(propertyStatus);
			orderNow.setNumber(number);

			result.add(orderNow);
		}
		return result;
	}

	public List<OrderItemModel> grabCreditMomoOrderItems() {
		List<OrderItemModel> result = new ArrayList<OrderItemModel>();
        waitForPageToLoad();
		element(tableContainer).waitUntilVisible();

		List<WebElement> listEntries = getDriver().findElements(By.cssSelector("table.order-tables > tbody > tr"));

		waitFor(ExpectedConditions.visibilityOfAllElements(listEntries));

		for (WebElement elementNow : listEntries) {
			OrderItemModel orderNow = new OrderItemModel();

			String productName = elementNow.findElement(By.cssSelector("span[id*='order_item']")).getText();
			String productCode = elementNow.findElement(By.cssSelector("div")).getText();
			String price = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(2) span.price")).getText());
			String number = elementNow.findElement(By.cssSelector("td:nth-child(3) table tr:nth-child(1) td:last-child")).getText();
			String subtotal = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText());
			String taxAmount = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(11) span.price")).getText());
			String discountAmount = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(12) span.price")).getText());
			String ip = elementNow.findElement(By.cssSelector("td:nth-child(7) table tr:nth-child(1) td:last-child")).getText();
			String jb = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(9) table tr:nth-child(1) td:last-child")).getText());
			String mb = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(10) table tr:nth-child(1) td:last-child")).getText());
			String fd = elementNow.findElement(By.cssSelector("td:nth-child(8) table tr:nth-child(1) td:last-child")).getText();
			String rowSum = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:nth-child(13)")).getText());

			orderNow.setProductName(productName);
			orderNow.setProductCode(productCode.replace("Artikelposition: ", ""));
			orderNow.setPrice(price);
			orderNow.setNumber(number);
			orderNow.setSubtotal(subtotal);
			orderNow.setTaxAmount(taxAmount);
			orderNow.setDiscountAmount(discountAmount);
			orderNow.setiP(ip);
			orderNow.setjB(jb);
			orderNow.setmB(mb);
			orderNow.setfD(fd);
			orderNow.setRowSum(rowSum);

			result.add(orderNow);
		}
		return result;
	}
	
	
}
