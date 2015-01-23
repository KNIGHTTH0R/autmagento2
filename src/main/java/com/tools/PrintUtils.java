package com.tools;

import java.text.DecimalFormat;
import java.util.List;

import com.tools.data.CalculationModel;
import com.tools.data.EmailModel;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.ProductBasicModel;

public class PrintUtils {

	public static void printList(List<CartProductModel> list) {
		System.out.println(" *** Print List from Cart *** ");
		for (CartProductModel cartProductModel : list) {
			System.out.println("------------------------");
			System.out.println(cartProductModel.getName());
			System.out.println(cartProductModel.getProdCode());
			System.out.println(cartProductModel.getQuantity());
			System.out.println(cartProductModel.getUnitPrice());
			System.out.println(cartProductModel.getProductsPrice());
			System.out.println(cartProductModel.getFinalPrice());
			System.out.println(cartProductModel.getPriceIP());
		}
	}
	public static void printOrderTtems(List<OrderItemModel> list) {
		System.out.println(" *** Print Products List from Orders(backend) *** ");
		for (OrderItemModel orderItem : list) {
			System.out.println("------------------------");
			System.out.println(orderItem.getProductName());
			System.out.println(orderItem.getProductCode());
			System.out.println(orderItem.getPropertyStatus());
			System.out.println(orderItem.getOriginalPrice());
			System.out.println(orderItem.getPrice());
			System.out.println(orderItem.getNumber());
			System.out.println(orderItem.getSubtotal());
			System.out.println(orderItem.getTaxAmount());
			System.out.println(orderItem.getTaxPercentage());
			System.out.println(orderItem.getDiscountAmount());
			System.out.println(orderItem.getiP());
			System.out.println(orderItem.getjB());
			System.out.println(orderItem.getmB());
			System.out.println(orderItem.getfD());
			System.out.println(orderItem.getRowSum());

		}
	}

	public static void printCartTotals(CartTotalsModel model) {
		System.out.println(" *** Print Total section from Cart *** ");
		System.out.println("------------------------");
		System.out.println(model.getSubtotal());
		System.out.println(model.getJewelryBonus());
		System.out.println(model.getDiscount());
		System.out.println(model.getTax());
		System.out.println(model.getShipping());
		System.out.println(model.getTotalAmount());
		System.out.println(model.getIpPoints());
	}
	
	public static void printOrderInfo(OrderInfoModel model) {
		System.out.println(" *** Print Order Info *** ");
		System.out.println("------------------------");
		System.out.println(model.getOrderDate());
		System.out.println(model.getOrderStatus());
		System.out.println(model.getAquiredBy());
		System.out.println(model.getOrderIP());
	
	}
	
	public static void printCalculationModel(CalculationModel model) {
		System.out.println(" *** Print Total *** ");
		System.out.println("------------------------");
//		System.out.println(model.getTableType());
//		System.out.println(model.getRetailPrice());
		System.out.println(model.formatDouble(model.getAskingPrice()));
		System.out.println(model.formatDouble(model.getFinalPrice()));
		System.out.println(model.getIpPoints());

	}
	public static void printOrderTotals(OrderTotalsModel model) {
		
	
		
		System.out.println(" *** Print backend orders Totals  *** ");
		System.out.println("------------------------");
		System.out.println(model.getSubtotal());
		System.out.println(model.getShipping());
		System.out.println(model.getDiscount());		
		System.out.println(model.getTax());
		System.out.println(model.getTotalAmount());
		System.out.println(model.getTotalPaid());
		System.out.println(model.getTotalRefunded());
		System.out.println(model.getTotalPayable());
		System.out.println(model.getTotalIP());
		System.out.println(model.getTotalFortyDiscounts());		
		System.out.println(model.getTotalBonusJeverly());
		System.out.println(model.getTotalMarketingBonus());
		

	}

	public static double cleanNumberToDouble(String unitPrice) {
		String result = unitPrice;
		result = result.replace(" €", "");
		result = result.replace("€ ", "");
		result = result.replace(".", "");
		result = result.replace(" IP", "");
		result = result.replace(",", ".");
		result = result.replace("-", "");

		return Double.parseDouble(result);
	}
	public static String cleanNumberToString(String unitPrice) {
		String result = unitPrice;
		result = result.replace(" €", "");
		result = result.replace("€ ", "");
		result = result.replace(".", "");
		result = result.replace(" IP", "");
		result = result.replace(",", ".");
		result = result.replace("-", "");
		result = result.replace("%", "");
		
		return result;
	}

	public static int cleanNumberToInt(String unitPrice) {
		String result = unitPrice;
		int finalResult = 0;
		result = result.replace(" €", "");
		result = result.replace("€ ", "");
		result = result.replace(".", "");
		result = result.replace(" IP", "");
		result = result.replace(",", ".");

		try {
			finalResult = Integer.valueOf(result);
		} catch (Exception e) {
			System.err.println("WARNING: Could not convert to integer - "
					+ unitPrice);
			e.printStackTrace();
		}

		return finalResult;
	}

	public static Double getDoubleWithTwoDigits(Double number) {
		DecimalFormat twoFiguresFormat = new DecimalFormat("####0.00");
		return Double.valueOf(twoFiguresFormat.format(number));
	}

	public static void printAddressModel(AddressModel dataModel) {
		
		System.out.println("---- PRINT Adress Model ----");
		System.out.println(dataModel.getStreetAddress());
		System.out.println(dataModel.getStreetNumber());
		System.out.println(dataModel.getPostCode());
		System.out.println(dataModel.getHomeTown());
		System.out.println(dataModel.getPhoneNumber());
		
	}

	public static void printEmailList(List<EmailModel> emailList) {
		System.out.println("Email list:");
		for (EmailModel emailModel : emailList) {
			System.out.println(emailModel.getSubject());
			System.out.println(emailModel.getContent());
			System.out.println(emailModel.getSentDate() + " - " + emailModel.getRecievedDate());
		}
		
	}

	public static void printProductsCompare(ProductBasicModel productNow, CartProductModel compare) {
		System.out.println(productNow.getName() + " - " + compare.getName());
		System.out.println(productNow.getPrice() + " - " + compare.getUnitPrice());
		System.out.println(productNow.getQuantity() + " - " + compare.getQuantity());
		System.out.println(productNow.getType() + " - " + compare.getProdCode());
		
	}
	
	public static void printProductsCompareBackend(ProductBasicModel basicProduct, OrderItemModel orderProduct) {
		System.out.println(basicProduct.getName() + " - " + orderProduct.getProductName());
		System.out.println(basicProduct.getPrice() + " - " + orderProduct.getPrice());
		System.out.println(basicProduct.getQuantity() + " - " + orderProduct.getNumber());
		System.out.println(basicProduct.getType() + " - " + orderProduct.getProductCode());
		
	}

	public static void printOrderItemsList(List<OrderItemModel> orderItemsList) {
		System.out.println("---------- Order Items List --------------");
		
		for (OrderItemModel orderItemModel : orderItemsList) {
			System.out.println("------------------------");
			System.out.println(orderItemModel.getProductName());
			System.out.println(orderItemModel.getProductCode());
			System.out.println(orderItemModel.getPropertyStatus());
			System.out.println(orderItemModel.getOriginalPrice());
			System.out.println(orderItemModel.getPrice());
			System.out.println(orderItemModel.getNumber());
			System.out.println(orderItemModel.getSubtotal());
			System.out.println(orderItemModel.getTaxAmount());
			System.out.println(orderItemModel.getTaxPercentage());
			System.out.println(orderItemModel.getDiscountAmount());
			System.out.println(orderItemModel.getiP());
			System.out.println(orderItemModel.getjB());
			System.out.println(orderItemModel.getmB());
			System.out.println(orderItemModel.getfD());
			System.out.println(orderItemModel.getRowSum());
		}
		
	}
}
