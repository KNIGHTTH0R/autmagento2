package com.pages.frontend.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.CustomVerification;
import com.tools.constants.TimeConstants;
import com.tools.data.backend.OrderModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.DateUtils;

import net.serenitybdd.core.annotations.findby.FindBy;

public class ProfileHistoryPage extends AbstractPage {

	@FindBy(css = "table#my-orders-table")
	private WebElement listContainer;

	@FindBy(css = "#my-orders-table tbody")
	private WebElement orderListContainer;

	@FindBy(css = ".block-content li:nth-child(5) a")
	private WebElement myOrdersLink;

	@FindBy(css = "a[href*='reorder']")
	private WebElement reorderLink;

	@FindBy(css = "#order_history")
	private WebElement tableOrderHistory;

	@FindBy(css = "#tab-button-related")
	
	private WebElement dokumenteTab;
	
	@FindBy(css = "#tabs-2 p")
	private WebElement dokMessage;
	
	@FindBy(css = "#sendEmailSubmitBtn")
	private WebElement sendEmailBtn;
	
	
	@FindBy(css = "#email_receiver_type")
	private WebElement specifiedEmailAddress;
	
	@FindBy(css = ".input-text.send-to-email")
	private WebElement emailReceiverInput;
	
	
	@FindBy(css = "#success")
	private WebElement successMessage;
	
	

	public List<OrderModel> grabOrderHistory() {
		waitABit(5000);
		element(tableOrderHistory).waitUntilVisible();
		List<WebElement> orderList = tableOrderHistory.findElements(By.cssSelector("tbody > tr"));

		List<OrderModel> result = new ArrayList<OrderModel>();

		for (WebElement elementNow : orderList) {
			OrderModel orderNow = new OrderModel();
			String orderId = elementNow.findElement(By.cssSelector("td:nth-child(1)")).getText();
			String date = elementNow.findElement(By.cssSelector("td:nth-child(2)")).getText();
			String invoiceTo = elementNow.findElement(By.cssSelector("td:nth-child(3)")).getText();
			String deliveryTo = elementNow.findElement(By.cssSelector("td:nth-child(4)")).getText();
			String totalSum = elementNow.findElement(By.cssSelector("td:nth-child(5)")).getText();
			String status = elementNow.findElement(By.cssSelector("td:nth-child(6)")).getText();

			orderNow.setOrderId(orderId);
			orderNow.setDate(date);
			orderNow.setInvoiceContact(invoiceTo);
			orderNow.setDeliveryContact(deliveryTo);
			orderNow.setTotalPrice(totalSum);
			orderNow.setStatus(status);

			result.add(orderNow);

		}

		return result;
	}

	public List<OrderModel> grabOrderDetails(String idOrder) {
		// waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
		// ContextConstants.LOADING_MESSAGE));
		waitABit(TimeConstants.TIME_MEDIUM);
		element(tableOrderHistory).waitUntilVisible();
		List<WebElement> orderList = tableOrderHistory.findElements(By.cssSelector("tbody > tr"));
		System.out.println("idOrder " + idOrder);
		List<OrderModel> result = new ArrayList<OrderModel>();
		boolean found = false;
		theFor: for (WebElement elementNow : orderList) {
			System.out.println(elementNow.getText());
			if (elementNow.getText().toLowerCase().contains(idOrder)) {
				found = true;
				OrderModel orderNow = new OrderModel();
				String orderId = elementNow.findElement(By.cssSelector("td:nth-child(1)")).getText();
				String date = elementNow.findElement(By.cssSelector("td:nth-child(2)")).getText();
				String invoiceTo = elementNow.findElement(By.cssSelector("td:nth-child(3)")).getText();
				String deliveryTo = elementNow.findElement(By.cssSelector("td:nth-child(4)")).getText();
				String totalSum = elementNow.findElement(By.cssSelector("td:nth-child(5)")).getText();
				String status = elementNow.findElement(By.cssSelector("td:nth-child(6)")).getText();

				orderNow.setOrderId(orderId);
				orderNow.setDate(date);
				orderNow.setInvoiceContact(invoiceTo);
				orderNow.setDeliveryContact(deliveryTo);
				orderNow.setTotalPrice(totalSum);
				orderNow.setStatus(status);

				result.add(orderNow);

				break theFor;
			}
		}
		Assert.assertTrue("The order was not found in the list !!!", found);

		return result;

	}

	public void clickOnOrder(String orderId) {
		// waitFor(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".blockUI.blockMsg.blockElement"),
		// ContextConstants.LOADING_MESSAGE));
		waitABit(TimeConstants.TIME_MEDIUM);
		element(tableOrderHistory).waitUntilVisible();
		List<WebElement> orderList = tableOrderHistory.findElements(By.cssSelector("tbody > tr"));
		boolean found = false;
		theFor: for (WebElement elementNow : orderList) {
			System.out.println(elementNow.getText());
			if (elementNow.getText().toLowerCase().contains(orderId)) {
				System.out.println("l-am gasit");
				found = true;
				elementNow.findElement(By.cssSelector("td:nth-child(1) a")).click();
				break theFor;
			}
		}
		Assert.assertTrue("The order was not found in the list !!!", found);

	}

	// public void clickReorderLink(String name) {
	// evaluateJavascript("jQuery.noConflict();");
	// element(orderListContainer).waitUntilVisible();
	// List<WebElement> listElements =
	// orderListContainer.findElements(By.tagName("tr"));
	// theFor: for (WebElement elementNow : listElements) {
	// if (elementNow.getText().contains(name)) {
	// elementNow.findElement(By.cssSelector("a[href*='reorder']")).click();
	// break theFor;
	// }
	// }
	// }

	public void clickReorderLink(String name) {
		evaluateJavascript("jQuery.noConflict();");
		System.out.println("Sunt aici");
		element(reorderLink).waitUntilVisible();
		reorderLink.click();

	}

	public void clickOnDokumenteTab() {
		// TODO Auto-generated method stub
		evaluateJavascript("jQuery.noConflict();");
		element(dokumenteTab).waitUntilVisible();
		dokumenteTab.click();
	}

	public void valdateOriginalInvoiceIsReceived(boolean isReceived) {

		String dateCompare = "";
		waitABit(2000);
		List<WebElement> documents = getDriver().findElements(By.cssSelector("#order_related_documents tbody tr"));

		if (isReceived) {
			CustomVerification.verifyTrue("Failure: Original invoice file is not received",
					documents.get(0).findElement(By.cssSelector("td:nth-child(2)")).getText().contains("invoice"));
			dateCompare = extractDocDate(documents.get(0).findElement(By.cssSelector("td:nth-child(1)")).getText());
			CustomVerification.verifyTrue("Failure: Date is not displayed correctly",
					 DateUtils.getCurrentDate("dd MMMM yyyy",Locale.GERMANY).contains(dateCompare));
		} else {
			Assert.assertTrue("Failure: Original invoice file is displayed and should not be ", documents.size() == 0);
		}

	}
	
	
	public void valdateOriginalReturnIsReceived(boolean isReceived) {
		waitABit(2000);
		List<WebElement> documents = getDriver().findElements(By.cssSelector("#order_related_documents tbody tr"));

		if (isReceived) {
			CustomVerification.verifyTrue("Failure: Original invoice file is not received",
					documents.get(0).findElement(By.cssSelector("td:nth-child(3)")).getText().contains("return form"));
		
		} else {
			Assert.assertTrue("Failure: Original invoice file is displayed and should not be ", !documents.get(0).getText().contains("return form"));
		}

	}

	

	public String extractDocDate(String dateString) {
		String dateCompare = dateString.replace(".", "");
		
		System.out.println(DateUtils.getCurrentDate("dd MMMM yyyy",Locale.GERMANY));
		if (DateUtils.getCurrentDate("dd MMMM yyyy",Locale.GERMANY).contains(dateCompare)) {
			System.out.println("evrica");
		}
		return dateCompare;
	}

	public void valdateExchangeDocIsReceived(boolean isReceived) {

		String dateCompare = "";
		waitABit(2000);
		List<WebElement> documents = getDriver().findElements(By.cssSelector("#order_related_documents tbody tr"));

		if (isReceived) {
			CustomVerification.verifyTrue("Failure: Exchange file is not received",
					documents.get(0).findElement(By.cssSelector("td:nth-child(2)")).getText().contains("exchange letter"));
			dateCompare = extractDocDate(documents.get(0).findElement(By.cssSelector("td:nth-child(1)")).getText());
			CustomVerification.verifyTrue("Failure: Date is not displayed correctly",
					 DateUtils.getCurrentDate("dd MMMM yyyy",Locale.GERMANY).contains(dateCompare));
		} else {
			Assert.assertTrue("Failure: Exchange file is displayed and should not be ", documents.size() == 1);
		}
	}

	
	public void valdateExchangeReturnIsReceived( boolean isReceived) {
		waitABit(2000);
		List<WebElement> documents = getDriver().findElements(By.cssSelector("#order_related_documents tbody tr"));

		if (isReceived) {
			CustomVerification.verifyTrue("Failure: Original invoice file is not received",
					documents.get(0).findElement(By.cssSelector("td:nth-child(3)")).getText().contains("return form"));
		
		} else {
			Assert.assertTrue("Failure: Original invoice file is displayed and should not be ", !documents.get(0).getText().contains("return form"));
		}		
	}

	public void validateMessage(String message) {
		element(dokMessage).waitUntilVisible();
		CustomVerification.verifyTrue("Failure: The expected message is not displayed", dokMessage.getText().contains(message));
	}

	public void sendOriginalDocumentsToCustomer() {
		element(sendEmailBtn).waitUntilVisible();
		sendEmailBtn.click();
		waitABit(2000);
		CustomVerification.verifyTrue("Failure: the mail confirmation message is not displayed", successMessage.getText().contains("Email gesendet"));
		
		
	}
	
	public void clickSpecifiedEmailAddressOption(){
		waitForFancyBox();
		element(specifiedEmailAddress).waitUntilVisible();
		specifiedEmailAddress.click();
	}
	
	public void insertSpecifiedEmailAddress(String emailAddress){
		waitABit(2000);
		element(emailReceiverInput).waitUntilVisible();
		emailReceiverInput.sendKeys(emailAddress);
	}

	public void clickOnEmailIcon(String docName) {
		waitABit(2000);
		
		List<WebElement> documents = getDriver().findElements(By.cssSelector("#order_related_documents tbody tr"));
		
		for (WebElement doc : documents) {
			if(doc.findElement(By.cssSelector("td:nth-child(2)")).getText().contains(docName)){
				clickElement(doc.findElement(By.cssSelector(" td:nth-child(4) a")));
				System.out.println("done");
				break;
			}
		}
	}



	
	
	
}
