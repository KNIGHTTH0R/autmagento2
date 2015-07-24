package com.steps.frontend.registration.connectWithMe;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.requirements.AbstractSteps;

public class ConnectWithMeRegistrationSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;


	@Step
	@Title("Fill connect with me form")
	public void fillContactBoosterRegistrationForm(CustomerFormModel dataModel, AddressModel addressModel) {
		connectWithMeRegistrationPage().selectGender(true);
		connectWithMeRegistrationPage().inputFirstName(dataModel.getFirstName());
		connectWithMeRegistrationPage().inputLastName(dataModel.getLastName());
		connectWithMeRegistrationPage().inputEmail(dataModel.getEmailName());
		connectWithMeRegistrationPage().inputPostCode(addressModel.getPostCode());
		connectWithMeRegistrationPage().inputCity(addressModel.getHomeTown());
		connectWithMeRegistrationPage().inputTelephoneAreaCode("000");
		connectWithMeRegistrationPage().inputTelephone(addressModel.getPhoneNumber());
		connectWithMeRegistrationPage().selectCountry(addressModel.getCountryName());
		connectWithMeRegistrationPage().checkNewslletterCheckbox(true);
		connectWithMeRegistrationPage().checkMemberCheckbox(true);
		connectWithMeRegistrationPage().checkPartiesCheckbox(true);;
		connectWithMeRegistrationPage().clickOk();
	}

}
