package com.pages.backend.creditMemo;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.tools.env.constants.ContextConstants;
import com.tools.requirements.AbstractPage;

public class CreditMemoDetailsPage extends AbstractPage {

	@FindBy(css = "button[onclick*='/sales_order_creditmemo/forcecancel/creditmemo_id/']")
	private WebElement cancelCreditMemoButton;
	
	@FindBy(css = "li.success-msg")
	private WebElement successMessage;

	public void cancelCreditMemo() {
		evaluateJavascript("jQuery.noConflict();");
		element(cancelCreditMemoButton).waitUntilVisible();
		cancelCreditMemoButton.click();
	}
	
	public void verifyCreditMemoRefundedMessage() {
		element(successMessage).waitUntilVisible();
		Assert.assertTrue("Failure: The mesage should be " + ContextConstants.CREDIT_MEMO_REFUNDED + " and it's not! Actual: " + successMessage.getText(), successMessage.getText().contains(ContextConstants.CREDIT_MEMO_REFUNDED));
	}


}
