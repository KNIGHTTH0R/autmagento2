package com.pages.backend.orders.details;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tools.CustomVerification;
import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.requirements.AbstractPage;
import com.tools.utils.DateUtils;

import net.serenitybdd.core.annotations.findby.FindBy;

public class OrdersActionsPage extends AbstractPage {

	@FindBy(css = "#order_history_block button:nth-child(3)")
	private WebElement markAsPaidButton;

	@FindBy(css = "#order_history_block button:nth-child(2)")
	private WebElement uncancelOrderButton;

	@FindBy(css = "button[onclick*='/sales_order/cancel/order_id/']")
	private WebElement cancelOrderButton;

	@FindBy(css = "li.success-msg")
	private WebElement successMessage;

	@FindBy(css = "button.scalable.save.submit-button")
	private WebElement refundOffline;

	@FindBy(css = "button[onclick*='/sales_order_invoice/start/order_id/']")
	private WebElement invoiceButton;

	@FindBy(css = "button[onclick*='/sales_order_shipment/start/order_id/']")
	private WebElement shipButton;

	@FindBy(id = "_orderfilesadditional_email_receiver")
	private WebElement emailReceiverInput;

	@FindBy(id = "_orderfilessubmitBtn")
	private WebElement clickSubmitEmailBtn;

	@FindBy(id = "send_email")
	private WebElement shipmentEmailCheckBox;

	@FindBy(css = "button[onclick*='/sales_order_creditmemo/start/order_id/']")
	private WebElement creditMemoButton;

	@FindBy(css = "input#invoice_do_shipment")
	private WebElement createShippment;

	@FindBy(css = "button.scalable.save.submit-button")
	private WebElement submitInvoice;

	@FindBy(css = "button.scalable.save.submit-button")
	private WebElement submitShipmentBtn;

	@FindBy(css = "#sales_order_view_tabs_adyenPayment_order_notifications")
	private WebElement adyenNotificationTab;

	@FindBy(css = "#dt-order_related_files_form")
	private WebElement navDocSectionTab;
	
	@FindBy(css = "button[title*='Trackingnummer']")
	private WebElement trackingNoBtn;
	
	
	@FindBy(css = "#trackingN1")
	private WebElement inputTrackingNo;
	
	

	public void markOrderAsPaid() {
		waitABit(2000);
		//scrollPageDown();
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

	////////////// emilian pom modfications

	public void waitForLoading() {
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.id("loading_mask_loader"),
				ConfigConstants.LOADING));

		// waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector("loading_mask_loader"),ConfigConstants.LOADING));
	}

	public void cancelOrder() {
		element(cancelOrderButton).waitUntilVisible();
		cancelOrderButton.click();
		getAlert().accept();
		evaluateJavascript("jQuery.noConflict();");
		getDriver().switchTo().defaultContent();
		evaluateJavascript("jQuery.noConflict();");
	}

	public void uncancelOrder() {
		element(uncancelOrderButton).waitUntilVisible();
		String onClick = uncancelOrderButton.getAttribute("onclick");
		evaluateJavascript("jQuery.noConflict();");
		onClick += " window.confirm = function(deleteConfirm){return true;};";
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

	public void clickShipButton() {
		evaluateJavascript("jQuery.noConflict();");
		element(shipButton).waitUntilVisible();
		shipButton.click();
	}

	public void clickcreditMemoButton() {
		evaluateJavascript("jQuery.noConflict();");
		element(creditMemoButton).waitUntilVisible();
		creditMemoButton.click();
	}

	public void checkShipmentMail() {
		evaluateJavascript("jQuery.noConflict();");
		element(shipmentEmailCheckBox).waitUntilVisible();
		shipmentEmailCheckBox.click();
	}
	
	public void checkInvoiceMail() {
		evaluateJavascript("jQuery.noConflict();");
		element(shipmentEmailCheckBox).waitUntilVisible();
		shipmentEmailCheckBox.click();
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
		waitFor(ExpectedConditions.visibilityOf(successMessage));
	}

	public void submitShipment() {
		evaluateJavascript("jQuery.noConflict();");
		element(submitShipmentBtn).waitUntilVisible();
		submitShipmentBtn.click();
		waitFor(ExpectedConditions.visibilityOf(successMessage));
	}

	public void refundOffline() {
		evaluateJavascript("jQuery.noConflict();");
		element(refundOffline).waitUntilVisible();
		System.out.println(refundOffline.getText());
		refundOffline.click();
	}

	public void verifyInvoiceShippingSubmitedMessage() {
		element(successMessage).waitUntilVisible();
		Assert.assertTrue(
				"Failure: The mesage should be " + ContextConstants.INVOICE_SHIPPING_SUBMITED_MESSAGE
						+ " and it's not! Actual: " + successMessage.getText(),
				successMessage.getText().contains(ContextConstants.INVOICE_SHIPPING_SUBMITED_MESSAGE));
	}

	public void verifyShippingSubmitedMessage() {
		element(successMessage).waitUntilVisible();
		Assert.assertTrue("Failure: The mesage should be " + "Die Lieferung wurde erstellt." + " and it's not! Actual: "
				+ successMessage.getText(), successMessage.getText().contains("Die Lieferung wurde erstellt."));
	}

	public void verifyInvoiceSubmitedMessage() {
		element(successMessage).waitUntilVisible();
		Assert.assertTrue(
				"Failure: The mesage should be " + ContextConstants.INVOICE_SUBMITED_MESSAGE + " and it's not! Actual: "
						+ successMessage.getText(),
				successMessage.getText().contains(ContextConstants.INVOICE_SUBMITED_MESSAGE));
	}

	public void verifyRefundedSuccessMessage() {
		evaluateJavascript("jQuery.noConflict();");
		element(successMessage).waitUntilVisible();
		Assert.assertTrue(
				"Failure: The mesage should be " + ContextConstants.REFUNDED_SUCCESS_MESSAGE + " and it's not! Actual: "
						+ successMessage.getText(),
				successMessage.getText().contains(ContextConstants.REFUNDED_SUCCESS_MESSAGE));
	}

	public void openDocumentsSection() {
		evaluateJavascript("jQuery.noConflict();");
		element(navDocSectionTab).waitUntilVisible();

		Actions actions = new Actions(getDriver());
		actions.moveToElement(navDocSectionTab).click().perform();
		waitABit(4000);

	}

	public void valdateOriginalInvoiceIsReceived(String orderId, boolean isReceived) {
		String dateCompare="";
		waitABit(2000);
		List<WebElement> documents = getDriver()
				.findElements(By.cssSelector("#_orderfilesbase_fieldset .checkboxes li label[for*='related']"));

		if (isReceived) {
			CustomVerification.verifyTrue("Failure: Original invoice file is not received",
					documents.get(0).getText().contains("INVOICE_" + orderId + ".pdf"));
			dateCompare=extractDocDate(documents.get(0).getText());
			CustomVerification.verifyTrue("Failure: Date is not displayed correctly",
					dateCompare.contentEquals(DateUtils.getCurrentDate("dd MMM yyyy")));
		} else {
			Assert.assertTrue("Failure: Original invoice file is displayed and should not be ", documents.size() == 0);
		}

	}
	
	public String  extractDocDate(String dateString){
		String dateSubstring=dateString.substring(dateString.indexOf("pdf")+3); 
		String day= dateSubstring.substring(2, dateSubstring.indexOf(".")); 
		String month=dateSubstring.substring(dateSubstring.indexOf(". ")+2, dateSubstring.indexOf(" 2")); 
		String year=dateSubstring.substring(dateSubstring.indexOf(" 2")+1, dateSubstring.indexOf(")")); 
		
		String dateCompare=day+" "+month.substring(0,3)+" "+year;
		
		if(dateCompare.contentEquals(DateUtils.getCurrentDate("dd MMM yyyy"))){
			System.out.println("evrica");
		}
		return dateCompare;
	}

	public static void main(String[] args) {
		String date="INVOICE_10026903600.pdf (22. November 2017) ";
		String dateSubstring=date.substring(date.indexOf("pdf")+3); 
		String day= dateSubstring.substring(2, dateSubstring.indexOf(".")); 
		String month=dateSubstring.substring(dateSubstring.indexOf(". ")+2, dateSubstring.indexOf(" 2")); 
		String year=dateSubstring.substring(dateSubstring.indexOf(" 2")+1, dateSubstring.indexOf(")")); 
		
		System.out.println(dateSubstring);
		System.out.println(day);
		System.out.println(month);
		System.out.println(year);
		String dateCompare=day+" "+month.substring(0,3)+" "+year;
		
		if(dateCompare.contentEquals(DateUtils.getCurrentDate("dd MMM yyyy"))){
			System.out.println("evrica");
		}
	//System.out.println(DateUtils.getCurrentDate("dd MMM yyyy"));	
		//DateUtils.addDaysToAAGivenDate(dateString, formatString, days)
	}
	
	
	public void valdateOriginalReturnIsReceived(String orderId, boolean isReceived) {
		// TODO Auto-generated method stub
		List<WebElement> documents = getDriver()
				.findElements(By.cssSelector("#_orderfilesbase_fieldset .checkboxes li label[for*='related']"));
		if (isReceived) {
			CustomVerification.verifyTrue("Failure: Original return file is not received",
					documents.get(1).getText().contains("RETURN_FORM_" + orderId + ".pdf"));
		} else {
			Assert.assertTrue("Failure: Original return file is displayed and should not be ", documents.size() < 2);
		}

	}

	public void validateEchangeDocIsReceived(String orderId, boolean isReceived) {

		waitABit(2000);
		boolean isDisplayed = false;
		List<WebElement> documents = getDriver()
				.findElements(By.cssSelector("#_orderfilesbase_fieldset .checkboxes li label[for*='related']"));


		for (WebElement doc : documents) {
			if (doc.getText().contains("EXCHANGE")) {
				isDisplayed = true;
			}
		}

		if (isReceived) {
			CustomVerification.verifyTrue("Failure: Exchange file is not received",
					isDisplayed);
		} else {
			Assert.assertTrue("Failure: Exchange file is displayed and should not be ", isDisplayed==false);
		}

	}

	public void valdateExcangeReturnIsReceived(String orderId, boolean isReceived, int noOfFile) {
		// TODO Auto-generated method stub
		List<WebElement> documents = getDriver()
				.findElements(By.cssSelector("#_orderfilesbase_fieldset .checkboxes li label[for*='related']"));

		// Assert.assertTrue("The no of duments is not correct
		// expected:"+noOfFile+", actual:"+documents.size(),
		// noOfFile==documents.size());
		CustomVerification.verifyTrue("The exchange retunr is not receioved ",
				documents.get(documents.size() - 1).getText().contains("RETURN_FORM_"));

	}

	public void verifyCompleteStatus() {
		List<WebElement> statusList = getDriver().findElements(By.cssSelector("#order_history_block .note-list li"));
		Assert.assertTrue("Failure: The complete status is not displayed",
				statusList.get(0).getText().contains("Vollständig"));
	}

	public void sendIndividualDocumentToSpecificReceiver(String documentName) {
		evaluateJavascript("jQuery.noConflict();");
		List<WebElement> documents = getDriver()
				.findElements(By.cssSelector(".checkboxes li "));

		for (WebElement doc : documents) {
			if (doc.getText().contains(documentName)) {
				doc.findElement(By.cssSelector("input")).click();
				break;
			}
		}

	}
	


	public void clickSubmitEmail() {
		element(clickSubmitEmailBtn).waitUntilVisible();
		clickSubmitEmailBtn.click();
	}

	public void insertReceiver(String receiver) {
		waitABit(2000);
		element(emailReceiverInput).waitUntilVisible();
		emailReceiverInput.sendKeys(receiver);
	}

	public void selectReceiverMethod(String receiver) {
		Select oSelect = new Select(getDriver().findElement(By.id("_orderfilesemail_receiver")));
		oSelect.selectByValue(receiver);
	}

	public void clickTrakingNumberBtn() {
		evaluateJavascript("jQuery.noConflict();");

		element(trackingNoBtn).waitUntilVisible();
		clickElement(trackingNoBtn);
		//trackingNoBtn.click();
	}
	
	public void selectTrakingNoMethod(String method) {
		evaluateJavascript("jQuery.noConflict();");
		waitABit(2000);
		Select oSelect = new Select(getDriver().findElement(By.id("trackingC1")));
		oSelect.selectByValue(method);
	}
	
	public void insertTrackingNo(String trackingNo){
		evaluateJavascript("jQuery.noConflict();");

		element(inputTrackingNo).waitUntilVisible();
		inputTrackingNo.sendKeys(trackingNo);
	}
	


}
