
package com.tools.datahandlers.partyHost;

import java.util.ArrayList;
import java.util.List;

import com.tools.Constants;
import com.tools.calculation.HostCartTotalsCalculation;
import com.tools.calculation.RegularCartTotalsCalculation;
import com.tools.data.HostCartCalcDetailsModel;
import com.tools.data.RegularCartCalcDetailsModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.ShippingModel;

public class HostCartCalculator {

	public static List<HostBasicProductModel> allProductsList = new ArrayList<HostBasicProductModel>();
	public static List<HostBasicProductModel> allProductsListWithRegularPrice = new ArrayList<HostBasicProductModel>();
	public static HostCartCalcDetailsModel calculatedTotalsDiscounts = new HostCartCalcDetailsModel();
	public static ShippingModel shippingCalculatedModel = new ShippingModel();

	public static void wipe() {
		allProductsList = new ArrayList<HostBasicProductModel>();
		allProductsListWithRegularPrice = new ArrayList<HostBasicProductModel>();
		calculatedTotalsDiscounts = new HostCartCalcDetailsModel();
		shippingCalculatedModel = new ShippingModel();
	}

	public static List<RegularBasicProductModel> getProductswithRegularPriceFromList(List<RegularBasicProductModel> productsList) {
		List<RegularBasicProductModel> newProductsList = new ArrayList<RegularBasicProductModel>();
		for (RegularBasicProductModel product : productsList) {
			if (product.getBonusType().contentEquals(Constants.REGULAR_PRICE)) {
				newProductsList.add(product);
			}
		}
		return newProductsList;
	}

	public static void calculateCartAndShippingTotals(List<HostBasicProductModel> prodList, String discountClass, String shippingValue) {
		calculatedTotalsDiscounts = HostCartTotalsCalculation.calculateTotals(allProductsList, discountClass);
		shippingCalculatedModel = HostCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts, shippingValue);
	}
//	public static void calculateCartBuy3Get1CartAndShippingTotals(List<RegularBasicProductModel> prodList, String discountClass, String shippingValue, String voucherValue) {
//		allProductsListWithRegularPrice = getProductswithRegularPriceFromList(allProductsList);
//		calculatedTotalsDiscounts = RegularCartTotalsCalculation.calculateTotalsWithBuy3Get1Active (allProductsList,allProductsListWithRegularPrice, discountClass, voucherValue);
//		shippingCalculatedModel = RegularCartTotalsCalculation.calculateShippingTotals(calculatedTotalsDiscounts, shippingValue);
//		
//	}
		

}

