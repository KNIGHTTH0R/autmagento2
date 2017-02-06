package com.steps.frontend;

import java.util.List;

import com.tools.CustomVerification;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.salesOnSpeed.MagentoSOSContactModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class ContactDetailsValidationSteps extends AbstractSteps {

	private static final long serialVersionUID = 743498685895573421L;
	
	@Step
	public RegularBasicProductModel findWishlistItems(String sosContactId, List<RegularBasicProductModel> grabbedproductsWishList) {
		RegularBasicProductModel result = new RegularBasicProductModel();
		theFor: for (RegularBasicProductModel item : grabbedproductsWishList) {
			if (item.getProdCode().contains(sosContactId)) {
				result = item;
				break theFor;
			}
		}
		return result;
	}

	@Step
	public void validateFinalPrice(String finalPrice, String finalPriceContact) {
		CustomVerification.verifyTrue("Failure: Final Price doesn't match: " + finalPrice + " - " + finalPriceContact,
				finalPrice.contentEquals(finalPriceContact));
		
	}

	@Step
	public void validateProductQty(String quantity, String quantityContact) {
		CustomVerification.verifyTrue("Failure: Quantity doesn't match: " + quantity + " - " + quantityContact,
				quantity.contentEquals(quantityContact));
		
	}
}
