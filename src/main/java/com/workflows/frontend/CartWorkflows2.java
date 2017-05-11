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
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;

public class CartWorkflows2 {

	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;

	private List<BasicProductModel> basicProductsList = new ArrayList<BasicProductModel>();
	private List<CartProductModel> cartProductsList = new ArrayList<CartProductModel>();

	public void setValidateProductsModels(List<BasicProductModel> basicProductsList, List<CartProductModel> cartProductsList) {
		this.basicProductsList = basicProductsList;
		this.cartProductsList = cartProductsList;
	}

	@Step
	public void validateProducts(String message) {
		System.out.println(" ");
		System.out.println(message);
		System.out.println("basicProductsListbasicProductsList "+basicProductsList);
		System.out.println("cartProductsListcartProductsList "+cartProductsList);
		for (BasicProductModel productNow : basicProductsList) {
			CartProductModel compare = findProduct(productNow.getProdCode(), productNow.getQuantity(), cartProductsList);
		//	CartProductModel compare = findProduct(productNow.getProdCode(), productNow.getDiscountClass(), cartProductsList);
			System.out.println("compare "+compare);
			if (compare != null) {
				try {
					compare.setQuantity(compare.getQuantity().replace("x", "").trim());

				} catch (Exception e) {
				}
			}

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
//		Assert.assertTrue("Failure: Products list is empty. ", basicProductsList.size() != 0);
		Assert.assertTrue("Failure: Not all products have been validated . ", cartProductsList.size() == 0);

	}

	public CartProductModel findProduct(String productCode, String quantity, List<CartProductModel> cartProducts) {
		CartProductModel result = new CartProductModel();
		theFor: for (CartProductModel cartProductModel : cartProducts) {
			if (cartProductModel.getProdCode().contains(productCode) && cartProductModel.getQuantity().contentEquals(quantity)) {
			//if (cartProductModel.getProdCode().contains(productCode) && cartProductModel.getDiscountClass().contentEquals(quantity)) {
				result = cartProductModel;
				break theFor;
			}
		}
		return result;
	}

	private CartTotalsModel discountTotals = new CartTotalsModel();
	private CalcDetailsModel discountCalculationModel = new CalcDetailsModel();

	public void setVerifyTotalsDiscount(CartTotalsModel discountTotals, CalcDetailsModel discountCalculationModel) {
		this.discountCalculationModel = discountCalculationModel;
		this.discountTotals = discountTotals;

	}

	@StepGroup
	public void verifyTotalsDiscount(String message) {
		System.out.println(" ");
		System.out.println(message);
		verifySubTotals(discountTotals.getSubtotal(), discountCalculationModel.getSubTotal());
		verifyTotalAmount(discountTotals.getTotalAmount(), discountCalculationModel.getTotalAmount());
		verifyJewelryBonus(discountTotals.getJewelryBonus(), discountCalculationModel.getJewelryBonus());
		verifyMarketingBonus(discountTotals.getMarketingBonus(), discountCalculationModel.getMarketingBonus());
		verifyIP(discountTotals.getIpPoints(), discountCalculationModel.getIpPoints());
	}

	@StepGroup
	public void verifyTotalsDiscountNoMarketing(String message) {
		verifySubTotals(discountTotals.getSubtotal(), discountCalculationModel.getSubTotal());
		verifyTotalAmount(discountTotals.getTotalAmount(), discountCalculationModel.getTotalAmount());		
		verifyJewelryBonus(discountTotals.getJewelryBonus(), discountCalculationModel.getJewelryBonus());
		verifyIP(discountTotals.getIpPoints(), discountCalculationModel.getIpPoints());

	}

	@Step
	public void verifyIP(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: IP points dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyTotalAmount(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Total Amount dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyJewelryBonus(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Jewelry Bonus dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifySubTotals(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Sub Totals dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyMarketingBonus(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Marketing Bonus dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyShippingPrice(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Shipping Price dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyDiscountsPrice(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Discounts Price dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

}