package com.pages.backend.orders.details;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.data.backend.OrderTotalsModel;
import com.tools.persistance.MongoTableKeys;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

public class OrderTotalsPage extends AbstractPage {

	@FindBy(css = "div.order-totals")
	private WebElement tableContainer;

	public OrderTotalsModel grabTotals() {

		OrderTotalsModel result = new OrderTotalsModel();
		element(tableContainer).waitUntilVisible();

		List<WebElement> listEntries = tableContainer.findElements(By.cssSelector("tr"));
		waitFor(ExpectedConditions.visibilityOfAllElements(listEntries));
		System.out.println(listEntries.size());

		String valueTransformer = "";

		for (WebElement elementNow : listEntries) {

			String key = elementNow.findElement(By.cssSelector("td:first-child")).getText();
			System.out.println(key);

			if (key.contains("Zwischensumme")) {
				valueTransformer = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setSubtotal((valueTransformer));
			}
			if (key.contains("Lieferung und Verarbeitung")) {
				valueTransformer = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setShipping((valueTransformer));
			}
			if (key.contains("Rabatt (25% Style Coach Discount)")) {
				valueTransformer = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.addDiscount(MongoTableKeys.DISCOUNT_25_KEY, valueTransformer);
			}
			if (key.contains("Steuer")) {
				valueTransformer = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTax((valueTransformer));
			}
			if (key.contains("Gesamtbetrag")) {
				valueTransformer = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTotalAmount((valueTransformer));
			}
			if (key.contains("Gesamtsumme bezahlt")) {
				valueTransformer = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTotalPaid((valueTransformer));
			}
			if (key.contains("Gesamtsumme rückerstattet")) {
				valueTransformer = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTotalRefunded((valueTransformer));
			}
			if (key.contains("Gesamtsumme fällig")) {
				valueTransformer = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTotalPayable((valueTransformer));
			}
			if (key.contains("Total IPs")) {
				valueTransformer = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTotalIP((valueTransformer));
			}
			if (key.contains("Total Forty Discounts")) {
				valueTransformer = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTotalFortyDiscounts((valueTransformer));
			}
			if (key.contains("Total Jewelry Bonus")) {
				valueTransformer = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTotalBonusJeverly((valueTransformer));
			}
			if (key.contains("Total Marketing Bonus")) {
				valueTransformer = FormatterUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTotalMarketingBonus((valueTransformer));
			}

		}
		return result;
	}

}
