package com.workflows.frontend.partyHost;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.tools.calculation.CartDiscountsCalculation;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;

public class AddProductsForCustomerWorkflow {
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
	@Title("Add product to cart")
	public HostBasicProductModel setHostProductToCart(ProductDetailedModel model, String qty, String productProperty) {
		searchSteps.searchAndSelectProduct(model.getSku(), model.getName());
		String finalPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(), qty);
		String ipPoints = CartDiscountsCalculation.calculateIpPoints(model.getIp(),qty);

		return productSteps.setHostBasicProductAddToCart(qty, productProperty, finalPrice, ipPoints);
	}

}
