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
	
	@Step
	public void waitMore(){
		waitABit(9000);
	}

	public List<OrderModel> grabOrderHistory() {
		return profileHistoryPage().grabOrderHistory();
	}



}
