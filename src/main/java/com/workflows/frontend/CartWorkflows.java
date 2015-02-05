package com.workflows.frontend;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.data.CalcDetailsModel;
import com.tools.data.CalculationModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.utils.PrintUtils;
import static org.fest.assertions.Assertions.assertThat;


public class CartWorkflows {

	@Steps
	public static CheckoutValidationSteps checkoutValidationSteps;

	private static List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();
	private static List<CartProductModel> cartProducts = new ArrayList<CartProductModel>();

	public void setValidateProductsModels(List<ProductBasicModel> productsList, List<CartProductModel> cartProducts) {
		CartWorkflows.productsList = productsList;
		CartWorkflows.cartProducts = cartProducts;
	}

//	@Step
//	public void validateProducts(String message) {
//		int count = 0;
//		if (!productsList.isEmpty()) {
//			for (ProductBasicModel productNow : productsList) {
//
//				CartProductModel compare = findProduct(productNow.getType(), cartProducts);
//
//				PrintUtils.printProductBasicModel(productNow);
//
//				compare.setQuantity(compare.getQuantity().replace("x", "").trim());
//
//				if (compare.getName() != null) {
//					checkoutValidationSteps.matchName(productNow.getName(), compare.getName());
//					checkoutValidationSteps.validateMatchPrice(productNow.getPrice(), compare.getUnitPrice());
//					checkoutValidationSteps.validateMatchQuantity(productNow.getQuantity(), compare.getQuantity());
//					count++;
//				} else {
//					Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
//				}
//			}
//		}
//
//		Assert.assertTrue("Failure: Products list is empty. ", productsList.size() != 0);
//		Assert.assertTrue("Failure: not all products have been validated. ", count == productsList.size());
//	}

	@Step
	public void validateProducts(String message) {

		for (ProductBasicModel productNow : productsList) {
			CartProductModel compare = findProduct(productNow.getType(), cartProducts);

			compare.setQuantity(compare.getQuantity().replace("x", "").trim());

			if (compare.getName() != null) {
				checkoutValidationSteps.matchName(productNow.getName(), compare.getName());
				checkoutValidationSteps.validateMatchPrice(productNow.getPrice(), compare.getUnitPrice());
				checkoutValidationSteps.validateMatchQuantity(productNow.getQuantity(), compare.getQuantity());
			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}

			int index = cartProducts.indexOf(compare);
			if (index > -1) {
				cartProducts.remove(index);
				System.out.println("index of " + compare.getName() + " removed");
				System.out.println(cartProducts.size() + " items remained");
			}
		}
		Assert.assertTrue("Failure: Products list is empty. ", productsList.size() != 0);

	}

	private CalculationModel calculationModel = new CalculationModel();
	private CartTotalsModel cartTotalModel = new CartTotalsModel();

	public void setCheckCalculationTotalsModels(CartTotalsModel cartTotalModel, CalculationModel calculationModel) {
		this.calculationModel = calculationModel;
		this.cartTotalModel = cartTotalModel;
	}

	@StepGroup
	public void checkCalculationTotals(String message) {

		checkoutValidationSteps.printCalculationModel("Calculated Values", String.valueOf(calculationModel.getAskingPrice()), String.valueOf(calculationModel.getFinalPrice()),
				String.valueOf(calculationModel.getIpPoints()));
		checkoutValidationSteps.printTotalsModel("Cart Totals", cartTotalModel.getSubtotal(), cartTotalModel.getDiscountSumString(), cartTotalModel.getTotalAmount(), cartTotalModel.getTax(),
				cartTotalModel.getShipping(), cartTotalModel.getJewelryBonus(), cartTotalModel.getIpPoints());

		Assert.assertTrue("The subtotal should be " + cartTotalModel.getSubtotal() + " and it is " + calculationModel.getAskingPrice() + "!",
				cartTotalModel.getSubtotal().equals(calculationModel.getAskingPrice().toString()));

		Assert.assertTrue("The final price should be " + cartTotalModel.getTotalAmount() + " and it is " + calculationModel.getFinalPrice() + "!",
				cartTotalModel.getTotalAmount().equals(calculationModel.getFinalPrice().toString()));

		Assert.assertTrue("The total ip points should be " + cartTotalModel.getIpPoints() + " and it is " + calculationModel.getIpPoints() + "!",
				cartTotalModel.getIpPoints().equals(String.valueOf(calculationModel.getIpPoints())));
	}

	public CartProductModel findProduct(String productCode, List<CartProductModel> cartProducts) {
		CartProductModel result = new CartProductModel();
		theFor: for (CartProductModel cartProductModel : cartProducts) {
			if (cartProductModel.getProdCode().contains(productCode)) {
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
		verifySubTotals(discountTotals.getSubtotal(), discountCalculationModel.getSubTotal());
		verifyTotalAmount(discountTotals.getTotalAmount(), discountCalculationModel.getTotalAmount());
		verifyTax(discountTotals.getTax(), discountCalculationModel.getTax());
		verifyJewelryBonus(discountTotals.getJewelryBonus(), discountCalculationModel.getJewelryBonus());
		verifyMarketingBonus(discountTotals.getMarketingBonus(), discountCalculationModel.getMarketingBonus());
		verifyIP(discountTotals.getIpPoints(), discountCalculationModel.getIpPoints());

	}
	@StepGroup
	public void verifyTotalsDiscountNoMarketing(String message) {
		verifySubTotals(discountTotals.getSubtotal(), discountCalculationModel.getSubTotal());
		verifyTotalAmount(discountTotals.getTotalAmount(), discountCalculationModel.getTotalAmount());
		verifyTax(discountTotals.getTax(), discountCalculationModel.getTax());
		verifyJewelryBonus(discountTotals.getJewelryBonus(), discountCalculationModel.getJewelryBonus());		
		verifyIP(discountTotals.getIpPoints(), discountCalculationModel.getIpPoints());
		
	}

	@Step
	public void verifyIP(String productNow, String compare) {

		Assert.assertTrue("Failure: IP points dont match Expected" + compare + " Actual: " + productNow, productNow.contentEquals(compare));

//		assertThat(productNow.contains(compare));
//		Assert.assertTrue("Failure: IP points dont match Expected" + compare + " Actual: " + productNow, productNow.contains(compare));


	}

	@Step
	public void verifyTotalAmount(String productNow, String compare) {
		assertThat(productNow.contains(compare));
//		Assert.assertTrue("Failure: Total Amount dont match Expected" + compare + " Actual: " + productNow, productNow.contains(compare));

	}

	@Step
	public void verifyTax(String productNow, String compare) {
		assertThat(productNow.contains(compare));
//		Assert.assertTrue("Failure: TAX dont match Expected" + compare + " Actual: " + productNow, productNow.contains(compare));

	}

	@Step
	public void verifyJewelryBonus(String productNow, String compare) {
		
		assertThat(productNow.contains(compare));
//		Assert.assertTrue("Failure: Jewelry Bonus dont match Expected" + compare + " Actual: " + productNow, productNow.contains(compare));

	}

	@Step
	public void verifySubTotals(String productNow, String compare) {
		
		assertThat(productNow.contains(compare));
//		Assert.assertTrue("Failure: Sub Totals dont match Expected" + compare + " Actual: " + productNow, productNow.contains(compare));

	}

	@Step
	public void verifyMarketingBonus(String productNow, String compare) {
		
		assertThat(productNow.contains(compare));
//		Assert.assertTrue("Failure: Marketing Bonus dont match Expected" + compare + " Actual: " + productNow, productNow.contains(compare));

	}

	@Step
	public void verifyShippingPrice(String productNow, String compare) {
		
		assertThat(productNow.contains(compare));
//		Assert.assertTrue("Failure: Shipping Price dont match Expected" + compare + " Actual: " + productNow, productNow.contains(compare));

	}

	@Step
	public void verifyDiscountsPrice(String productNow, String compare) {
		assertThat(productNow.contains(compare));
		
//		Assert.assertTrue("Failure: Discounts Price dont match Expected" + compare + " Actual: " + productNow, productNow.contains(compare));

	}

	private ShippingModel shippingGrabbedModel = new ShippingModel();;
	private ShippingModel shippingCalculatedModel = new ShippingModel();

	public void setVerifyShippingTotals(ShippingModel shippingTotals, ShippingModel shippingCalculatedModel) {
		this.shippingCalculatedModel = shippingCalculatedModel;
		this.shippingGrabbedModel = shippingTotals;
	}

	@Step
	public void verifyShippingTotals(String string) {
		verifyTotalAmount(shippingGrabbedModel.getTotalAmount(), shippingCalculatedModel.getTotalAmount());
		verifyShippingPrice(shippingGrabbedModel.getShippingPrice(), shippingCalculatedModel.getShippingPrice());
		verifyDiscountsPrice(shippingGrabbedModel.getDiscountPrice(), shippingCalculatedModel.getDiscountPrice());
		verifySubTotals(shippingGrabbedModel.getSubTotal(), shippingCalculatedModel.getSubTotal());
	}

}
