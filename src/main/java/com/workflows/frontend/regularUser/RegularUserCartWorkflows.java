package com.workflows.frontend.regularUser;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.CustomVerification;
import com.tools.constants.ConfigConstants;
import com.tools.data.RegularCartCalcDetailsModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.RegularUserCartProductModel;
import com.tools.data.frontend.RegularUserCartTotalsModel;

public class RegularUserCartWorkflows {

	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;

	@Steps
	public CustomVerification customVerification;

	private List<RegularBasicProductModel> basicProductsList = new ArrayList<RegularBasicProductModel>();
	private List<RegularUserCartProductModel> cartProductsList = new ArrayList<RegularUserCartProductModel>();

	public void setValidateProductsModels(List<RegularBasicProductModel> basicProductsList, List<RegularUserCartProductModel> cartProductsList) {
		this.basicProductsList = basicProductsList;
		this.cartProductsList = cartProductsList;
	}

	@Step
	public void validateProducts(String message) {

		for (RegularBasicProductModel productNow : basicProductsList) {
			RegularUserCartProductModel compare = findProduct(productNow.getProdCode(), productNow.getQuantity(), cartProductsList);

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

	public RegularUserCartProductModel findProduct(String productCode, String quantity, List<RegularUserCartProductModel> cartProducts) {
		RegularUserCartProductModel result = new RegularUserCartProductModel();
		theFor: for (RegularUserCartProductModel cartProductModel : cartProducts) {
			if (cartProductModel.getProdCode().contains(productCode) && cartProductModel.getQuantity().contentEquals(quantity)) {
				result = cartProductModel;
				break theFor;
			}
		}
		return result;
	}

	private RegularUserCartTotalsModel discountTotals = new RegularUserCartTotalsModel();
	private RegularCartCalcDetailsModel discountCalculationModel = new RegularCartCalcDetailsModel();

	public void setVerifyTotalsDiscount(RegularUserCartTotalsModel discountTotals, RegularCartCalcDetailsModel discountCalculationModel) {
		this.discountCalculationModel = discountCalculationModel;
		this.discountTotals = discountTotals;

	}

	@StepGroup
	public void verifyTotalsDiscountWith40JbAndBuy3Get1Discount(String message) {
		verifySubTotals(discountTotals.getSubtotal(), discountCalculationModel.getSubTotal());
		verifyTotalAmount(discountTotals.getTotalAmount(), discountCalculationModel.getTotalAmount());
		verifyForthyDiscount(discountTotals.getDiscount(ConfigConstants.DISCOUNT_40_BONUS), discountCalculationModel.findSegment(ConfigConstants.DISCOUNT_40_BONUS));
		verifyJewelryDiscount(discountTotals.getDiscount(ConfigConstants.JEWELRY_BONUS), discountCalculationModel.findSegment(ConfigConstants.JEWELRY_BONUS));
		verifyBuy3Get1Discount(discountTotals.getDiscount(ConfigConstants.DISCOUNT_BUY_3_GET_1), discountCalculationModel.findSegment(ConfigConstants.DISCOUNT_BUY_3_GET_1));
	}

	@StepGroup
	public void verifyTotalsDiscountWith40AndJbDiscount(String message) {
		verifySubTotals(discountTotals.getSubtotal(), discountCalculationModel.getSubTotal());
		verifyTotalAmount(discountTotals.getTotalAmount(), discountCalculationModel.getTotalAmount());
		verifyForthyDiscount(discountTotals.getDiscount(ConfigConstants.DISCOUNT_40_BONUS), discountCalculationModel.findSegment(ConfigConstants.DISCOUNT_40_BONUS));
		verifyJewelryDiscount(discountTotals.getDiscount(ConfigConstants.JEWELRY_BONUS), discountCalculationModel.findSegment(ConfigConstants.JEWELRY_BONUS));

	}

	@StepGroup
	public void verifyTotalsDiscountWithVoucher(String message, boolean shouldVoucherBeVisible) {
		verifySubTotals(discountTotals.getSubtotal(), discountCalculationModel.getSubTotal());
		verifyTotalAmount(discountTotals.getTotalAmount(), discountCalculationModel.getTotalAmount());
		if (shouldVoucherBeVisible) {
			verifyVoucherDiscount(discountTotals.getDiscount(ConfigConstants.VOUCHER_DISCOUNT), discountCalculationModel.findSegment(ConfigConstants.VOUCHER_DISCOUNT));
		}
	}

	@Step
	public void verifyIP(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: IP points dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyJewelryDiscount(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Jewelry bonus doesn't match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyBuy3Get1Discount(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Buy 3 Get 1 for 50% bonus doesn't match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyForthyDiscount(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: 40% bonus doesn't match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyVoucherDiscount(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: The voucher discount doesn't match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyTotalAmount(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Total Amount dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifyTax(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: TAX dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));
	}

	@Step
	public void verifySubTotals(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Sub Totals dont match Expected: " + compare + " Actual: " + productNow, productNow.contains(compare));

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