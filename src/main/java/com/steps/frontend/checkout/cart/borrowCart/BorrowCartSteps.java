package com.steps.frontend.checkout.cart.borrowCart;

import java.util.List;

import org.junit.Assert;

import net.thucydides.core.annotations.Step;

import com.tools.data.frontend.BorrowCartTotalsModel;
import com.tools.data.frontend.BorrowedCartModel;
import com.tools.requirements.AbstractSteps;

public class BorrowCartSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public List<BorrowedCartModel> grabProductsData() {
		return borrowCartPage().grabProductsData();
	}
	
	
	@Step
	public List<BorrowedCartModel> grabProductsDataNewFunctionality() {
		return borrowCartPage().grabProductsDataNewFunctionality();
	}

	@Step
	public BorrowCartTotalsModel grabTotals() {
		return borrowCartPage().grabTotals();
	}

	@Step
	public void clickGoToShipping() {
		borrowCartPage().clickToShipping();
	}

	@Step
	public void clickWipeCart() {
		borrowCartPage().clickWipeCart();
	}
	
	@Step
	public void verifyStockMessageForProduct(String productName, String stockInfo) {
		borrowCartPage().verifyStockMessageForProduct(productName, stockInfo);
	}
	
	@Step
	public void verifyErrorMessageInCart(String message) {
		System.out.println("error" + cartPage().getCartErrorMessage());
		System.out.println("message"+ message);
		Assert.assertTrue("The error message is not found !!!", cartPage().getCartErrorMessage().contains(message));
	}
	
	@Step
	public void verifyPresenceOfGoToCheckoutButton(boolean shouldBePresent){
		cartPage().verifyPresenceOfGoToCheckoutButton(shouldBePresent);
	}


	@Step
	public void checkBorrowCartForNewFunctionality(boolean topCheckout,boolean buttomCheckout) {
		//from top of the page
		borrowCartPage().checkPresenceOfTopCheckoutButton(topCheckout);
		System.out.println("from top of the page");
		//from down of the page
		borrowCartPage().checkPresenceOfButtomCheckoutButton(buttomCheckout);
		System.out.println("from down of the page");
		borrowCartPage().checkPresenceOfAddItemsButton(false);
		borrowCartPage().checkPresenceOfClearCartButton(false);
		borrowCartPage().checkPresenceOfUpdateCartButton(false);
	}


	public void checkNoOfProductsDisplayedInMiniCart(int size) {
		borrowCartPage().checkNoOfProductsDisplayedInMiniCart(size);
		
	}


	


}
