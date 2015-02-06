package com.tools.data.frontend;

import com.tools.Constants;
import com.tools.FieldGenerators;
import com.tools.FieldGenerators.Mode;

public class CustomerFormModel {
	
	private String firstName;
	private String lastName;
	private String emailName;
	private String password;
	
	public CustomerFormModel() throws Exception {
		setFirstName();
		setLastName();
		setEmailName();
		setPassword();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailName() {
		return emailName;
	}

	public String getPassword() {
		return password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	//Custom setters for data generation
	public void setFirstName() throws Exception {
		this.firstName = FieldGenerators.generateRandomString(8, Mode.ALPHANUMERIC);
	}
	
	public void setLastName() throws Exception {
		this.lastName = FieldGenerators.generateRandomString(8, Mode.ALPHANUMERIC);
	}
	
	public void setEmailName() throws Exception {
		this.emailName = FieldGenerators.generateRandomString(8, Mode.ALPHANUMERIC) + "@" + Constants.WEB_MAIL;
	}
	
	public void setPassword() throws Exception {
		this.password = FieldGenerators.generateRandomString(13, Mode.ALPHANUMERIC) + "q1";
	}

}
