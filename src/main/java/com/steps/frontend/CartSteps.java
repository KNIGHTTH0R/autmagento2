package com.steps.frontend;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.AbstractSteps;
import com.tools.PrintUtils;
import com.tools.data.CartProductModel;
import com.tools.data.CartTotalsModel;

public class CartSteps extends  AbstractSteps{
	
	private static final long serialVersionUID = 4077671481867589798L;

	@Step
	public List<CartProductModel> grabProductsData(){
		PrintUtils.printList(cartPage().grabProductsData());
		
		return cartPage().grabProductsData();
	}

	@Step
	public CartTotalsModel grabTotals() {
		PrintUtils.printCartTotals(cartPage().grabTotals());
		return cartPage().grabTotals();
	}

}
