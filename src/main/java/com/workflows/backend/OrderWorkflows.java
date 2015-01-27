package com.workflows.backend;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import com.steps.backend.validations.OrderValidationSteps;
import com.tools.PrintUtils;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.ProductBasicModel;


public class OrderWorkflows {
	@Steps
	public static OrderValidationSteps orderValidationSteps;
	
	private static List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();
	private static List<OrderItemModel> orderProducts = new ArrayList<OrderItemModel>();
	
	public void setValidateProductsModels(List<ProductBasicModel> productsList, List<OrderItemModel> orderProducts) {
		OrderWorkflows.productsList = productsList;
		OrderWorkflows.orderProducts = orderProducts;
	}
	
	@Step
	public void validateProducts(String message) {
		for (ProductBasicModel productNow : productsList) {
			OrderItemModel compare = orderValidationSteps.findProduct(productNow.getType(), orderProducts);

			PrintUtils.printProductsCompareBackend(productNow, compare);

			if (compare.getProductName() != null) {
				orderValidationSteps.matchName(productNow.getName(), compare.getProductName());
				orderValidationSteps.validateMatchPrice(productNow.getPrice(), compare.getPrice());
				orderValidationSteps.validateMatchQuantity(productNow.getQuantity(), compare.getNumber());
			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}
		}
	}
	
	private OrderTotalsModel orderTotalModel = new OrderTotalsModel();
	private CartTotalsModel cartTotalModel = new CartTotalsModel();
	
	public void setCheckCalculationTotalsModels(OrderTotalsModel orderTotalModel, CartTotalsModel cartTotalModel){
		this.orderTotalModel = orderTotalModel;
		this.cartTotalModel = cartTotalModel;
	}
	
	@Step
	public void validateTotals(String message){
		Assert.assertTrue("Failure: Subtotal values dont match: " + orderTotalModel.getSubtotal() + " - " + cartTotalModel.getSubtotal(), orderTotalModel.getSubtotal().contentEquals(cartTotalModel.getSubtotal()));
		Assert.assertTrue("Failure: Tax values dont match: " + orderTotalModel.getTax() + " - " + cartTotalModel.getTax(), orderTotalModel.getTax().contentEquals(cartTotalModel.getTax()));
		Assert.assertTrue("Failure: Shipping values dont match: " + orderTotalModel.getShipping() + " - " + cartTotalModel.getShipping(), orderTotalModel.getShipping().contentEquals(cartTotalModel.getShipping()));
		Assert.assertTrue("Failure: Total Amount values dont match: " + orderTotalModel.getTotalAmount() + " - " + cartTotalModel.getTotalAmount(), orderTotalModel.getTotalAmount().contentEquals(cartTotalModel.getTotalAmount()));
		Assert.assertTrue("Failure: Total IP values dont match: " + orderTotalModel.getTotalIP() + " - " + cartTotalModel.getIpPoints(), orderTotalModel.getTotalIP().contentEquals(cartTotalModel.getIpPoints()));
		Assert.assertTrue("Failure: Jewelry Bonus values dont match: " + orderTotalModel.getTotalBonusJeverly() + " - " + cartTotalModel.getJewelryBonus(), orderTotalModel.getTotalBonusJeverly().contentEquals(PrintUtils.getDoubleWithTwoDigitstToString(Double.parseDouble(cartTotalModel.getJewelryBonus()))));
		
	}
	
	


}
