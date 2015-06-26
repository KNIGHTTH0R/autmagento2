package com.tools.utils;

import java.util.List;

import com.tools.data.BorrowCartCalcDetailsModel;
import com.tools.data.CalcDetailsModel;
import com.tools.data.CalculationModel;
import com.tools.data.HostCartCalcDetailsModel;
import com.tools.data.RegularCartCalcDetailsModel;
import com.tools.data.backend.OrderInfoModel;
import com.tools.data.backend.OrderItemModel;
import com.tools.data.backend.OrderTotalsModel;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.data.email.EmailModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.HostCartTotalsModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.RegularUserCartProductModel;
import com.tools.data.frontend.RegularUserCartTotalsModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.data.newsletter.SubscriberModel;
import com.tools.data.soap.DBStylistModel;

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

	// TODO print all fields here
	public static void printListDbStylists(List<DBStylistModel> list) {
		System.out.println(" *** Print List from Cart *** ");
		for (DBStylistModel dBStylistModel : list) {
			System.out.println("------------------------");

			System.out.println(dBStylistModel.getStatus());
			System.out.println(dBStylistModel.getEmail());
			System.out.println(dBStylistModel.getFirstName());
			System.out.println(dBStylistModel.getDistanceFromCoordinates());
		}
	}

	public static void printListBasicProductModel(List<BasicProductModel> list) {
		System.out.println(" *** Print List from Cart *** ");
		System.out.println("list size is : " + list.size());
		for (BasicProductModel cartProductModel : list) {
			System.out.println("------------------------");
			System.out.println(cartProductModel.getName());
			System.out.println(cartProductModel.getProdCode());
			System.out.println(cartProductModel.getQuantity());
			System.out.println(cartProductModel.getUnitPrice());
			System.out.println(cartProductModel.getProductsPrice());
			System.out.println(cartProductModel.getFinalPrice());
			System.out.println(cartProductModel.getPriceIP());
			System.out.println(cartProductModel.getDiscountClass());
		}
	}

	public static void printListRegularBasicProductModel(List<RegularBasicProductModel> list) {
		System.out.println(" *** Print List from Cart *** ");
		System.out.println("list size is : " + list.size());
		for (RegularBasicProductModel cartProductModel : list) {
			System.out.println("------------------------");
			System.out.println(cartProductModel.getName());
			System.out.println(cartProductModel.getProdCode());
			System.out.println(cartProductModel.getQuantity());
			System.out.println(cartProductModel.getUnitPrice());
			System.out.println(cartProductModel.getFinalPrice());
			System.out.println(cartProductModel.getBonusType());
			System.out.println(cartProductModel.getBunosValue());

		}
	}

	public static void printListHostBasicProductModel(List<HostBasicProductModel> list) {
		System.out.println(" *** Print List from Cart *** ");
		System.out.println("list size is : " + list.size());
		for (HostBasicProductModel cartProductModel : list) {
			System.out.println("------------------------");
			System.out.println(cartProductModel.getName());
			System.out.println(cartProductModel.getProdCode());
			System.out.println(cartProductModel.getQuantity());
			System.out.println(cartProductModel.getUnitPrice());
			System.out.println(cartProductModel.getFinalPrice());
			System.out.println(cartProductModel.getIpPoints());
			System.out.println(cartProductModel.getBonusType());
			System.out.println(cartProductModel.getBunosValue());

		}
	}

	public static void printListRegularCartProductModel(List<RegularUserCartProductModel> list) {
		System.out.println(" *** Print List from Cart *** ");
		System.out.println("list size is : " + list.size());
		for (RegularUserCartProductModel cartProductModel : list) {
			System.out.println("------------------------");
			System.out.println(cartProductModel.getName());
			System.out.println(cartProductModel.getProdCode());
			System.out.println(cartProductModel.getQuantity());
			System.out.println(cartProductModel.getUnitPrice());
			System.out.println(cartProductModel.getFinalPrice());

		}
	}

	public static void printListHostCartProductModel(List<HostCartProductModel> list) {
		System.out.println(" *** Print List from Cart *** ");
		System.out.println("list size is : " + list.size());
		for (HostCartProductModel cartProductModel : list) {
			System.out.println("------------------------");
			System.out.println(cartProductModel.getName());
			System.out.println(cartProductModel.getProdCode());
			System.out.println(cartProductModel.getQuantity());
			System.out.println(cartProductModel.getUnitPrice());
			System.out.println(cartProductModel.getFinalPrice());
			System.out.println(cartProductModel.getIpPoints());

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
		System.out.println("subtotal " + model.getSubtotal());
		System.out.println("jewerly bonus " + model.getJewelryBonus());
		System.out.println("marketing bonus " + model.getMarketingBonus());
		for (String discountNow : model.getDiscountsMap().keySet()) {
			System.out.println("Discount " + discountNow + ": " + model.getDiscountsMap().get(discountNow));
		}
		System.out.println("tax " + model.getTax());
		System.out.println("shiping " + model.getShipping());
		System.out.println("total amount " + model.getTotalAmount());
		System.out.println("ip " + model.getIpPoints());
	}

	public static void printSubscriberData(SubscriberModel model) {
		System.out.println(" *** Print Subscriber configuration *** ");
		System.out.println("------------------------");
		System.out.println("email: " + model.getEmail());
		System.out.println("activated at: " + model.getActivatedAt());
		System.out.println("country: " + model.getCountry());
		System.out.println("revenue 3 months: " + model.getRevenue3Months());
		System.out.println("revenue 1 year: " + model.getRevenue1Year());
		System.out.println("is stylist: " + model.getIsStylist());
		System.out.println("last Purchase: " + model.getLastPurchase());
		System.out.println("registration date: " + model.getRegistrationDate());
		System.out.println("preffered website: " + model.getPreferredWebsite());
		System.out.println("product sku: " + model.getProductSku());
		System.out.println("contact or user: " + model.getContactOrUser());
		System.out.println("revenue 6 months: " + model.getRevenu6Months());
		System.out.println("Customer used a kobo voucher:" +model.getKoboUsed());
		System.out.println("FirstName:" +model.getFirstName());
		System.out.println("LastName:" +model.getLastName());
		System.out.println("SC is subscribed to kobo:" +model.getKoboCode());
		System.out.println("SC sponsor Name:" +model.getSCsponsorName());
		System.out.println("SC sponsor email:" +model.getSCsponsorEmail());
		System.out.println("SC sponsor phone:" +model.getSCsponsorPhone());
		System.out.println("SC sponsor shop:" +model.getSCsponsorShop());
		System.out.println("Product Name:" +model.getProductName());
		System.out.println("Customer used kobo voucher:" +model.getCustKobo());
		System.out.println("Flag stylist:" +model.getFlagStylist());
		System.out.println("Flag host:" +model.getFlagHost());

	}
	public static void printCustomerSubscriberData(SubscriberModel model) {
		System.out.println(" *** Print Subscriber configuration *** ");
		System.out.println("------------------------");
		System.out.println("email: " + model.getEmail());
		System.out.println("country: " + model.getCountry());
		System.out.println("revenue 3 months: " + model.getRevenue3Months());
		System.out.println("revenue 1 year: " + model.getRevenue1Year());
		System.out.println("is stylist: " + model.getIsStylist());
		System.out.println("last Purchase: " + model.getLastPurchase());
		System.out.println("preffered website: " + model.getPreferredWebsite());
		System.out.println("product sku: " + model.getProductSku());
		System.out.println("contact or user: " + model.getContactOrUser());
		System.out.println("revenue 6 months: " + model.getRevenu6Months());
		System.out.println("FirstName:" +model.getFirstName());
		System.out.println("LastName:" +model.getLastName());
		System.out.println("Customer used kobo voucher:" +model.getCustKobo());
		System.out.println("Flag stylist:" +model.getFlagStylist());
		System.out.println("Flag host:" +model.getFlagHost());
		
	}

	public static void printRegularUserCartTotalsModel(RegularUserCartTotalsModel model) {
		System.out.println(" *** Print Total section from Cart *** ");
		System.out.println("------------------------");
		System.out.println("subtotal " + model.getSubtotal());

		for (String discountNow : model.getDiscountsMap().keySet()) {
			System.out.println("Discount " + discountNow + ": " + model.getDiscountsMap().get(discountNow));
		}
		System.out.println("tax " + model.getTax());
		System.out.println("shiping " + model.getShipping());
		System.out.println("total amount " + model.getTotalAmount());

	}

	public static void printHostCartTotalsModel(HostCartTotalsModel model) {
		System.out.println(" *** Print Total section from Cart *** ");
		System.out.println("------------------------");
		System.out.println("subtotal " + model.getSubtotal());

		for (String discountNow : model.getDiscountsMap().keySet()) {
			System.out.println("Discount " + discountNow + ": " + model.getDiscountsMap().get(discountNow));
		}
		System.out.println("tax " + model.getTax());
		System.out.println("shiping " + model.getShipping());
		System.out.println("total amount " + model.getTotalAmount());

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
		// System.out.println(model.getTableType());
		// System.out.println(model.getRetailPrice());
		System.out.println(model.getAskingPrice());
		System.out.println(model.getFinalPrice());
		System.out.println(model.getIpPoints());
	}

	public static void printOrderTotals(OrderTotalsModel model) {
		System.out.println(" *** Print backend orders Totals  *** ");
		System.out.println("------------------------");
		System.out.println(model.getSubtotal());
		System.out.println(model.getShipping());
		System.out.println(model.getTax());
		System.out.println(model.getTotalAmount());
		System.out.println(model.getTotalPaid());
		System.out.println(model.getTotalRefunded());
		System.out.println(model.getTotalPayable());
		System.out.println(model.getTotalIP());
		System.out.println(model.getTotalFortyDiscounts());
		System.out.println(model.getTotalBonusJeverly());
		System.out.println(model.getTotalMarketingBonus());

		for (String discountNow : model.getDiscountsMap().keySet()) {
			System.out.println(discountNow + ": " + model.getDiscountsMap().get(discountNow));
		}
	}

	public static void printAddressModel(AddressModel dataModel) {
		System.out.println("---- PRINT Adress Model ----");
		System.out.println(dataModel.getStreetAddress());
		System.out.println(dataModel.getStreetNumber());
		System.out.println(dataModel.getPostCode());
		System.out.println(dataModel.getHomeTown());
		System.out.println(dataModel.getPhoneNumber());
		System.out.println(dataModel.getCountryName());
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

	public static void printProductsComparisonBackend(BasicProductModel basicProduct, OrderItemModel orderProduct) {
		System.out.println(basicProduct.getName() + " - " + orderProduct.getProductName());
		System.out.println(basicProduct.getUnitPrice() + " - " + orderProduct.getPrice());
		System.out.println(basicProduct.getQuantity() + " - " + orderProduct.getNumber());
		System.out.println(basicProduct.getProdCode() + " - " + orderProduct.getProductCode());
	}

	public static void printStylistPropertiesModel(StylistPropertiesModel model) {
		System.out.println(model.getType());
		System.out.println(model.getStatus());
		System.out.println(model.getJewelryreceived());
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

	public static void printCartProductModel(CartProductModel productNow) {
		System.out.println("------------------------");
		System.out.println("name " + productNow.getName());
		System.out.println("code " + productNow.getProdCode());
		System.out.println("price " + productNow.getUnitPrice());
		System.out.println("qty " + productNow.getQuantity());
		System.out.println("prices " + productNow.getProductsPrice());
		System.out.println("ip " + productNow.getPriceIP());
		System.out.println("final " + productNow.getFinalPrice());
	}

	public static void printBasicProductModel(BasicProductModel productNow) {
		System.out.println("------------------------");
		System.out.println("name " + productNow.getName());
		System.out.println("code " + productNow.getProdCode());
		System.out.println("price " + productNow.getUnitPrice());
		System.out.println("qty " + productNow.getQuantity());
		System.out.println("prices " + productNow.getProductsPrice());
		System.out.println("ip " + productNow.getPriceIP());
		System.out.println("final " + productNow.getFinalPrice());
	}

	public static void printShippingTotals(ShippingModel shippingTotals) {
		System.out.println("------------------------");
		System.out.println("SubTotal " + shippingTotals.getSubTotal());
		System.out.println("DiscountPrice " + shippingTotals.getDiscountPrice());
		System.out.println("ShippingPrice " + shippingTotals.getShippingPrice());
		System.out.println("TotalAmount " + shippingTotals.getTotalAmount());
	}

	public static void printProductBasicModel(ProductBasicModel productNow) {

		System.out.println("PBM -----------------------------");
		System.out.println("Name : " + productNow.getName());
		System.out.println("Price : " + productNow.getPrice());
		System.out.println("Qty : " + productNow.getQuantity());
		System.out.println("Type: " + productNow.getType());

	}

	public static void printCalcDetailsModel(CalcDetailsModel calculatedDetailsModel) {
		System.out.println("CDM -----------------------------");
		System.out.println("jewelryBonus : " + calculatedDetailsModel.getJewelryBonus());
		System.out.println("marketingBonus : " + calculatedDetailsModel.getMarketingBonus());
		System.out.println("totalAmount : " + calculatedDetailsModel.getTotalAmount());
		System.out.println("subTotal : " + calculatedDetailsModel.getSubTotal());
		System.out.println("ipPoints : " + calculatedDetailsModel.getIpPoints());
		System.out.println("tax : " + calculatedDetailsModel.getTax());
		System.out.println("Segments : " + calculatedDetailsModel.getSegments());
		System.out.println("Calculations : " + calculatedDetailsModel.getCalculations());

	}

	public static void printRegularCartCalcDetailsModel(RegularCartCalcDetailsModel calculatedDetailsModel) {
		System.out.println("printRegularCartCalcDetailsModel -----------------------------");

		System.out.println("totalAmount : " + calculatedDetailsModel.getTotalAmount());
		System.out.println("subTotal : " + calculatedDetailsModel.getSubTotal());
		System.out.println("tax : " + calculatedDetailsModel.getTax());
		System.out.println("Segments : " + calculatedDetailsModel.getSegments());

	}
	public static void printBorrowCartCalcDetailsModel(BorrowCartCalcDetailsModel calculatedDetailsModel) {
		System.out.println("printRegularCartCalcDetailsModel -----------------------------");
		
		System.out.println("totalAmount : " + calculatedDetailsModel.getTotalAmount());
		System.out.println("subTotal : " + calculatedDetailsModel.getSubTotal());
		System.out.println("tax : " + calculatedDetailsModel.getTax());
		System.out.println("tax : " + calculatedDetailsModel.getIpPoints());
	
		
	}

	public static void printHostCartCalcDetailsModel(HostCartCalcDetailsModel calculatedDetailsModel) {
		System.out.println("printRegularCartCalcDetailsModel -----------------------------");

		System.out.println("totalAmount : " + calculatedDetailsModel.getTotalAmount());
		System.out.println("subTotal : " + calculatedDetailsModel.getSubTotal());
		System.out.println("tax : " + calculatedDetailsModel.getTax());
		System.out.println("ip : " + calculatedDetailsModel.getIpPoints());
		System.out.println("Segments : " + calculatedDetailsModel.getSegments());

	}
}
