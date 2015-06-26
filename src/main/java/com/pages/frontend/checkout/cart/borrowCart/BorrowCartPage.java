package com.pages.frontend.checkout.cart.borrowCart;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.BorrowCartTotalsModel;
import com.tools.datahandlers.borrowCart.BorrowDataGrabber;
import com.tools.env.constants.TimeConstants;
import com.tools.env.variables.ContextConstants;
import com.tools.requirements.AbstractPage;
import com.tools.utils.FormatterUtils;

public class BorrowCartPage extends AbstractPage {

	@FindBy(css = "table#shopping-cart-table")
	private WebElement cartTable;

	@FindBy(css = "table#shopping-cart-totals-table")
	private WebElement totalsTable;

	@FindBy(css = "ul.checkout-types li button:last-child")
	private WebElement kasseButton;

	@FindBy(css = "div.main.col1-layout")
	private WebElement cartMainContainer;


	public List<BorrowProductModel> grabProductsData() {
		element(cartTable).waitUntilVisible();
		List<WebElement> entryList = getDriver().findElements(By.cssSelector("div.cart table.cart-table tbody > tr"));

		List<BorrowProductModel> resultList = new ArrayList<BorrowProductModel>();

		for (WebElement webElementNow : entryList) {
			BorrowProductModel productNow = new BorrowProductModel();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("h2.product-name")).getText().replace(productNow.getName(), "").trim());
			productNow.setUnitPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText()));
			productNow.setFinalPrice(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(5) span:nth-child(1).price")).getText()));
			productNow.setIpPoints(FormatterUtils.cleanNumberToString(webElementNow.findElement(By.cssSelector("td:nth-child(5) span:nth-child(2).price")).getText()));

			resultList.add(productNow);
		}
		BorrowDataGrabber.grabbedBorrowCartProductsList = resultList;

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
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setSubtotal(valueTransformer);
			}
			if (key.contains(ContextConstants.STEUER)) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setTax(valueTransformer);
			}
			if (key.contains(ContextConstants.GESAMTBETRAG)) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setTotalAmount(valueTransformer);
			}
			if (key.contains(ContextConstants.IP_PUNKTE)) {
				valueTransformer = FormatterUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setIpPoints(valueTransformer);
			}
		}

		BorrowDataGrabber.borrowCartGrabbedCartTotals = resultModel;

		return resultModel;
	}

	public void clickToShipping() {
		element(kasseButton).waitUntilVisible();
		kasseButton.click();
	}

	/**
	 * Verify Wipe cart if cart contains any data
	 */
	public void verifyWipeCart() {
		element(cartMainContainer).waitUntilVisible();
		System.out.println("TEXT from CONTAINER: " + cartMainContainer.getText());

		Assert.assertTrue(cartMainContainer.getText().contains(ContextConstants.EMPTY_CART));

	}

}
