package com.steps.frontend;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import com.tools.AbstractSteps;
import com.tools.Constants;

public class ProductSteps extends AbstractSteps{

	private static final long serialVersionUID = 6517884556963111931L;

	/**
	 * Set the product quantity and size. If Size is set to 0 field is ignored.
	 * @param qty
	 * @param size
	 */
	@StepGroup
	public void setProductAddToCart(String qty, String size){
		if(!size.contentEquals("0")){
			setDropDownValue(size);
		}
		
		setQuantity(qty);
		addToCart();
		waitABit(Constants.TIME_CONSTANT);
	}
	
	@Step
	public void setDropDownValue(String size) {
		productDetailsPage().selectValueFromDropDown(size);
	}

	@Step
	public void setQuantity(String qty) {
		productDetailsPage().setPrice(qty);
		
	}
	
	@Step
	public void addToCart(){
		productDetailsPage().addToCart();
	}

	
	
}
