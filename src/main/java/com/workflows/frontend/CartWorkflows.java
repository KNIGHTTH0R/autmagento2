package com.workflows.frontend;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.data.CalculationModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.ProductBasicModel;

public class CartWorkflows {

	@Steps
	public static CheckoutValidationSteps checkoutValidationSteps;

	private static List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();
	private static List<CartProductModel> cartProducts = new ArrayList<CartProductModel>();

	public void setValidateProductsModels(List<ProductBasicModel> productsList, List<CartProductModel> cartProducts) {
		CartWorkflows.productsList = productsList;
		CartWorkflows.cartProducts = cartProducts;
	}

	@Step
	public void validateProducts(String message) {
		int count = 0;
		for (ProductBasicModel productNow : productsList) {
			CartProductModel compare = findProduct(productNow.getType(), cartProducts);

			compare.setQuantity(compare.getQuantity().replace("x", "").trim());

			if (compare.getName() != null) {
				checkoutValidationSteps.matchName(productNow.getName(), compare.getName());
				checkoutValidationSteps.validateMatchPrice(productNow.getPrice(), compare.getUnitPrice());
				checkoutValidationSteps.validateMatchQuantity(productNow.getQuantity(), compare.getQuantity());
				count++;
			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}
		}
		Assert.assertTrue("Failure: Products list is empty. ", productsList.size() != 0);
		Assert.assertTrue("Failure: not all products have been validated. ", count == productsList.size());
	}
	
	@Step
	public void validateProducts2(String message) {
	
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
			if(index > -1){
				cartProducts.remove(index);	
				System.out.println("index of "+ compare.getName() +" removed");
				System.out.println(cartProducts.size() + " items remained");
			}
		}
		Assert.assertTrue("Failure: Products list is empty. ", productsList.size() != 0);
		
	}
	
	
	private CalculationModel calculationModel = new CalculationModel();
	private CartTotalsModel cartTotalModel = new CartTotalsModel();
	
	public void setCheckCalculationTotalsModels(CalculationModel calculationModel, CartTotalsModel cartTotalModel){
		this.calculationModel = calculationModel;
		this.cartTotalModel = cartTotalModel;
	}
	
	@StepGroup
	public void checkCalculationTotals(String message) {
		
		checkoutValidationSteps.printCalculationModel("Calculated Values", String.valueOf(calculationModel.getAskingPrice()), String.valueOf(calculationModel.getFinalPrice()), String.valueOf(calculationModel.getIpPoints()));
		checkoutValidationSteps.printTotalsModel("Cart Totals", cartTotalModel.getSubtotal(), cartTotalModel.getDiscountSumString(), cartTotalModel.getTotalAmount(), cartTotalModel.getTax(), cartTotalModel.getShipping(),
				cartTotalModel.getJewelryBonus(), cartTotalModel.getIpPoints());

		Assert.assertTrue("The subtotal should be " + cartTotalModel.getSubtotal() + " and it is " + calculationModel.getAskingPrice() + "!", cartTotalModel
				.getSubtotal().equals(calculationModel.getAskingPrice().toString()));

		Assert.assertTrue("The final price should be " + cartTotalModel.getTotalAmount() + " and it is " + calculationModel.getFinalPrice() + "!", cartTotalModel
				.getTotalAmount().equals(calculationModel.getFinalPrice().toString()));

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

	
//	private CartTotalsModel cartCalculatedTotal = new CartTotalsModel();
//	private CartTotalsModel cartGrabbedTotal = new CartTotalsModel();
//	
//	public void setTotalsModels(CartTotalsModel cartCalculatedTotal, CartTotalsModel cartGrabbedTotal){
//		this.cartCalculatedTotal = cartCalculatedTotal;
//		this.cartGrabbedTotal = cartGrabbedTotal;
//	}
//	
//	@StepGroup
//	public void checkCompleteTotals(String message){
//
//		
//		checkoutValidationSteps.printTotalsModel("Cart Totals", cartGrabbedTotal.getSubtotal(), cartGrabbedTotal.getDiscountSumString(), cartGrabbedTotal.getTotalAmount(), cartGrabbedTotal.getTax(), cartGrabbedTotal.getShipping(),
//				cartGrabbedTotal.getJewelryBonus(), cartGrabbedTotal.getIpPoints());
//		
//		Assert.assertTrue("The subtotal should be " + cartGrabbedTotal.getSubtotal() + " and it is " + cartCalculatedTotal.getSubtotal() + "!", cartCalculatedTotal
//				.getSubtotal().equals(cartGrabbedTotal.getSubtotal()));
//		
//		Assert.assertTrue("The subtotal should be " + cartGrabbedTotal.getDiscountSumString() + " and it is " + cartCalculatedTotal.getDiscountSumString() + "!", cartCalculatedTotal
//				.getDiscountSumString().equals(cartGrabbedTotal.getDiscountSumString()));
//		
//		Assert.assertTrue("The subtotal should be " + cartGrabbedTotal.getTotalAmount() + " and it is " + cartCalculatedTotal.getTotalAmount() + "!", cartCalculatedTotal
//				.getTotalAmount().equals(cartGrabbedTotal.getTotalAmount()));
//		
//		Assert.assertTrue("The subtotal should be " + cartGrabbedTotal.getTax() + " and it is " + cartCalculatedTotal.getTax() + "!", cartCalculatedTotal
//				.getTax().equals(cartGrabbedTotal.getTax()));
//		
//		Assert.assertTrue("The subtotal should be " + cartGrabbedTotal.getJewelryBonus() + " and it is " + cartCalculatedTotal.getJewelryBonus() + "!", cartCalculatedTotal
//				.getJewelryBonus().equals(cartGrabbedTotal.getJewelryBonus()));
//		
//		Assert.assertTrue("The subtotal should be " + cartGrabbedTotal.getMarketingBonus() + " and it is " + cartCalculatedTotal.getMarketingBonus() + "!", cartCalculatedTotal
//				.getMarketingBonus().equals(cartGrabbedTotal.getMarketingBonus()));
//		
//		Assert.assertTrue("The subtotal should be " + cartGrabbedTotal.getIpPoints() + " and it is " + cartCalculatedTotal.getIpPoints() + "!", cartCalculatedTotal
//				.getIpPoints().equals(cartGrabbedTotal.getIpPoints()));
//
//		
//	}
	
	
	
	
	
}
