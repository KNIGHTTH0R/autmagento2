package com.tools.data.frontend;

public class ElvPaymentMethodModel {
	
	private String bankAcckountNumber;
	private String bankId;
	private String bankName;
	private String bankLocation;
	private String bankAccountHolderName;

	public ElvPaymentMethodModel() {
		super();
		this.bankAcckountNumber = "1234567890";
		this.bankId = "12345678";
		this.bankName = "test";
		this.bankLocation = "test";
		this.bankAccountHolderName = "test";
	}

	public String getBankAcckountNumber() {
		return bankAcckountNumber;
	}

	public void setBankAcckountNumber(String bankAcckountNumber) {
		this.bankAcckountNumber = bankAcckountNumber;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankLocation() {
		return bankLocation;
	}

	public void setBankLocation(String bankLocation) {
		this.bankLocation = bankLocation;
	}

	public String getBankAccountHolderName() {
		return bankAccountHolderName;
	}

	public void setBankAccountHolderName(String bankAccountHolderName) {
		this.bankAccountHolderName = bankAccountHolderName;
	}
	
}
