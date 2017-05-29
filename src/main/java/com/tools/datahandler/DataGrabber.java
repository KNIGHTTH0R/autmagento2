package com.tools.datahandler;

import java.util.ArrayList;
import java.util.List;

import com.tools.data.UrlModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.ShippingModel;

public class DataGrabber {

	// Extras
	public static UrlModel urlModel = new UrlModel();
	public static OrderModel orderModel = new OrderModel();
	public static OrderModel orderModelTp1 = new OrderModel();
	public static OrderModel orderModelTp2 = new OrderModel();
	public static OrderModel orderModelTp3=new OrderModel();
	public static OrderModel orderModelTp4=new OrderModel();
	public static OrderModel orderModelTp5=new OrderModel();
	public static OrderModel orderModelTp6=new OrderModel();

	// Phase One
	public static List<CartProductModel> cartProductsWith50Discount = new ArrayList<CartProductModel>();
	public static List<CartProductModel> cartProductsWith25Discount = new ArrayList<CartProductModel>();
	public static List<CartProductModel> cartMarketingMaterialsProducts = new ArrayList<CartProductModel>();

	// Phase Two
	public static List<CartProductModel> cartProductsWith50DiscountDiscounted = new ArrayList<CartProductModel>();
	public static List<CartProductModel> cartProductsWith25DiscountDiscounted = new ArrayList<CartProductModel>();
	public static List<CartProductModel> cartMarketingMaterialsProductsDiscounted = new ArrayList<CartProductModel>();
	public static List<CartProductModel> cartProductsList25AndMm = new ArrayList<CartProductModel>();
	public static CartTotalsModel cartTotals = new CartTotalsModel();

	// Phase Three
	public static ShippingModel shippingTotals = new ShippingModel();
	public static ShippingModel shippingTotalsTp0 = new ShippingModel();
	public static ShippingModel shippingTotalsTp1 = new ShippingModel();
	public static ShippingModel shippingTotalsTp2 = new ShippingModel();
	public static ShippingModel shippingTotalsTp3 = new ShippingModel();
	public static List<CartProductModel> shippingProducts = new ArrayList<CartProductModel>();
	public static List<CartProductModel> shippingProductsTp1 = new ArrayList<CartProductModel>();
	public static List<CartProductModel> shippingProductsTp2 = new ArrayList<CartProductModel>();

	// Phase Four
	public static List<CartProductModel> confirmationProducts = new ArrayList<CartProductModel>();
	public static List<CartProductModel> confirmationProductsTp1 = new ArrayList<CartProductModel>();
	public static List<CartProductModel> confirmationProductsTp2 = new ArrayList<CartProductModel>();
	public static AddressModel grabbedBillingAddress = new AddressModel();
	public static AddressModel grabbedShippingAddress = new AddressModel();

	public static ShippingModel confirmationTotals = new ShippingModel();
	public static ShippingModel confirmationTotalsTp0 = new ShippingModel();
	public static ShippingModel confirmationTotalsTp1 = new ShippingModel();
	public static ShippingModel confirmationTotalsTp2 = new ShippingModel();
	public static ShippingModel confirmationTotalsTp3 = new ShippingModel();

	public static ShippingModel sfmShippingTotalsTp0 = new ShippingModel();
	public static ShippingModel sfmShippingTotalsTp1 = new ShippingModel();
	public static ShippingModel sfmShippingTotalsTp2 = new ShippingModel();
	public static ShippingModel sfmShippingTotalsTp3 = new ShippingModel();

	public static List<CartProductModel> grabbedSFMConfirmationProductsListTp0 = new ArrayList<CartProductModel>();
	public static List<CartProductModel> grabbedSFMConfirmationProductsListTp1 = new ArrayList<CartProductModel>();
	public static List<CartProductModel> grabbedSFMConfirmationProductsListTp2 = new ArrayList<CartProductModel>();
	public static List<CartProductModel> grabbedSFMConfirmationProductsListTp3 = new ArrayList<CartProductModel>();

	public static List<CartProductModel> grabbedSFMShippingProductsListTp0=new ArrayList<CartProductModel>(); 
	public static List<CartProductModel> grabbedSFMShippingProductsListTp1=new ArrayList<CartProductModel>(); 
	public static List<CartProductModel> grabbedSFMShippingProductsListTp2=new ArrayList<CartProductModel>(); 
	public static List<CartProductModel> grabbedSFMShippingProductsListTp3=new ArrayList<CartProductModel>();
	
	public static ShippingModel sfmConfirmationTotalsTp0=new ShippingModel();
	public static ShippingModel sfmConfirmationTotalsTp1=new ShippingModel();
	public static ShippingModel sfmConfirmationTotalsTp2=new ShippingModel();
	public static ShippingModel sfmConfirmationTotalsTp3=new ShippingModel();
	
	
	
	public static void wipe() {
		urlModel = new UrlModel();
		orderModel = new OrderModel();

		// Phase One
		cartProductsWith50Discount = new ArrayList<CartProductModel>();
		cartProductsWith25Discount = new ArrayList<CartProductModel>();
		cartMarketingMaterialsProducts = new ArrayList<CartProductModel>();
		cartProductsList25AndMm = new ArrayList<CartProductModel>();

		// Phase Two
		cartProductsWith50DiscountDiscounted = new ArrayList<CartProductModel>();
		cartProductsWith25DiscountDiscounted = new ArrayList<CartProductModel>();
		cartMarketingMaterialsProductsDiscounted = new ArrayList<CartProductModel>();
		cartTotals = new CartTotalsModel();

		// Phase Three
		shippingTotals = new ShippingModel();
		shippingTotalsTp0 = new ShippingModel();
		shippingTotalsTp1 = new ShippingModel();
		shippingTotalsTp2 = new ShippingModel();
		shippingProducts = new ArrayList<CartProductModel>();
		shippingProductsTp1 = new ArrayList<CartProductModel>();
		shippingProductsTp2 = new ArrayList<CartProductModel>();

		// Phase Four
		confirmationProducts = new ArrayList<CartProductModel>();
		confirmationProductsTp1 = new ArrayList<CartProductModel>();
		confirmationProductsTp2 = new ArrayList<CartProductModel>();
		confirmationTotals = new ShippingModel();
		confirmationTotalsTp0 = new ShippingModel();
		confirmationTotalsTp1 = new ShippingModel();
		confirmationTotalsTp2 = new ShippingModel();

		grabbedBillingAddress = new AddressModel();
		grabbedShippingAddress = new AddressModel();
	}

	public static void addAll25AndMmProducts() {
		cartProductsList25AndMm.addAll(cartProductsWith25Discount);
		cartProductsList25AndMm.addAll(cartMarketingMaterialsProducts);
	}

}
