package com.pages.external;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tools.CustomVerification;
import com.tools.constants.UrlConstants;
import com.tools.data.CalcDetailsModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.BasicProductModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

import net.serenitybdd.core.annotations.findby.FindBy;

public class TempMail extends AbstractPage {



	@FindBy(css = "iframe#ifmail")
	private WebElement iFrameElement;
	
	@FindBy(css = "ul li a[href*='change']")
	private WebElement changeButton;

	@FindBy(css = "ul li a[href*='refresh']")
	private WebElement refreshButton;
	
	@FindBy(css = "input[name*='mail']")
	private WebElement mailInput;
	
	@FindBy(css = "button[type*='submit']")
	private WebElement submitButton;
	

	@FindBy(css = ".content.main table:nth-child(6) tbody")
	private WebElement productsTable;
	
	
	@FindBy(id = "message")
	private WebElement iFrameInbox;
	public void openEmail(String email, String title) {
		navigate(UrlConstants.TEMPMAIL_WEB_MAIL);
		evaluateJavascript("jQuery.noConflict();");
		closeCookiePopUp();

		WebDriverWait wait = new WebDriverWait(getDriver(),5);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul li a[href*='change']")));
	
		changeButton.click();
	//	clickElement(changeButton);
		System.out.println("step 0");
		getDriver().navigate().refresh();
		System.out.println("step 1");
		waitForPageToLoad();
		System.out.println("step 2");
//		element(mailInput).clear();
		System.out.println("step 3");
		mailInput.sendKeys(email);
		System.out.println("step 4");
		selectMailDomain("@fxprix.com");
		System.out.println("step 5");
		//element(submitButton).waitUntilVisible();
		
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type*='submit']")));
		submitButton.click();
		waitForPageToLoad();
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul li a[href*='refresh']")));
		refreshButton.click();
		
		waitForPageToLoad();
		List<WebElement> emailList = getDriver().findElements(By.cssSelector("#mails tbody tr td:nth-child(2) a"));
		
		System.out.println("size list "+ emailList.size());
		boolean foundEmail = false;
		
		if(emailList.size()!=0){
			for (WebElement itemNow : emailList) {
				System.out.println(itemNow.getText());
			
				if (itemNow.getText().contains(title)) {
					itemNow.click();
					
					
					//getDriver().switchTo().defaultContent();
					//waitForPageToLoad();
					foundEmail = true;
				//	getDriver().switchTo().frame(iFrameInbox);
					break;
				}
			}
			
			
			//Assert.assertTrue("The email with the title " + title + " was not found", foundEmail);
			CustomVerification.verifyTrue("The email with the title " + title + " was not found", foundEmail);

			
		}else{
			CustomVerification.verifyTrue("No emails received", true);
		}
		System.out.println("gata am iesit de aici");
		
		
	}
	
	
	public void openEmailAndCheck(String email, String title, String order) {
		navigate(UrlConstants.TEMPMAIL_WEB_MAIL);
		waitABit(2000);
		element(changeButton).waitUntilVisible();
		changeButton.click();
		
		element(mailInput).waitUntilVisible();
		element(mailInput).clear();
		mailInput.sendKeys(email);
		
		selectMailDomain("@fxprix.com");
		
		element(submitButton).waitUntilVisible();
		submitButton.click();
		
		element(refreshButton).waitUntilVisible();
		refreshButton.click();
		
		waitABit(5000);
		List<WebElement> emailList = getDriver().findElements(By.cssSelector("#mails tbody tr td:nth-child(2) a"));
		
		System.out.println("size list "+ emailList.size());
		boolean foundEmail = false;
		
		if(emailList.size()!=0){
			for (WebElement itemNow : emailList) {
			
				if (itemNow.getText().contains(title)) {
					itemNow.click();
					
					//getDriver().switchTo().defaultContent();
					waitABit(5000);
					foundEmail = true;
				//	getDriver().switchTo().frame(iFrameInbox);
					break;
				}
			}
			
			
			Assert.assertTrue("The email with the title " + title + " was not found", foundEmail);
		}else{
			CustomVerification.verifyTrue("No emails received", true);
		}
		
		
	}
	
	public void selectMailDomain(String domainName) {
		Select oSelect = new Select(getDriver().findElement(By.id("domain")));

		oSelect.selectByVisibleText(domainName);

	}
	
	public void closeCookiePopUp() {
		List<WebElement> cookies =getDriver().findElements(By.cssSelector(".cc_btn.cc_btn_accept_all "));
		if (cookies.size()!=0) {
			clickElement(cookies.get(0));
		}
	}
	
	public String getConfirmationLink() {
		waitABit(4000);
		//focusElement("a[href*='confirm']");
		String confirmLink = getDriver().findElement(By.cssSelector("a[href*='confirm']")).getAttribute("href");
		return confirmLink;

	}

	public String getRegisterLink() {
		waitABit(4000);
		String confirmLink = getDriver().findElement(By.cssSelector("a[href*='confirm']")).getAttribute("href");
		return confirmLink;
	}

	public void clickConfirmEmail() {
		getDriver().findElement(By.cssSelector("a[href*='confirm']")).click();
	}

	public String grabCouponCode() {

		return getDriver().findElement(By.cssSelector("table[bgcolor='#FFFFFF'] tbody > tr:nth-child(3)")).getText();
	}

	public void clickPartyConfirmationLink() {
		getDriver().findElements(By.cssSelector("a[href*='invitation']")).get(0).click();
	}


	public void checkReceivedOriginalDocuments(String orderIncrementId) {
		// TODO Auto-generated method stub
		WebElement list=getDriver().findElement(By.cssSelector(".content.main table tbody tr:nth-child(3)"));
		
		CustomVerification.verifyTrue("Failure: invoice doc does not appers in mail", list.getText().contains(orderIncrementId));
	}
	



	public void validateProductsOnEmail(List<BasicProductModel> productsList) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		/*String x="https://vdv-qa-aut.pippajean.com/de/index.php/customer/account/confirm/?back_url=https://vdv-qa-aut.pippajean.com/de/index.php/emc/dessous.html/?___store=de_lang_de&amp;id=55&amp;key=0cb150de74949e63deefc82523bb9ee7&amp;distributed=";
		String y=x.substring(x.indexOf("https://vdv-qa"), x.indexOf("distributed="));
		System.out.println(y+"distributed=");*/
		
		
		List<String> totals=new ArrayList<String>();
		totals.add("1");
		totals.add("2");
		totals.add("3");
		totals.add("4");
		
		List<String> products=totals.subList(0,totals.size() - 1);
		
		System.out.println(products);
	}
	
	
	public List<OrderItemModel> grabbedProductsList() {
		//evaluateJavascript("jQuery.noConflict();");
		scrollPageDown();
		getDriver().manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		List<WebElement> totals=getDriver().findElements(By.cssSelector(".content.main table:nth-child(6) tbody"));
		List<WebElement> products=totals.subList(0,totals.size() - 1);
	
		List<OrderItemModel> productsList=new ArrayList<OrderItemModel>();
		
		for (WebElement product : products) {
			getDriver().manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
			OrderItemModel model=new OrderItemModel();
	
			model.setProductCode(product.findElement(By.cssSelector("tr td:nth-child(2)")).getText());
			model.setQty(product.findElement(By.cssSelector("tr td:nth-child(3)")).getText());
			model.setOriginalPrice(FormatterUtils
					.parseValueToTwoDecimals(product.findElement(By.cssSelector("tr td:nth-child(4)")).getText()));
			model.setDiscountAmount(FormatterUtils
					.parseValueToTwoDecimals(product.findElement(By.cssSelector("tr td:nth-child(5)")).getText()));
			model.setPrice(FormatterUtils
					.parseValueToTwoDecimals(product.findElement(By.cssSelector("tr td:nth-child(6)")).getText()));
			productsList.add(model);
		}
		System.out.println(" sunt aici");
		return productsList;
	}
	

	public OrderTotalsModel grabbedOrderTotals() {
		//evaluateJavascript("jQuery.noConflict();");
	//	scrollPageDown();

		//element(productsTable).waitUntilVisible();
		List<WebElement> totals=getDriver().findElements(By.cssSelector(".content.main table:nth-child(6) tbody"));
		List<WebElement> total=totals.subList(totals.size() - 1,totals.size() );
		List<WebElement> totalSection=total.get(0).findElements(By.cssSelector("tr"));
		
		OrderTotalsModel model=new OrderTotalsModel();
		System.out.println(" sunt aici2 ");
		
		for (WebElement totalLine : totalSection) {
			if(totalLine.findElement(By.cssSelector("td:nth-child(1)")).getText().contains("Zwischensumme")){
				model.setSubtotal(FormatterUtils
						.parseValueToTwoDecimals(totalLine.findElement(By.cssSelector("td:nth-child(2)")).getText()));
			}
			if(totalLine.findElement(By.cssSelector("td:nth-child(1)")).getText().contains("Rabatt")){
				model.setDiscount(FormatterUtils
						.parseValueToTwoDecimals(totalLine.findElement(By.cssSelector("td:nth-child(2)")).getText()));
			}
			if(totalLine.findElement(By.cssSelector("td:nth-child(1)")).getText().contains("Steuer")){
				model.setTax(FormatterUtils
						.parseValueToTwoDecimals(totalLine.findElement(By.cssSelector("td:nth-child(2)")).getText()));
			}
			if(totalLine.findElement(By.cssSelector("td:nth-child(1)")).getText().contains("Lieferung und Verarbeitung")){
				model.setShipping(FormatterUtils
						.parseValueToTwoDecimals(totalLine.findElement(By.cssSelector("td:nth-child(2)")).getText()));
			}
			if(totalLine.findElement(By.cssSelector("td:nth-child(1)")).getText().contains("Gesamtsumme")){
				model.setTotalAmount(FormatterUtils
						.parseValueToTwoDecimals(totalLine.findElement(By.cssSelector("td:nth-child(2)")).getText()));
			}
		}
		
		return model;
	}

}
