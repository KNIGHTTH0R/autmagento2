package com.tools.data.frontend;

public class SepaPaymentMethodModel {
  
	private String bankAccountName;
	private String country;
	private String bankAccountNumber;
	
	public SepaPaymentMethodModel() {
		super();
		this.bankAccountName = "Schneider";
		this.country = "Germany";
		this.bankAccountNumber = "DE87123456781234567890";
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	
	
}
