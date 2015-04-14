package com.tests.uss10.us10003;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.SoapKeys;
import com.tools.data.UrlModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.stagingaut.Constants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US10", type = "external")
@Story(Application.StyleParty.class)
@RunWith(ThucydidesRunner.class)
public class US10003VerifyHostPartyCreationEmailTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;

	private String email;
	private static CustomerFormModel customerModel;
	private UrlModel urlModel = new UrlModel();

	@Before
	public void setUp() throws Exception {
		customerModel = new CustomerFormModel();
		customerModel = MongoReader.grabCustomerFormModels("US10003CreatePartyWithNewContactHostTest" + SoapKeys.GRAB).get(0);
		email = customerModel.getEmailName();
		System.out.println(email);

	}

	@Test
	public void us10003VerifyHostPartyCreationEmailTest() {

		emailClientSteps.openMailinator();
		urlModel.setUrl(emailClientSteps.validateThatEmailIsReceivedAndConfirm(email.replace("@" + Constants.WEB_MAIL, ""), Constants.PARTY_CREATION_EMAIL_SUBJECT));

	}

	@After
	public void saveData() {
		MongoWriter.saveUrlModel(urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
	}

}
