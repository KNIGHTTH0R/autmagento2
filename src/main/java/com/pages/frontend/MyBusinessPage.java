package com.pages.frontend;

import java.util.List;

import org.junit.Assert;

import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
import com.tools.requirements.AbstractPage;
import com.tools.utils.DateUtils;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MyBusinessPage extends AbstractPage {

//	@FindBy(css = "#kobo-cancel div.col-3.col ul.link-list li:nth-child(1) a")
//	private WebElementFacade accesKoboCart;
	
//	@FindBy(css = ".flex-col-set .col-box:nth-child(4) ul.link-list li:nth-child(1) a")
//	private WebElementFacade accesKoboCart;
	
	@FindBy(css = ".link-list li a[href*='contactBoosterOrder']")
	private WebElementFacade accesKoboCart;

	
//	@FindBy(css = "#kobo-cancel div.col-3.col ul.link-list li:nth-child(2) a")
//	private WebElementFacade cancelSubstription;
	
	@FindBy(css = ".flex-col-set .col-box:nth-child(6) ul.link-list li:nth-child(2) a")
	private WebElementFacade cancelSubstription;
	
	

	@FindBy(css = "span.cb-code")
	private WebElementFacade coboCodeContainer;

	@FindBy(css = "div.cb-sprite.contact-booster")
	private WebElementFacade voucherContainer;

	@FindBy(css = "div.cb-sprite-large.contact-booster")
	private WebElementFacade voucherlargeContainer;

//	@FindBy(css = "#kobo-cancel div.col-3.col")
//	private WebElementFacade coboSection;
	
	@FindBy(css = ".flex-col-set .col-box:nth-child(6)")
	private WebElementFacade coboSection;

	@FindBy(css = "#confirmCancelCbSubscriptionModal form button[type='submit']")
	private WebElementFacade confirmCancelSubstription;
	
//	@FindBy(css = "#kobo-cancel div.col-2.col li:nth-child(1) a")
//	private WebElementFacade borrowCartLink;
	
	
	@FindBy(css = ".flex-col-set .col-box:nth-child(4) li:nth-child(1) a")
	private WebElementFacade borrowCartLink;
	
	
	@FindBy(css = ".col-box:nth-child(6) ul.link-list li")
	private List<WebElementFacade> contactBoosterCartLinks;
//	public void verifyThatNumberOfLinksAreEqualTo(String expectedNoOflinks) {
//		Assert.assertTrue("", getDriver().findElements(By.cssSelector("#kobo-cancel div.col-3.col ul.link-list li")).size() == Integer.parseInt(expectedNoOflinks));
//	}
	
	public void verifyThatNumberOfLinksAreEqualTo(String expectedNoOflinks) {
		
		System.out.println("expected "+expectedNoOflinks);
		System.out.println("print the size " +contactBoosterCartLinks.size());
		for (WebElementFacade webElementFacade : contactBoosterCartLinks) {
			System.out.println("elementul " +webElementFacade.getText());
		}
		Assert.assertTrue("No of links are not correct", contactBoosterCartLinks.size() == Integer.parseInt(expectedNoOflinks));
	}

	
	
	public void accessKoboCart() {
		element(accesKoboCart).waitUntilVisible();
		accesKoboCart.click();
	}

	public void confirmCancelSubstription() {
		element(confirmCancelSubstription).waitUntilVisible();
		confirmCancelSubstription.click();
		waitABit(TimeConstants.TIME_CONSTANT);
	}

	public void cancelSubstription() {
		element(cancelSubstription).waitUntilVisible();
		cancelSubstription.click();
	}

	public String getKoboCode() {
		element(coboCodeContainer).waitUntilVisible();
		return coboCodeContainer.getText();
	}

	public void verifyKoboSectionContainsText(String text) {
		Assert.assertTrue("The text does not appear in kobo section from my business page", coboSection.getText().contains(text));
	}

	public void verifyCancelledKoboMessageAndActiveUntilDate() {
		Assert.assertTrue("The message or the -valid until- date is not correct !!",
				voucherlargeContainer.getText().contains(ContextConstants.SUBSCRIPTION_CANCELLED_LOUNGE + " " + DateUtils.getLastDayOfTheCurrentMonth("dd.MM.yyy")));
	}

	public void verifyKoboStatusBeforePlaceTheOrder() {
		Assert.assertTrue("The Kobo status before subscription is not correct", voucherContainer.getText().contains(ContextConstants.SUBSCRIPTION_BEFORE_PLACE_THE_ORDER));
	}

	public void verifyKoboOrderProcessingStatus() {
		System.out.println(voucherContainer.getText());
		Assert.assertTrue("The processing order status is missing", voucherContainer.getText().contains(ContextConstants.SUBSCRIPTION_PROCESSING_ORDER));
	}

	public void verifyKoboVoucherIsActive() {
		System.out.println(voucherlargeContainer.getText());
		Assert.assertTrue("The Kobo voucher active message is not found", voucherlargeContainer.getText().contains(ContextConstants.SUBSCRIPTION_KOBO_ACTIVE));
	}
	
public void checkIfBorrowCartLinkIsDisplayed(boolean isDisplayed){
		
	    element(borrowCartLink).waitUntilVisible();
	    String text=toAsciiString(borrowCartLink.getText());
	    System.out.println("text: " +text);
	    System.out.println("text2: Schmuckstucke Ausleihen");
	    if (isDisplayed)
			Assert.assertTrue("The Borrow Link should be present and it's not !!!",
					
					toAsciiString(borrowCartLink.getText()).contains("Schmuck ausleihen"));

		else
			Assert.assertTrue("The Borrow Link is present and it shouldn't !!!",
					!toAsciiString(borrowCartLink.getText()).contains("Schmuck ausleihen"));

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
