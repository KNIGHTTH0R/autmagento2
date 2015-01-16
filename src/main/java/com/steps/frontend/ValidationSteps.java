package com.steps.frontend;

import java.util.List;

import com.tools.AbstractSteps;
import com.tools.PrintUtils;
import com.tools.data.CartProductModel;
import com.tools.data.CartTotalsModel;

public class ValidationSteps extends AbstractSteps {

	private static final long serialVersionUID = 4274219181280984116L;

	public CartTotalsModel calculateCartProducts(List<CartProductModel> productList) {

		double totalPrice = 0;
		double discountSum = 0;

		for (CartProductModel cartProductModel : productList) {
			double productPrice = 0;
			productPrice += (PrintUtils.cleanNumberToDouble(cartProductModel.getUnitPrice()) * PrintUtils.cleanNumberToInt(cartProductModel.getQuantity()));
			totalPrice += productPrice;

			if (PrintUtils.cleanNumberToInt(cartProductModel.getPriceIP()) > 0) {
				discountSum += calculateDiscountValue(cartProductModel);
			}

			System.out.println("Unit Price: " + cartProductModel.getUnitPrice());
			System.out.println("Quantity: " + cartProductModel.getQuantity());
			System.out.println("Product Price: " + productPrice);
			System.out.println("Total Price: " + totalPrice);
		}

		CartTotalsModel result = new CartTotalsModel();

		result.setSubtotal(String.valueOf(totalPrice));
		result.setDiscount(String.valueOf(discountSum));
		System.out.println("------------------------------");
		System.out.println("------------------------------> " + result.getSubtotal());
		System.out.println("------------------------------> " + result.getDiscount());

		return result;
	}

	public double calculateDiscountValue(CartProductModel cartProductModel) {
		double productPrice = 0;
		productPrice += (PrintUtils.cleanNumberToDouble(cartProductModel.getUnitPrice()) * PrintUtils.cleanNumberToInt(cartProductModel.getQuantity()));
		return productPrice = (productPrice * 25 / 100);
	}

}
