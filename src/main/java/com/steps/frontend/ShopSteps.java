package com.steps.frontend;

import com.tools.constants.ContextConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class ShopSteps  extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	
	@Step
	public void checkIfBorrowLinkIsdisplayed(boolean isDisplayed) {
		shopPage().checkIfBorrowLinkIsDisplayed(isDisplayed);
		
	}


	public static String retriveAddressAsString(CustomerFormModel stylistData, AddressModel addressModel) {
		
		String billingAddress=stylistData.getFirstName()+" "+stylistData.getLastName()+", "+toAsciiString(addressModel.getStreetAddress())+", "+addressModel.getStreetNumber()+ ", "
				+addressModel.getPostCode()+" "+ addressModel.getHomeTown()+", "+addressModel.getCountryName();
		
		
		System.out.println("billing address " +billingAddress);
		return toAsciiString(billingAddress);
		
		
		
	}
	
	
	public static String toAsciiString(String str) {
	    if (str == null) {
	        return null;
	    }
	    StringBuilder sb = new StringBuilder();
	    for (int index = 0; index < str.length(); index++) {
	        char c = str.charAt(index);
	        int pos = ContextConstants.UNICODE.indexOf(c);
	        if (pos > -1)
	            sb.append(ContextConstants.PLAIN_ASCII.charAt(pos));
	        else {
	            sb.append(c);
	        }
	    }
	    return sb.toString();
	}

	
}
