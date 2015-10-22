package com.pages.backend.products;

import java.util.List;

import net.thucydides.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tools.env.constants.TimeConstants;
import com.tools.requirements.AbstractPage;

public class BackendProductDetailsPage extends AbstractPage {

	@FindBy(css = "#product_info_tabs li")
	private WebElement optionsList;

	@FindBy(id = "add_new_option")
	private WebElement addNewProduct;

	@FindBy(id = "id_bundle_options_0_title")
	private WebElement titleforBundleChild;

	@FindBy(id = "bundle_option_0_add_button")
	private WebElement addBundleChild;

	@FindBy(id = "bundle_selection_search_grid_0_filter_sku")
	private WebElement searchChildUsingSkuField;

	@FindBy(css = "table#bundle_selection_search_grid_0_table tbody tr:nth-child(1) td:nth-child(6) input")
	private WebElement selectFirstChildList;

	@FindBy(css = "button[onclick*='saveAndContinueEdit']")
	private WebElement saveAndContinueEdit;
	
	@FindBy(css = "td.filter-actions > button.task")
	private WebElement searchButton;
	
	@FindBy(css = "button[onclick='bSelection.productGridAddSelected(event)']")
	private WebElement AddSelectedProductToOption;

	public void selectOptionFromProductMenu(String searchedOption) {
		evaluateJavascript("jQuery.noConflict();");
		element(optionsList).waitUntilVisible();
		List<WebElement> optionsList = getDriver().findElements(By.cssSelector("#product_info_tabs li"));
		for (WebElement option : optionsList) {
			System.out.println(option.getText());
			if (option.getText().contains(searchedOption)) {
				option.findElement(By.cssSelector("a")).click();
				waitABit(TimeConstants.TIME_CONSTANT);
				break;
			}
		}
	}

	public void saveAndContinueEdit() {
		evaluateJavascript("jQuery.noConflict();");
		element(saveAndContinueEdit).waitUntilVisible();
		saveAndContinueEdit.click();
	}

	public void clickAddNewProductButton() {
		element(addNewProduct).waitUntilVisible();
		addNewProduct.click();
	}

	public void setTitleForBundleChild(String titleName) {
		element(titleforBundleChild).waitUntilVisible();
		titleforBundleChild.sendKeys(titleName);
	}

	public void clickAddSelectionButton() {
		element(addBundleChild).waitUntilVisible();
		addBundleChild.click();
	}

	public void searchByChild(String childName) {
		element(searchChildUsingSkuField).waitUntilVisible();
		searchChildUsingSkuField.sendKeys(childName);
	}

	public void selectFirstProductAsAChild() {
		element(selectFirstChildList).waitUntilVisible();
		selectFirstChildList.click();
	}

	
	public void clickOnSearch() {
		evaluateJavascript("jQuery.noConflict();");
		element(searchButton).waitUntilVisible();
		searchButton.click();
		waitABit(1000);
	}
	
	public void clickAddSelectedProductToOption() {
		evaluateJavascript("jQuery.noConflict();");
		element(AddSelectedProductToOption).waitUntilVisible();
		AddSelectedProductToOption.click();
		waitABit(1000);
	}
}
