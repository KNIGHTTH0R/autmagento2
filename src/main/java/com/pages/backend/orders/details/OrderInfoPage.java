package com.pages.backend.orders.details;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.data.backend.OrderInfoModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

public class OrderInfoPage extends AbstractPage {

	@FindBy(css = "div#sales_order_view_tabs_order_info_content > div:first-child > div:nth-child(2)")
	private WebElement infoContainer;

	@FindBy(css = "span#order_status")
	private WebElement orderStatusField;

	public OrderInfoModel grabOrderInfo() {

		OrderInfoModel orderInfo = new OrderInfoModel();
		element(infoContainer).waitUntilVisible();
		List<WebElement> elemList = infoContainer.findElements(By.cssSelector("table tr"));

		for (WebElement webElement : elemList) {

			String elementLabel = webElement.findElement(By.cssSelector("td:first-child")).getText();

			if (elementLabel.contains("Bestelldatum")) {
				orderInfo.setOrderDate(webElement.findElement(By.cssSelector("td:last-child")).getText());
			}
			if (elementLabel.contains("Status")) {
				orderInfo.setOrderStatus(webElement.findElement(By.cssSelector("td:last-child")).getText());
			}
			if (elementLabel.contains("Erworben von")) {
				orderInfo.setAquiredBy(webElement.findElement(By.cssSelector("td:last-child")).getText());
			}
			if (elementLabel.contains("Bestellt von IP Nummer")) {
				orderInfo.setOrderIP(webElement.findElement(By.cssSelector("td:last-child")).getText());
			}
			if (elementLabel.contains("Scheduled Delivery Date")) {
				String[] tokens = webElement.findElement(By.cssSelector("td:last-child")).getText().split(" ");
				orderInfo.setDeliveryDate(tokens[0]);
			}
			String pspRef = getDriver().findElement(By.cssSelector("div#sales_order_view_tabs_order_info_content .box-left:nth-child(9) fieldset div:nth-child(3) b")).getText();
			pspRef = FormatterUtils.parseValueToZeroDecimals(pspRef);
			orderInfo.setPspReference(pspRef);
		}

		return orderInfo;
	}

}
