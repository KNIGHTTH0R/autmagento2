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
		double totalAmount = 0;
		double ipPointsSum = 0;
		double taxSum = 0;
		double jeverlyBonus = 0;
		double shiping = 0;
		
		for (CartProductModel cartProductModel : productList) {
			double productPrice = 0;
			productPrice += (PrintUtils.cleanNumberToDouble(cartProductModel.getUnitPrice()) * PrintUtils.cleanNumberToInt(cartProductModel.getQuantity()));
			totalPrice += productPrice;
			double discount = 0;
			if (PrintUtils.cleanNumberToInt(cartProductModel.getPriceIP()) > 0) {
				discount = calculateDiscountValue(cartProductModel);
				discountSum += calculateDiscountValue(cartProductModel);
				
			}			
			totalAmount += (PrintUtils.cleanNumberToDouble(cartProductModel.getProductsPrice())- discount);			
			ipPointsSum += PrintUtils.cleanNumberToInt(cartProductModel.getPriceIP());
			 
			
			System.out.println("Unit Price: " + cartProductModel.getUnitPrice());
			System.out.println("Quantity: " + cartProductModel.getQuantity());
			System.out.println("Product Price: " + productPrice);
			System.out.println("Total Price: " + totalPrice);
		}
		taxSum = (totalAmount * 19) /119;

		CartTotalsModel result = new CartTotalsModel();

		result.setSubtotal(String.valueOf(totalPrice));
		result.setDiscount(String.valueOf(discountSum));
		result.setTotalAmount(String.valueOf(totalAmount));
		result.setIpPoints(String.valueOf(ipPointsSum));
		result.setTax(String.valueOf(taxSum));
		result.setShipping(String.valueOf(shiping));
		result.setJewelryBonus(String.valueOf(jeverlyBonus));
		
		System.out.println("------------------------------");
		System.out.println("------------------------------> " + result.getSubtotal());
		System.out.println("------------------------------> " + result.getDiscount());
		System.out.println("------------------------------> " + result.getTotalAmount());
		System.out.println("------------------------------> " + result.getIpPoints());
		System.out.println("------------------------------> " + result.getTax());
		System.out.println("------------------------------> " + result.getJewelryBonus());
		System.out.println("------------------------------> " + result.getShipping());

		return result;
	}

	public double calculateDiscountValue(CartProductModel cartProductModel) {
		double productPrice = 0;
		productPrice += (PrintUtils.cleanNumberToDouble(cartProductModel.getUnitPrice()) * PrintUtils.cleanNumberToInt(cartProductModel.getQuantity()));
		return productPrice = (productPrice * 25 / 100);
	}
	
			
	
	

}
