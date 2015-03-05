package com.tools.datahandlers;

import java.util.ArrayList;
import java.util.List;

import com.tools.data.UrlModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.ShippingModel;

public class DataGrabber {

	// Extras
	public static UrlModel urlModel = new UrlModel();
	public static OrderModel orderModel = new OrderModel();

	// Phase One
	public static List<CartProductModel> cartProductsWith50Discount = new ArrayList<CartProductModel>();
	public static List<CartProductModel> cartProductsWith25Discount = new ArrayList<CartProductModel>();
	public static List<CartProductModel> cartMarketingMaterialsProducts = new ArrayList<CartProductModel>();

	// Phase Two
	public static List<CartProductModel> cartProductsWith50DiscountDiscounted = new ArrayList<CartProductModel>();
	public static List<CartProductModel> cartProductsWith25DiscountDiscounted = new ArrayList<CartProductModel>();
	public static List<CartProductModel> cartMarketingMaterialsProductsDiscounted = new ArrayList<CartProductModel>();
	public static CartTotalsModel cartTotals = new CartTotalsModel();

	// Phase Three
	public static ShippingModel shippingTotals = new ShippingModel();
	public static List<CartProductModel> shippingProducts = new ArrayList<CartProductModel>();

	// Phase Four
	public static List<CartProductModel> confirmationProducts = new ArrayList<CartProductModel>();
	public static AddressModel grabbedBillingAddress = new AddressModel();
	public static AddressModel grabbedShippingAddress = new AddressModel();

	public static ShippingModel confirmationTotals = new ShippingModel();


	public static void wipe(){
		urlModel = new UrlModel();
		orderModel = new OrderModel();

		// Phase One
		cartProductsWith50Discount = new ArrayList<CartProductModel>();
		cartProductsWith25Discount = new ArrayList<CartProductModel>();
		cartMarketingMaterialsProducts = new ArrayList<CartProductModel>();

		// Phase Two
		cartProductsWith50DiscountDiscounted = new ArrayList<CartProductModel>();
		cartProductsWith25DiscountDiscounted = new ArrayList<CartProductModel>();
		cartMarketingMaterialsProductsDiscounted = new ArrayList<CartProductModel>();
		cartTotals = new CartTotalsModel();

		// Phase Three
		shippingTotals = new ShippingModel();
		shippingProducts = new ArrayList<CartProductModel>();

		// Phase Four
		confirmationProducts = new ArrayList<CartProductModel>();
		grabbedBillingAddress = new AddressModel();
		grabbedShippingAddress = new AddressModel();

		confirmationTotals = new ShippingModel();
	}
}
