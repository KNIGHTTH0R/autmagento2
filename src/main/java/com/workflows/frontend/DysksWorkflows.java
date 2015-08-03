package com.workflows.frontend;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.CustomVerification;
import com.tools.data.frontend.DykscSeachModel;
import com.tools.data.soap.DBStylistModel;

public class DysksWorkflows {

	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;

	private List<DBStylistModel> dBStylistList = new ArrayList<DBStylistModel>();
	private List<DykscSeachModel> dysksStylistList = new ArrayList<DykscSeachModel>();

	public void setValidateStylistsModels(List<DBStylistModel> dBStylistList, List<DykscSeachModel> dysksStylistList) {
		this.dBStylistList = dBStylistList;
		this.dysksStylistList = dysksStylistList;
	}

	@Step
	public void validateStylists(String message) {

		for (DBStylistModel stylistNow : dBStylistList) {
			DykscSeachModel compare = findStylist(stylistNow.getStylistId(), dysksStylistList);

			if (compare.getId() != null) {
				validateStylistName(stylistNow.getFirstName(), compare.getName());

			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}

			int index = dysksStylistList.indexOf(compare);
			if (index > -1) {
				dysksStylistList.remove(index);
				System.out.println("index of " + compare.getName() + " removed");
				System.out.println(dysksStylistList.size() + " items remained");
			}
		}
		Assert.assertTrue("Failure: Products list is empty. ", dBStylistList.size() != 0);
		Assert.assertTrue("Failure: Not all products have been validated . ", dysksStylistList.size() == 0);

	}

	public DykscSeachModel findStylist(String id, List<DykscSeachModel> dysksStylistList) {
		DykscSeachModel result = new DykscSeachModel();
		theFor: for (DykscSeachModel stylist : dysksStylistList) {
			if (stylist.getId().contains(id)) {
				result = stylist;
				break theFor;
			}
		}
		return result;
	}

	@Step
	public void validateStylistName(String stylistNow, String compare) {
		CustomVerification.verifyTrue("Failure: Quantity values dont match: " + stylistNow + " - " + compare, compare.contains(stylistNow));
	}

}
