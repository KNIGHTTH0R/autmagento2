package com.steps.external.unbounce;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.DykscSeachModel;
import com.tools.requirements.AbstractSteps;

public class DoYouKnowAScSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void searchByPlz(AddressModel addressModel) {
		unbounceDykscPage().selectSearchByPlzOption();
		unbounceDykscPage().enterPLZ(addressModel.getPostCode());
		unbounceDykscPage().selectCountryOption(addressModel.getCountryName());
		unbounceDykscPage().clickSearchStylistByPlz();
	}

	@Step
	public List<DykscSeachModel> selectFirstIfFound() {
		if (unbounceDykscPage().isStylecoachFound()) {
			List<DykscSeachModel> scList = unbounceDykscPage().getFoundStylecoachesData();
			unbounceDykscPage().selectFirstStylistFromList();
			return scList;
		}
		return null;
	}

	//@emilian kobo campaing submit and contiunue
		 @Step
		 public void submitAndContinue(){
			 unbounceDykscPage().clickSubmitAndContinue();
		 }
}
