package com.workflows.backend;

import net.thucydides.core.annotations.Steps;

import com.steps.backend.validations.StylistValidationSteps;
import com.tools.data.backend.RegistrationActivationDateModel;
import com.tools.data.backend.StylistPropertiesModel;

public class CustomerAndStylistRegistrationWorkflows {

	@Steps
	public StylistValidationSteps stylistValidationSteps;

	private StylistPropertiesModel grabbedStylistModel = new StylistPropertiesModel();
	private StylistPropertiesModel expectedStylistModel = new StylistPropertiesModel();

	public void setValidateStylistProperties(StylistPropertiesModel grabbedStylistModel, StylistPropertiesModel expectedStylistModel) {
		this.grabbedStylistModel = grabbedStylistModel;
		this.expectedStylistModel = expectedStylistModel;
	}

	public void validateStylistProperties(String string) {
		stylistValidationSteps.validateStatus(grabbedStylistModel.getStatus(), expectedStylistModel.getStatus());
		stylistValidationSteps.validateJewwelryBonus(grabbedStylistModel.getJewelryreceived(), expectedStylistModel.getJewelryreceived());
		stylistValidationSteps.validateCustomerType(grabbedStylistModel.getType(), expectedStylistModel.getType());
	}

	private RegistrationActivationDateModel grabbedDatesModel = new RegistrationActivationDateModel();
	private RegistrationActivationDateModel expectedDatesModel = new RegistrationActivationDateModel();

	public void setValidateStylistDates(RegistrationActivationDateModel grabbedDatesModel, RegistrationActivationDateModel expectedDatesModel) {
		this.grabbedDatesModel = grabbedDatesModel;
		this.expectedDatesModel = expectedDatesModel;
	}

	public void validateStylistDAtes(String string) {
		stylistValidationSteps.validateDates(grabbedDatesModel.getRegistrationDate(), expectedDatesModel.getRegistrationDate());
		stylistValidationSteps.validateDates(grabbedDatesModel.getConfirmationDate(), expectedDatesModel.getConfirmationDate());
	
	}
	public void validateCustomerRegistrationDate(String string) {
		stylistValidationSteps.validateDates(grabbedDatesModel.getRegistrationDate(), expectedDatesModel.getRegistrationDate());
	}
	
	

}
