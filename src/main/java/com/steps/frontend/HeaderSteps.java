package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.tools.AbstractSteps;
import com.tools.Constants;

public class HeaderSteps extends AbstractSteps{

	private static final long serialVersionUID = 1221784607709066875L;
	
	/**
	 * Return the total sum on the cart preview.
	 * @return - Preview Price
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

	public void redirectToProfileHistory() {
		getDriver().get(Constants.PROFILE_HISTORY_URL);
	}

}
