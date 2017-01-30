package com.pages.backend.borrow;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.tools.data.backend.TermPurchaseOrderModel;
import com.tools.requirements.AbstractPage;
import com.tools.utils.DateUtils;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.Step;

public class BorrowPage extends AbstractPage {

	@FindBy(id = "pippajean_borrow_stylist_borrow_process_disable_borrowed_items")
	private WebElement disableBorrowProcess;
	
	@FindBy(id = "pippajean_borrow_stylist_borrow_process_switch_borrow_functionality")
	private WebElement borrowProcessType;
	
	@FindBy(id = "pippajean_borrow_stylist_borrow_process_borrow_tax_product_sku")
	private WebElement taxProductSkuForBorrow;
	
	@FindBy(id = "pippajean_borrow_stylist_borrow_process_take_of_phase")
	private WebElement takeOfPhaseNumber;
	
	@FindBy(id = "pippajean_borrow_stylist_borrow_process_take_of_phase_product_sku")
	private WebElement productAvailableForTakeOfPhase;
	
	@FindBy(id = "pippajean_borrow_stylist_borrow_process_allowed_countries")
	private WebElement borrowProcessAvailableForCountry;
	
	@FindBy(id = "pippajean_borrow_stylist_borrow_process_extended_product_sku")
	private WebElement productsAvailableForStylistWithExtendedConditions;

	
	public void selectBorrowOption(String borrowOption) {
    	element(disableBorrowProcess).waitUntilVisible();
		element(disableBorrowProcess).selectByVisibleText(borrowOption);
    }	
	
	public void selectBorrowProcessType(String borrowType) {
    	element(borrowProcessType).waitUntilVisible();
		element(borrowProcessType).selectByVisibleText(borrowType);
    }
	
	public void taxProductSkuInput(String taxProductSku) {
		element(taxProductSkuForBorrow).waitUntilVisible();
		taxProductSkuForBorrow.sendKeys(taxProductSku);
	}
	
	public void takeOfPhaseInput(String numberOfDays) {
		element(takeOfPhaseNumber).waitUntilVisible();
		takeOfPhaseNumber.sendKeys(numberOfDays);
	}
	
	public void selectProductForTakeOfPhase(String product) {
    	element(productAvailableForTakeOfPhase).waitUntilVisible();
		element(productAvailableForTakeOfPhase).selectByVisibleText(product);
    }


	public void selectCountries() {
       Select oSelect = new Select(getDriver().findElement(By.id("pippajean_borrow_stylist_borrow_process_allowed_countries")));

        oSelect.selectByIndex(1);
        oSelect.selectByIndex(8);

	}
	
	public void selectProductsForStylistwithExtendedOption() {
	       Select oSelect = new Select(getDriver().findElement(By.id("pippajean_borrow_stylist_borrow_process_extended_product_sku")));

	        oSelect.selectByValue("23962");
	  //      oSelect.selectByIndex(1);

		}

}
