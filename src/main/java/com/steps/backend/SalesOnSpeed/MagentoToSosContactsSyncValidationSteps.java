package com.steps.backend.SalesOnSpeed;

import java.util.List;

import org.junit.Assert;

import com.connectors.http.JerseyClientSos;
import com.connectors.http.SalesOnSpeedCalls;
import com.tools.CustomVerification;
import com.tools.constants.SalesOnSpeedConstants;
import com.tools.data.salesOnSpeed.MagentoSOSContactModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class MagentoToSosContactsSyncValidationSteps extends AbstractSteps {
	public static final long serialVersionUID = 3609006291221433240L;

	@Step
	public void validatePrename(String customerID, String compare) {
		CustomVerification.verifyTrue("Failure: Prename doesn't match: " + customerID + " - " + compare,
				customerID.contentEquals(compare));
	}

	@Step
	public void validateLastName(String shopLastName, String compare) {
		CustomVerification.verifyTrue("Failure: LastName doesn't match: " + shopLastName + " - " + compare,
				shopLastName.contentEquals(compare));
	}

	@Step
	public void validateCity(String shopCity, String compare) {
		CustomVerification.verifyTrue("Failure: City doesn't match: " + shopCity + " - " + compare,
				shopCity == null || shopCity=="" ? compare == null : shopCity.equals(compare));
	}

	@Step
	public void validateCountry(String shopCountry, String compare) {
		CustomVerification.verifyTrue("Failure: City doesn't match: " + shopCountry + " - " + compare,
				shopCountry.contentEquals(compare));
	}

	@Step
	public void validatePostCode(String postcode, String postcode2) {
		CustomVerification.verifyTrue("Failure: postcode doesn't match: " + postcode + " - " + postcode2,
				postcode == null || postcode==""? postcode2 == null : postcode.equals(postcode2));
	}

	@Step
	public MagentoSOSContactModel findContact(String sosContactId, List<MagentoSOSContactModel> grabbedList) {
		MagentoSOSContactModel result = new MagentoSOSContactModel();
		theFor: for (MagentoSOSContactModel item : grabbedList) {
			if (item.get_id().contains(sosContactId)) {
				result = item;
				break theFor;
			}
		}
		return result;
	}
	
	
	

	@Step
	public void validateContactId(String contactId, String sosContactId) {
		CustomVerification.verifyTrue("Failure: contactId doesn't match: " + contactId + " - " + sosContactId,
				contactId.contentEquals(sosContactId));

	}

	@Step
	public void validateStreet(String street, String sosStreet) {
		CustomVerification.verifyTrue("Failure: Street doesn't match: " + street + " - " + sosStreet,
				street.contentEquals(sosStreet));
	}

	
	@Step
	public void validateContactSosID(String Id, String sosId) {
		CustomVerification.verifyTrue("Failure: Sos ID doesn't match: " + Id + " - " + sosId, Id.contentEquals(sosId));

	}

	@Step
	public void validateUpdatedDate(String updated, String sosUpdated) {
		CustomVerification.verifyTrue("Failure: Updated date doesn't match: " + updated + " - " + sosUpdated,
				updated.contentEquals(sosUpdated));

	}

	@Step
	public void validateCreatedDate(String created, String sosCreated) {
		CustomVerification.verifyTrue("Failure: Created date doesn't match: " + created + " - " + sosCreated,
				created.contentEquals(sosCreated));

	}

	@Step
	public void validateEmail(String email, String sosEmail) {
		CustomVerification.verifyTrue("Failure: Email  doesn't match: " + email + " - " + sosEmail,
				email == null || email=="" ? sosEmail == null : email.equals(sosEmail));
	}

	@Step
	public void validateLanguage(String language, String sosLanguage) {
		// TODO Auto-generated method stub
		CustomVerification.verifyTrue("Failure: language  doesn't match: " + language + " - " + sosLanguage,
				language.contentEquals(sosLanguage));
	}

	@Step
	public void validatePrimaryPhone(String primaryPhoneNumber, String sosPrimaryPhoneNumber) {
		// TODO Auto-generated method stub
		CustomVerification.verifyTrue(
				"Failure: primaryPhoneNumber  doesn't match: " + primaryPhoneNumber + " - " + sosPrimaryPhoneNumber,
				primaryPhoneNumber.contentEquals(sosPrimaryPhoneNumber));
	}

	@Step
	public void validateUnderagedValue(String underagedValue, String sosUnderagedValue) {

		String magUnderagedValue = underagedValue == "1" ? "true" : "false";

		CustomVerification.verifyTrue(
				"Failure: Underaged Value  doesn't match: " + magUnderagedValue + " - " + sosUnderagedValue,
				magUnderagedValue.contentEquals(sosUnderagedValue));
	}
	
	@Step
	public void validateNotInterestedValue(String not_interestedValue, String sosNot_interestedValue) {
		String magNot_interestedValue = not_interestedValue == "1" ? "true" : "false";

		CustomVerification.verifyTrue("Failure: not_interestedValue  doesn't match: " + magNot_interestedValue + " - "
				+ sosNot_interestedValue, magNot_interestedValue.contentEquals(sosNot_interestedValue));

	}

	@Step
	public void validateContactProgress(String contactedProgress, String sosContactedProgress) {
		

		CustomVerification.verifyTrue(
				"Failure: contactedProgress  doesn't match: " + contactedProgress + " - "
						+ sosContactedProgress,
						contactedProgress.contentEquals(sosContactedProgress));
	}

	@Step
	public void validateContactBoosterFlagValue(String flagContactBoosterValue, String sosFlagContactBoosterValue) {
	
		

		CustomVerification.verifyTrue(
				"Failure: flagContactBoosterValue  doesn't match: " + flagContactBoosterValue + " - "	+ sosFlagContactBoosterValue,
				flagContactBoosterValue.contentEquals(sosFlagContactBoosterValue));
	}
	

	@Step
	public void validateContactProgress3Value(String contactedProgress_3Value, String sosContactedProgress_3Value) {
		

		CustomVerification.verifyTrue(
				"Failure: contactedProgress_3Value  doesn't match: " + contactedProgress_3Value + " - "
						+ sosContactedProgress_3Value,
						contactedProgress_3Value.contentEquals(sosContactedProgress_3Value));
		
	}

	@Step
	public void validateWrongDetailsValue(String wrongDetailsValue, String sosWrongDetailsValue2) {
//		String magWrongDetailsValue = wrongDetailsValue == "1" ? "true" : "false";

		CustomVerification.verifyTrue(
				"Failure: wrongDetailsValue  doesn't match: " + wrongDetailsValue + " - " + sosWrongDetailsValue2,
				wrongDetailsValue.contentEquals(sosWrongDetailsValue2));
		
	}

	
	@Step
	public void validateMaleValue(String maleValue, String sosMaleValue) {
//		String magMaleValue = maleValue == "1" ? "true" : "false";

		CustomVerification.verifyTrue(
				"Failure: maleValue  doesn't match: " + maleValue + " - " + sosMaleValue,
				maleValue.contentEquals(sosMaleValue));
		
	}

	@Step
	public void validateSignUpIssueValue(String signupIssuesValue, String sosSignupIssuesValue) {
//		String magSignupIssuesValue = signupIssuesValue == "1" ? "true" : "false";

		CustomVerification.verifyTrue(
				"Failure: signupIssuesValue  doesn't match: " + signupIssuesValue + " - " + sosSignupIssuesValue,
				signupIssuesValue.contentEquals(sosSignupIssuesValue));
		
	}

	@Step
	public void validateCampaignNameValue(String campaignNameValue, String sosCampaignNameValue) {
//		String magCampaignNameValue = campaignNameValue == "1" ? "true" : "false";

		CustomVerification.verifyTrue(
				"Failure: signupIssuesValue  doesn't match: " + campaignNameValue + " - " + sosCampaignNameValue,
				campaignNameValue.contentEquals(sosCampaignNameValue));
		
	}

	@Step
	public void validateFlagPartiesValue(String flagPartiesValue, String sosFlagPartiesValue) {

		CustomVerification.verifyTrue(
				"Failure: flagPartiesValue  doesn't match: " + flagPartiesValue + " - " + sosFlagPartiesValue,
				flagPartiesValue.contentEquals(sosFlagPartiesValue));
		
	}

	@Step
	public void validateIsDistributedValue(String isDistributedValue, String isDistributedValue2) {
		CustomVerification.verifyTrue(
				"Failure: isDistributedValue  doesn't match: " + isDistributedValue + " - " + isDistributedValue2,
				isDistributedValue.contentEquals(isDistributedValue2));
		
	}

	@Step
	public void validateFlagMemberValue(String flagMemberValue, String sosFlagMemberValue) {
		CustomVerification.verifyTrue(
				"Failure: flagMemberValue  doesn't match: " + flagMemberValue + " - " + sosFlagMemberValue,
				flagMemberValue.contentEquals(sosFlagMemberValue));
		
	}

	@Step
	public void validateRoadShowCityValue(String roadshowCityValue, String sosRoadshowCityValue) {
		CustomVerification.verifyTrue(
				"Failure: roadshowCityValue  doesn't match: " + roadshowCityValue + " - " + sosRoadshowCityValue,
				roadshowCityValue.contentEquals(sosRoadshowCityValue));
		
	}

	@Step
	public void validateFollowUpDateValue(String followUpDateValue, String sosFollowUpDateValue2) {
		CustomVerification.verifyTrue(
				"Failure: followUpDateValue  doesn't match: " + followUpDateValue + " - " + sosFollowUpDateValue2,
				followUpDateValue.contentEquals(sosFollowUpDateValue2));
		
	}

	@Step
	public void validateLangIssueValue(String langIssuesValue, String sosLangIssuesValue) {
		// TODO Auto-generated method stub
		CustomVerification.verifyTrue(
				"Failure: langIssuesValue  doesn't match: " + langIssuesValue + " - " + sosLangIssuesValue,
				langIssuesValue.contentEquals(sosLangIssuesValue));
		
	}

	@Step
	public void validateMagContactSosId(String savedSOSContact,
			String magentoSOSContact) {
			Assert.assertFalse("Contact Sos id is equal and should not be ",savedSOSContact.contentEquals(magentoSOSContact));
	}

	@Step
	public void validateStylistSosId(String stylistSosId, String stylistNewSosId) {
		Assert.assertFalse("Stylist Sos id is equal and should not be ",stylistSosId.contentEquals(stylistNewSosId));
	}

	@Step
	public void validateSosPasswordIsChanged(String sosPassword, String sosNewPassword) {
		Assert.assertFalse("Stylist Sos Password  is equal and should not be ",sosPassword.contentEquals(sosNewPassword));
	}

	@Step
	public void validateOldCredentials(String stylistSosId, String email, String sosPassword) throws Exception {
		
		//SalesOnSpeedCalls.getListCustomerInfo(stylistSosId, email, sosPassword);
		String responseMessage=JerseyClientSos.sendGetValidation(SalesOnSpeedConstants.SOS_GET_STYLIST_CONTACTS + stylistSosId, email, sosPassword);
		
		Assert.assertTrue("The user is allowed for login and should be Unauthorized ",responseMessage.contentEquals(SalesOnSpeedConstants.UNAUTHORIZED));
		
	}

}
