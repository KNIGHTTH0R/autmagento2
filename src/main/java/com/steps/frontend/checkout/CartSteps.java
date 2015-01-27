package com.steps.frontend.checkout;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.AbstractSteps;
import com.tools.PrintUtils;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;

public class CartSteps extends  AbstractSteps{
	
	private static final long serialVersionUID = 4077671481867589798L;

	
	/**
	 * Grab all cart product sections from the Cart Page
	 * in a single list.
	 * @return
	 */
	public List<CartProductModel> grabProductsData(){
		return cartPage().grabProductsData();
	}
	
	/**
	 * Grab 25% Discount section from the Cart Page
	 * @return
	 */
	public List<CartProductModel> grabProductsDataWith25PercentDiscount(){
		PrintUtils.printList(cartPage().grabProductsDataWith25Discount());
		
		return cartPage().grabProductsDataWith25Discount();
	}

	/**
	 * Grab 50% Discount section from the Cart Page
	 * @return
	 */
	public List<CartProductModel> grabProductsDataWith50PercentDiscount(){
		PrintUtils.printList(cartPage().grabProductsDataWith50Discount());
		
		return cartPage().grabProductsDataWith50Discount();
	}

	/**
	 * Grab Marketing Material section from the Cart Page
	 * @return
	 */
	public List<CartProductModel> grabMarketingMaterialProductsData(){
		PrintUtils.printList(cartPage().grabMarketingMaterialProductsData());
		
		return cartPage().grabMarketingMaterialProductsData();
	}
	
	/**
	 * Grab Cart Totals section from the Cart Page
	 * @return
	 */
	public CartTotalsModel grabTotals() {		
		return cartPage().grabTotals();
	}
	
	@Step
	public void clickGoToShipping(){
		cartPage().clickToShipping();
	}
	@Step
	public void updateCart(){
		cartPage().clickUpdateCart();
	}
	@Step
	public void updateJewerlyBonus(){
		cartPage().clickUpdateJewerlyBonus();
	}
	@Step
	public void updateMarketingBonus(){
		cartPage().clickUpdateMarketingBonus();
	}
	@Step
	public void updateProductQuantity(String quantity,String... terms){
		cartPage().updateProductQuantity(quantity, terms);
	}
	@Step
	public void typeJewerlyBonus(String jevewrlyBonus){
		cartPage().typeJewerlyBonus(jevewrlyBonus);
	}
	@Step
	public void typeMarketingBonus(String marketingBonus){
		cartPage().typeMarketingBonus(marketingBonus);
	}

}
