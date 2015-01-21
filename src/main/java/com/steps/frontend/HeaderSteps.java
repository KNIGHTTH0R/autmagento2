package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.AbstractSteps;

public class HeaderSteps extends AbstractSteps{

	private static final long serialVersionUID = 1221784607709066875L;
	
	/**
	 * Return the total sum on the cart preview.
	 * @return
	 */
	@Step
	public String openCartPreview(){
		headerPage().clickShoppingBag();
		return headerPage().getShoppingBagTotalSum();
	}
	
	@Step
	public void goToCart(){
		headerPage().clickGoToCart();
	}
	
	@Step
	public void goToProfile(){
		headerPage().clickOnProfileButton();
	}

}
