package com.tools.generalCalculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.tools.data.navision.SyncInfoModel;

/**
 * @author mihaibarta
 *
 */
public class StockCalculations {

	public static List<SyncInfoModel> calculateNewStock(List<SyncInfoModel> initialList, String quantity,
			boolean isConstantStock) {

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

		
		System.out.println("initialList" +initialList);
		System.out.println("initialList"+ initialList.size());
		List<SyncInfoModel> finalList = new ArrayList<SyncInfoModel>();

		for (SyncInfoModel product : initialList) {

			int index = initialList.indexOf(product);
			System.out.println("index "+index);
			System.out.println("product"+product.getSku());
			System.out.println(quantities.get(index));
			product.setQuantity(calculateStockToDouble(product.getQuantity(), quantities.get(index)));
			System.out.println(">>1");
			product.setEarliestAvailability(product.getEarliestAvailability());
			System.out.println(">>2");
			product.setIsDiscontinued(product.getIsDiscontinued());
			System.out.println(">>3");
			product.setMaxPercentToBorrow(product.getMaxPercentToBorrow());
			System.out.println(">>4");
			product.setMinumimQuantity(product.getMinumimQuantity());
			System.out.println(">>5");
			product.setPendingQuantity(product.getPendingQuantity());

			finalList.add(product);

		}
		return finalList;
	}

	public static List<SyncInfoModel> calculateNewStockAfterSync(List<SyncInfoModel> initialList,
			List<String> quantities) {

		List<SyncInfoModel> finalList = new ArrayList<SyncInfoModel>();

		for (SyncInfoModel product : initialList) {
			int index = initialList.indexOf(product);
			int productQty = Integer.parseInt(product.getQuantity()) - Integer.parseInt(quantities.get(index));

			String[] stock = calculateStockToDoubleAfterSync(String.valueOf(productQty), quantities.get(index),
					product.getMinumimQuantity());
			product.setQuantity(stock[0]);

			product.setEarliestAvailability(product.getEarliestAvailability());
			product.setIsDiscontinued(product.getIsDiscontinued());
			product.setMaxPercentToBorrow(product.getMaxPercentToBorrow());
			product.setMinumimQuantity(stock[1]);
			product.setPendingQuantity(product.getPendingQuantity());

			finalList.add(product);

		}
		return finalList;
	}
	
	public static List<SyncInfoModel> calculateNewStockAfterSyncAndTransfer(List<SyncInfoModel> initialList,
			List<String> quantities,List<String> transferQty) {

		List<SyncInfoModel> finalList = new ArrayList<SyncInfoModel>();

		for (SyncInfoModel product : initialList) {
			int index = initialList.indexOf(product);
			int productQty = Integer.parseInt(product.getQuantity())+ Integer.parseInt(transferQty.get(index)) - Integer.parseInt(quantities.get(index));

			product.setQuantity(Integer.toString(productQty));

			product.setEarliestAvailability(product.getEarliestAvailability());
			product.setIsDiscontinued(product.getIsDiscontinued());
			product.setMaxPercentToBorrow(product.getMaxPercentToBorrow());
			product.setMinumimQuantity(calculateMinimumQty(product, transferQty.get(index)));
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

	public static List<SyncInfoModel> calculateStockAfterTransferQuantity(List<SyncInfoModel> initialList,
			List<String> boughtProductsQuantities) {

		List<SyncInfoModel> finalList = new ArrayList<SyncInfoModel>();
		int counter=0;
		for (SyncInfoModel product : initialList) {
			product.setSimpleQty(addTransferQtyToStock(product, boughtProductsQuantities.get(counter),boughtProductsQuantities.get(counter)));
			product.setQuantity(addTransferQtyAndPendingStockToStock(product, boughtProductsQuantities.get(counter)));
			product.setEarliestAvailability(product.getEarliestAvailability());
			product.setIsDiscontinued(product.getIsDiscontinued());
			product.setMaxPercentToBorrow(product.getMaxPercentToBorrow());
			product.setMinumimQuantity(calculateMinimumQty(product, boughtProductsQuantities.get(counter)));
			product.setPendingQuantity(product.getPendingQuantity());
			counter++;
			finalList.add(product);

		}
		return finalList;
	}

	private static String calculateMinimumQty(SyncInfoModel product, String transferQty) {
		//need navision formula
		
		double transferValue = Double.parseDouble(transferQty) * 0.5;
		System.out.println("transferValue"+transferValue);
		System.out.println("product.getMinumimQuantity()"+ product.getMinumimQuantity());
		double newMinQty = Double.parseDouble(product.getMinumimQuantity()) + transferValue;
		//double roudedValue=Math.round(newMinQty);
		BigDecimal roudedValue = BigDecimal.valueOf(newMinQty).setScale(0, RoundingMode.HALF_DOWN);
		
		return String.valueOf(roudedValue);
		
		
//		double transferValue = Double.parseDouble(transferQty);
//		double newMinQty = Double.parseDouble(product.getMinumimQuantity()) + transferValue;
//
//		BigDecimal trasVal = BigDecimal.valueOf(transferValue).multiply(BigDecimal.valueOf(0.5));
//		BigDecimal newMinVal = BigDecimal.valueOf(newMinQty).add(trasVal);
//
//		//int roundedValue=newMinVal.ROUND_HALF_UP;
		
	//	return String.valueOf(newMinVal);
	}
	
	

	

	private static String addTransferQtyToStock(SyncInfoModel product, String transferQty,String orderedQty) {
		
		System.out.println("product.getQTY "+product.getQuantity());
		// TODO Auto-generated method stub
		return String.valueOf(Double.parseDouble(product.getQuantity())+Double.parseDouble(transferQty)-Double.parseDouble(orderedQty));
	}
	
	

	private static String addTransferQtyAndPendingStockToStock(SyncInfoModel product, String transferQty) {
		// TODO Auto-generated method stub
		return String.valueOf(Double.parseDouble(product.getQuantity()) + Double.parseDouble(transferQty)
				+ Double.parseDouble(product.getPendingQuantity()));
	}

	public static String[] calculateStockToDoubleAfterSync(String currentStock, String quantityOnOrder,
			String minimQty) {
		int stock = Integer.parseInt(currentStock);
		int qtyOrdered = Integer.parseInt(quantityOnOrder);
		int min_qty = Integer.parseInt(minimQty);

		String[] stockValue = new String[10];
		int result = stock - qtyOrdered;
		if (result <= 0) {
			stock = 0;
			min_qty = min_qty - result;
		}
		stockValue[0] = String.valueOf(stock);
		stockValue[1] = String.valueOf(min_qty);
		System.out.println(stockValue[0] + "  " + stockValue[1]);
		return stockValue;
	}

	public static String calculateStockToDouble(String currentStock, String quantityOnOrder) {
		return String.valueOf(Double.parseDouble(currentStock) - Integer.parseInt(quantityOnOrder));
	}

	public static String calculateStockToInt(String currentStock, String quantityOnOrder) {
		return String.valueOf(Integer.parseInt(currentStock) - Integer.parseInt(quantityOnOrder));
	}

	private static String addPendingStockToStock(SyncInfoModel product) {
		return String
				.valueOf(Double.parseDouble(product.getQuantity()) + Double.parseDouble(product.getPendingQuantity()));
	}

	/**
	 * Determine Quantity To Be Bought For Term Purchase
	 * 
	 * @param currentStock
	 * @return
	 */
	public static String determineQuantity(String currentStock) {
		BigDecimal stock = BigDecimal.valueOf(Double.parseDouble(currentStock));
		// stock = stock.add(BigDecimal.valueOf(60.00));

		stock = stock.add(BigDecimal.valueOf(3.00));

		return String.valueOf(stock.intValue());
	}

	

	public static List<SyncInfoModel> calculateStockAfterTransferQty(List<SyncInfoModel> changingStockMagentoProducts,
			List<SyncInfoModel> changingStockNavisionProducts) {

		//emilian mixt product details combinations ( in order to compare qty, min_qty shop vs nav)
		List<SyncInfoModel> finalList = new ArrayList<SyncInfoModel>();

		for (SyncInfoModel product : changingStockMagentoProducts) {
			SyncInfoModel compare = findProduct(product.getSku(), changingStockNavisionProducts);
			
			if (compare.getSku() != null) {
				product.setSimpleQty(calculateSimpleQuantity(compare.getQuantity(), product.getPendingQuantity()));
				System.out.println("simple Qtyyyyy "+calculateSimpleQuantity(compare.getQuantity(), product.getPendingQuantity()));
				product.setQuantity(compare.getQuantity());
				product.setMinumimQuantity(compare.getMinumimQuantity());
				finalList.add(product);
			}
		}

		Assert.assertTrue("Failure: Not all the products have been found ",
				changingStockMagentoProducts.size() == changingStockNavisionProducts.size());
		return finalList;
	}

	private static String calculateSimpleQuantity(String quantity, String pendingQuantity) {
		// TODO Auto-generated method stub
		return String.valueOf(Double.parseDouble(quantity) - Double.parseDouble(pendingQuantity));
	}

	private static SyncInfoModel findProduct(String sku, List<SyncInfoModel> productsList) {
		SyncInfoModel result = new SyncInfoModel();
		String[] skuParts = sku.split("-");
		theFor: for (SyncInfoModel product : productsList) {
			if (product.getSku().contains(skuParts[0])) {
				result = product;
				break theFor;
			}
		}
		System.out.println("sku foud "+result.getSku());
		return result;
	}

	
	public static void main(String args[]) {
		// System.out.println(StockCalculations.determineQuantity("59"));
		// System.out.println(StockCalculations.calculateStockToDoubleAfterSync("4","6","-3"));
		//System.out.println(StockCalculations.calculateMinimumQty("204","7"));
	}



	

	
}
