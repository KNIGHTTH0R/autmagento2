package com.tests;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.List;

import com.connectors.mongo.MongoConnector;
import com.tools.PrintUtils;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;

public class Dummy extends BaseTest {

	// public static void main(String args[]) throws UnknownHostException {
	//
	// MongoConnector connect = new MongoConnector();
	//
	// CartTotalsModel cart = new CartTotalsModel();
	// cart.addDiscount("zuchini", "11.02");
	// cart.addDiscount("2", "22.28");
	// cart.addDiscount("3", "33.09");
	//
	// MongoWriter.saveTotalsModel(cart, "Mark");
	// List<CartTotalsModel> result = MongoReader.grabTotalsModels("Mark");
	//
	//
	// System.out.println("Totals");
	// for (CartTotalsModel cartTotalsModel : result) {
	//
	// PrintUtils.printCartTotals(cartTotalsModel);
	// System.out.println(cartTotalsModel.getDiscountSumDouble());
	// }
	//
	// }
	public static void main(String args[]) throws UnknownHostException {

		CartProductModel product = new CartProductModel();
		product.setQuantity("5");
		product.setUnitPrice("1.245,64");
		BigDecimal productPrice = BigDecimal.valueOf(0);
		
		System.out.println(PrintUtils.cleanNumberToBigDecimal(product.getUnitPrice()));
		System.out.println(PrintUtils.cleanNumberToInt(product.getQuantity()));
		System.out.println(BigDecimal.valueOf(25));
		System.out.println(BigDecimal.valueOf(100));
		System.out.println("---------------------------");
		
		
		productPrice = productPrice.add(PrintUtils.cleanNumberToBigDecimal(product.getUnitPrice()));
		System.out.println(productPrice);
		productPrice = productPrice.multiply(PrintUtils.cleanNumberToBigDecimal(product.getQuantity()));
		System.out.println(productPrice);
		productPrice = productPrice.multiply(BigDecimal.valueOf(25));
		System.out.println(productPrice);
		productPrice = productPrice.divide(BigDecimal.valueOf(100));
		System.out.println("Final: " + productPrice);

		// productPrice. +=
		// (PrintUtils.cleanNumberToBigDecimal(product.getUnitPrice()) *
		// PrintUtils.cleanNumberToBigDecimal(product.getQuantity())) * 25 /
		// 100;
	}
}
