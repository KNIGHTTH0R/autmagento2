package com.tests;

import java.net.UnknownHostException;
import java.util.List;

import com.connectors.mongo.MongoConnector;
import com.tools.PrintUtils;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;

public class Dummy extends BaseTest{
	
	public static void  main(String args[]) throws UnknownHostException {
		
		MongoConnector connect = new MongoConnector();
		
		CartTotalsModel cart = new CartTotalsModel();
		cart.addDiscount("zuchini", "11.02");
		cart.addDiscount("2", "22.28");
		cart.addDiscount("3", "33.09");
		
		MongoWriter.saveTotalsModel(cart, "Mark");
		List<CartTotalsModel> result = MongoReader.grabTotalsModels("Mark");
		
		
		System.out.println("Totals");
		for (CartTotalsModel cartTotalsModel : result) {

			PrintUtils.printCartTotals(cartTotalsModel);
			System.out.println(cartTotalsModel.getDiscountSumDouble());
		}
		
	}

}
