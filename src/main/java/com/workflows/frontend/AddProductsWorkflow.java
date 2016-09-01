package com.workflows.frontend;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.tools.cartcalculations.smf.CartCalculator;
import com.tools.cartcalculations.smf.CartDiscountsCalculation;
import com.tools.constants.ConfigConstants;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;

public class AddProductsWorkflow {
	@Steps
	public SearchSteps searchSteps;
	@Steps
	public ProductSteps productSteps;

	/**
	 * The first three fields are mandatory (1 and 2 may be the same if the its
	 * the sku of the product or a means of identify the product uniquely) The
	 * productProperty field refers to the size or color property on the product
	 * Page. this should be set to 0 if no property is expected.
	 * 
	 * @param productCode
	 * @param productName
	 * @param qty
	 * @param productProperty
	 * @return
	 */
	@StepGroup
	public void setProductToCart(String productCode, String productName, String qty, String productProperty) {
		searchSteps.searchAndSelectProduct(productCode, productName);
		productSteps.setProductAddToCart(qty, productProperty);
	}

	@StepGroup
	@Title("Add product to cart")
	public BasicProductModel setBasicProductToCart(ProductDetailedModel model, String qty, String productProperty,
			String discountclass) {
		searchSteps.searchAndSelectProduct(model.getSku(), model.getName());
		String askingPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(), qty);
		String finalPrice = CartDiscountsCalculation.calculateFinalPrice(askingPrice, discountclass,CartCalculator.delta);
		String ipPoints = CartDiscountsCalculation.calculateIpPoints(model.getIp(), qty);
		if (discountclass.equals(ConfigConstants.DISCOUNT_50) || discountclass.equals(ConfigConstants.DISCOUNT_0)) {
			ipPoints = "0";
		}

		return productSteps.setBasicProductAddToCart(qty, productProperty, askingPrice, finalPrice, ipPoints,
				discountclass);
	}

	@StepGroup
	@Title("Update products in list")
	public BasicProductModel updateBasicProductToCart(ProductDetailedModel model, String qty, String productProperty,
			String discountclass) {
		String askingPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(), qty);
		String finalPrice = CartDiscountsCalculation.calculateFinalPrice(askingPrice, discountclass,CartCalculator.delta);
		String ipPoints = CartDiscountsCalculation.calculateIpPoints(model.getIp(), qty);
		if (discountclass.equals(ConfigConstants.DISCOUNT_50) || discountclass.equals(ConfigConstants.DISCOUNT_0)) {
			ipPoints = "0";
		}

		return productSteps.updateProduct(model, qty, productProperty, askingPrice, finalPrice, ipPoints,
				discountclass);
	}
	
	@StepGroup
	@Title("Add product to wishlist")
	public BasicProductModel setBasicProductToWishlist(ProductDetailedModel model, String qty, String productProperty,
			String discountclass) {
		searchSteps.searchAndSelectProduct(model.getSku(), model.getName());
		String askingPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(), qty);
		String finalPrice = CartDiscountsCalculation.calculateFinalPrice(askingPrice, discountclass,CartCalculator.delta);
		String ipPoints = CartDiscountsCalculation.calculateIpPoints(model.getIp(), qty);
		if (discountclass.equals(ConfigConstants.DISCOUNT_50) || discountclass.equals(ConfigConstants.DISCOUNT_0)) {
			ipPoints = "0";
		}

		return productSteps.setBasicProductAddToWhislist(qty, productProperty, askingPrice, finalPrice, ipPoints,
				discountclass);
	}

}
