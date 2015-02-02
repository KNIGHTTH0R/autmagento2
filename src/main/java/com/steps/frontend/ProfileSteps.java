package com.steps.frontend;

import java.util.List;

import org.junit.Assert;

import net.thucydides.core.annotations.Step;

import com.tools.data.backend.OrderModel;
import com.tools.requirements.AbstractSteps;

public class ProfileSteps extends AbstractSteps{

	private static final long serialVersionUID = -6439771925934414965L;
	
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
	public void verifyOrderId(String orderId){
		Assert.assertTrue("Failure: order id is null", orderId != null);
	}
}
