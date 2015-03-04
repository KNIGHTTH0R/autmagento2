package com.workflows.frontend;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.tools.Constants;
import com.tools.calculation.CartDiscountsCalculation;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.data.soap.ProductDetailedModel;

public class AddProductsWorkflow {
	@Steps
	public SearchSteps searchSteps;
	@Steps
	public ProductSteps productSteps;
	
	/**
	 * The first three fields are mandatory (1 and 2 may be the same if the its the sku of the product or a means of identify the product uniquely)
	 * The productProperty field refers to the size or color property on the product Page. this should be set to 0 if no property is expected.
	 * @param productCode
	 * @param productName
	 * @param qty
	 * @param productProperty
	 * @return 
	 */
	@StepGroup
	public ProductBasicModel setProductToCart(String productCode, String productName, String qty, String productProperty){
		searchSteps.searchAndSelectProduct(productCode, productName);
		
		return productSteps.setProductAddToCart(qty, productProperty);
	}
	
	@StepGroup
	public BasicProductModel setBasicProductToCart(ProductDetailedModel model,String qty, String productProperty,String discountclass){
		searchSteps.searchAndSelectProduct(model.getSku(), model.getName());
		String askingPrice = CartDiscountsCalculation.calculateAskingPrice(model.getPrice(),qty);
		String finalPrice = CartDiscountsCalculation.calculateFinalPrice(askingPrice, discountclass);
		String ipPoints = CartDiscountsCalculation.calculateIpPoints(model.getIp(),qty);
		if(discountclass.equals(Constants.DISCOUNT_50) || discountclass.equals(Constants.DISCOUNT_0)){
			ipPoints = "0";
		}

		return productSteps.setBasicProductAddToCart(qty, productProperty,askingPrice,finalPrice,ipPoints,discountclass);
	}

}
