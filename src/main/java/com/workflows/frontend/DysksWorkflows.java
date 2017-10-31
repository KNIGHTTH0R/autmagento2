package com.workflows.frontend;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

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

	/*@Step    comment by emilina - changed dBStylistList with dysksStylistList
	public void validateStylists(String message) {

		for (DBStylistModel stylistNow : dBStylistList) {
			DykscSeachModel dykscStylist = findStylist(stylistNow.getStylistId(), dysksStylistList);

			validateStylistName(stylistNow.getFirstName(), dykscStylist.getName());
		}
	}*/
	
	public void validateStylists(String message) {

		for (DykscSeachModel dykscStylist : dysksStylistList) {
			DBStylistModel dbStylist = findStylistDb(dykscStylist.getId(), dBStylistList);

			validateStylistName(dykscStylist.getName(),dbStylist.getFirstName() );
		}
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
	
	public DBStylistModel findStylistDb(String id, List<DBStylistModel> dBStylistList) {
		DBStylistModel result = new DBStylistModel();
		theFor: for (DBStylistModel stylist : dBStylistList) {
			if (stylist.getStylistId().contains(id)) {
				result = stylist;
				break theFor;
			}
		}
		return result;
	}

	@Step
	public void validateStylistName(String stylistNow, String compare) {
		CustomVerification.verifyTrue("Failure: Names dont match: " + stylistNow + " - " + compare, compare.toLowerCase().contains(stylistNow.toLowerCase()));
	}

}
