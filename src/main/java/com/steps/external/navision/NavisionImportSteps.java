package com.steps.external.navision;

import net.thucydides.core.annotations.Step;

import com.connectors.http.ImportInterfaceCalls;
import com.tools.requirements.AbstractSteps;

public class NavisionImportSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void importOrderInNav(String url, String orderId) {
		ImportInterfaceCalls.importOrderInNav(url, orderId);
	}

}
