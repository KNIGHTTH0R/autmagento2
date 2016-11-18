package com.steps.frontend.reports;

import java.util.List;


import com.tools.data.IpOverViewSummaryModel;
import com.tools.data.IpOverViewIpCorrectionModel;
import com.tools.data.IpOverViewOpenIpsModel;
import com.tools.data.IpOverViewPayedOrdersModel;
import com.tools.data.IpOverViewReturnsListModel;
import com.tools.data.frontend.TermPurchaseIpModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class IpReportsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public TermPurchaseIpModel grabIpsInfo() {
		return ipReportsPage().grabIpsInfo();
	}

	@Step
	public IpOverViewSummaryModel getIpOverviewSummaryModel() {
		return ipReportsPage().getIpOverviewSummaryModel();
	}
	
	@Step
	public  IpOverViewOpenIpsModel getOpenIpsModel() {
		return ipReportsPage().getOpenIpsModel();
	}

	@Step
	public List<IpOverViewPayedOrdersModel> getPayedOrdersModel() {
		return ipReportsPage().getPayedOrdersModel();
	}

	@Step
	public List<IpOverViewReturnsListModel> getReturnsListModel() {
		return ipReportsPage().getReturnsListModel();
	}

	@Step
	public List<IpOverViewIpCorrectionModel> getIpCorrectionModel() {
		return ipReportsPage().getIpCorrectionModel();
	}

	@Step
	public void selectMonth(String month) {
		ipReportsPage().selectMonthForReport(month);
		
	}

	

}
