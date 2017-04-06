package com.tools.generalCalculation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tools.data.navision.SyncInfoModel;

/**
 * @author mihaibarta
 *
 */
public class StockCalculations {

	public static List<SyncInfoModel> calculateNewStock(List<SyncInfoModel> initialList, String quantity, boolean isConstantStock) {

		List<SyncInfoModel> finalList = new ArrayList<SyncInfoModel>();

		for (SyncInfoModel product : initialList) {

			if (isConstantStock)
				product.setQuantity(product.getQuantity());
			else {
				product.setQuantity(calculateStockToDouble(product.getQuantity(), quantity));
			}

			product.setEarliestAvailability(product.getEarliestAvailability());
			product.setIsDiscontinued(product.getIsDiscontinued());
			product.setMaxPercentToBorrow(product.getMaxPercentToBorrow());
			product.setMinumimQuantity(product.getMinumimQuantity());
			product.setPendingQuantity(product.getPendingQuantity());

			finalList.add(product);

		}
		return finalList;
	}

	/**
	 * calculates new stock based on a list of quantities bought from each
	 * product
	 * 
	 * @see dsdsds
	 * @param initialList
	 * @param quantities
	 * @return
	 */
	public static List<SyncInfoModel> calculateNewStock(List<SyncInfoModel> initialList, List<String> quantities) {

		List<SyncInfoModel> finalList = new ArrayList<SyncInfoModel>();

		for (SyncInfoModel product : initialList) {

			int index = initialList.indexOf(product);

			product.setQuantity(calculateStockToDouble(product.getQuantity(), quantities.get(index)));

			product.setEarliestAvailability(product.getEarliestAvailability());
			product.setIsDiscontinued(product.getIsDiscontinued());
			product.setMaxPercentToBorrow(product.getMaxPercentToBorrow());
			product.setMinumimQuantity(product.getMinumimQuantity());
			product.setPendingQuantity(product.getPendingQuantity());

			finalList.add(product);

		}
		return finalList;
	}
	
	public static List<SyncInfoModel> calculateNewStockAfterSync(List<SyncInfoModel> initialList, List<String> quantities) {

		List<SyncInfoModel> finalList = new ArrayList<SyncInfoModel>();

		for (SyncInfoModel product : initialList) {
			int index = initialList.indexOf(product);
			int productQty=Integer.parseInt(product.getQuantity())-Integer.parseInt(quantities.get(index));
			
			String[] stock=calculateStockToDoubleAfterSync(String.valueOf(productQty), quantities.get(index), product.getMinumimQuantity());
			product.setQuantity(stock[0]);

			product.setEarliestAvailability(product.getEarliestAvailability());
			product.setIsDiscontinued(product.getIsDiscontinued());
			product.setMaxPercentToBorrow(product.getMaxPercentToBorrow());
			product.setMinumimQuantity(stock[0]);
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

	
	
	
	public static String[] calculateStockToDoubleAfterSync(String currentStock, String quantityOnOrder,String minimQty) {
		int stock=Integer.parseInt(currentStock);
		int qtyOrdered=Integer.parseInt(quantityOnOrder);
		int min_qty=Integer.parseInt(minimQty);
		
		String[] stockValue= new String[10];
		int result=stock-qtyOrdered;
		if(result<=0){
			stock=0;
			min_qty=min_qty-result;
		}
		stockValue[0]=String.valueOf(stock);
		stockValue[1]=String.valueOf(min_qty);
		System.out.println(stockValue[0]+"  "+ stockValue[1]);
		return stockValue;
	}
	
	public static String calculateStockToDouble(String currentStock, String quantityOnOrder) {
		return String.valueOf(Double.parseDouble(currentStock) - Integer.parseInt(quantityOnOrder));
	}
	public static String calculateStockToInt(String currentStock, String quantityOnOrder) {
		return String.valueOf(Integer.parseInt(currentStock) - Integer.parseInt(quantityOnOrder));
	}
	
	private static String addPendingStockToStock(SyncInfoModel product) {
		return String.valueOf(Double.parseDouble(product.getQuantity()) + Double.parseDouble(product.getPendingQuantity()));
	}

	/**
	 * Determine Quantity To Be Bought For Term Purchase
	 * 
	 * @param currentStock
	 * @return
	 */
	public static String determineQuantity(String currentStock) {
		BigDecimal stock = BigDecimal.valueOf(Double.parseDouble(currentStock));
		stock = stock.add(BigDecimal.valueOf(60.00));

		return String.valueOf(stock.intValue());
	}
	
	public static void main(String args[]) {
		//System.out.println(StockCalculations.determineQuantity("59"));
		System.out.println(StockCalculations.calculateStockToDoubleAfterSync("4","5","-2"));
	}

}
