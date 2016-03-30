package com.tests.uss15.us15001;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.FooterSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US15.1 Check simple subscriber in mailchimp", type = "Scenarios")
@Story(Application.Newsletter.US15_1.class)
@RunWith(SerenityRunner.class)
public class US15001SubscribeToNewsletterTest extends BaseTest {

	@Steps
	FooterSteps footerSteps;

	private CustomerFormModel dataModel;

	@Before
	public void setUp() throws Exception {

		dataModel = new CustomerFormModel();

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us15001SubscribeToNewsletterTest() {

		footerSteps.subscribeToNewsletter(dataModel);

	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());

	}

}
