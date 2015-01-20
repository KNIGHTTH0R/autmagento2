package com.steps.frontend.checkout;

import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.AbstractSteps;
import com.tools.PrintUtils;
import com.tools.data.CartProductModel;
import com.tools.data.CartTotalsModel;

public class CartSteps extends  AbstractSteps{
	
	private static final long serialVersionUID = 4077671481867589798L;

	@StepGroup
	public List<CartProductModel> grabProductsData(){
//		PrintUtils.printList(cartPage().grabProductsData());
		return cartPage().grabProductsData();
	}
	@Step
	public List<CartProductModel> grabProductsDataWith25PercentDiscount(){
		PrintUtils.printList(cartPage().grabProductsDataWith25Discount());
		
		return cartPage().grabProductsDataWith25Discount();
	}
	@Step
	public List<CartProductModel> grabProductsDataWith50PercentDiscount(){
		PrintUtils.printList(cartPage().grabProductsDataWith50Discount());
		
		return cartPage().grabProductsDataWith50Discount();
	}
	@Step
	public List<CartProductModel> grabMarketingMaterialProductsData(){
		PrintUtils.printList(cartPage().grabMarketingMaterialProductsData());
		
		return cartPage().grabMarketingMaterialProductsData();
	}

	@Step
	public CartTotalsModel grabTotals() {
		return cartPage().grabTotals();
	}
	
	@Step
	public void clickGoToShipping(){
		cartPage().clickToShipping();
	}

}
