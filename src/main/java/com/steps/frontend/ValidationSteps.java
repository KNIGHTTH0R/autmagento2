package com.steps.frontend;

import java.text.DecimalFormat;
import java.util.List;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import com.tools.AbstractSteps;
import com.tools.PrintUtils;
import com.tools.data.CartProductModel;
import com.tools.data.CartTotalsModel;

public class ValidationSteps extends AbstractSteps {

	private static final long serialVersionUID = 4274219181280984116L;

	public CartTotalsModel calculateCartProducts(List<CartProductModel> productList) {
		
	 DecimalFormat df = new DecimalFormat("#####0.00");
	 
		double totalPrice = 0;
		double discountSum = 0;
		double totalAmount = 0;
		int ipPointsSum = 0;
		double taxSum = 0;
		int jeverlyBonus = 0;
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
		taxSum = PrintUtils.getDoubleWithTwoDigits(((totalAmount * 19) /119));

		CartTotalsModel result = new CartTotalsModel();

		result.setSubtotal(df.format((totalPrice)));
		result.setDiscount(df.format(discountSum));
		result.setTotalAmount(df.format(totalAmount));
		result.setIpPoints(String.valueOf((ipPointsSum)));
		result.setTax(df.format(taxSum));
		result.setShipping(df.format(shiping));
		result.setJewelryBonus(String.valueOf((jeverlyBonus)));
		
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
	
	@Step
	public void checkTotalsInCart(CartTotalsModel cartTotalsModel1,CartTotalsModel cartTotalsModel2){
		
		System.out.println("----------SUBTOTAL: " + cartTotalsModel2.getSubtotal() + " : " +  cartTotalsModel1.getSubtotal());	
		Assert.assertTrue("The subtotal should be " + cartTotalsModel2.getSubtotal() +" and it is " +  cartTotalsModel1.getSubtotal() +  "!",
				cartTotalsModel2.getSubtotal().equals(cartTotalsModel1.getSubtotal()));
		
		System.out.println("----------DISCOUNT: " + cartTotalsModel2.getDiscount() + " : " +  cartTotalsModel1.getDiscount());	
		Assert.assertTrue("The discount should be " + cartTotalsModel2.getDiscount() +" and it is " +  cartTotalsModel1.getDiscount() +  "!",
				cartTotalsModel2.getDiscount().equals(cartTotalsModel1.getDiscount()));		
	
		System.out.println("----------TOTAL AMOUNT: " + cartTotalsModel2.getTotalAmount() + " : " +  cartTotalsModel1.getTotalAmount());	
		Assert.assertTrue("The total amount should be " + cartTotalsModel2.getTotalAmount() +" and it is " + cartTotalsModel1.getTotalAmount() +  "!",
				cartTotalsModel2.getTotalAmount().equals(cartTotalsModel1.getTotalAmount()));
		
		System.out.println("----------TAX: " + cartTotalsModel2.getTax() + " : " +  cartTotalsModel1.getTax());	
		Assert.assertTrue("The tax should be " + cartTotalsModel2.getTax() +" and it is " + cartTotalsModel1.getTax() +  "!",
				cartTotalsModel2.getTax().equals(cartTotalsModel1.getTax()));
		
		System.out.println("----------SHIPPING: " + cartTotalsModel2.getShipping() + " : " +  cartTotalsModel1.getShipping());	
		Assert.assertTrue("The shipping  should be " + cartTotalsModel2.getShipping() +" and it is " + cartTotalsModel1.getShipping() +  "!",
				cartTotalsModel2.getShipping().equals(cartTotalsModel1.getShipping()));
		
		System.out.println("----------JEWERLY BONUS: " + cartTotalsModel2.getJewelryBonus() + " : " +  cartTotalsModel1.getJewelryBonus());	
		Assert.assertTrue("The jewerly bouns should be " + cartTotalsModel2.getJewelryBonus() +" and it is " + cartTotalsModel1.getJewelryBonus() +  "!",
				cartTotalsModel2.getJewelryBonus().equals(cartTotalsModel1.getJewelryBonus()));
		
		System.out.println("----------IP POINTS: " + cartTotalsModel2.getIpPoints() + " : " +  cartTotalsModel1.getIpPoints());	
		Assert.assertTrue("The ip points should be " + cartTotalsModel2.getIpPoints() +" and it is " + cartTotalsModel1.getIpPoints() +  "!",
				cartTotalsModel2.getIpPoints().equals(cartTotalsModel1.getIpPoints()));	
	
	
	}	
	
	
	

}
