package com.workflows.backend;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;

import com.steps.backend.validations.OrderValidationSteps;
import com.tools.CustomVerification;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.ProductBasicModel;

public class OrderWorkflows {

	@Steps
	public OrderValidationSteps orderValidationSteps;

	private List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();
	private List<OrderItemModel> orderProducts = new ArrayList<OrderItemModel>();

	public void setValidateProductsModels(List<ProductBasicModel> productsList, List<OrderItemModel> orderProducts) {
		this.productsList = productsList;
		this.orderProducts = orderProducts;
	}

	@Step
	public void validateProducts(String message) {

		for (ProductBasicModel productNow : productsList) {

			OrderItemModel compare = orderValidationSteps.findProduct(productNow.getType(), productNow.getQuantity(), orderProducts);

			if (compare.getProductName() != null && (productNow.getQuantity().contentEquals(compare.getNumber()))) {
				orderValidationSteps.matchName(productNow.getName(), compare.getProductName());
				orderValidationSteps.validateMatchPrice(productNow.getPrice(), compare.getPrice());
				orderValidationSteps.validateMatchQuantity(productNow.getQuantity(), compare.getNumber());
			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}
			int index = orderProducts.indexOf(compare);
			if (index > -1) {
				orderProducts.remove(index);
			}
		}

		Assert.assertTrue("Failure: Products list is empty. ", productsList.size() != 0);
		Assert.assertTrue("Failure: Not all products have been validated . ", orderProducts.size() == 0);

	}

	private OrderTotalsModel orderTotalModel = new OrderTotalsModel();
	private CartTotalsModel cartTotalModel = new CartTotalsModel();

	public void setValidateTotals(OrderTotalsModel orderTotalModel, CartTotalsModel cartTotalModel) {
		this.orderTotalModel = orderTotalModel;
		this.cartTotalModel = cartTotalModel;
	}

	@Step
	public void validateTotals(String message) {
		printOrderTotals(orderTotalModel.getSubtotal(), orderTotalModel.getTax(), orderTotalModel.getShipping(), orderTotalModel.getTotalAmount(), orderTotalModel.getTotalIP(),
				orderTotalModel.getTotalBonusJeverly());
		printCartTotals(cartTotalModel.getSubtotal(), cartTotalModel.getTax(), cartTotalModel.getShipping(), cartTotalModel.getTotalAmount(), cartTotalModel.getIpPoints(),
				cartTotalModel.getJewelryBonus());

		verifySubTotals(orderTotalModel.getSubtotal(), cartTotalModel.getSubtotal());
		verifyTax(orderTotalModel.getTax(), cartTotalModel.getTax());
		verifyShipping(orderTotalModel.getShipping(), cartTotalModel.getShipping());
		verifyTotalAmount(orderTotalModel.getTotalAmount(), cartTotalModel.getTotalAmount());
		verifyIP(orderTotalModel.getTotalIP(), cartTotalModel.getIpPoints());
		verifyJewelryBonus(orderTotalModel.getTotalBonusJeverly(), cartTotalModel.getJewelryBonus());

	}

	private OrderTotalsModel orderTotalsGrabbed = new OrderTotalsModel();
	private OrderTotalsModel calculatedTotals = new OrderTotalsModel();

	public void setValidateCalculationTotals(OrderTotalsModel orderTotalsModel, OrderTotalsModel calcDetailsModel) {
		this.orderTotalsGrabbed = orderTotalsModel;
		this.calculatedTotals = calcDetailsModel;
	}

	public void validateCalculationTotals(String string) {

		verifySubTotals(orderTotalsGrabbed.getSubtotal(), calculatedTotals.getSubtotal());
		verifyTax(orderTotalsGrabbed.getTax(), calculatedTotals.getTax());
		verifyShipping(orderTotalsGrabbed.getShipping(), calculatedTotals.getShipping());
		verifyTotalAmount(orderTotalsGrabbed.getTotalAmount(), calculatedTotals.getTotalAmount());
		verifyIP(orderTotalsGrabbed.getTotalIP(), calculatedTotals.getTotalIP());
		verifyJewelryBonus(orderTotalsGrabbed.getTotalBonusJeverly(), calculatedTotals.getTotalBonusJeverly());
		verifyMarketingBonus(orderTotalsGrabbed.getTotalMarketingBonus(), calculatedTotals.getTotalMarketingBonus());
		verifyTotalPaid(orderTotalsGrabbed.getTotalPaid(), calculatedTotals.getTotalPaid());
		verifyTotalRefunded(orderTotalsGrabbed.getTotalRefunded(), calculatedTotals.getTotalRefunded());
		verifyTotalPayable(orderTotalsGrabbed.getTotalPayable(), calculatedTotals.getTotalPayable());

	}

	public void validateRegularUserCalculationTotals(String string) {

		verifySubTotals(orderTotalsGrabbed.getSubtotal(), calculatedTotals.getSubtotal());
		verifyTax(orderTotalsGrabbed.getTax(), calculatedTotals.getTax());
		verifyShipping(orderTotalsGrabbed.getShipping(), calculatedTotals.getShipping());
		verifyTotalAmount(orderTotalsGrabbed.getTotalAmount(), calculatedTotals.getTotalAmount());
		verifyTotalPaid(orderTotalsGrabbed.getTotalPaid(), calculatedTotals.getTotalPaid());
		verifyTotalRefunded(orderTotalsGrabbed.getTotalRefunded(), calculatedTotals.getTotalRefunded());
		verifyTotalPayable(orderTotalsGrabbed.getTotalPayable(), calculatedTotals.getTotalPayable());

	}
	public void validateBorrowCartCalculationTotals(String string) {
		
		verifySubTotals(orderTotalsGrabbed.getSubtotal(), calculatedTotals.getSubtotal());
		verifyTax(orderTotalsGrabbed.getTax(), calculatedTotals.getTax());
		verifyShipping(orderTotalsGrabbed.getShipping(), calculatedTotals.getShipping());
		verifyTotalAmount(orderTotalsGrabbed.getTotalAmount(), calculatedTotals.getTotalAmount());
		verifyTotalPaid(orderTotalsGrabbed.getTotalPaid(), calculatedTotals.getTotalPaid());
		verifyTotalRefunded(orderTotalsGrabbed.getTotalRefunded(), calculatedTotals.getTotalRefunded());
		verifyTotalPayable(orderTotalsGrabbed.getTotalPayable(), calculatedTotals.getTotalPayable());
		
	}
	public void validateStarterSetCartCalculationTotals(String string) {
		
		verifySubTotals(orderTotalsGrabbed.getSubtotal(), calculatedTotals.getSubtotal());
		verifyTax(orderTotalsGrabbed.getTax(), calculatedTotals.getTax());
		verifyShipping(orderTotalsGrabbed.getShipping(), calculatedTotals.getShipping());
		verifyTotalAmount(orderTotalsGrabbed.getTotalAmount(), calculatedTotals.getTotalAmount());
		verifyTotalPaid(orderTotalsGrabbed.getTotalPaid(), calculatedTotals.getTotalPaid());
		verifyTotalRefunded(orderTotalsGrabbed.getTotalRefunded(), calculatedTotals.getTotalRefunded());
		verifyTotalPayable(orderTotalsGrabbed.getTotalPayable(), calculatedTotals.getTotalPayable());
		
	}

	// ----------------------------------------//

	@Step
	public void verifySubTotals(String orderValue, String calculation) {

		CustomVerification.verifyTrue("Failure: Subtotal values dont match: " + orderValue + " - " + calculation, orderValue.contains(calculation));
	}

	@Step
	public void verifyTotalAmount(String orderValue, String calculation) {
		CustomVerification.verifyTrue("Failure: Total Amount values dont match: " + orderValue + " - " + calculation, orderValue.contains(calculation));
	}

	@Step
	public void verifyTax(String orderValue, String calculation) {
		CustomVerification.verifyTrue("Failure: Tax values dont match: " + orderValue + " - " + calculation, orderValue.contains(calculation));
	}

	@Step
	public void verifyShipping(String orderValue, String calculation) {
		CustomVerification.verifyTrue("Failure: Shipping values dont match: " + orderValue + " - " + calculation, orderValue.contains(calculation));
		System.out.println(orderValue + " : " + calculation);
	}

	@Step
	public void verifyIP(String orderValue, String calculation) {
		CustomVerification.verifyTrue("Failure: IP values dont match: " + orderValue + " - " + calculation, orderValue.contentEquals(calculation));
	}

	@Step
	public void verifyJewelryBonus(String orderValue, String calculation) {
		CustomVerification.verifyTrue("Failure: Jewelry bonus values dont match: " + orderValue + " - " + calculation, orderValue.contains(calculation));
	}

	/**
	 * Method used only for reporting
	 * 
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

	@Step
	public void verifyMarketingBonus(String orderValue, String calculation) {
		CustomVerification.verifyTrue("Failure: Marketing bonus values dont match: " + orderValue + " - " + calculation, orderValue.contains(calculation));
	}

	@Step
	public void verifyTotalPayable(String orderValue, String calculation) {
		CustomVerification.verifyTrue("Failure: Total Payable values dont match: " + orderValue + " - " + calculation, orderValue.contains(calculation));
	}

	@Step
	public void verifyTotalPaid(String orderValue, String calculation) {
		CustomVerification.verifyTrue("Failure: Total Paid values dont match: " + orderValue + " - " + calculation, orderValue.contains(calculation));
	}

	@Step
	public void verifyTotalRefunded(String orderValue, String calculation) {
		CustomVerification.verifyTrue("Failure: Total Refunded values dont match: " + orderValue + " - " + calculation, orderValue.contains(calculation));
	}

	/**
	 * Method used only for reporting
	 * 
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
		CustomVerification.verifyTrue("Failure: Status expected is " + string + ", actual status is " + orderStatus, orderStatus.contentEquals(string));

	}
	@Step
	public void validateScheduledDeliveryDate(String orderStatus, String string) {
		CustomVerification.verifyTrue("Failure: Status expected is " + string + ", actual status is " + orderStatus, orderStatus.contentEquals(string));
		
	}

}
