package com.pages.backend.orders.details;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class OrdersActionsPage extends AbstractPage{
	

	@FindBy(css = "#order_history_block button:nth-child(3)")
	private WebElement markAsPaidButton;
	
	public void markOrderAsPaid(){
		element(markAsPaidButton).waitUntilVisible();
		markAsPaidButton.click();
		try {
	        Alert alert = getDriver().switchTo().alert();
	        String AlertText = alert.getText();
	        System.out.println(AlertText);
	        alert.accept();
	    } catch (Exception e) {
	        System.out.println("no alert");
	    }
	
	}
	
	

}
