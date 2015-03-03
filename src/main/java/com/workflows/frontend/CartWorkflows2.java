package com.workflows.frontend;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.CustomVerification;
import com.tools.data.CalcDetailsModel;
import com.tools.data.CalculationModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.data.frontend.ShippingModel;


public class CartWorkflows2 {

	@Steps
	public static CheckoutValidationSteps checkoutValidationSteps;
	

	@Steps 
	public static CustomVerification customVerification;

	private static List<BasicProductModel> basicProductsList = new ArrayList<BasicProductModel>();
	private static List<CartProductModel> cartProductsList = new ArrayList<CartProductModel>();
	
	public void setValidateProductsModels(List<BasicProductModel> basicProductsList, List<CartProductModel> cartProductsList) {
		CartWorkflows2.basicProductsList = basicProductsList;
		CartWorkflows2.cartProductsList = cartProductsList;
	}
	
	@Step
	public void validateProducts(String message) {

		for (BasicProductModel productNow : basicProductsList) {
			CartProductModel compare = findProduct(productNow.getProdCode(),productNow.getQuantity(), cartProductsList);

			compare.setQuantity(compare.getQuantity().replace("x", "").trim());

			if (compare.getName() != null) {
				checkoutValidationSteps.matchName(productNow.getName(), compare.getName());
				checkoutValidationSteps.validateMatchPrice(productNow.getUnitPrice(), compare.getUnitPrice());
				checkoutValidationSteps.validateMatchQuantity(productNow.getQuantity(), compare.getQuantity());
				checkoutValidationSteps.validateMatchFinalPrice(productNow.getFinalPrice(), compare.getFinalPrice());
				checkoutValidationSteps.validateMatchIpPoints(productNow.getPriceIP(), compare.getPriceIP());
			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}

			int index = cartProductsList.indexOf(compare);
			if (index > -1) {
				cartProductsList.remove(index);
				System.out.println("index of " + compare.getName() + " removed");
				System.out.println(cartProductsList.size() + " items remained");
			}
		}
		Assert.assertTrue("Failure: Products list is empty. ", basicProductsList.size() != 0);
		Assert.assertTrue("Failure: Not all products have been validated . ", cartProductsList.size() == 0);

	}
	
	public CartProductModel findProduct(String productCode,String quantity,  List<CartProductModel> cartProducts) {
		CartProductModel result = new CartProductModel();
		theFor: for (CartProductModel cartProductModel : cartProducts) {
			if (cartProductModel.getProdCode().contains(productCode) && cartProductModel.getQuantity().contentEquals(quantity)) {
				result = cartProductModel;
				break theFor;
			}
		}
		return result;
	}

}