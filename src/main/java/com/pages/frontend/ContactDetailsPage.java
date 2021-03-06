package com.pages.frontend;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.data.frontend.ContactModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;
import com.tools.utils.PrintUtils;

import net.serenitybdd.core.annotations.findby.FindBy;


public class ContactDetailsPage extends AbstractPage {
	
	@FindBy(id = "sosMassSyncContacts")
	private WebElement sosButton;
	
	@FindBy(css = "#sosFormMassSyncContacts button.blue-button")
	private WebElement submitButton;
	
	@FindBy(css = "	a[class='fancybox-item fancybox-close']")
	private WebElement closeModal;
	
	@FindBy(css= "customer-infos-accordion ui-accordion")
	private WebElement customerBlock;
	
	@FindBy(css= ".customer-infos-accordion.ui-accordion h3")
	private List<WebElement> customerBlockLines;

	@FindBy(css= ".col-left.sidebar .white-button")
	private WebElement backToContactsBtn;
	
	@FindBy(css= ".customer-infos-accordion.ui-accordion .ui-accordion-header:nth-child(3)")
	private WebElement wishlistCounter;
	
	@FindBy(css= ".customer-infos-accordion.ui-accordion .ui-accordion-header:nth-child(3) span")
	private WebElement productListCounter;
	
	
	@FindBy(css= "")
	private WebElement wishlistItems;
	
	@FindBy(css=".customer-infos-accordion.ui-accordion h3")
	private List<WebElement> historyMenu;
	
	
	
	
	public ContactModel grabContactDetails() {

		ContactModel result = new ContactModel();

		result.setName(getDriver().findElement(By.cssSelector("div.col1-set.page-title .page-title-inner h1")).getText());
		result.setCreatedAt(getDriver().findElement(By.cssSelector("#contact-source span")).getText().trim());
		result.setHasPartyHostInterrest(getDriver().findElement(By.id("flag_parties")).isSelected());
		result.setHasStyleCoachInterrest(getDriver().findElement(By.id("flag_member")).isSelected());
//		result.setIsNewsletterSubscribed(getDriver().findElement(By.id("#contact-email-signup p")).isSelected());
		result.setStreet(getDriver().findElement(By.xpath("//*[@id='contact-information']/p[2]")).getText());
		result.setNumber(getDriver().findElement(By.xpath("//*[@id='contact-information']/p[2]")).getText());
		result.setZip(getDriver().findElement(By.xpath("//*[@id='contact-information']/p[3]")).getText().replace(",", ""));
		result.setTown(getDriver().findElement(By.xpath("//*[@id='contact-information']/p[4]")).getText());
		result.setCountry(getDriver().findElement(By.cssSelector("#contact-information p.dp-bl.contact-city")).getText());
//		element(historyMenu).waitUntilVisible();
//		historyMenu.click();
		String historyRegistarion=historyDetails("VERLAUF");
		waitABit(2000);
		result.setLastHistoryRegistration(historyRegistarion);
		System.out.println("result history"+result.getLastHistoryRegistration());
		
		
		
		PrintUtils.printContactModel(result);

		return result;

	}
	
	
	public String historyDetails(String text){
		
		for (WebElement webElement : historyMenu) {
			System.out.println("webElement "+webElement.getText());
			if(webElement.getText().contains(text)){
				System.out.println("here "+webElement.getText());
				webElement.click();
				
				break;
			}
		}
		
		String lastHystoryRegistration=getDriver().findElement(By.cssSelector("#reports-table-default tbody tr:nth-child(1)")).getText();
		return lastHystoryRegistration;
		
	}
	
	public boolean checkIsPresentSosButton(){
		boolean isVisible = false;
		if(element(sosButton).isVisible())
		{
			isVisible=true;
			Assert.assertTrue("The menu was  found", isVisible);
			
		}
		else 
		{
			isVisible=false;
			Assert.assertFalse("The menu was not found", isVisible);
		}
		

		return isVisible;
	}
	
	public void clickOnSosSyncButton() {
		element(sosButton).waitUntilVisible();
		sosButton.click();
		
	}
	
	public void clickOnSubmitSosButton() {
		element(submitButton).waitUntilVisible();
		submitButton.click();
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementWithText(
				By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.SOS_LOADING_MESSAGE));
	}
	
	public void closeModalWindow() {
		element(closeModal).waitUntilVisible();
		closeModal.click();
		waitABit(5000);
		
	}

	public void checkPresenceOfCustomerInfosBlock(boolean isDisplayed) {
		if (isDisplayed)
			Assert.assertTrue("The Customer block should be present and it's not !!!",
					customerBlock.isDisplayed());

		else
			Assert.assertTrue("The Customer block is present and it shouldn't !!!",
					!customerBlock.isDisplayed());
		
	}
	
	public void checkBlockLinesForNotRegisterContact(){
		boolean correctNoLine=true;
		System.out.println("customerBlockLines.size()" +customerBlockLines.size());
		if(customerBlockLines.size() !=1){
			correctNoLine=false;
		}
		Assert.assertTrue("The Customer  block does not displauy the correct No of Lines ",
					correctNoLine);
	}

	public void checkBlockLinesForRegisterContact(int size ){
		
		System.out.println("customerBlockLines.size()" +customerBlockLines.size());
		boolean correctNoLine=true;
		if(customerBlockLines.size() !=size){
			correctNoLine=false;
		}
		Assert.assertTrue("The Customer block does not displauy the correct No of Lines ",
					correctNoLine);
		
		
	}
	
	public void clickBacktoContactsButton() {
		element(backToContactsBtn).waitUntilVisible();
		backToContactsBtn.click();
		
	}

	public List<RegularBasicProductModel> grabCartItems() {
		// TODO Auto-generated method stub
		List<RegularBasicProductModel>  cartItems= new ArrayList<RegularBasicProductModel>();
		
	//	WebElement cartItemSection= customerBlockLines.get(1);
		
		
		return cartItems;
	}
	
	public List<RegularBasicProductModel> grabWishlistItems() {
		// TODO Auto-generated method stub
		List<RegularBasicProductModel>  grabbedWishlistItems= new ArrayList<RegularBasicProductModel>();
		List<WebElement> wishList=getDriver().findElements(By.cssSelector(".customer-wishlist-items table tbody tr "));
		//WebElement unitPrice= getDriver().findElement(By.cssSelector(".customer-wishlist-items table tbody tr .price"));
		
		for (WebElement webElement : wishList) {
			RegularBasicProductModel wishlistItem= new RegularBasicProductModel();
			String productCode=webElement.findElement(By.cssSelector("td:nth-child(2) p:nth-child(2)")).getAttribute("innerText");
			String qty1=webElement.findElement(By.cssSelector("td:nth-child(3)")).getAttribute("innerText").replaceAll("\\s+","");
//			String dateAddedOn=webElement.findElement(By.cssSelector("td:nth-child(4)")).getAttribute("innerText").replaceAll("\\s+","");
//			String availabilityStatus=webElement.findElement(By.cssSelector("td:nth-child(4)")).getAttribute("innerText");
			String price=webElement.findElement(By.cssSelector(".price")).getAttribute("innerText").replaceAll("\\s+","");
			
			//should be added on availabilityStatus
		//	String parseDate =availabilityStatus.trim();
			
			

			wishlistItem.setProdCode(productCode);
			wishlistItem.setQuantity(qty1);
			//wishlistItem.setUnitPrice(FormatterUtils.parseValueToTwoDecimals(unitPrice));
			wishlistItem.setFinalPrice(FormatterUtils.parseValueToTwoDecimals(price));
			System.out.println("from model prod code=" +wishlistItem.getProdCode().trim());
			System.out.println("from model prod qty=" +wishlistItem.getQuantity());
			System.out.println("from model prod price=" +wishlistItem.getFinalPrice());
			
			
			
			
			grabbedWishlistItems.add(wishlistItem);
	
		}
		
		System.out.println("size from method " + grabbedWishlistItems.size());
		
		
		return grabbedWishlistItems;
	}

	public void checkBlockLinesForContacts() {
	/*if lista1 contains:
	3 -> stylist
	1 -> contact 
	1-> regular */
		
		//lines that should be contained by the accordion menu
		int lineNo;
		List<WebElement> lista1=getDriver().findElements(By.cssSelector(".col-2 .info-box span"));
	
		if(lista1.size()==1){
			if(lista1.get(0).getText().contains("Registrierter User /")){
				lineNo=3;
				checkBlockLinesForRegisterContact(lineNo);
			}else{
				checkBlockLinesForNotRegisterContact();
			}
			
		}else{
			lineNo=1;
			checkBlockLinesForRegisterContact(lineNo);
		}
		
		
	}

	public void validateWishlistCounter(int size) {
		//replace all digits from a string 
		int counter=Integer.parseInt(wishlistCounter.getText().replaceAll("\\D+",""));
		
		Assert.assertTrue("The Wishlist does not contains correct no of products!",size==counter);
		
	}

	


}
//*[@id="contact-information"]/p[4]
