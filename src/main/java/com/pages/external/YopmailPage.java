package com.pages.external;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.constants.UrlConstants;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

public class YopmailPage extends AbstractPage {

	@FindBy(css = ".bodyinbox")
	private WebElement inboxContainer;

	@FindBy(className = "mailview")
	private WebElement mailContainer;

	@FindBy(css = "iframe#ifmail")
	private WebElement iFrameElement;

	@FindBy(id = "ifinbox")
	private WebElement iFrameInbox;

	@FindBy(id = "login")
	private WebElement emailInput;

	@FindBy(css = "input[type='submit']")
	private WebElement submitEmailBtn;

	public void openEmail(String email, String title) {

		navigate(UrlConstants.YOPMAIL_WEB_MAIL);
		element(emailInput).clear();
		element(emailInput).sendKeys(email);
		submitEmailBtn.click();
		getDriver().switchTo().frame(iFrameInbox);
		element(inboxContainer).waitUntilVisible();
		boolean foundEmail = false;
		List<WebElement> emailList = inboxContainer.findElements(By.cssSelector("div.m a"));
		for (WebElement itemNow : emailList) {
			System.out.println(itemNow.getText());
			if (itemNow.getText().contains(title) && !itemNow.getText().contains("Aktualisierung")) {
				itemNow.click();
				getDriver().switchTo().defaultContent();
				waitABit(2000);
				foundEmail = true;
				break;
			}
		}
		Assert.assertTrue("The email with the title " + title + " was not found", foundEmail);
	}

	public String getConfirmationLink() {

		getDriver().switchTo().frame(iFrameElement);
		String confirmLink = getDriver().findElement(By.cssSelector("a[href*='confirm']")).getAttribute("href");
		return confirmLink;
	}

	public String getRegisterLink() {

		getDriver().switchTo().frame(iFrameElement);
		String confirmLink = getDriver().findElement(By.cssSelector("a[href*='confirm']")).getAttribute("href");
		return confirmLink;
	}

	public void clickConfirmEmail() {

		getDriver().switchTo().frame(iFrameElement);
		getDriver().findElement(By.cssSelector("a[href*='confirm']")).click();
	}

	public String grabCouponCode() {

		getDriver().switchTo().frame(iFrameElement);
		return getDriver().findElement(By.cssSelector("table[bgcolor='#FFFFFF'] tbody > tr:nth-child(3)")).getText();
	}

	public void clickPartyConfirmationLink() {
		getDriver().switchTo().frame(iFrameElement);
		//get first a (link with Yes) from page
		getDriver().findElements(By.cssSelector("a[href*='invitation']")).get(0).click();
	}

	public List<OrderItemModel> grabbedProductsList() {
		getDriver().switchTo().frame(iFrameElement);
		scrollPageDown();
		getDriver().manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		List<WebElement> totals=getDriver().findElements(By.cssSelector("#mailmillieu  table:nth-child(6) tbody"));
		List<WebElement> products=totals.subList(0,totals.size() - 1);
	
		List<OrderItemModel> productsList=new ArrayList<OrderItemModel>();
		
		for (WebElement product : products) {
			getDriver().manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
			OrderItemModel model=new OrderItemModel();
	
			model.setProductCode(product.findElement(By.cssSelector("tr td:nth-child(2)")).getText());
			model.setNumber(product.findElement(By.cssSelector("tr td:nth-child(3)")).getText());
			model.setOriginalPrice(FormatterUtils
					.parseValueToTwoDecimals(product.findElement(By.cssSelector("tr td:nth-child(4)")).getText()));
			model.setDiscountAmount(FormatterUtils
					.parseValueToTwoDecimals(product.findElement(By.cssSelector("tr td:nth-child(5)")).getText()));
			model.setPrice(FormatterUtils
					.parseValueToTwoDecimals(product.findElement(By.cssSelector("tr td:nth-child(6)")).getText()));
			productsList.add(model);
		}
		return productsList;
	}
	

	public OrderTotalsModel grabbedOrderTotals() {

		List<WebElement> totals=getDriver().findElements(By.cssSelector("#mailmillieu  table:nth-child(6) tbody"));
		List<WebElement> total=totals.subList(totals.size() - 1,totals.size() );
		List<WebElement> totalSection=total.get(0).findElements(By.cssSelector("tr"));
		
		OrderTotalsModel model=new OrderTotalsModel();
		
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
