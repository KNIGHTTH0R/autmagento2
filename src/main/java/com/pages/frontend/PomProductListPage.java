package com.pages.frontend;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.TimeConstants;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;


public class PomProductListPage extends AbstractPage {

	@FindBy(className = "pom-category-products")
	private WebElement productCatalogContainer;
	
	@FindBy(css = ".product-shop")
	private WebElement productDetailsContainer;

	public void findProductAndClick(String productName) {

		element(productCatalogContainer).waitUntilVisible();
		List<WebElement> productsList = productCatalogContainer.findElements(By.cssSelector("li.item"));
		boolean found = false;
		theFor: for (WebElement webElement : productsList) {
			String productText = webElement.findElement(By.cssSelector("div h2")).getText();
			if (productText.contains(productName)) {

				String url = webElement.findElement(By.cssSelector("div div a")).getAttribute("href");
				elementjQueryClick("a[href='" + url + "']");
				withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions
						.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockMsg.blockElement")));
				waitABit(TimeConstants.WAIT_TIME_SMALL);
				found = true;
				break theFor;
			}
		}
		Assert.assertTrue("The searched product was not found !!!", found);
	}
	
	
	public void findProductAndClick2(String productName) {
		@SuppressWarnings("deprecation")
		WebElement product=getDriver().findElement(net.thucydides.core.annotations.findby.By.jquery("#pom-products-content ul li div.prod-box-name h2:contains('"+productName+"')"));
		product.click();
		
				
		
//		JavascriptExecutor js;
//		if (getDriver() instanceof JavascriptExecutor) {
//		    js = (JavascriptExecutor)getDriver();
//		} else {
//		    throw new IllegalStateException("This driver cannot run JavaScript.");
//		}
//		
//	//	String sa = "#pom-products-content ul li div.prod-box-name h2:contains('"+productName+"')";
//		WebElement element = (WebElement)js.executeScript("#pom-products-content ul li div.prod-box-name h2:contains('"+productName+"')");
//		element.click();
		
	//	WebElement product=getDriver().findElement(By.)
	}

	public String getProductDetailsText() {
		return productDetailsContainer.getText();
	}
	
}
