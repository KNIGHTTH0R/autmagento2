package com.pages.frontend.checkout.cart.borrowCart;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tools.constants.ContextConstants;
import com.tools.constants.TimeConstants;
import com.tools.data.frontend.BorrowCartTotalsModel;
import com.tools.data.frontend.BorrowedCartModel;
import com.tools.datahandler.BorrowDataGrabber;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

public class BorrowCartPage extends AbstractPage {

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

	public List<BorrowedCartModel> grabProductsData() {
		element(cartTable).waitUntilVisible();

		List<WebElement> entryList = getDriver().findElements(By.cssSelector("div.cart table.cart-table tbody > tr"));
		entryList.remove(0);

		List<BorrowedCartModel> resultList = new ArrayList<BorrowedCartModel>();

		BorrowedCartModel defaultProduct = new BorrowedCartModel();

		WebElement prodListFirstRow = getDriver()
				.findElement(By.cssSelector("div.cart table.cart-table tbody > tr:nth-child(1)"));

		defaultProduct.setName(
				prodListFirstRow.findElement(By.cssSelector("h2.product-name")).getText().replace(" Z999", "").trim());
		defaultProduct.setProdCode(prodListFirstRow.findElement(By.cssSelector("h2.product-name")).getText()
				.replace(defaultProduct.getName(), "").trim());
		defaultProduct.setUnitPrice(FormatterUtils
				.parseValueToTwoDecimals(prodListFirstRow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
		defaultProduct.setFinalPrice(FormatterUtils.parseValueToTwoDecimals(
				prodListFirstRow.findElement(By.cssSelector("td:nth-child(5) span:nth-child(1).price")).getText()));
		defaultProduct.setIpPoints(FormatterUtils.parseValueToZeroDecimals(
				prodListFirstRow.findElement(By.cssSelector("td:nth-child(5) span:nth-child(2).price")).getText()));

		resultList.add(defaultProduct);

		for (WebElement webElementNow : entryList) {

			BorrowedCartModel productNow = new BorrowedCartModel();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("h2.product-name")).getText()
					.replace(productNow.getName(), "").trim());
			productNow.setUnitPrice(FormatterUtils
					.parseValueToTwoDecimals(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice(FormatterUtils.parseValueToTwoDecimals(
					webElementNow.findElement(By.cssSelector("td:nth-child(5) span:nth-child(1).price")).getText()));
			productNow.setIpPoints(FormatterUtils.parseValueToZeroDecimals(
					webElementNow.findElement(By.cssSelector("td:nth-child(5) span:nth-child(2).price")).getText()));

			resultList.add(productNow);
		}

		BorrowDataGrabber.grabbedBorrowCartProductsList = resultList;

		return resultList;
	}

	public List<BorrowedCartModel> grabProductsDataNewFunctionality() {
		// element(cartTable).waitUntilVisible();
		System.out.println("aici 1");
		// List<WebElement> entryList =
		// getDriver().findElements(By.cssSelector("div.cart table.cart-table
		// tbody > tr"));

		List<BorrowedCartModel> resultList = new ArrayList<BorrowedCartModel>();

		BorrowedCartModel defaultProduct = new BorrowedCartModel();

		WebElement prodListFirstRow = getDriver()
				.findElement(By.cssSelector("div.cart table.cart-table tbody > tr:nth-child(1)"));
		defaultProduct
				.setName(prodListFirstRow.findElement(By.cssSelector(" h2.product-name strong")).getText().trim());
		defaultProduct
				.setProdCode(prodListFirstRow.findElement(By.cssSelector("h2.product-name div")).getText().trim());
		defaultProduct.setUnitPrice(FormatterUtils
				.parseValueToTwoDecimals(prodListFirstRow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
		defaultProduct.setFinalPrice(FormatterUtils.parseValueToTwoDecimals(
				prodListFirstRow.findElement(By.cssSelector("td:nth-child(5) span:nth-child(1).price")).getText()));
		defaultProduct.setIpPoints(FormatterUtils.parseValueToZeroDecimals(
				prodListFirstRow.findElement(By.cssSelector("td:nth-child(5) span:nth-child(2).price")).getText()));
		resultList.add(defaultProduct);

		BorrowedCartModel xxxProduct = new BorrowedCartModel();
		WebElement prodListSecondRow = getDriver()
				.findElement(By.cssSelector("div.cart table.cart-table tbody > tr:nth-child(2)"));

		xxxProduct.setName(prodListSecondRow.findElement(By.cssSelector(" h2.product-name strong")).getText().trim());
		xxxProduct.setProdCode(prodListSecondRow.findElement(By.cssSelector("h2.product-name div")).getText().trim());
		xxxProduct.setUnitPrice(FormatterUtils
				.parseValueToTwoDecimals(prodListSecondRow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
		xxxProduct.setFinalPrice(FormatterUtils.parseValueToTwoDecimals(
				prodListSecondRow.findElement(By.cssSelector("td:nth-child(5) span:nth-child(1).price")).getText()));
		xxxProduct.setIpPoints(FormatterUtils.parseValueToZeroDecimals(
				prodListSecondRow.findElement(By.cssSelector("td:nth-child(5) span:nth-child(2).price")).getText()));

		resultList.add(xxxProduct);
		System.out.println(xxxProduct);
		BorrowDataGrabber.grabbedBorrowCartProductsList = resultList;

		
		Assert.assertTrue("The cart does not contains correct no of items", resultList.size()==2 );
		
		
		return resultList;
	}

	public BorrowCartTotalsModel grabTotals() {
		BorrowCartTotalsModel resultModel = new BorrowCartTotalsModel();
		waitABit(TimeConstants.TIME_CONSTANT);

		String valueTransformer = "";
		element(totalsTable).waitUntilVisible();

		waitFor(ExpectedConditions.visibilityOfAllElements(totalsTable.findElements(By.tagName("tr"))));
		List<WebElement> valuesList = totalsTable.findElements(By.cssSelector("tr"));

		for (WebElement itemNow : valuesList) {
			String key = itemNow.findElement(By.cssSelector("td:first-child")).getText();

			if (key.contains(ContextConstants.ZWISCHENSUMME)) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setSubtotal(valueTransformer);
			}
			if (key.contains(ContextConstants.STEUER)) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setTax(valueTransformer);
			}
			if (key.contains(ContextConstants.GESAMTBETRAG)) {
				valueTransformer = FormatterUtils
						.parseValueToTwoDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setTotalAmount(valueTransformer);
			}
			if (key.contains(ContextConstants.IP_PUNKTE)) {
				valueTransformer = FormatterUtils
						.parseValueToZeroDecimals(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setIpPoints(valueTransformer);
			}
		}

		BorrowDataGrabber.borrowCartGrabbedCartTotals = resultModel;

		return resultModel;
	}

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

	/**
	 * Verify Wipe cart if cart contains any data
	 */
	public void verifyWipeCart() {
		element(cartMainContainer).waitUntilVisible();
		Assert.assertTrue(cartMainContainer.getText().contains(ContextConstants.EMPTY_CART));

	}

	public void verifyStockMessageForProduct(String productName, String stockInfo) {
		element(borrowProductFromTable).waitUntilVisible();
		List<WebElement> entryList = getDriver()
				.findElements(By.cssSelector("table[class*='data-table cart-table'] tbody > tr"));
		boolean found = false;
		for (WebElement entry : entryList) {
			if (entry.findElement(By.cssSelector("td:nth-child(2)")).getText().contains(productName)) {
				found = true;
				Assert.assertTrue("The stock message is not correct !!!",
						entry.findElement(By.cssSelector("td:nth-child(2)")).getText().contains(stockInfo));
				break;
			}
		}
		Assert.assertTrue("The product was not found in the cart", found);
	}

	public void verifyPresenceOfGoToCheckoutButton(boolean shouldBePresent) {
		if (shouldBePresent)
			Assert.assertTrue("The go to checkout button should be visible and it's not!", getDriver()
					.findElement(By.cssSelector("div.page-title ul.checkout-types button:last-child")).isDisplayed());
		else
			Assert.assertTrue("The go to checkout button is visible and it shouldn't !", getDriver()
					.findElements(By.cssSelector("div.page-title ul.checkout-types button:last-child")).size() == 0);
	}

	public void checkPresenceOfAddItemsButton(boolean isDisplayed) {
		if (isDisplayed)
			Assert.assertTrue("The Add items to shopping bag button should be visible and it's not!",
					getDriver().findElement(By.cssSelector("#add-items-button span")).isDisplayed());
		else
			Assert.assertTrue("The Add items to shopping bag button is visible and it shouldn't !",
					getDriver().findElements(By.cssSelector("#add-items-button span")).size() == 0);

	}

	public void checkPresenceOfTopCheckoutButton(boolean isDisplayed) {
		// TODO Auto-generated method stub

		if (isDisplayed)
			Assert.assertTrue("The Proceed to Checkout(from top) button should be visible and it's not!",
					getDriver().findElement(By.cssSelector(".page-title button")).isDisplayed());
		else
			Assert.assertTrue("The Proceed to Checkout(from top) button is visible and it shouldn't !",
					getDriver().findElements(By.cssSelector(".checkout-types button")).size() == 0);

	}
	
	public void checkPresenceOfButtomCheckoutButton(boolean isDisplayed) {
		// TODO Auto-generated method stub

		if (isDisplayed)
			Assert.assertTrue("The Proceed to Checkout(from buttom) button should be visible and it's not!",
					getDriver().findElement(By.cssSelector(".checkout-types li button")).isDisplayed());
		else
			Assert.assertTrue("The Proceed to Checkout(from buttom) button is visible and it shouldn't !",
					getDriver().findElements(By.cssSelector(".checkout-types li button")).size() == 0);

	}

	public void checkPresenceOfClearCartButton(boolean isDisplayed) {
		// TODO Auto-generated method stub

		if (isDisplayed)
			Assert.assertTrue("The Clear All Items button should be visible and it's not!", getDriver()
					.findElement(By.cssSelector(".shopping-bag-form .buttons-set button[value*=empty]")).isDisplayed());
		else
			Assert.assertTrue("The Clear All Items button is visible and it shouldn't !", getDriver()
					.findElements(By.cssSelector(".shopping-bag-form .buttons-set button[value*=empty]")).size() == 0);
	}

	public void checkPresenceOfUpdateCartButton(boolean isDisplayed) {
		// TODO Auto-generated method stub

		if (isDisplayed)
			Assert.assertTrue("The Update Shopping Cart button should be visible and it's not!",
					getDriver().findElement(By.cssSelector(".shopping-bag-form .buttons-set button[value*=update]"))
							.isDisplayed());
		else
			Assert.assertTrue("The Update Shopping Cart button is visible and it shouldn't !", getDriver()
					.findElements(By.cssSelector(".shopping-bag-form .buttons-set button[value*=update]")).size() == 0);
	}

	public void checkNoOfProductsDisplayedInMiniCart(int size) {
		int counter=Integer.parseInt(minicartCounter.getText());
		
		Assert.assertTrue("The Minicart does not contains correct no of products!",size==counter);
		
	}


}
