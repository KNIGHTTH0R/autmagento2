package com.steps.frontend.reports;

import com.tools.data.frontend.TermPurchaseIpModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class IpReportsSteps extends AbstractSteps{

	private static final long serialVersionUID = 1L;
	
	@Step
	public TermPurchaseIpModel grabIpsInfo(){
		 return ipReportsPage().grabIpsInfo();
	}

}
