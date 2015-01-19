package com.tools.data;

public class CreditCardModel {

	private String cardNumber;
	private String cardName;
	private String monthExpiration;
	private String yearExpiration;
	private String cvcNumber;

	public String getCardNumber() {
		return cardNumber;
	}

	public String getCardName() {
		return cardName;
	}

	public String getMonthExpiration() {
		return monthExpiration;
	}

	public String getYearExpiration() {
		return yearExpiration;
	}

	public String getCvcNumber() {
		return cvcNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public void setMonthExpiration(String monthExpiration) {
		this.monthExpiration = monthExpiration;
	}

	public void setYearExpiration(String yearExpiration) {
		this.yearExpiration = yearExpiration;
	}

	public void setCvcNumber(String cvcNumber) {
		this.cvcNumber = cvcNumber;
	}

}