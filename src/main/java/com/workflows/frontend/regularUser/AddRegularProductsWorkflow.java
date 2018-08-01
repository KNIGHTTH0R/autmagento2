package com.workflows.frontend.regularUser;

import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.tools.cartcalculations.smf.CartDiscountsCalculation;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

public class AddRegularProductsWorkflow {
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
	
	
	@Step
	@Title("VDV Add product to cart")
	public RegularBasicProductModel setChildProductToCart(ProductDetailedModel model, String qty, String productProperty) {

	//	searchSteps.navigateToProductPage(model.getParentProductSku());
		
		String finalPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(), qty);
		String ipPoints = CartDiscountsCalculation.calculateIpPoints(model.getIp(), qty);
		

		return productSteps.setRegularBasicProductAddToCart(model,qty, productProperty, finalPrice, ipPoints);
	//	return null;
	
	}
	
	
	
	
	
	@StepGroup
	@Title("Add product to cart")
	public RegularBasicProductModel setBasicProductToCart(ProductDetailedModel model, String qty, String productProperty) {

		String finalPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(), qty);
		String ipPoints = CartDiscountsCalculation.calculateIpPoints(model.getIp(), qty);

		return productSteps.setRegularBasicProductAddToCart(model,qty, productProperty, finalPrice, ipPoints);
	}
	@StepGroup
	@Title("Add product to cart1")
	public RegularBasicProductModel setBasicProductToCart1(ProductDetailedModel model, String qty,
			String productProperty) {
		searchSteps.navigateToProductPage(model.getName());
		String finalPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(), qty);
		System.out.println("finalPrice"+finalPrice);
		String ipPoints = CartDiscountsCalculation.calculateIpPoints(model.getIp(), qty);
		System.out.println("ipPoints"+ ipPoints);
		return productSteps.setRegularBasicProductAddToCart(model,qty, productProperty, finalPrice, ipPoints);
	}
	
	@StepGroup
	@Title("Add product to cart")
	public void addProductToCart(ProductDetailedModel model, String qty, String productProperty) {
		searchSteps.navigateToProductPage(model.getSku());
		productSteps.setQuantityAndAddToCart(qty, productProperty);
	}

	@StepGroup
	@Title("Add product to wishlist")
	public RegularBasicProductModel setBasicProductToWishlist(ProductDetailedModel model, String qty, String productProperty) {
		searchSteps.navigateToProductPage(model.getSku());
		String finalPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(), qty);

		return productSteps.setRegularBasicProductAddToWishlist(model,qty, productProperty, finalPrice);
	}

	@StepGroup
	@Title("Add product to wishlist")
	public RegularBasicProductModel updateBasicProductToCart(ProductDetailedModel model, String qty, String productProperty) {
		String finalPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(), qty);
		String ipPoints = CartDiscountsCalculation.calculateIpPoints(model.getIp(), qty);
		return productSteps.updateRegularBasicProductAddToCart(model, qty, productProperty, finalPrice,ipPoints);
	}

	

	
}
