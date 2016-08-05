package com.tests.uss28.us28001;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.gmail.GmailConnector;
import com.connectors.mongo.MongoConnector;
import com.steps.backend.BackEndSteps;
import com.steps.backend.stylecoach.StylecoachListBackendSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.Credentials;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.ContactDetailsModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.backend.contact.ContactBackendValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithDriver;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US28.1 Unbounce registration with regular distribution", type = "Scenarios")
@Story(Application.UnbounceRegistration.US28_1.class)
@RunWith(SerenityRunner.class)
public class US28001CheckContactInBackendTest extends BaseTest {

//	@Managed(uniqueSession = false, driver = "firefox")
//	public WebDriver webdriver;

	public MongoConnector mongoConnector;
	public GmailConnector gmailConnector;

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public StylecoachListBackendSteps stylecoachListBackendSteps;
	@Steps
	public ContactBackendValidationWorkflows contactBackendValidationWorkflows;
	@Steps
	public CustomVerification customVerifications;

	private ContactDetailsModel expectedContactDetailsModel;
	private CustomerFormModel customerFormModel;
	private AddressModel addressModel;
	private DateModel dateModel;
	private List<DBStylistModel> stylistsList;

//	/**
//	 * This method will grab dictionary properties by context and save it to
//	 * MongoDb.
//	 *
//	 * @throws InterruptedException
//	 */
//	public static void updateDictionary() {
//		MongoConnector.cleanCollection(MongoTableKeys.TEST_CONFIG, MongoTableKeys.DICTIONARY_MODEL);
//
//		System.out.println("Dictionary PATH: " + UrlConstants.CONTEXT_PATH + MongoReader.getContext() + File.separator
//				+ "dictionary.properties");
//		System.out.println("Load Dictionary... ");
//
//		Properties prop = new Properties();
//		InputStream input = null;
//		try {
//			input = new FileInputStream(
//					UrlConstants.CONTEXT_PATH + MongoReader.getContext() + File.separator + "dictionary.properties");
//			prop.load(input);
//			for (Object keyNow : prop.keySet()) {
//				String value = prop.getProperty(String.valueOf(keyNow));
//				MongoWriter.saveToDictionary(String.valueOf(keyNow), value);
//			}
//
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		} finally {
//			if (input != null) {
//				try {
//					input.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}

	@Before
	public void setUp() throws Exception {
		
//		try {
//			System.err.println("--------------------------------- Test Start---------------------------------------");
//
//			mongoConnector = new MongoConnector();
//			System.out.println("Connect to Mongo DB...");
//			System.out.println("DB: " + mongoConnector.getDbAddress());
//		} catch (Exception e) {
//			System.out.println("Error: Mongo connection could not be initialized");
//			e.printStackTrace();
//		}
//
//		// Context and environment - clean and setup to mongo
//		MongoConnector.cleanCollection(MongoTableKeys.TEST_CONFIG);
//		String contextValue = System.getProperty("runContext");
//		String envValue = System.getProperty("runEnv");
//		MongoWriter.saveEnvContext(envValue, contextValue);
//
//		String baseUrl = "";
//		String storeIDs = "";
//		String soapUrl = "";
//		Properties prop = new Properties();
//		InputStream input = null;
//
//		try {
//
//			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "config.properties");
//			prop.load(input);
//			baseUrl = prop.getProperty("baseUrl");
//			soapUrl = prop.getProperty("soapUrl");
//			storeIDs = prop.getProperty("storeIDs");
//
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		} finally {
//			if (input != null) {
//				try {
//					input.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		MongoWriter.saveStoreUrl(storeIDs, baseUrl, soapUrl);
//
//		EmailCredentialsModel emailDefaults = new EmailCredentialsModel();
//		emailDefaults.setHost(EmailConstants.RECEIVING_HOST);
//		emailDefaults.setProtocol(EmailConstants.PROTOCOL);
//		emailDefaults.setUsername(EmailConstants.EMAIL_DEF_USERNAME);
//		emailDefaults.setPassword(EmailConstants.EMAIL_DEF_PASSWORD);
//		gmailConnector = new GmailConnector(emailDefaults);
//		updateDictionary();

		customerFormModel = MongoReader.grabCustomerFormModels("US28001UnbounceRegistrationTest").get(0);
		addressModel = MongoReader.grabAddressModels("US28001UnbounceRegistrationTest").get(0);
		dateModel = MongoReader.grabDateModels("US28001UnbounceRegistrationTest").get(0);

		stylistsList = MongoReader.grabDbStylistModels("US28001UnbounceRegistrationTest");

		expectedContactDetailsModel = contactBackendValidationWorkflows.populateContactDetailsModel(customerFormModel,
				addressModel, dateModel);
	}

	@Test
	@WithDriver("firefox")
	public void us28001CheckContactInBackendTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnContactList();
		stylecoachListBackendSteps.openContactDetails(customerFormModel.getEmailName());
		ContactDetailsModel grabbedContactDetailsModel = stylecoachListBackendSteps.grabContactDetails();
		contactBackendValidationWorkflows.verifyUnbounceContactDetails(grabbedContactDetailsModel,
				expectedContactDetailsModel);
		contactBackendValidationWorkflows.validateContactsParentStylecoach(stylistsList,
				grabbedContactDetailsModel.getParentId());

		customVerifications.printErrors();
	}

}
