package com.workflows.backend;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.steps.backend.validations.OrderValidationSteps;
import com.tools.CustomVerification;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.frontend.BasicProductModel;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

public class OrderProductsWorkflows {

	@Steps
	public OrderValidationSteps orderValidationSteps;

	private List<BasicProductModel> productsList = new ArrayList<BasicProductModel>();
	private List<OrderItemModel> orderProducts = new ArrayList<OrderItemModel>();

	public void setValidateProductsModels(List<BasicProductModel> productsList, List<OrderItemModel> orderProducts) {
		this.productsList = productsList;
		this.orderProducts = orderProducts;
	}

	@Step
	public void validateProducts(String message) {
		for (BasicProductModel productNow : productsList) {

			OrderItemModel compare = orderValidationSteps.findProduct(productNow.getProdCode(),
					productNow.getQuantity(), orderProducts);

			if (compare.getProductName() != null && (productNow.getQuantity().contentEquals(compare.getNumber()))) {
				// orderValidationSteps.matchSku(productNow.getProdCode(),
				// compare.getProductCode());
				orderValidationSteps.validateMatchPrice(productNow.getUnitPrice(), compare.getPrice());
				orderValidationSteps.validateMatchQuantity(productNow.getQuantity(), compare.getNumber());

			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}
			int index = orderProducts.indexOf(compare);
			if (index > -1) {
				orderProducts.remove(index);
				System.out.println("index of " + compare.getProductName() + " removed");
				System.out.println(orderProducts.size() + " items remained");
			}
		}

		Assert.assertTrue("Failure: Products list is empty. ", productsList.size() != 0);
		Assert.assertTrue("Failure: Not all products have been validated . ", orderProducts.size() == 0);

	}

	@StepGroup
	public void validateOrderMailProducts(String message) {

		for (BasicProductModel productNow : productsList) {

			OrderItemModel compare = findProduct(productNow.getProdCode(),
					productNow.getQuantity(), orderProducts);

			if (compare.getProductCode() != null && (productNow.getQuantity().contentEquals(compare.getNumber()))) {
				matchSku(productNow.getProdCode(), compare.getProductCode());
				validateMatchPrice(productNow.getProductsPrice(), compare.getOriginalPrice());
				validateMatchQuantity(productNow.getQuantity(), compare.getNumber());
				validateMatchFinalPrice(productNow.getFinalPrice(), compare.getPrice());
				validateMatchDiscount(productNow.getTotalDiscount(), compare.getDiscountAmount());

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

	@Step
	public void validateProductsOnShippment(String message) {

		for (BasicProductModel productNow : productsList) {

			OrderItemModel compare = orderValidationSteps.findProduct(productNow.getProdCode(),
					productNow.getQuantity(), orderProducts);

			if (compare.getProductName() != null && (productNow.getQuantity().contentEquals(compare.getNumber()))) {
				// orderValidationSteps.matchSku(productNow.getProdCode(),
				// compare.getProductCode());
				// orderValidationSteps.validateMatchPrice(productNow.getUnitPrice(),
				// compare.getPrice());
				orderValidationSteps.validateMatchQuantity(productNow.getQuantity(), compare.getNumber());

			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}
			int index = orderProducts.indexOf(compare);
			if (index > -1) {
				orderProducts.remove(index);
				System.out.println("index of " + compare.getProductName() + " removed");
				System.out.println(orderProducts.size() + " items remained");
			}
		}

		Assert.assertTrue("Failure: Products list is empty. ", productsList.size() != 0);
		Assert.assertTrue("Failure: Not all products have been validated . ", orderProducts.size() == 0);

	}

	@Step
	public void matchName(String productNow, String compare) {

		CustomVerification.verifyTrue("Failure: Name values dont match: " + productNow + " - " + compare,
				productNow.toLowerCase().contains(compare.toLowerCase()));

	}

	@Step
	public void matchSku(String prodCode, String compare) {
		CustomVerification.verifyTrue("Failure: Sku values dont match: " + prodCode + " - " + compare,
				compare.contains(prodCode));
	}

	@Step
	public void validateMatchQuantity(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Quantity values dont match: " + productNow + " - " + compare,
				compare.contains(productNow));
	}

	@Step
	@Screenshots(onlyOnFailures = true)
	public void validateMatchDiscount(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Discount values dont match: " + productNow + " - " + compare,
				productNow.replaceAll("\\s+", "").contentEquals(compare.replaceAll("\\s+", "")));
	}

	@Step
	@Screenshots(onlyOnFailures = true)
	public void validateMatchPrice(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Price values dont match: " + productNow + " - " + compare,
				productNow.replaceAll("\\s+", "").contentEquals(compare.replaceAll("\\s+", "")));
	}

	@Step
	@Screenshots(onlyOnFailures = true)
	public void validateMatchFinalPrice(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Final Price values dont match: " + productNow + " - " + compare,
				productNow.replaceAll("\\s+", "").contentEquals(compare.replaceAll("\\s+", "")));
	}


	public OrderItemModel findProduct(String productCode, String qty, List<OrderItemModel> orderProducts) {
		OrderItemModel result = new OrderItemModel();

		theFor: for (OrderItemModel orderProduct : orderProducts) {
			if (orderProduct.getProductCode().contains(productCode) && orderProduct.getNumber().contentEquals(qty)) {

				result = orderProduct;

				break theFor;
			}
		}
		return result;
	}
	
}
