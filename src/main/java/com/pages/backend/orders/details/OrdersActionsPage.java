package com.pages.backend.orders.details;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import com.tools.requirements.AbstractPage;

public class OrdersActionsPage extends AbstractPage{
	@FindBy(css = "#order_history_block button:nth-child(3)")
	private WebElement markAsPaidButton;
	
	public void markOrderAsPaid(){
		element(markAsPaidButton).waitUntilVisible();
		String onClick = markAsPaidButton.getAttribute("onclick");
		evaluateJavascript("jQuery.noConflict();");
		onClick += " window.confirm = function(submitConfirmAndReloadPage){return true;};";
		System.out.println("---- OnClick: " + onClick);
		evaluateJavascript(onClick);
		getAlert().accept();
		evaluateJavascript("jQuery.noConflict();");
		getDriver().switchTo().defaultContent();
		evaluateJavascript("jQuery.noConflict();");
	}
	
}
