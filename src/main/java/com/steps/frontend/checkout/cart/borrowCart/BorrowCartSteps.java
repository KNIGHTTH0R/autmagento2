package com.steps.frontend.checkout.cart.borrowCart;

import java.util.List;

import net.thucydides.core.annotations.Step;

import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.BorrowCartTotalsModel;
import com.tools.requirements.AbstractSteps;

public class BorrowCartSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public List<BorrowProductModel> grabProductsData() {
		return borrowCartPage().grabProductsData();
	}

	@Step
	public BorrowCartTotalsModel grabTotals() {
		return borrowCartPage().grabTotals();
	}

	@Step
	public void clickGoToShipping() {
		regularUserCartPage().clickToShipping();
	}

}
