package com.workflows.backend;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;

import com.steps.backend.validations.OrderValidationSteps;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.frontend.BasicProductModel;
import com.tools.utils.PrintUtils;

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

			OrderItemModel compare = orderValidationSteps.findProduct(productNow.getProdCode(), productNow.getQuantity(), orderProducts);


			PrintUtils.printProductsComparisonBackend(productNow, compare);
			if (compare.getProductName() != null && (productNow.getQuantity().contentEquals(compare.getNumber()))) {
				orderValidationSteps.matchName(productNow.getName(), compare.getProductName());
				orderValidationSteps.validateMatchPrice(productNow.getUnitPrice(), compare.getOriginalPrice());
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
}
