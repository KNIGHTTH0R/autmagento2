package com.steps.frontend.reports;

import java.util.List;

import org.junit.Assert;

import com.steps.backend.ImportOrdersToNavSteps;
import com.tools.CustomVerification;
import com.tools.data.frontend.BorrowCartTotalsModel;
import com.tools.data.frontend.BorrowedCartModel;
import com.tools.data.frontend.StylistInventoryModel;
import com.tools.data.soap.NavOrderLinesModel;
import com.tools.data.soap.NavOrderModel;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

public class StylistInventorySteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void openBorrowSection() {
		stylistInventoryPage().openBorrowSection();

	}
	
	@Step
	public void openReturnSection() {
		stylistInventoryPage().openReturnSection();

	}
	
	@Step
	public List<StylistInventoryModel> grabProductsData() {
		return stylistInventoryPage().grabAllProducts();
	}

	@StepGroup
	public void validateBorrowedProducts(List<NavOrderLinesModel> billOfMaterial, List<StylistInventoryModel> productsData) {
		for (NavOrderLinesModel billof : billOfMaterial) {
			StylistInventoryModel compare = findOrder(billof.getNo(), productsData);
			
			if (compare.getProductSku()== null) {
				CustomVerification.verifyTrueForOrderImport(
						"Failure: Could not validate product " + compare.getProductSku(), compare == null);
			}else{
				validateProductSku(billof.getNo(),compare.getProductSku());
				validatePackageSku("xxxx",compare.getPackageSku());
				validateStatus("Leihen", compare.getStatus());
				validateQtyBorrowed("1", compare.getQtyBorrowed());
				validateQtyReturned("0", compare.getQtyReturned());
			}
		}
		

	}

	@StepGroup
	public void validateReturnedProducts(List<NavOrderLinesModel> billOfMaterial, List<StylistInventoryModel> productsData) {
		for (NavOrderLinesModel billof : billOfMaterial) {
			StylistInventoryModel compare = findOrder(billof.getNo(), productsData);
			
			if (compare.getProductSku()== null) {
				CustomVerification.verifyTrueForOrderImport(
						"Failure: Could not validate product " + compare.getProductSku(), compare == null);
			}else{
				validateProductSku(billof.getNo(),compare.getProductSku());
				validatePackageSku("xxxx",compare.getPackageSku());
				validateStatus("Retournieren", compare.getStatus());
				validateQtyBorrowed("0", compare.getQtyBorrowed());
				validateQtyReturned("1", compare.getQtyReturned());
			}
		}
		

	}
	
	@Step
	public void validateProductSku(String no, String productSku) {
		CustomVerification.verifyTrue("Failure: The  sku does not matck", no.contains(productSku));
		
	}

	private StylistInventoryModel findOrder(String productSku, List<StylistInventoryModel> model) {
		StylistInventoryModel result = new StylistInventoryModel();
		theFor: for (StylistInventoryModel item : model) {
			if (productSku.contains(item.getProductSku())) {
				result = item;
				break theFor;
			}
		}
		return result;

	}

	@Step
	public void validatePackageSku(String expected, String compare){
		CustomVerification.verifyTrue("Failure: The package sku does not matck", expected.contains(compare));
	}
	@Step
	public void validateStatus(String expected, String compare){
		CustomVerification.verifyTrue("Failure: The status does not matck", expected.contains(compare));
	}
	
	@Step
	public void validateQtyBorrowed(String expected, String compare){
		CustomVerification.verifyTrue("Failure: The QtyBorrowed  does not matck", expected.contains(compare));
	}
	
	@Step
	public void validateQtyReturned(String expected, String compare){
		CustomVerification.verifyTrue("Failure: The QtyReturned  does not matck", expected.contains(compare));
	}
}
