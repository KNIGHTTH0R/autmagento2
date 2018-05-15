package com.steps.frontend;

import java.util.List;

import org.junit.Assert;

import com.tools.data.frontend.ContactModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class ContactDetailsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public ContactModel grabContactDetails() {
		return contactDetailsPage().grabContactDetails();
	}
	
	@Step
	public boolean checkIsPresentSosButton() {
		return contactDetailsPage().checkIsPresentSosButton();
	}

	
	@Step
	public void clickSosButton() {
		contactDetailsPage().clickOnSosSyncButton();
	}
	
	@Step
	public void clickOnSubmitSosButton() {
		contactDetailsPage().clickOnSubmitSosButton();
		contactDetailsPage().closeModalWindow();
	}
	
	
	@Step
	public void checkPresenceOfCustomerInfosBlock(boolean isDisplayed) {
		contactDetailsPage().checkPresenceOfCustomerInfosBlock(isDisplayed);
		
	}
	
	@Step
	public void checkBlockLinesForNotRegisterContact(){
		contactDetailsPage().checkBlockLinesForNotRegisterContact();
	}
//	@Step
//	public void checkBlockLinesForRegisterContact(){
//		contactDetailsPage().checkBlockLinesForRegisterContact();
//	}
	@Step
	public void clickBackToContactsbutton() {
		contactDetailsPage().clickBacktoContactsButton();
		
	}
	@Step
	public List<RegularBasicProductModel> grabCartItems() {
		 return	contactDetailsPage().grabCartItems();	
		}
	
	@Step
	public List<RegularBasicProductModel> grabWishlistItems() {
		 return	contactDetailsPage().grabWishlistItems();	
		}
	
	
	@Steps
	ContactDetailsValidationSteps itemValidation;

	@Step
	public void validateProductWishListBlock(List<RegularBasicProductModel> productsWishList,
			List<RegularBasicProductModel> grabbedproductsWishList) {
	
		Assert.assertTrue("Failure: The list size are not equal", productsWishList.size() == grabbedproductsWishList.size());
	
		for (RegularBasicProductModel customerItem : productsWishList) {
			RegularBasicProductModel contactItem = itemValidation.findWishlistItems(customerItem.getProdCode(), grabbedproductsWishList);
			if (contactItem.getProdCode() != null) {

			} else {
				Assert.assertTrue("Failure: Could not validate all products  in the wishlist section", contactItem != null);
			}

		
			itemValidation.validateFinalPrice(customerItem.getFinalPrice(),contactItem.getFinalPrice());
			itemValidation.validateProductQty(customerItem.getQuantity(),contactItem.getQuantity());
			//validate added date
			//validate availability status 
		
		
	}
	
	}

	@Step
	public void checkBlockLinesForContacts() {
		contactDetailsPage().checkBlockLinesForContacts();
		
	}
	
	@Step
	public void validateWishlistCounter(int size) {
		contactDetailsPage().validateWishlistCounter(size);
		
	}
	
	
}
