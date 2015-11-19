package com.tests.uss15.us15001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.FooterSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;

@WithTag(name = "US15.1 Check simple subscriber in mailchimp", type = "Scenarios")
@Story(Application.Newsletter.US15_1.class)
@RunWith(ThucydidesRunner.class)
public class US15001SubscribeToNewsletterTest extends BaseTest {

	@Steps
	FooterSteps footerSteps;

	private CustomerFormModel dataModel;
	private DateModel dateModel;

	@Before
	public void setUp() throws Exception {

		dataModel = new CustomerFormModel();
		
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us15001SubscribeToNewsletterTest() {

		footerSteps.subscribeToNewsletter(dataModel);
		dateModel.setDate(DateUtils.getCurrentDate("MM/dd/yyyy"));

	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
		MongoWriter.saveDateModel(dateModel, getClass().getSimpleName());

	}

}
