package com.pages.backend.orders.details;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractPage;

public class OrdersActionsPage extends AbstractPage {

	@FindBy(css = "#order_history_block button:nth-child(3)")
	private WebElement markAsPaidButton;

	@FindBy(css = "li.success-msg")
	private WebElement successMessage;

	@FindBy(css = "button.scalable.save.submit-button")
	private WebElement refundOffline;

	@FindBy(css = "button[onclick*='/sales_order_invoice/start/order_id/']")
	private WebElement invoiceButton;

	@FindBy(css = "button[onclick*='/sales_order_creditmemo/start/order_id/']")
	private WebElement creditMemoButton;

	@FindBy(css = "input#invoice_do_shipment")
	private WebElement createShippment;

	@FindBy(css = "button.scalable.save.submit-button")
	private WebElement submitInvoice;

	public void markOrderAsPaid() {
		element(markAsPaidButton).waitUntilVisible();
		String onClick = markAsPaidButton.getAttribute("onclick");
		evaluateJavascript("jQuery.noConflict();");
		onClick += " window.confirm = function(submitConfirmAndReloadPage){return true;};";
		evaluateJavascript(onClick);
		getAlert().accept();
		evaluateJavascript("jQuery.noConflict();");
		getDriver().switchTo().defaultContent();
		evaluateJavascript("jQuery.noConflict();");
	}

	public void clickInvoiceButton() {
		evaluateJavascript("jQuery.noConflict();");
		element(invoiceButton).waitUntilVisible();
		invoiceButton.click();
	}

	public void clickcreditMemoButton() {
		evaluateJavascript("jQuery.noConflict();");
		element(creditMemoButton).waitUntilVisible();
		creditMemoButton.click();
	}

	public void checkCreateShippment() {
		evaluateJavascript("jQuery.noConflict();");
		element(createShippment).waitUntilVisible();
		createShippment.click();
	}

	public void submitInvoice() {
		evaluateJavascript("jQuery.noConflict();");
		element(submitInvoice).waitUntilVisible();
		submitInvoice.click();
	}

	public void refundOffline() {
		evaluateJavascript("jQuery.noConflict();");
		element(refundOffline).waitUntilVisible();
		System.out.println(refundOffline.getText());
		refundOffline.click();		
	}

	public void verifyInvoiceShippingSubmitedMessage() {
		element(successMessage).waitUntilVisible();
		Assert.assertTrue("Failure: The mesage should be " + ContextConstants.INVOICE_SHIPPING_SUBMITED_MESSAGE + " and it's not! Actual: " + successMessage.getText(),
				successMessage.getText().contains(ContextConstants.INVOICE_SHIPPING_SUBMITED_MESSAGE));
	}
	public void verifyRefundedSuccessMessage() {
		evaluateJavascript("jQuery.noConflict();");
		element(successMessage).waitUntilVisible();
		Assert.assertTrue("Failure: The mesage should be " + ContextConstants.REFUNDED_SUCCESS_MESSAGE + " and it's not! Actual: " + successMessage.getText(),
				successMessage.getText().contains(ContextConstants.REFUNDED_SUCCESS_MESSAGE));
	}
}
