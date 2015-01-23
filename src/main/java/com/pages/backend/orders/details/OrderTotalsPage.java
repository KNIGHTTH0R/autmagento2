package com.pages.backend.orders.details;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.AbstractPage;
import com.tools.Constants;
import com.tools.PrintUtils;
import com.tools.data.OrderItemModel;
import com.tools.data.OrderTotalsModel;

public class OrderTotalsPage extends AbstractPage {

	@FindBy(css = "div.order-totals")
	private WebElement tableContainer;

	public OrderTotalsModel grabTotals() {
		
		OrderTotalsModel result = new OrderTotalsModel();
		
		element(tableContainer).waitUntilVisible();
		System.out.println("nimic");

		List<WebElement> listEntries = tableContainer.findElements(By.cssSelector("tr"));		
		waitFor(ExpectedConditions.visibilityOfAllElements(listEntries));
		System.out.println(listEntries.size());
		
		String valueTransformer = "";
		
		//TODO finish this Mihai!:))
		
		for (WebElement elementNow : listEntries) {			
			
			String key = elementNow.findElement(By.cssSelector("td:first-child")).getText();
			System.out.println(key);
//			private String subtotal;
//			private String shipping;
//			private String discount;
//			private String tax;
//			private String totalAmount;
//			private String totalPaid;
//			private String totalRefunded;
//			private String totalPayable;
//			private String totalIP;
//			private String totalFortyDiscounts;
//			private String totalBonusJeverly;
//			private String totalMarketingBonus;
			
			
			if(key.contains("Zwischensumme")){
				valueTransformer = PrintUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setSubtotal((valueTransformer));
			}
			if(key.contains("Lieferung und Verarbeitung")){
				valueTransformer = PrintUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setShipping((valueTransformer));
			}
			if(key.contains("Rabatt (25% Style Coach Discount)")){
				valueTransformer = PrintUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setDiscount((valueTransformer));
			}
			if(key.contains("Steuer")){
				valueTransformer = PrintUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTax((valueTransformer));
			}
			if(key.contains("Gesamtbetrag")){
				valueTransformer = PrintUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTotalAmount((valueTransformer));
			}
			if(key.contains("Gesamtsumme bezahlt")){
				valueTransformer = PrintUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTotalPaid((valueTransformer));
			}
			if(key.contains("Gesamtsumme rückerstattet")){
				valueTransformer = PrintUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTotalRefunded((valueTransformer));
			}
			if(key.contains("Gesamtsumme fällig")){
				valueTransformer = PrintUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTotalPayable((valueTransformer));
			}
			if(key.contains("Total IPs")){
				valueTransformer = PrintUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTotalIP((valueTransformer));
			}
			if(key.contains("Total Forty Discounts")){
				valueTransformer = PrintUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTotalFortyDiscounts((valueTransformer));
			}
			if(key.contains("Total Jewelry Bonus")){
				valueTransformer = PrintUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTotalBonusJeverly((valueTransformer));
			}
			if(key.contains("Total Marketing Bonus")){
				valueTransformer = PrintUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setTotalMarketingBonus((valueTransformer));
			}
			
		
			
			
		}
		return result;
	}
	

}
