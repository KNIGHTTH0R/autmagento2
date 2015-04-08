package com.workflows.frontend.borrowCart;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.tools.data.frontend.BorrowedProductModel;

public class AddBorrowedProductsWorkflow {
	@Steps
	public SearchSteps searchSteps;
	@Steps
	public ProductSteps productSteps;

	@StepGroup
	@Title("Add product to cart")
	public BorrowedProductModel setBorrowedProductToCart(String productName, String price, String finalPrice, String ipPoints) {
		return productSteps.setBorrowedProductAddToCart(productName,price,finalPrice,ipPoints);
	}

}
