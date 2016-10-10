package com.steps.frontend.reports;

import org.junit.Assert;

import com.tools.data.backend.JewelryHistoryModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

public class JewelryBonusHistorySteps extends AbstractSteps {

	private static final long serialVersionUID = -6439771925934414965L;

	@Step
	public JewelryHistoryModel grabJewelryBonusHistory() {
		return jewelryBonusHistoryPage().grabJewelryBonusHistory();
	}

	@Title("Validate the new JB history registration")
	@Step
	public void validateNewHistoryRegistration(JewelryHistoryModel expectedHistoryRegistration, JewelryHistoryModel actualHistoryRegistration) {
		validateTotal(expectedHistoryRegistration.getTotalPoints(), actualHistoryRegistration.getTotalPoints());
		validateAmount(expectedHistoryRegistration.getAmountValue(), actualHistoryRegistration.getAmountValue());
		validateDate(expectedHistoryRegistration.getDate(), actualHistoryRegistration.getDate());
		validateReason(expectedHistoryRegistration.getReason(), actualHistoryRegistration.getReason());
	}

	@Step
	private void validateTotal(String expectedTotal, String actualTotal) {
		Assert.assertTrue("The totals don't match", expectedTotal.contentEquals(actualTotal));
	}

	@Step
	private void validateAmount(String expectedAmount, String actualAmount) {
		Assert.assertTrue("The amounts don't match", expectedAmount.contentEquals(actualAmount));
	}

	@Step
	private void validateDate(String expectedDate, String actualDate) {
		System.out.println("@@@@" + expectedDate + "," + actualDate + "@@@");
		Assert.assertTrue("The dates don't match", expectedDate.contentEquals(actualDate));
	}

	@Step
	private void validateReason(String expectedReason, String actualReason) {
		Assert.assertTrue("The reason doesn't match", expectedReason.contentEquals(actualReason));
	}

}
