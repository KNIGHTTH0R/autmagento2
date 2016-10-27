package com.steps.frontend.profile;

import java.util.List;

import org.junit.Assert;

import com.tools.data.backend.OrderModel;
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
	public List<OrderModel> grabOrderHistory() {
		return profileHistoryPage().grabOrderHistory();
	}

	@Step
	public void verifyOrderId(String orderId, String compare) {
		Assert.assertTrue("Failure: Order id is not as expected. Expected: " + compare + " Actual: " + orderId, orderId.contains(compare));
	}

	@Step
	public void verifyOrderPrice(String orderPrice, String compare) {
		orderPrice = FormatterUtils.parseValueToZeroDecimals(orderPrice);
		Assert.assertTrue("Failure: Order Price is not as expected. Expected: " + compare + " Actual: " + orderPrice, orderPrice.contentEquals(compare));
	}
	@Step
	public void clickReorderLink(String orderId) {
		profileHistoryPage().clickReorderLink(orderId);
	}

}
