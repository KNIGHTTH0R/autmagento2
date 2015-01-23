package com.steps.frontend;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.AbstractSteps;
import com.tools.data.OrderModel;

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
}
