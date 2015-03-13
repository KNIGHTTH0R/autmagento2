package com.tools.data.backend;

public class RegistrationActivationDateModel {

	public RegistrationActivationDateModel() {
	}

	public RegistrationActivationDateModel(String registrationDate, String confirmationDate) {
		this.registrationDate = registrationDate;
		this.confirmationDate = confirmationDate;
	}

	private String registrationDate;
	private String confirmationDate;

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(String confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

}
