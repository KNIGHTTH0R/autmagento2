package com.steps.frontend.profile;

import java.util.List;

import org.junit.Assert;

import com.tools.constants.ContextConstants;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.ReturnProductModel;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.FormatterUtils;

import net.thucydides.core.annotations.Step;

public class ProfileSteps extends AbstractSteps {

	private static final long serialVersionUID = -6439771925934414965L;

	/**
	 * Grab Order History List data from Profile OrderHistory
	 * 
	 * @return
	 */
	@Step
	public List<OrderModel> grabOrderHistory() {
		return profileHistoryPage().grabOrderHistory();
	}

	@Step
	public void verifyOrderId(String orderId, String compare) {
		Assert.assertTrue(
				"Failure: Order id is not as expected. Expected: " + compare + " Actual: " + orderId.toLowerCase(),
				orderId.toLowerCase().contains(compare.toLowerCase()));
	}

	@Step
	public void verifyOrderPrice(String orderPrice, String compare) {
		orderPrice = FormatterUtils.parseValueToZeroDecimals(orderPrice);
		Assert.assertTrue("Failure: Order Price is not as expected. Expected: " + compare + " Actual: " + orderPrice,
				orderPrice.contentEquals(compare));
	}

	@Step
	public void clickReorderLink(String orderId) {
		profileHistoryPage().clickReorderLink(orderId);
	}

	@Step
	public void verifySosMessage() {
		sosPage().verifySosMessage();
	}

	@Step
	public void clickOnOrder(String orderId) {
		profileHistoryPage().clickOnOrder(orderId);
	}

	@Step
	public List<OrderModel> grabOrderDetails(String orderId) {
		return profileHistoryPage().grabOrderDetails(orderId);
	}

	@Step
	public void clickOnDokumenteTab() {
		profileHistoryPage().clickOnDokumenteTab();
	}

	@Step
	public void valdateOriginalInvoiceIsReceived(boolean isReceived) {
		profileHistoryPage().valdateOriginalInvoiceIsReceived(isReceived);
	}

	@Step
	public void valdateOriginalReturnIsReceived(boolean isReceived) {
		profileHistoryPage().valdateOriginalReturnIsReceived(isReceived);
	}

	@Step
	public void valdateExchangeDocIsReceived(boolean isReceived) {
		profileHistoryPage().valdateExchangeDocIsReceived(isReceived);
	}

	@Step
	public void valdateExchangeReturnIsReceived(boolean isReceived) {
		profileHistoryPage().valdateExchangeReturnIsReceived(isReceived);
	}

	@Step
	public void validateMessage(String message) {
		profileHistoryPage().validateMessage(message);
	}

	@Step
	public void clickOnReturnLink() {
		profileHistoryPage().clickOnReturnLink();
	}

	public void sendOriginalDocumentsToCustomer(String docName) {
		profileHistoryPage().clickOnEmailIcon(docName);
		profileHistoryPage().sendOriginalDocumentsToCustomer();

	}

	public void sendExchangeDocumentsToSpecificAddress(String docName, String mailAddress) {
		profileHistoryPage().clickOnEmailIcon(docName);
		profileHistoryPage().clickSpecifiedEmailAddressOption();
		profileHistoryPage().insertSpecifiedEmailAddress(mailAddress);
		profileHistoryPage().sendOriginalDocumentsToCustomer();

	}

	public void returnProduct(BasicProductModel product, String returnQty, String productNo) {

		profileHistoryPage().selectProduct(product.getName(), productNo);
		profileHistoryPage().insertReturnQty(returnQty, productNo);
		profileHistoryPage().selectResolution(ContextConstants.RETURN_RESOLUTION, productNo);
		profileHistoryPage().selectReason(ContextConstants.RETURN_REASON, productNo);

	}

	public ReturnProductModel populateDeatailsForReturnedProducts(BasicProductModel product, String returnQty,
			String status) {

		return profileHistoryPage().populateDeatailsForReturnedProducts(product, returnQty, status);

	}

	public void addAnotherArtickle() {
		profileHistoryPage().addAnotherArtickle();
	}

	public void submitReturn() {
		profileHistoryPage().submitReturn();

	}

	public void selectNewestCreatedRMA() {
		profileHistoryPage().selectNewestCreatedRMA();
	}

	public List<ReturnProductModel> grabReturnItemsDetails() {

		return profileHistoryPage().grabReturnItemsDetails();

	}

}
