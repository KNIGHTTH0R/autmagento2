package com.workflows.stockSynk;

import java.util.List;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import com.tools.CustomVerification;
import com.tools.data.navision.SyncInfoModel;

public class StockSyncValidations {

	@Step
	public void validateProducts(List<SyncInfoModel> list1, List<SyncInfoModel> list2) {

		for (SyncInfoModel productNow : list1) {
			SyncInfoModel compare = findProduct(productNow.getSku(), list2);

			if (compare.getSku() != null) {
				validateMatchQuantity(productNow.getQuantity(), compare.getQuantity());
				validateIsDiscontinued(productNow.getIsDiscontinued(), compare.getIsDiscontinued());
				validateEarliestAvailability(productNow.getEarliestAvailability(), compare.getEarliestAvailability());
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

	@Step
	private void validateMatchQuantity(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Quantity values dont match: " + productNow + " - " + compare, productNow.contentEquals(compare));
	}

	@Step
	private void validateIsDiscontinued(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Is discontinued status doesn't match: " + productNow + " - " + compare, productNow.contentEquals(compare));
	}

	@Step
	private void validateEarliestAvailability(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Quantity values doesn't match: " + productNow + " - " + compare, productNow.contentEquals(compare));
	}

}
