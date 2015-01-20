package com.pages.frontend.checkout;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.jruby.ir.instructions.PutInstr;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.AbstractPage;
import com.tools.PrintUtils;
import com.tools.data.CartProductModel;
import com.tools.data.CartTotalsModel;

public class CartPage extends AbstractPage {

	@FindBy(css = "table#shopping-cart-totals-table")
	private WebElement totalsTable;

	@FindBy(css = "ul.checkout-types button:last-child")
	private WebElement kasseButton;

	/**
	 * Will grab all products data from all carts
	 * 
	 * @return
	 */
	public List<CartProductModel> grabProductsData() {
		List<WebElement> entryList = getDriver().findElements(By.cssSelector("div.cart table.cart-table tbody > tr"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();

		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("h2.product-name")).getText().replace(productNow.getName(), "").trim());
			productNow.setQuantity(webElementNow.findElement(By.cssSelector("input")).getAttribute("value"));
			productNow.setUnitPrice(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText());
			productNow.setProductsPrice(webElementNow.findElement(By.cssSelector("td:nth-child(5)")).getText());
			productNow.setFinalPrice(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText());
			productNow.setPriceIP(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.ff-Ge")).getText());
			productNow.setDiscountClass("");
			
			resultList.add(productNow);
		}

		return resultList;
	}
	
	/**
	 * Will grab all products data from the cart where discount is 25%
	 * 
	 * @return
	 */
	public List<CartProductModel> grabProductsDataWith25Discount() {
		List<WebElement> entryList = getDriver().findElements(By.id("shopping-cart-25-table"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();
		System.out.println(resultList.size());

		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();

			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("h2.product-name")).getText().replace(productNow.getName(), "").trim());
			productNow.setQuantity(webElementNow.findElement(By.cssSelector("input")).getAttribute("value"));
			productNow.setUnitPrice(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText());
			productNow.setProductsPrice(webElementNow.findElement(By.cssSelector("td:nth-child(5)")).getText());
			productNow.setFinalPrice(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText());
			productNow.setPriceIP(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.ff-Ge")).getText());
			productNow.setDiscountClass("25");
			
			resultList.add(productNow);
		}

		return resultList;
	}
	/**
	 * Will grab all products data from the cart where discount is 50%
	 * 
	 * @return
	 */
	public List<CartProductModel> grabProductsDataWith50Discount() {
		List<WebElement> entryList = getDriver().findElements(By.id("shopping-cart-50-table"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();
		System.out.println(resultList.size());
		
		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();
			
			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("h2.product-name")).getText().replace(productNow.getName(), "").trim());
			productNow.setQuantity(webElementNow.findElement(By.cssSelector("input")).getAttribute("value"));
			productNow.setUnitPrice(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText());
			productNow.setProductsPrice(webElementNow.findElement(By.cssSelector("td:nth-child(5)")).getText());
			productNow.setFinalPrice(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText());
			productNow.setPriceIP(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.ff-Ge")).getText());
			productNow.setDiscountClass("50");
			
			resultList.add(productNow);
		}
		
		return resultList;
	}
	/**
	 * Will grab all products data from the cart which are marketing materials
	 * 
	 * @return
	 */
	public List<CartProductModel> grabMarketingMaterialProductsData() {
		List<WebElement> entryList = getDriver().findElements(By.id("shopping-cart-table-marketing-material"));
		List<CartProductModel> resultList = new ArrayList<CartProductModel>();
		System.out.println(resultList.size());
		
		for (WebElement webElementNow : entryList) {
			CartProductModel productNow = new CartProductModel();
			
			productNow.setName(webElementNow.findElement(By.cssSelector("h2.product-name a")).getText());
			productNow.setProdCode(webElementNow.findElement(By.cssSelector("h2.product-name")).getText().replace(productNow.getName(), "").trim());
			productNow.setQuantity(webElementNow.findElement(By.cssSelector("input")).getAttribute("value"));
			productNow.setUnitPrice(webElementNow.findElement(By.cssSelector("td:nth-child(4)")).getText());
			productNow.setProductsPrice(webElementNow.findElement(By.cssSelector("td:nth-child(5)")).getText());
			productNow.setFinalPrice(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.price")).getText());
			productNow.setPriceIP(webElementNow.findElement(By.cssSelector("td:nth-child(6) span.ff-Ge")).getText());
			productNow.setDiscountClass("0");
			
			resultList.add(productNow);
		}
		
		return resultList;
	}

	public CartTotalsModel grabTotals() {
		CartTotalsModel resultModel = new CartTotalsModel();
		
		String valueTransformer = "";
		element(totalsTable).waitUntilVisible();
		List<WebElement> valuesList = totalsTable.findElements(By.cssSelector("tr"));
		
		System.out.println("******PUSHKE ----- hamster");
		
		//TODO if totalAmount is < 150  than shipping element is not present- {"method":"css selector","selector":"tbody tr:nth-child(5) > td:last-child"}
		
		for (WebElement itemNow : valuesList) {
			String key = itemNow.findElement(By.cssSelector("td:first-child")).getText();
			
			if(key.contains("ZWISCHENSUMME")){
				valueTransformer = PrintUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setSubtotal(valueTransformer);
			}
			if(key.contains("SCHMUCK BONUS")){
				valueTransformer = PrintUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setJewelryBonus(valueTransformer);
			}
			if(key.contains("VERSANDKOSTENFREI")){
				valueTransformer = PrintUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setShipping(valueTransformer);
			}
			if(key.contains("25%")){
				valueTransformer = PrintUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setDiscount25(valueTransformer);
			}
			if(key.contains("50% - STYLIST")){
				valueTransformer = PrintUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setDiscount50(valueTransformer);
			}
			if(key.contains("GESAMTBETRAG")){
				valueTransformer = PrintUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setTotalAmount(valueTransformer);
			}
			if(key.contains("IP PUNKTE")){
				valueTransformer = PrintUtils.cleanNumberToString(itemNow.findElement(By.cssSelector("td:last-child")).getText());
				resultModel.setIpPoints(valueTransformer);
			}
			
		}
		
		
		
		
		
		
		
		resultModel.setSubtotal(PrintUtils.cleanNumberToString(totalsTable.findElement(By.cssSelector("tbody tr:nth-child(1) > td:last-child")).getText()));
		resultModel.setJewelryBonus(PrintUtils.cleanNumberToString(totalsTable.findElement(By.cssSelector("tbody tr:nth-child(2) input#jewelry_credits")).getAttribute("value")));
		resultModel.setDiscount(PrintUtils.cleanNumberToString(totalsTable.findElement(By.cssSelector("tbody tr:nth-child(3) > td:last-child")).getText()));
		resultModel.setTax(PrintUtils.cleanNumberToString(totalsTable.findElement(By.cssSelector("tbody tr:nth-child(4) > td:last-child")).getText()));
		resultModel.setShipping(PrintUtils.cleanNumberToString(totalsTable.findElement(By.cssSelector("tbody tr:nth-child(5) > td:last-child")).getText()));
		resultModel.setShipping(PrintUtils.cleanNumberToString(totalsTable.findElement(By.cssSelector("tbody tr:nth-child(5) > td:last-child")).getText()));
		resultModel.setTotalAmount(PrintUtils.cleanNumberToString(totalsTable.findElement(By.cssSelector("tr.grandtotal > td:last-child")).getText()));
		resultModel.setIpPoints(PrintUtils.cleanNumberToString(totalsTable.findElement(By.cssSelector("tfoot tr:nth-child(2) > td:last-child")).getText()));

		return resultModel;
	}

	public void clickToShipping() {
		element(kasseButton).waitUntilVisible();
		kasseButton.click();
	}

}
