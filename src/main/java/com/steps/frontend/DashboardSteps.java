package com.steps.frontend;

import java.util.List;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import com.tools.calculation.JewelryBonusHistoryCalulation;
import com.tools.data.backend.JewelryHistoryModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.DateUtils;

public class DashboardSteps extends AbstractSteps {

	private static final long serialVersionUID = 1221784607709066875L;

	@Step
	public String getStyleCoachFirstNameFromProfile() {
		return dashboardPage().getStyleCoachFirstNameFromProfile();
	}

	@Step
	public String getStyleCoachFullNameFromProfile() {
		return dashboardPage().getStyleCoachFullNameFromProfile();
	}

	@Step
	public String getStyleCoachEmailFromProfile() {
		return dashboardPage().getStyleCoachEmailFromProfile();
	}

	@Step
	public void validateCustomeStyleCoachName(String boutiqueName, String styleCoachName) {
		Assert.assertTrue("Failure: The stylecoach name and boutique name don't match !", boutiqueName.contentEquals(styleCoachName));
		Assert.assertFalse("Failure: The stylecoach name and boutique is empty !", boutiqueName.contentEquals("") || boutiqueName == null);
	}

	@Step
	public void validateCustomerIsAssignedToStyleCoach(String expectedSCName, String grabbedSCName) {
		Assert.assertTrue("Failure: The customer is not assigned to the expected SC !", expectedSCName.contentEquals(grabbedSCName));

	}

	@Step
	public String getJewelryBonus() {
		return dashboardPage().getJewelryBonus();
	}
	@Step
	public String getMarketingMaterialBonus() {
		return dashboardPage().getMarketingMaterialBonus();
	}

	@Step
	public void validateDashboardTotalJewerlyBonus(String expectedTotal, String actualTotal) {
		Assert.assertTrue("The dashboard total JB doesn't match", expectedTotal.contains(actualTotal.replace(".", "").replace(",", ".")));
	}
	@Step
	public void validateDashboardTotalMarketingBonus(String expectedTotal, String actualTotal) {
		Assert.assertTrue("The dashboard totals MMB doesn't match", expectedTotal.contains(actualTotal.replace(".", "").replace(",", ".")));
	}

	@Step
	public DBStylistModel validateCustomerIsAssignedToOneOfTheStyleCoachesAndGetConfig(List<DBStylistModel> stylistsList, String grabbedEmail) {
		boolean match = false;
		DBStylistModel result = new DBStylistModel();
		for (DBStylistModel dbStylistModel : stylistsList) {
			if (dbStylistModel.getEmail().contentEquals(grabbedEmail)) {
				match = true;
				result.setEmail(dbStylistModel.getEmail());
				result.setStatus(dbStylistModel.getStatus());
				result.setFirstName(dbStylistModel.getFirstName());
				result.setLattitude(dbStylistModel.getLattitude());
				result.setLongitude(dbStylistModel.getLongitude());
				result.setQualifiedSC(dbStylistModel.getQualifiedSC());
				result.setQualifiedHost(dbStylistModel.getQualifiedHost());
				result.setQualifiedCustomer(dbStylistModel.getQualifiedCustomer());
				result.setTotalSCReceived(dbStylistModel.getTotalSCReceived());
				result.setTotalHostReceived(dbStylistModel.getTotalHostReceived());
				result.setTotalCustomerReceived(dbStylistModel.getTotalCustomerReceived());
				result.setTotalSCCurrentWeek(dbStylistModel.getTotalSCCurrentWeek());
				result.setTotalHostCurrentWeek(dbStylistModel.getTotalHostCurrentWeek());
				result.setMaxSCPerWeek(dbStylistModel.getMaxSCPerWeek());
				result.setLeadRetrievalPaused(dbStylistModel.getLeadRetrievalPaused());
				result.setStyleCoachLeadRange(dbStylistModel.getStyleCoachLeadRange());
				result.setHostLeadRange(dbStylistModel.getHostLeadRange());

				break;
			}
		}
		Assert.assertTrue("Failure: The customer is not assigned to the expected SC !", match);

		return result;
	}

	@Step
	public JewelryHistoryModel calculateExpectedJewelryConfiguration(String currentTotal, String productJewelryValue, boolean increase) {

		JewelryHistoryModel result = new JewelryHistoryModel();

		if (increase) {
			result.setAmountValue(productJewelryValue);
			result.setTotalPoints(JewelryBonusHistoryCalulation.addNewJewelryBonusToTotal(currentTotal, productJewelryValue));
		} else {
			result.setAmountValue("-" + productJewelryValue);
			result.setTotalPoints(JewelryBonusHistoryCalulation.addNewJewelryBonusToTotal(currentTotal, "0"));
		}
		result.setDate(DateUtils.getCurrentDate("dd.MM.yyyy"));
		// TODO change this hardcoded message when the dev team will implement
		// the message in different languages
		result.setReason(ContextConstants.UPDATED_BY_SYSTEM);

		return result;
	}

}
