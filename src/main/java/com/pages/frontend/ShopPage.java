package com.pages.frontend;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
import com.tools.data.frontend.LoungeIpPerformanceModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.PrintUtils;

import net.serenitybdd.core.annotations.findby.FindBy;

public class ShopPage extends AbstractPage {

	
	@FindBy(css = ".dropdown-container.dropdown-left> ul > li:nth-child(6) > ul li:nth-child(1) span")
	private WebElement borrowCartLink;
	
	@FindBy(css = ".dropdown-container.dropdown-left a")
	private WebElement myBussinessLink;
	
	@FindBy(css = ".dropdown-container.dropdown-left> ul > li:nth-child(6)")
	private WebElement loanedLink;

	

     public void checkIfBorrowLinkIsDisplayed(boolean isDisplayed){
		
    	 Actions builder = new Actions(getDriver());

 		builder.moveToElement(myBussinessLink).build().perform();
 		builder.moveToElement(loanedLink).build().perform();
	
	    if (isDisplayed)
			Assert.assertTrue("The Borrow Link should be present and it's not !!!",
					toAsciiString(borrowCartLink.getText()).contains("SCHMUCKSTUCKE AUSLEIHEN"));

		else
			Assert.assertTrue("The Borrow Link is present and it shouldn't !!!",
					!toAsciiString(borrowCartLink.getText()).contains("SCHMUCKSTUCKE AUSLEIHEN"));
	    
	    
	    waitABit(3000);
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
