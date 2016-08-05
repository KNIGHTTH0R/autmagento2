package com.tests.uss29.us29001;

import java.util.List;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.external.unbounce.DoYouKnowAScSteps;
import com.steps.external.unbounce.UnbounceRegSuccessSteps;
import com.steps.external.unbounce.UnbounceSteps;
import com.tests.BaseTest;
import com.tools.constants.SoapConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.frontend.DykscSeachModel;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.generalCalculation.StylistListCalculation;
import com.tools.geolocation.AddressConverter;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.tools.utils.RandomAddress;
import com.workflows.frontend.DysksWorkflows;

@WithTag(name = "US28.1 Unbounce registration with regular distribution", type = "Scenarios")
@Story(Application.UnbounceRegistration.US28_1.class)
@RunWith(SerenityRunner.class)
public class US29001UnbounceDykscRegistrationTest extends BaseTest {

	@Steps
	public UnbounceSteps unbounceSteps;
	@Steps
	public UnbounceRegSuccessSteps unbounceRegSuccessSteps;
	@Steps
	public DoYouKnowAScSteps doYouKnowAScSteps;
	@Steps
	public DysksWorkflows dysksWorkflows;

	private CustomerFormModel dataModel;
	private DateModel customerFormDate;
	private AddressModel addressModel;
	private CoordinatesModel coordinatesModel = new CoordinatesModel();
	private RandomAddress randomAddress;
	private List<DBStylistModel> searchByPlzAndCountryStylistList;
	private List<DykscSeachModel> foundScList;

	@Before
	public void setUp() throws Exception {

		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		randomAddress = new RandomAddress();

		while (coordinatesModel.getLattitude() == null) {
			addressModel = randomAddress.getRandomAddressFromFile();
			coordinatesModel = AddressConverter.calculateLatAndLongFromAddressWithComponent(addressModel);
		}

		searchByPlzAndCountryStylistList = StylistListCalculation.getCompatibleStylistsForDysks(coordinatesModel, SoapConstants.SOAP_STYLIST_RANGE,
				SoapConstants.STYLIST_ID_FILTER, SoapConstants.LESS_THAN, SoapConstants.GREATER_THAN, SoapConstants.STYLIST_ID_2000, 3);
		PrintUtils.printListDbStylists(searchByPlzAndCountryStylistList);
		MongoConnector.cleanCollection(getClass().getSimpleName());

	}

	@Test
	public void us29001UnbounceDykscRegistrationTest() {

		unbounceSteps.navigateToUnbouncePage();
		String date = unbounceSteps.fillUnbounceDetails(dataModel, addressModel);
		doYouKnowAScSteps.searchByPlz(addressModel);
		foundScList = doYouKnowAScSteps.selectFirstIfFound();
		customerFormDate.setDate(date);
		unbounceSteps.submitUnbounceForm();

		unbounceRegSuccessSteps.verifySuccessMessage();

		dysksWorkflows.setValidateStylistsModels(searchByPlzAndCountryStylistList, foundScList);
		dysksWorkflows.validateStylists("VALIDATE THAT SEARCH BY PLZ AND COUNTRY RETURNS THE CORRECT LIST");
	}

	@After
	public void saveData() {
		MongoWriter.saveDateModel(customerFormDate, getClass().getSimpleName());
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
		MongoWriter.saveAddressModel(addressModel, getClass().getSimpleName());
		for (DBStylistModel stylist : searchByPlzAndCountryStylistList) {
			MongoWriter.saveDbStylistModel(stylist, getClass().getSimpleName());
		}
	}

}