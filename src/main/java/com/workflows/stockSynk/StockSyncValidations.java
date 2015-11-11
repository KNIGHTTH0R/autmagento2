package com.workflows.stockSynk;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;

import com.tools.data.navision.SyncInfoModel;

public class StockSyncValidations {

	@Steps
	StockProductsValidations stockProductsValidations;

	private List<SyncInfoModel> list1 = new ArrayList<SyncInfoModel>();
	private List<SyncInfoModel> list2 = new ArrayList<SyncInfoModel>();

	public void setValidateProductsModels(List<SyncInfoModel> list1, List<SyncInfoModel> list2) {
		this.list1 = list1;
		this.list2 = list2;
	}

	@StepGroup
	public void validateProducts(String message) {

		for (SyncInfoModel productNow : list1) {
			SyncInfoModel compare = findProduct(productNow.getSku(), list2);

			if (compare.getSku() != null) {

				int intProductNowQuantity = (int) Math.round(Double.parseDouble(productNow.getQuantity()));
				int intCompareQuantity = (int) Math.round(Double.parseDouble(compare.getQuantity()));
				
				stockProductsValidations.validateIsDiscontinued(productNow.getSku(), compare.getSku());
				stockProductsValidations.validateMatchQuantity(String.valueOf(intProductNowQuantity), String.valueOf(intCompareQuantity));
				stockProductsValidations.validateIsDiscontinued(productNow.getIsDiscontinued(), compare.getIsDiscontinued());
				stockProductsValidations.validateEarliestAvailability(productNow.getEarliestAvailability(), compare.getEarliestAvailability());
			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}

		}
		Assert.assertTrue("Failure: Not all the products have been validated ", list1.size() == list2.size());
	}

	private SyncInfoModel findProduct(String sku, List<SyncInfoModel> productsList) {
		SyncInfoModel result = new SyncInfoModel();
		theFor: for (SyncInfoModel product : productsList) {
			if (product.getSku().contains(sku)) {
				result = product;
				break theFor;
			}
		}
		return result;
	}

}
