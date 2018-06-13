package com.workflows.frontend;

import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.tools.cartcalculations.smf.CartCalculator;
import com.tools.cartcalculations.smf.CartDiscountsCalculation;
import com.tools.constants.ConfigConstants;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.soap.ProductDetailedModel;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

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
		System.out.println("productName "+productName);
		searchSteps.navigateToProductPage(productName);
		productSteps.setProductAddToCart(qty, productProperty);
	}

	@StepGroup
	@Title("Add product to cart")
	public BasicProductModel setBasicProductToCart(ProductDetailedModel model, String qty, String productProperty,
			String discountclass) {
		searchSteps.navigateToProductPage(model.getSku());
		String askingPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(), qty);
		String finalPrice = CartDiscountsCalculation.calculateFinalPrice(askingPrice, discountclass,
				CartCalculator.delta);
		String ipPoints = CartDiscountsCalculation.calculateIpPoints(model.getIp(), qty);
		if (discountclass.equals(ConfigConstants.DISCOUNT_50) || discountclass.equals(ConfigConstants.DISCOUNT_0)) {
			ipPoints = "0";
		}

		return productSteps.setBasicProductAddToCart(qty, productProperty, askingPrice, finalPrice, ipPoints,
				discountclass);
	}
	
	
	@StepGroup
	@Title("Add product to cart")
	public BasicProductModel setBasicChildProductToCart(ProductDetailedModel model, String qty, String productProperty,
			String discountclass) {
		String askingPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(), qty);
		String finalPrice = CartDiscountsCalculation.calculateFinalPrice(askingPrice, discountclass,
				CartCalculator.delta);
		String ipPoints = CartDiscountsCalculation.calculateIpPoints(model.getIp(), qty);
	/*	if (discountclass.equals(ConfigConstants.DISCOUNT_50) || discountclass.equals(ConfigConstants.DISCOUNT_0)) {
			ipPoints = "0";
		}*/

		return productSteps.setChildProductAddToCart(model,qty, productProperty, askingPrice, finalPrice, ipPoints,
				discountclass);
	}

	
	@StepGroup
	@Title("Add product to cart")
	public BasicProductModel setMarketingProductToCart(ProductDetailedModel model, String qty, String productProperty,
			String discountclass) {
		String askingPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(), qty);
		String finalPrice = CartDiscountsCalculation.calculateFinalPrice(askingPrice, discountclass,
				CartCalculator.delta);
		String ipPoints = CartDiscountsCalculation.calculateIpPoints(model.getIp(), qty);
		/*if (discountclass.equals(ConfigConstants.DISCOUNT_50) || discountclass.equals(ConfigConstants.DISCOUNT_0)) {
			ipPoints = "0";
		}*/

		return productSteps.setMarketingProductAddToCart(model,qty, productProperty, askingPrice, finalPrice, ipPoints,
				discountclass);
	}
	
	public BasicProductModel setBasicProductToCartBeta(ProductDetailedModel model, String qty, String productProperty,
			String discountclass) {
		
		System.out.println(model.getPrice());
		String askingPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(), qty);
		System.out.println(askingPrice);
		System.out.println(discountclass);
		System.out.println("delta "+ CartCalculator.delta);
		String finalPrice = CartDiscountsCalculation.calculateFinalPrice(askingPrice, discountclass,
				CartCalculator.delta);
		String ipPoints = CartDiscountsCalculation.calculateIpPoints(model.getIp(), qty);
		if (discountclass.equals(ConfigConstants.DISCOUNT_50) || discountclass.equals(ConfigConstants.DISCOUNT_0)) {
			ipPoints = "0";
		}

		BasicProductModel result = new BasicProductModel();
		result.setDiscountClass(discountclass);
		result.setProductsPrice(askingPrice);
		result.setFinalPrice(finalPrice);
		result.setPriceIP(ipPoints);	
		
		return result;
	}
	
	@StepGroup
	@Title("Update products in list")
	public BasicProductModel updateBasicProductToCart(ProductDetailedModel model, String qty, String productProperty,
			String discountclass) {
		String askingPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(), qty);
		String finalPrice = CartDiscountsCalculation.calculateFinalPrice(askingPrice, discountclass,
				CartCalculator.delta);
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
		searchSteps.navigateToProductPage(model.getSku());
		String askingPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(), qty);
		String finalPrice = CartDiscountsCalculation.calculateFinalPrice(askingPrice, discountclass,
				CartCalculator.delta);
		String ipPoints = CartDiscountsCalculation.calculateIpPoints(model.getIp(), qty);
		if (discountclass.equals(ConfigConstants.DISCOUNT_50) || discountclass.equals(ConfigConstants.DISCOUNT_0)) {
			ipPoints = "0";
		}

		return productSteps.setBasicProductAddToWhislist(qty, productProperty, askingPrice, finalPrice, ipPoints,
				discountclass);
	}

}
