package com.steps.frontend.reports;

import java.util.List;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import com.tools.data.backend.OrderModel;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.FormatterUtils;

public class StylistsCustomerOrdersReportSteps extends AbstractSteps{

	private static final long serialVersionUID = -6439771925934414965L;
	
	

	
	/**
	 * Grab Stylist's Customer's Order Reports 
	 * @return
	 */
	public List<OrderModel> grabOrderReport() {
		return stylistsCustomerOrderReportPage().grabCustomerOrdersReport();
	}
	
	
	@Step
	public void verifyOrderId(String orderId, String compare){
		Assert.assertTrue("Failure: Order id is not as expected. Expected: " + compare + " Actual: " + orderId, orderId.toLowerCase().contentEquals(compare.toLowerCase()));
	}

	@Step
	public void verifyOrderPrice(String orderPrice, String compare) {
		orderPrice = FormatterUtils.cleanNumberToString(orderPrice).replace(".", "");
		Assert.assertTrue("Failure: Order Price is not as expected. Expected: " + compare + " Actual: " + orderPrice, orderPrice.contentEquals(compare));
	}

}
