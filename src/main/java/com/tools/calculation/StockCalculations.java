package com.tools.calculation;

import java.util.ArrayList;
import java.util.List;

import com.tools.data.navision.SyncInfoModel;

public class StockCalculations {

	public static List<SyncInfoModel> calculateMagStockAfterPlaceOrder(List<SyncInfoModel> initialList, String quantityOnOrder, boolean isConstantStock) {

		List<SyncInfoModel> finalList = new ArrayList<SyncInfoModel>();

		for (SyncInfoModel product : initialList) {

			if (isConstantStock)
				product.setQuantity(product.getQuantity());
			else
				product.setQuantity(calculateNewStock(product.getQuantity(), quantityOnOrder));

			product.setEarliestAvailability(product.getEarliestAvailability());
			product.setIsDiscontinued(product.getIsDiscontinued());
			product.setMaxPercentToBorrow(product.getMaxPercentToBorrow());
			product.setMinumimQuantity(product.getMinumimQuantity());
			product.setPendingQuantity(product.getPendingQuantity());

			finalList.add(product);

		}
		return finalList;
	}

	public static String calculateNewStock(String currentStock, String quantityOnOrder) {
		return String.valueOf(Integer.parseInt(currentStock) + Integer.parseInt(quantityOnOrder));
	}
}
