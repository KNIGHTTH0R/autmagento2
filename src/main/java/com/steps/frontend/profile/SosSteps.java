package com.steps.frontend.profile;

import java.util.List;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import com.tools.constants.ContextConstants;
import com.tools.data.backend.JewelryHistoryModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.generalCalculation.JewelryBonusHistoryCalulation;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.DateUtils;

public class SosSteps extends AbstractSteps {

	private static final long serialVersionUID = 1221784607709066875L;

	@Step
	public boolean getSosUserEmail() {
//		return sosPage().getUsername();
		return sosPage().checkUsernameField();
	}

	@Step
	public boolean getSosPass() throws Exception {
//		return sosPage().getSosPassword();
		return sosPage().checkSosPassword();
	}
	
	@Step
	public void verifySosMessage() {
		sosPage().verifySosMessage();
	}



	@Step
	public void validateSosUsername(String expectedTotal, String actualTotal) {
		Assert.assertTrue("The sos username doesn't match - Expected: " + expectedTotal + "   Actual: " + actualTotal, expectedTotal.contentEquals(actualTotal));
	}

	@Step
	public void validateSosPass(String expectedTotal, String actualTotal) {
		Assert.assertTrue("The sos password doesn't match - Expected: " + expectedTotal + "   Actual: " + actualTotal, expectedTotal.contentEquals(actualTotal));
	}




}
