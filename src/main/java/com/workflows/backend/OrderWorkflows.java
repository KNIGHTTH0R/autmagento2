package com.workflows.backend;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;

import com.steps.backend.validations.OrderValidationSteps;
import com.tools.PrintUtils;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.ProductBasicModel;


public class OrderWorkflows {
	
	@Steps
	public static OrderValidationSteps orderValidationSteps;
	
	private List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();
	private List<OrderItemModel> orderProducts = new ArrayList<OrderItemModel>();
	
	public void setValidateProductsModels(List<ProductBasicModel> productsList, List<OrderItemModel> orderProducts) {
		this.productsList = productsList;
		this.orderProducts = orderProducts;
	}
	
	@StepGroup
	public void validateProducts(String message) {
		int count = 0;
		for (ProductBasicModel productNow : productsList) {
			OrderItemModel compare = orderValidationSteps.findProduct(productNow.getType(), orderProducts);

			PrintUtils.printProductsCompareBackend(productNow, compare);

			if (compare.getProductName() != null) {
				orderValidationSteps.matchName(productNow.getName(), compare.getProductName());
				orderValidationSteps.validateMatchPrice(productNow.getPrice(), compare.getPrice());
				orderValidationSteps.validateMatchQuantity(productNow.getQuantity(), compare.getNumber());
				count++;
			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}
		}
		
		Assert.assertTrue("Failure: Products list is empty. ", productsList.size() != 0);
		Assert.assertTrue("Failure: not all products have been validated. ", count == productsList.size());
	}
	
	private OrderTotalsModel orderTotalModel = new OrderTotalsModel();
	private CartTotalsModel cartTotalModel = new CartTotalsModel();
	
	public void setCheckCalculationTotalsModels(OrderTotalsModel orderTotalModel, CartTotalsModel cartTotalModel){
		this.orderTotalModel = orderTotalModel;
		this.cartTotalModel = cartTotalModel;
	}
	
	@Step
	public void validateTotals(String message){
		printOrderTotals(orderTotalModel.getSubtotal(), orderTotalModel.getTax(),orderTotalModel.getShipping(), orderTotalModel.getTotalAmount(),orderTotalModel.getTotalIP(),orderTotalModel.getTotalBonusJeverly());
		printCartTotals(cartTotalModel.getSubtotal(), cartTotalModel.getTax(),cartTotalModel.getShipping(), cartTotalModel.getTotalAmount(),cartTotalModel.getIpPoints(),cartTotalModel.getJewelryBonus());
		
		Assert.assertTrue("Failure: Subtotal values dont match: " + orderTotalModel.getSubtotal() + " - " + cartTotalModel.getSubtotal(), orderTotalModel.getSubtotal().contentEquals(cartTotalModel.getSubtotal()));
		Assert.assertTrue("Failure: Tax values dont match: " + orderTotalModel.getTax() + " - " + cartTotalModel.getTax(), orderTotalModel.getTax().contentEquals(cartTotalModel.getTax()));
		Assert.assertTrue("Failure: Shipping values dont match: " + orderTotalModel.getShipping() + " - " + cartTotalModel.getShipping(), orderTotalModel.getShipping().contentEquals(cartTotalModel.getShipping()));
		Assert.assertTrue("Failure: Total Amount values dont match: " + orderTotalModel.getTotalAmount() + " - " + cartTotalModel.getTotalAmount(), orderTotalModel.getTotalAmount().contentEquals(cartTotalModel.getTotalAmount()));
		Assert.assertTrue("Failure: Total IP values dont match: " + orderTotalModel.getTotalIP() + " - " + cartTotalModel.getIpPoints(), orderTotalModel.getTotalIP().contentEquals(cartTotalModel.getIpPoints()));
		//TODO - This might fail due to BigDecimal
		Assert.assertTrue("Failure: Jewelry Bonus values dont match: " + orderTotalModel.getTotalBonusJeverly() + " - " + cartTotalModel.getJewelryBonus(), orderTotalModel.getTotalBonusJeverly().contentEquals(cartTotalModel.getJewelryBonus()));
		
	}

	@Step
	public void printOrderTotals(String subtotal, String tax, String shipping, String totalAmount, String totalIP, String totalBonusJeverly) {
		// TODO Method used only for reporting - Might need to move
	}
	
	@Step
	public void printCartTotals(String subtotal, String tax, String shipping, String totalAmount, String totalIP, String totalBonusJeverly) {
		// TODO Method used only for reporting - Might need to move
	}
}
