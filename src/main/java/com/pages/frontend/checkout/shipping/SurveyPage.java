package com.pages.frontend.checkout.shipping;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;
import com.tools.PrintUtils;
import com.tools.data.CartTotalsModel;

public class SurveyPage extends AbstractPage{

	@FindBy(css = "div.checkout-totals-section")
	private WebElement surveyTotalsContainer;
	
	@FindBy(css = "button#submit-step")
	private WebElement toPaymentButton;
	
	
	/**
	 * Note: Only the Subtotal, Discount, Shipping Tax and Total Amount are passed in the model
	 * @return
	 */
	public CartTotalsModel grabSurveyData(){
		CartTotalsModel result = new CartTotalsModel();
		element(surveyTotalsContainer).waitUntilVisible();
		
		result.setSubtotal(PrintUtils.cleanNumberToString(surveyTotalsContainer.findElement(By.cssSelector("tr.subtotal td.a-right")).getText()));
		result.setDiscount(PrintUtils.cleanNumberToString(surveyTotalsContainer.findElement(By.cssSelector("tr.all_discounts td.a-right")).getText()));
		result.setShipping(PrintUtils.cleanNumberToString(surveyTotalsContainer.findElement(By.cssSelector("tr.shipping_tax td.a-right")).getText()));
		result.setTotalAmount(PrintUtils.cleanNumberToString(surveyTotalsContainer.findElement(By.cssSelector("tr.grand_total td.a-right")).getText()));
		
		return result;
	}
	
	public void clickGoToPaymentMethod(){
		element(toPaymentButton).waitUntilVisible();
		toPaymentButton.click();
	}
	
	
}
