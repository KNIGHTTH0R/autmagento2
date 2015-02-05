package com.steps.frontend;

import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import com.tools.CustomVerification;
import com.tools.data.backend.OrderModel;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.FormatterUtils;

public class ProfileSteps extends AbstractSteps{

	private static final long serialVersionUID = -6439771925934414965L;
	
	@Steps
	public static CustomVerification customVerification;
	
	@Step
	public void openProfileHistory(){
		dashboardMenuPage().clickOnProfileHistory();
	}
	
	/**
	 * Grab Order History List data from Profile OrderHistory
	 * @return
	 */
	public List<OrderModel> grabOrderHistory() {
		return profileHistoryPage().grabOrderHistory();
	}
	
	
	@Step
	public void verifyOrderId(String orderId, String compare){
		customVerification.verifyTrue("Failure: Order id is not as expected. Expected: " + compare + " Actual: " + orderId, orderId.contentEquals(compare));
	}

	@Step
	public void verifyOrderPrice(String orderPrice, String compare) {
		orderPrice = FormatterUtils.cleanNumberToString(orderPrice).replace(".", "");
		customVerification.verifyTrue("Failure: Order Price is not as expected. Expected: " + compare + " Actual: " + orderPrice, orderPrice.contentEquals(compare));
	}

}
