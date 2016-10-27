package com.tests.uss22;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.LoungeIpPerformanceModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.commission.StylecoachPerformanceValidationWorkflow;

@WithTag(name = "US22.1 Verify SC Performance based on new IP logic on backend and frontend", type = "Scenarios")
@Story(Application.NewIpLogicStylecoachPerformance.US22_1.class)
@RunWith(SerenityRunner.class)
public class US22001VerifySCPerformanceNewIpLogicFrontendTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public StylecoachPerformanceValidationWorkflow stylecoachPerformanceValidationWorkflow;
	@Steps
	public CustomVerification customVerifications;

	private LoungeIpPerformanceModel expectedLoungeIpPerformanceModel;
	private LoungeIpPerformanceModel grabbedLoungeIpPerformanceModel;
	private String username;
	private String password;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_22_FOLDER + File.separator + "us22001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		expectedLoungeIpPerformanceModel = MongoReader.grabLoungeIpPerformance("US22001VerifySCPerformanceNewIpLogicBackendTest").get(0);
	}

	@Test
	public void us22001VerifySCPerformanceNewIpLogicFrontendTest() {

		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToLounge();
		grabbedLoungeIpPerformanceModel = loungeSteps.grabSCPerformanceIpLogic();

		stylecoachPerformanceValidationWorkflow.validatePerformanceValuesInFrontend(expectedLoungeIpPerformanceModel, grabbedLoungeIpPerformanceModel);
		
		customVerifications.printErrors();
	}

}
