package com.steps.backend.validations;

import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import org.junit.Assert;

import com.tools.CustomVerification;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.requirements.AbstractSteps;
import com.tools.utils.PrintUtils;

public class OrderValidationSteps extends AbstractSteps {

	private static final long serialVersionUID = 4274219181280984116L;

	@StepGroup
	public void validateProducts(List<ProductBasicModel> productsList, List<OrderItemModel> orderProducts) {
		if (productsList != null) {
			for (ProductBasicModel productNow : productsList) {
				OrderItemModel compare = findProduct(productNow.getType(), productNow.getQuantity(), orderProducts);

				PrintUtils.printProductsCompareBackend(productNow, compare);

				if (compare.getProductName() != null) {
					matchName(productNow.getName(), compare.getProductName());
					validateMatchPrice(productNow.getPrice(), compare.getPrice());
					validateMatchQuantity(productNow.getQuantity(), compare.getNumber());
				} else {
					Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
				}
			}
		} else {
			Assert.assertTrue("Failure: to validate product list. Product list is empty", productsList != null);
		}
	}

	@Step
	@Screenshots(onlyOnFailures = true)
	public void validateMatchPrice(String productNow, String compare) {
		Assert.assertTrue("Failure: Price values dont match: " + productNow + " - " + compare, productNow.replaceAll("\\s+","").contentEquals(compare.replaceAll("\\s+","")));
	}

	/**
	 * Used only for reporting purposes. Display match names.
	 * 
	 * @param productNow
	 * @param compare
	 */
	@Step
	public void matchName(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Name values dont match: " + productNow + " - " + compare, productNow.contentEquals(compare));

	}
	@Step
	public void matchSku(String prodCode, String compare) {
		CustomVerification.verifyTrue("Failure: Sku values dont match: " + prodCode + " - " + compare, compare.contentEquals(prodCode));
	}

	@Step
	public void validateMatchQuantity(String productNow, String compare) {
		CustomVerification.verifyTrue("Failure: Quantity values dont match: " + productNow + " - " + compare, compare.contains(productNow));
	}

	public List<OrderItemModel> findProducts(String productCode, List<OrderItemModel> orderProducts) {

		List<OrderItemModel> resultList = new ArrayList<OrderItemModel>();

		for (OrderItemModel orderProduct : orderProducts) {

			if (orderProduct.getProductCode().contains(productCode)) {
				OrderItemModel result = new OrderItemModel();
				result = orderProduct;

				resultList.add(result);
			}
		}
		return resultList;
	}

	public OrderItemModel findProduct(String productCode, String qty, List<OrderItemModel> orderProducts) {
		OrderItemModel result = new OrderItemModel();

		theFor: for (OrderItemModel orderProduct : orderProducts) {

			if (orderProduct.getProductCode().contains(productCode) && orderProduct.getNumber().contentEquals(qty)) {

				result = orderProduct;

				break theFor;
			}
		}
		return result;
	}

	public OrderItemModel findProduct(String productCode, List<OrderItemModel> orderProducts) {
		OrderItemModel result = new OrderItemModel();

		theFor: for (OrderItemModel orderProduct : orderProducts) {

			if (orderProduct.getProductCode().contains(productCode)) {

				result = orderProduct;

				break theFor;
			}
		}
		return result;
	}

	

}
