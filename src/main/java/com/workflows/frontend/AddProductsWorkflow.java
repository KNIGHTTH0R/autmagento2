package com.workflows.frontend;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import com.steps.frontend.ProductSteps;
import com.steps.frontend.SearchSteps;
import com.tools.data.frontend.ProductBasicModel;

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

	
	

}
