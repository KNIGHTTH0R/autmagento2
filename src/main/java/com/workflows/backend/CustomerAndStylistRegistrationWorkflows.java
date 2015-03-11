package com.workflows.backend;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

import com.steps.backend.validations.StylistValidationSteps;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.data.backend.StylistRegistrationAndActivationDateModel;

public class CustomerAndStylistRegistrationWorkflows extends ScenarioSteps {

	private static final long serialVersionUID = 1L;

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

	private StylistRegistrationAndActivationDateModel grabbedDatesModel = new StylistRegistrationAndActivationDateModel();
	private StylistRegistrationAndActivationDateModel expectedDatesModel = new StylistRegistrationAndActivationDateModel();

	public void setValidateStylistDates(StylistRegistrationAndActivationDateModel grabbedDatesModel, StylistRegistrationAndActivationDateModel expectedDatesModel) {
		this.grabbedDatesModel = grabbedDatesModel;
		this.expectedDatesModel = expectedDatesModel;
	}

	public void validateStylistDAtes(String string) {
		stylistValidationSteps.validateDates(grabbedDatesModel.getRegistrationDate(), expectedDatesModel.getRegistrationDate());
		stylistValidationSteps.validateDates(grabbedDatesModel.getConfirmationDate(), expectedDatesModel.getConfirmationDate());
	
	}

}
