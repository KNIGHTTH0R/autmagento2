package com.tools.calculation;

import java.util.ArrayList;
import java.util.List;

import com.tools.data.navision.SyncInfoModel;

public class StockCalculations {

	public static List<SyncInfoModel> calculateNewStock(List<SyncInfoModel> initialList, String quantity, boolean isConstantStock) {

		List<SyncInfoModel> finalList = new ArrayList<SyncInfoModel>();

		for (SyncInfoModel product : initialList) {

			if (isConstantStock)
				product.setQuantity(product.getQuantity());
			else
				product.setQuantity(calculateStock(product.getQuantity(), quantity));

			product.setEarliestAvailability(product.getEarliestAvailability());
			product.setIsDiscontinued(product.getIsDiscontinued());
			product.setMaxPercentToBorrow(product.getMaxPercentToBorrow());
			product.setMinumimQuantity(product.getMinumimQuantity());
			product.setPendingQuantity(product.getPendingQuantity());

			finalList.add(product);

		}
		return finalList;
	}

	public static List<SyncInfoModel> calculateStockBasedOnPendingOrders(List<SyncInfoModel> initialList) {
		List<SyncInfoModel> finalList = new ArrayList<SyncInfoModel>();

		for (SyncInfoModel product : initialList) {

			product.setQuantity(addPendingStockToStock(product));
			product.setEarliestAvailability(product.getEarliestAvailability());
			product.setIsDiscontinued(product.getIsDiscontinued());
			product.setMaxPercentToBorrow(product.getMaxPercentToBorrow());
			product.setMinumimQuantity(product.getMinumimQuantity());
			product.setPendingQuantity(product.getPendingQuantity());

			finalList.add(product);

		}
		return finalList;
	}

	private static String calculateStock(String currentStock, String quantityOnOrder) {
		return String.valueOf(Integer.parseInt(currentStock) + Integer.parseInt(quantityOnOrder));
	}

	private static String addPendingStockToStock(SyncInfoModel product) {
		return String.valueOf(Integer.parseInt(product.getQuantity()) + Integer.parseInt(product.getPendingQuantity()));
	}

}
