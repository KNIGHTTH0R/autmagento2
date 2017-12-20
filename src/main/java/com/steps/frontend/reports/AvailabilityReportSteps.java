package com.steps.frontend.reports;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import java.lang.reflect.Field;

import com.tools.CustomVerification;
import com.tools.data.soap.DBOrderModel;
import com.tools.data.soap.NavOrderModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class AvailabilityReportSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void clickOnBackInStockTabProductsTab() {
		availabilityReportPage().clickOnBackInStockTabProducts();
		waitABit(4000);
	}

	@Step
	public void clickLessThan20AvailableProducts() {
		availabilityReportPage().clickOnBackInStockTabProducts();
	}

	@Step
	public void clickOnTemporarlyNotAvailablePoductsTab() {
		waitABit(4000);
		availabilityReportPage().clickOnTemporarlyNotAvailablePoducts();
		waitABit(4000);
	}

	@Step
	public void clickOnNotLongerAvailableProductsTab() {
		waitABit(4000);
		availabilityReportPage().clickOnNotLongerAvailableProducts();
		waitABit(4000);
	}

	@Step
	public void verifyIfBackInStockTabProductsTabIsSelected() {
		availabilityReportPage().verifyIfBackInStockTabProductsIsSelected();

	}

	@Step
	public void verifyIfTemporarlyNotAvailablePoductsTabIsSelected() {
		availabilityReportPage().verifyIfTemporarlyNotAvailablePoductsIsSelected();

	}

	@Step
	public void verifyIfBackInStockPoductsTabIsSelected() {
		availabilityReportPage().verifyIfBackInStockTabProductsIsSelected();

	}

	@Step
	public void verifyIfNotLongerAvailablePoductsTabIsSelected() {
		availabilityReportPage().verifyIfNotLongerAvailablePoductsTabIsSelected();

	}

	@Step
	public void verifyIfProductIsDisplayed(ProductDetailedModel model) {
		availabilityReportPage().verifyIfProductIsDisplayed(model);
	}

	@Step
	public void verifyProductInBackInStockTab(String sku, String catalogDetails, List<String> categoriesList) {
		availabilityReportPage().verifyProductInBackInStockTab(sku, catalogDetails, categoriesList);

	}

	@Step
	public void verifyTpProductInTemporarlyNotAvailableTab(String sku, String catalogDetails,
			String earliestAvailability) throws ParseException {
		availabilityReportPage().verifyTpProductInTemporarlyNotAvailableTab(sku, catalogDetails, earliestAvailability);

	}

	@Step
	public void verifyNoAsteriscNextToWillBeAnnouncedStatus(String sku, String catalogDetails,
			String earliestAvailability) throws ParseException {
		availabilityReportPage().verifyNoAsteriscNextToWillBeAnnouncedStatus(sku, catalogDetails, earliestAvailability);
	}

	@Step
	public void verifyProductInNotLongerAvailableTab(String sku, String catalogDetails) {
		availabilityReportPage().verifyProductInNotLongerAvailableTab(sku, catalogDetails);
	}

	@Step
	public void reloadReports() {
		availabilityReportPage().reloadReports();
		waitABit(7000);
	}

	@Step
	public void verifyIfProductIsNotDisplayedinSelectedTab(String sku) {
		availabilityReportPage().verifyIfProductIsNotDisplayedinSelectedTab(sku);

	}

	@Step
	public void clickOnStockReportTabProductsTab() {
		availabilityReportPage().clickOnStockReportTabProductsTab();

	}

	@Step
	public void verifyIfStockPoductsTabIsSelected() {
		availabilityReportPage().verifyIfStockPoductsTabIsSelected();
	}

	@Step
	public void clickOnLessThenXProductsTab() {
		availabilityReportPage().clickOnLessThenXProductsTab();
		waitABit(7000);
	}

	@Step
	public void verifyIfLessThenXPoductsTabIsSelected() {
		availabilityReportPage().verifyIfLessThenXPoductsTabIsSelected();
	}

	@Step
	public void verifyReportIsOpen() {
		availabilityReportPage().verifyReportIsOpen();
	}

	public List<ProductDetailedModel> grabTemporlyNotAvailableProducts() {
		return availabilityReportPage().grabTemporlyNotAvailableProducts();

	}

	public List<ProductDetailedModel> grabNotLogngerAvailableProduct() {
		return availabilityReportPage().grabNotLongerAvailableProducts();
	}

	public List<ProductDetailedModel> grabBackInStockProducts() {
		return availabilityReportPage().grabBackInStockProducts();
	}

	public List<ProductDetailedModel> grabLessThanTwentyAvailableProducts() {
		return availabilityReportPage().grabLessThanTwentyAvalableProducts();
	}
	
	public List<ProductDetailedModel> grabStockReportProducts() {
		return availabilityReportPage().grabStockReportProducts();
	}

	
	public void validateProductSku(String productSku, String compare) {
		CustomVerification.verifyTrue(
				"Failure: Product Sku doesn't match: " + productSku + " - " + compare,
				productSku.contentEquals(compare));

	}
	
	
	public void validateProductName(String productName, String compare, String pageNumber, String rowNumber) {
		if(!pageNumber.isEmpty()&&rowNumber.isEmpty()){
			productName=productName+",S. "+pageNumber;
		}
		if(!rowNumber.isEmpty() && !pageNumber.isEmpty()){
			productName=productName+","+pageNumber+", "+rowNumber;
		}
		
		CustomVerification.verifyTrue(
				"Failure: Product Name doesn't match: " + productName + " - " + compare,
				productName.replaceAll("\\s+","").contentEquals(compare.replaceAll("\\s+","")));
	}
	

	public void validateProductName(String productName, String compare) {
	
		
		CustomVerification.verifyTrue(
				"Failure: Product Name doesn't match: " + productName + " - " + compare,
				productName.replaceAll("\\s+","").contentEquals(compare.replaceAll("\\s+","")));
	}


	public static void validateDate(String date, String compare) {

		CustomVerification.verifyTrue("Failure: Product date doesn't match: " + date + " - " + compare,
				date.replaceAll("\\s+","").contentEquals(compare.replaceAll("\\s+","")));

	}
	
	
	public static void validateQty(String qty, String compare) {
		int quatity = (int) Double.parseDouble(qty);
		CustomVerification.verifyTrue("Failure: Product qty doesn't match: " + qty + " - " + compare,
				compare.replaceAll("\\s+","").contains(Integer.toString(quatity).replaceAll("\\s+","")));

	}
	

	public static void validateIsDiscontinued(String isDiscontinued, String compare) {
		CustomVerification.verifyTrue("Failure: Product qty doesn't match: " + isDiscontinued + " - " + compare,
				isDiscontinued.replaceAll("\\s+","").contains(compare.replaceAll("\\s+","")));

	}
	

	public static void validateStatus(String status, String compare) {
		CustomVerification.verifyTrue("Failure: Product qty doesn't match: " + status + " - " + compare,
				status.replaceAll("\\s+","").contains(compare.replaceAll("\\s+","")));

	}
	
	private void validateCategories(List<String> dbCategories, List<String> grabbedCategries) {
		for (String category : dbCategories) {
			CustomVerification.verifyTrue("Failure: Category list doesn't match", grabbedCategries.contains(category));
		}
		
	}

	@Step
	public <T> void verifyListOfObjests(List<T> list1, List<T> list2, String matchElement)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		Assert.assertTrue("The lists do not have the same size", list1.size() == list2.size());

		for (T item : list1) {
			T itemInList2 = getObjectByMatchingElement(list2, matchElement,
					PropertyUtils.getProperty(item, matchElement).toString());
			for (Field field : item.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				// Assert.assertTrue("<< " + field.getName() + " >> doesn't
				// match !!", field.get(item).equals(field.get(itemInList2)));
				CustomVerification.verifyTrue("<< " + field.getName() + " >> doesn't match !!",
						field.get(item).equals(field.get(itemInList2)));
			}
		}
	}

	protected <T> T getObjectByMatchingElement(List<T> list, String matchElement, String matchValue)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		for (T obj : list) {
			Object value = PropertyUtils.getProperty(obj, matchElement);
			if (((String) value).contains(matchValue)) {
				return obj;
			}
		}
		// Assert.assertTrue("The object with type " + matchElement + " wasn't
		// found", false);
		CustomVerification.verifyTrue("The object with type " + matchElement + " wasn't found", false);
		return null;
	}

	@Step("Validate Temporarly not available products")
	public void validateTemporarlyNotAvailablePoductsTab(List<ProductDetailedModel> temporarlyNotAvailableProducts,
			List<ProductDetailedModel> grabTemporlyNotAvailableProducts) {


		for (ProductDetailedModel product : temporarlyNotAvailableProducts) {
			ProductDetailedModel grabbedCompare = findProduct(product.getSku(), grabTemporlyNotAvailableProducts);
			if (grabbedCompare.getSku() == null) {
				CustomVerification.verifyTrue(
						"Failure: Could not validate product " + product.getSku(), grabbedCompare == null);
			} else {
				validateProductSku(product.getSku(), grabbedCompare.getSku());
				validateProductName(product.getName(), grabbedCompare.getName(), product.getCatalogPageNumber(),product.getCatalogRowNumber());
				validateDate(product.getEarliestAvDate(), grabbedCompare.getEarliestAvDate());
			}

		}

	}

	
	public static ProductDetailedModel findProduct(String sku, List<ProductDetailedModel> grabbedList) {
		ProductDetailedModel result = new ProductDetailedModel();
		theFor: for (ProductDetailedModel item : grabbedList) {
			if (item.getSku().replaceAll("\\s+","").contentEquals(sku.replaceAll("\\s+",""))) {
				result = item;
				break theFor;
			}
		}
		return result;
	}

	@Step("Validate Less than X products Tab")
	public void validateLessThanXProductsTab(List<ProductDetailedModel> lessThanTwentyAvailableProducts,
			List<ProductDetailedModel> grabLessThanTwentyAvailableProducts) {
		for (ProductDetailedModel product : lessThanTwentyAvailableProducts) {
			ProductDetailedModel grabbedCompare = findProduct(product.getSku(), grabLessThanTwentyAvailableProducts);
			if (grabbedCompare.getSku() == null) {
				CustomVerification.verifyTrue(
						"Failure: Could not validate product " + product.getSku(), grabbedCompare == null);
			} else {
				validateProductSku(product.getSku(), grabbedCompare.getSku());
				validateProductName(product.getName(), grabbedCompare.getName(), product.getCatalogPageNumber(),product.getCatalogRowNumber());
				validateQty(product.getQuantity(), grabbedCompare.getQuantity());
			}

		}

		
	}
	
	@Step("Validate Not Longer Available products Tab")
	public void validateNotLongerAvailableProducts(List<ProductDetailedModel> notLogngerAvailableProduct,
			List<ProductDetailedModel> grabNotLogngerAvailableProduct) {
		for (ProductDetailedModel product : notLogngerAvailableProduct) {
			ProductDetailedModel grabbedCompare = findProduct(product.getSku(), grabNotLogngerAvailableProduct);
			if (grabbedCompare.getSku() == null) {
				CustomVerification.verifyTrue(
						"Failure: Could not validate product " + product.getSku(), grabbedCompare == null);
			} else {
				validateProductSku(product.getSku(), grabbedCompare.getSku());
				validateProductName(product.getName(), grabbedCompare.getName(), product.getCatalogPageNumber(),product.getCatalogRowNumber());
			}

		}
	}

	@Step("Validate Back In Stock products Tab")
	public void validateBackInStockProductsTab(List<ProductDetailedModel> backInStockProducts,
			List<ProductDetailedModel> grabBackInStockProducts) {
		for (ProductDetailedModel product : backInStockProducts) {
			ProductDetailedModel grabbedCompare = findProduct(product.getSku(), grabBackInStockProducts);
			if (grabbedCompare.getSku() == null) {
				CustomVerification.verifyTrue(
						"Failure: Could not validate product " + product.getSku(), grabbedCompare == null);
			} else {
				validateProductSku(product.getSku(), grabbedCompare.getSku());
				validateProductName(product.getName(), grabbedCompare.getName());
				validateCategories(product.getCategoriesArray(),grabbedCompare.getCategoriesArray());
			}

		}		
	}

	

	@Step("Validate Stock Report (all products) Tab")
	public void validateStockReportProducts(List<ProductDetailedModel> stockReportProducts,
			List<ProductDetailedModel> grabBackInStockProducts) {
		for (ProductDetailedModel product : stockReportProducts) {
			ProductDetailedModel grabbedCompare = findProduct(product.getSku(), grabBackInStockProducts);
			if (grabbedCompare.getSku() == null) {
				CustomVerification.verifyTrue(
						"Failure: Could not validate product " + product.getSku(), grabbedCompare == null);
			} else {
				validateProductSku(product.getSku(), grabbedCompare.getSku());
				validateProductName(product.getName(), grabbedCompare.getName(), product.getCatalogPageNumber(),product.getCatalogRowNumber());
				validateStatus(product.getStatus(), grabbedCompare.getStatus());
				validateIsDiscontinued(product.getIsDiscountinued(), grabbedCompare.getIsDiscountinued());
			}
		
		}
	}

}
