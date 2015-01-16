package com.tests.us0;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.FrontEndSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.data.AddressModel;
import com.tools.data.CustomerFormModel;
import com.tools.data.StylistDataModel;
import com.tools.requirements.Application;

@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class CreateCustomerTest extends BaseTest{


	@Steps
	public FrontEndSteps frontEndSteps;

	public CustomerFormModel dataModel;
	public AddressModel addressModel;
	public StylistDataModel validationModel;

	@Before
	public void setUp() throws Exception {
		// Generate data for this test run
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
	}

	/**
	 * FrontEnd steps in this test
	 * 
	 * @throws Exception
	 */
	@Test
	public void createFECustomerTest() {

		frontEndSteps.fillCreateCustomerForm(dataModel, addressModel);
		frontEndSteps.verifyCustomerCreation();
	}

	@After
	public void saveData() {
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			output = new FileOutputStream(Constants.RESOURCES_PATH + "Customer.properties");
			prop.setProperty("clientName", dataModel.getEmailName());
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

}