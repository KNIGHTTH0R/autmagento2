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
			
			if(key.contains("Zwischensumme")){
				valueTransformer = PrintUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setSubtotal((valueTransformer));
			}
			if(key.contains("Lieferung und Verarbeitung")){
				valueTransformer = PrintUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setSubtotal((valueTransformer));
			}
			if(key.contains("Rabatt (25% Style Coach Discount)")){
				valueTransformer = PrintUtils.cleanNumberToString(elementNow.findElement(By.cssSelector("td:last-child")).getText());
				result.setSubtotal((valueTransformer));
			}
			
		
			
			
		}
		return result;
	}
	

}
