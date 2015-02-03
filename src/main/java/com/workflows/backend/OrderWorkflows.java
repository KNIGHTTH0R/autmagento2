package com.workflows.backend;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;

import com.steps.backend.validations.OrderValidationSteps;
import com.tools.data.CalcDetailsModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.utils.PrintUtils;


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
	
	@Step
	public void validateProducts2(String message) {
	
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
			int index = orderProducts.indexOf(compare);			
			if(index > -1){
				orderProducts.remove(index);	
				System.out.println("index of "+ compare.getProductName() +" removed");
				System.out.println(orderProducts.size() + " items remained");
			}
		}
		
		Assert.assertTrue("Failure: Products list is empty. ", productsList.size() != 0);
		
	}	
	
	
	private OrderTotalsModel orderTotalModel = new OrderTotalsModel();
	private CartTotalsModel cartTotalModel = new CartTotalsModel();
	
	public void setValidateTotals(OrderTotalsModel orderTotalModel, CartTotalsModel cartTotalModel){
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
		Assert.assertTrue("Failure: Jewelry Bonus values dont match: " + orderTotalModel.getTotalBonusJeverly() + " - " + cartTotalModel.getJewelryBonus(), orderTotalModel.getTotalBonusJeverly().contentEquals(cartTotalModel.getJewelryBonus()));
		
	}

	/**
	 * Method used only for reporting
	 * @param subtotal
	 * @param tax
	 * @param shipping
	 * @param totalAmount
	 * @param totalIP
	 * @param totalBonusJeverly
	 */
	@Step
	public void printOrderTotals(String subtotal, String tax, String shipping, String totalAmount, String totalIP, String totalBonusJeverly) {
	}
	
	/**
	 * Method used only for reporting
	 * @param subtotal
	 * @param tax
	 * @param shipping
	 * @param totalAmount
	 * @param totalIP
	 * @param totalBonusJeverly
	 */
	@Step
	public void printCartTotals(String subtotal, String tax, String shipping, String totalAmount, String totalIP, String totalBonusJeverly) {
	}

	
	@Step
	public void validateOrderStatus(String orderStatus, String string) {
		Assert.assertTrue("Failure: Status expected is " + string + ", actual status is " + orderStatus, orderStatus.contentEquals(string));
	}

	
	
	private OrderTotalsModel orderTotalsGrabbed = new OrderTotalsModel();
	private CalcDetailsModel calculatedTotals = new CalcDetailsModel();
	
	
	public void setValidateCalculationTotals(OrderTotalsModel orderTotalsModel, CalcDetailsModel calcDetailsModel) {
		this.orderTotalsGrabbed = orderTotalsModel;
		this.calculatedTotals = calcDetailsModel;
	}

	public void validateCalculationTotals(String string) {
		// TODO Auto-generated method stub
		
	}
}
