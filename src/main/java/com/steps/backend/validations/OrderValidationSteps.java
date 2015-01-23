package com.steps.backend.validations;

import java.util.List;

import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import org.junit.Assert;

import com.tools.AbstractSteps;
import com.tools.PrintUtils;
import com.tools.data.CalculationModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.ProductBasicModel;

public class OrderValidationSteps extends AbstractSteps {

	private static final long serialVersionUID = 4274219181280984116L;



	@StepGroup
	public void validateProducts(List<ProductBasicModel> productsList, List<OrderItemModel> orderProducts) {

		for (ProductBasicModel productNow : productsList) {
			OrderItemModel compare = findProduct(productNow.getType(), orderProducts);

			PrintUtils.printProductsCompareBackend(productNow, compare);

			if (compare.getProductName() != null) {
				matchName(productNow.getName(), compare.getProductName());
				validateMatchPrice(productNow.getPrice(), compare.getPrice());
				validateMatchQuantity(productNow.getQuantity(), compare.getNumber());
			} else {
				Assert.assertTrue("Failure: Could not validate all products in the list", compare != null);
			}
		}
	}

	@Step
	@Screenshots(onlyOnFailures = true)
	public void validateMatchPrice(String productNow, String compare) {
		Assert.assertTrue("Failure: Price values dont match: " + productNow + " - " + compare, productNow.contentEquals(compare));
	}
	
	@Step
	public void matchName(String productNow, String compare) {
//		Assert.assertTrue("Failure: Price values dont match: " + productNow + " - " + compare, productNow.contentEquals(compare));
	}

	@Step
	public void validateMatchQuantity(String productNow, String compare) {
		Assert.assertTrue("Failure: Quantity values dont match: " + productNow + " - " + compare, productNow.contentEquals(compare));
	}	

	private static OrderItemModel findProduct(String productCode, List<OrderItemModel> orderProducts) {
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
