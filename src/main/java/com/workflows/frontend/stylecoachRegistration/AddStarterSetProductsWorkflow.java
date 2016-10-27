package com.workflows.frontend.stylecoachRegistration;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import com.steps.frontend.ProductSteps;
import com.steps.frontend.StarterSetSteps;
import com.tools.data.frontend.StarterSetProductModel;

public class AddStarterSetProductsWorkflow {

	@Steps
	public ProductSteps productSteps;
	@Steps
	public StarterSetSteps starterSetSteps;

	@StepGroup
	@Title("Add starter set product to cart")
	public StarterSetProductModel setStarterSetProductToCart(String starterKitId, String price) {

		starterSetSteps.selectStarterKit(starterKitId);

		return productSteps.setStarterSetProductAddToCart(price);
	}
}
