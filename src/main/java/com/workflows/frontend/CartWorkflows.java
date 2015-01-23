package com.workflows.frontend;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;

import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.tools.PrintUtils;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.ProductBasicModel;

public class CartWorkflows {

	@Steps
	public static CheckoutValidationSteps checkoutValidationSteps;

	private static List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();
	private static List<CartProductModel> cartProducts = new ArrayList<CartProductModel>();

	public void setModels(List<ProductBasicModel> productsList, List<CartProductModel> cartProducts) {
		CartWorkflows.productsList = productsList;
		CartWorkflows.cartProducts = cartProducts;
	}

	@Step
	public void validateProducts(String message) {
		for (ProductBasicModel productNow : productsList) {
			CartProductModel compare = checkoutValidationSteps.findProduct(productNow.getType(), cartProducts);

			PrintUtils.printProductsCompare(productNow, compare);
			compare.setQuantity(compare.getQuantity().replace("x", "").trim());

			if (compare.getName() != null) {
				checkoutValidationSteps.matchName(productNow.getName(), compare.getName());
				checkoutValidationSteps.validateMatchPrice(productNow.getPrice(), compare.getUnitPrice());
				checkoutValidationSteps.validateMatchQuantity(productNow.getQuantity(), compare.getQuantity());
			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}
		}
	}

}
