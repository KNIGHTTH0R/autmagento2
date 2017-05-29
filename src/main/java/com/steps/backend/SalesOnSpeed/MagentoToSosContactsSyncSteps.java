package com.steps.backend.SalesOnSpeed;

import java.util.List;

import org.junit.Assert;

import com.tools.CustomVerification;
import com.tools.data.salesOnSpeed.MagentoSOSContactModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Steps;

public class MagentoToSosContactsSyncSteps extends AbstractSteps {
	private static final long serialVersionUID = 1L;

	@Steps
	CustomVerification customVerification;

	@Steps
	MagentoToSosContactsSyncValidationSteps contactValidation;

	public void validateContactInfoList(List<MagentoSOSContactModel> shopContactList,
			List<MagentoSOSContactModel> sosContactList) throws Exception {

		Assert.assertTrue("Failure: The list size are not equal", shopContactList.size() == sosContactList.size());
		for (MagentoSOSContactModel shopContact : shopContactList) {
			System.out.println("aici ??");
			MagentoSOSContactModel sosContact = contactValidation.findContact(shopContact.get_id(), sosContactList);
			if (sosContact.get_id() != null) {

			} else {
				Assert.assertTrue("Failure: Could not validate all contacts in the list", sosContact != null);
			}

			//validate campaign value should be done 
			contactValidation.validatePrename(shopContact.getPrename(), sosContact.getPrename());
			contactValidation.validateLastName(shopContact.getLastname(), sosContact.getLastname());
			contactValidation.validateCity(shopContact.getCity(), sosContact.getCity());
			contactValidation.validateCountry(shopContact.getCountry(), sosContact.getCountry());
			contactValidation.validateStreet(shopContact.getStreet(), sosContact.getStreet());
			contactValidation.validatePostCode(shopContact.getPostcode(), sosContact.getPostcode());
			contactValidation.validateContactSosID(shopContact.get_id(), sosContact.get_id());
			contactValidation.validateUpdatedDate(shopContact.getUpdated(), sosContact.getUpdated());
			contactValidation.validateCreatedDate(shopContact.getCreated(), sosContact.getCreated());
			contactValidation.validateEmail(shopContact.getEmail(), sosContact.getEmail());
			contactValidation.validatePrimaryPhone(shopContact.getPrimaryPhoneNumber(),
					shopContact.getPrimaryPhoneNumber());
			contactValidation.validateUnderagedValue(shopContact.getUnderagedValue(), sosContact.getUnderagedValue());
			contactValidation.validateNotInterestedValue(shopContact.getNot_interestedValue(),
					sosContact.getNot_interestedValue());
			contactValidation.validateContactProgress(shopContact.getContacted_progress(),
					sosContact.getContacted_progress());
			contactValidation.validateContactBoosterFlagValue(shopContact.getFlagContactBoosterValue(),
					sosContact.getFlagContactBoosterValue());
			contactValidation.validateWrongDetailsValue(shopContact.getWrongDetailsValue(),
					sosContact.getWrongDetailsValue());
		//	contactValidation.validateMaleValue(shopContact.getMaleValue(), sosContact.getMaleValue());
			contactValidation.validateSignUpIssueValue(shopContact.getSignupIssuesValue(),
					sosContact.getSignupIssuesValue());
			contactValidation.validateFlagPartiesValue(shopContact.getFlagPartiesValue(),
					sosContact.getFlagPartiesValue());
			contactValidation.validateIsDistributedValue(shopContact.getIsDistributedValue(),
					sosContact.getIsDistributedValue());
			contactValidation.validateFlagMemberValue(shopContact.getFlagMemberValue(),
					sosContact.getFlagMemberValue());
			contactValidation.validateRoadShowCityValue(shopContact.getRoadshowCityValue(),
					sosContact.getRoadshowCityValue());
			contactValidation.validateFollowUpDateValue(shopContact.getFollowUpDateValue(),
					sosContact.getFollowUpDateValue());
			contactValidation.validateLangIssueValue(shopContact.getLangIssuesValue(), sosContact.getLangIssuesValue());

		}

	}

	public void validateContactsSosIdAfterReset(List<MagentoSOSContactModel> savedContactList,
			List<MagentoSOSContactModel> contactsInfo) {
		// TODO Auto-generated method stub

		Assert.assertTrue("Failure: The list size are not equal", savedContactList.size() == contactsInfo.size());
		
		for (int j = 0; j < savedContactList.size(); j++) {
			contactValidation.validateMagContactSosId(savedContactList.get(j).get_id(), contactsInfo.get(j).get_id());
			System.out.println("numara ");
		}
	}

	
	public void validateStylistSosIdAfterReset(String stylistSosId, String stylistNewSosId) {
		// TODO Auto-generated method stub
		contactValidation.validateStylistSosId(stylistSosId,stylistNewSosId);
	}

	public void validateSosPasswordIsChanged(String sosPassword, String sosNewPassword) {
		// TODO Auto-generated method stub
		contactValidation.validateSosPasswordIsChanged(sosPassword,sosNewPassword);
		
	}

	public void validateOldCredential(String stylistSosId, String email, String sosPassword) throws Exception {
		// TODO Auto-generated method stub
		contactValidation.validateOldCredentials(stylistSosId,email,sosPassword);
		
	}

}
