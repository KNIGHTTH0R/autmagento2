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
	
	public void validateContactInfoList(List<MagentoSOSContactModel> shopContactList, List<MagentoSOSContactModel> sosContactList) throws Exception {
		
		Assert.assertTrue("Failure: The list size are not equal", shopContactList.size() == sosContactList.size());
		for (MagentoSOSContactModel shopContact : sosContactList) {
			
			MagentoSOSContactModel sosContact = contactValidation.findContact(shopContact.get_id(), sosContactList);
			if (sosContact.get_id() != null) {

			} else {
				Assert.assertTrue("Failure: Could not validate all contacts in the list", sosContact != null);
			}
			
			contactValidation.validatePrename(shopContact.getPrename(),sosContact.getPrename());
			contactValidation.validateLastName(shopContact.getLastname(),sosContact.getLastname());
			contactValidation.validateCity(shopContact.getCity(),sosContact.getCity());
			contactValidation.validateCountry(shopContact.getCountry(),sosContact.getCountry());
		//	customerValidation.validateContactId(shopContact.getContactId(),sosContact.getContactId());
			contactValidation.validateStreet(shopContact.getStreet(),sosContact.getStreet());
			contactValidation.validatePostCode(shopContact.getPostcode(),sosContact.getPostcode());
			contactValidation.validateContactSosID(shopContact.get_id(),sosContact.get_id());
			contactValidation.validateUpdatedDate(shopContact.getUpdated(),sosContact.getUpdated());
			contactValidation.validateCreatedDate(shopContact.getCreated(), sosContact.getCreated());
			contactValidation.validateEmail(shopContact.getEmail(),sosContact.getEmail());
			//userId ??? 
		//	contactValidation.validateLanguage(shopContact.getLanguage(),sosContact.getLanguage());
			contactValidation.validatePrimaryPhone(shopContact.getPrimaryPhoneNumber(),shopContact.getPrimaryPhoneNumber());
			contactValidation.validateUnderagedValue(shopContact.getUnderagedValue(),sosContact.getUnderagedValue());
			contactValidation.validateNotInterestedValue(shopContact.getNot_interestedValue(),sosContact.getNot_interestedValue());
			contactValidation.validateContactProgress(shopContact.getContacted_progress(),sosContact.getContacted_progress());
			contactValidation.validateContactBoosterFlagValue(shopContact.getFlagContactBoosterValue(),sosContact.getFlagContactBoosterValue());
			contactValidation.validateWrongDetailsValue(shopContact.getWrongDetailsValue(),sosContact.getWrongDetailsValue());
			contactValidation.validateMaleValue(shopContact.getMaleValue(),sosContact.getMaleValue());
			contactValidation.validateSignUpIssueValue(shopContact.getSignupIssuesValue(),sosContact.getSignupIssuesValue());
			contactValidation.validateFlagPartiesValue(shopContact.getFlagPartiesValue(),sosContact.getFlagPartiesValue());
			contactValidation.validateIsDistributedValue(shopContact.getIsDistributedValue(),sosContact.getIsDistributedValue());
			contactValidation.validateFlagMemberValue(shopContact.getFlagMemberValue(),sosContact.getFlagMemberValue());
			contactValidation.validateRoadShowCityValue(shopContact.getRoadshowCityValue(),sosContact.getRoadshowCityValue());
			contactValidation.validateFollowUpDateValue(shopContact.getFollowUpDateValue(),sosContact.getFollowUpDateValue());
			contactValidation.validateLangIssueValue(shopContact.getLangIssuesValue(),sosContact.getLangIssuesValue());
			
			
		}
		
		
		
	}
}
