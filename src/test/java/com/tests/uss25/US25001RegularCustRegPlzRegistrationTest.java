package com.tests.uss25;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.CustomerRegistrationStepsWithCsv;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US25.1 Check invalid plz validation on all carts and registration processes", type = "Scenarios")
@Story(Application.PlzValidation.US24_1.class)
@RunWith(SerenityRunner.class)
public class US25001RegularCustRegPlzRegistrationTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public CustomerRegistrationStepsWithCsv customerRegistrationStepsWithCsv;
	@Steps
	public CustomVerification customVerifications;

	private CustomerFormModel dataModel;
	private AddressModel addressModel;

	@Before
	public void setUp() throws Exception {
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
	}

	@Test
	public void us25001RegularCustRegPlzRegistrationTest() {
		

		customerRegistrationSteps.fillCreateCustomerFormWithoutPlz(dataModel, addressModel);
//		try {
//			withTestDataFrom("resources/invalidPlzTestData.csv").run(customerRegistrationStepsWithCsv).inputPostCodeCsv();
//		} catch (IOException e) {
//			e.printStackTrace();
//			Assert.fail("Failed !!!");
//		}
		customVerifications.printErrors();
	}

}