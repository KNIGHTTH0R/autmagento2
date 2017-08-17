package com.pages.frontend.reports;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.tools.constants.ContextConstants;
import com.tools.data.frontend.StylistInventoryModel;
import com.tools.requirements.AbstractPage;

import net.serenitybdd.core.annotations.findby.FindBy;

public class StylistInventoryPage extends AbstractPage {

	@FindBy(css = "table#shopping-cart-table")
	private WebElement cartTable;

	@FindBy(css = "table#shopping-cart-totals-table")
	private WebElement totalsTable;

	@FindBy(css = "ul.checkout-types li button:last-child")
	private WebElement kasseButton;

	@FindBy(css = "button[onclick*='cart/clearAllItems']")
	private WebElement wipeCart;

	@FindBy(css = "div.main.col1-layout")
	private WebElement cartMainContainer;

	@FindBy(css = "table.cart-table>tbody tr:nth-child(2)")
	private WebElement borrowProductFromTable;

	@FindBy(css = "li.error-msg span")
	private WebElement errorMessageContainer;
	
	@FindBy(css = ".block-title small span")
	private WebElement minicartCounter;

	
	public void clickToShipping() {
		element(kasseButton).waitUntilVisible();
		kasseButton.click();
		withTimeoutOf(30, TimeUnit.SECONDS).waitFor(ExpectedConditions.invisibilityOfElementWithText(
				By.cssSelector(".blockUI.blockMsg.blockElement"), ContextConstants.LOADING_MESSAGE));
	}

	public void clickWipeCart() {
		element(wipeCart).waitUntilVisible();
		wipeCart.click();
	}

	public void openBorrowSection() {
		Select oSelect = new Select(getDriver().findElement(By.id("dynamic_select")));
		oSelect.selectByVisibleText("Geliehene Waren");
		
	}
	
	public void openReturnSection() {
		Select oSelect = new Select(getDriver().findElement(By.id("dynamic_select")));
		oSelect.selectByVisibleText("Retournierte Waren");
	}

	
	public List<StylistInventoryModel> grabAllProducts(){
		List<StylistInventoryModel> stylistInventory=new ArrayList<StylistInventoryModel>();
		List<WebElement> grabbedReport=new ArrayList<WebElement>();
		
		grabbedReport=getDriver().findElements(By.cssSelector(".data-table.borrow-stock tbody tr:not(.inactive)"));
		
		for (WebElement row : grabbedReport) {
			StylistInventoryModel productData=new StylistInventoryModel();
			productData.setProductSku(row.findElement(By.cssSelector("td:nth-child(2) p.size10")).getText());
			productData.setPackageSku(row.findElement(By.cssSelector("td:nth-child(3) p")).getText());
			productData.setQtyBorrowed(row.findElement(By.cssSelector("td:nth-child(4) p")).getText().split("/")[0]);
			productData.setQtyReturned(row.findElement(By.cssSelector("td:nth-child(4) p")).getText().split("/")[1]);
			productData.setDateToReturn(row.findElement(By.cssSelector("td:nth-child(5) p")).getText());
			productData.setStatus(row.findElement(By.cssSelector("td:nth-child(6) .tt-no")).getText());

			stylistInventory.add(productData);
		}
		return stylistInventory;
		
	}
	/**
	 * Verify Wipe cart if cart contains any data
	 */
	

}
