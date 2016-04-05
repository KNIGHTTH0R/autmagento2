package com.tools.datahandler;

import java.util.ArrayList;
import java.util.List;

import com.tools.data.UrlModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.RegularUserCartProductModel;
import com.tools.data.frontend.RegularUserCartTotalsModel;
import com.tools.data.frontend.ShippingModel;

public class RegularUserDataGrabber {

	public static UrlModel urlModel = new UrlModel();
	public static OrderModel orderModel = new OrderModel();
	public static OrderModel orderModelTp1 = new OrderModel();
	public static OrderModel orderModelTp2 = new OrderModel();

	public static RegularUserCartTotalsModel regularUserGrabbedCartTotals = new RegularUserCartTotalsModel();
	public static ShippingModel regularUserShippingTotals = new ShippingModel();
	public static ShippingModel regularUserShippingTotalsTp0 = new ShippingModel();
	public static ShippingModel regularUserShippingTotalsTp1 = new ShippingModel();
	public static ShippingModel regularUserShippingTotalsTp2 = new ShippingModel();
	public static ShippingModel regularUserConfirmationTotals = new ShippingModel();
	public static ShippingModel regularUserConfirmationTotalsTp0 = new ShippingModel();
	public static ShippingModel regularUserConfirmationTotalsTp1 = new ShippingModel();
	public static ShippingModel regularUserConfirmationTotalsTp2 = new ShippingModel();
	public static List<RegularUserCartProductModel> grabbedRegularCartProductsList = new ArrayList<RegularUserCartProductModel>();
	public static List<RegularUserCartProductModel> grabbedRegularShippingProductsList = new ArrayList<RegularUserCartProductModel>();
	public static List<RegularUserCartProductModel> grabbedRegularShippingProductsListTp0 = new ArrayList<RegularUserCartProductModel>();
	public static List<RegularUserCartProductModel> grabbedRegularShippingProductsListTp1 = new ArrayList<RegularUserCartProductModel>();
	public static List<RegularUserCartProductModel> grabbedRegularShippingProductsListTp2 = new ArrayList<RegularUserCartProductModel>();
	public static List<RegularUserCartProductModel> grabbedRegularConfirmationProductsList = new ArrayList<RegularUserCartProductModel>();
	public static List<RegularUserCartProductModel> grabbedRegularConfirmationProductsListTp0 = new ArrayList<RegularUserCartProductModel>();
	public static List<RegularUserCartProductModel> grabbedRegularConfirmationProductsListTp1 = new ArrayList<RegularUserCartProductModel>();
	public static List<RegularUserCartProductModel> grabbedRegularConfirmationProductsListTp2 = new ArrayList<RegularUserCartProductModel>();

	public static void wipe() {

		urlModel = new UrlModel();
		orderModel = new OrderModel();
		orderModelTp1 = new OrderModel();
		orderModelTp2 = new OrderModel();

		regularUserGrabbedCartTotals = new RegularUserCartTotalsModel();
		grabbedRegularCartProductsList = new ArrayList<RegularUserCartProductModel>();
		grabbedRegularShippingProductsList = new ArrayList<RegularUserCartProductModel>();
		grabbedRegularShippingProductsListTp0 = new ArrayList<RegularUserCartProductModel>();
		grabbedRegularShippingProductsListTp1 = new ArrayList<RegularUserCartProductModel>();
		grabbedRegularShippingProductsListTp2 = new ArrayList<RegularUserCartProductModel>();
		regularUserShippingTotals = new ShippingModel();
		regularUserShippingTotalsTp0 = new ShippingModel();
		regularUserShippingTotalsTp1 = new ShippingModel();
		regularUserShippingTotalsTp2 = new ShippingModel();
		regularUserConfirmationTotals = new ShippingModel();
		regularUserConfirmationTotalsTp0 = new ShippingModel();
		regularUserConfirmationTotalsTp1 = new ShippingModel();
		regularUserConfirmationTotalsTp2 = new ShippingModel();
		grabbedRegularConfirmationProductsList = new ArrayList<RegularUserCartProductModel>();
		grabbedRegularConfirmationProductsListTp0 = new ArrayList<RegularUserCartProductModel>();
		grabbedRegularConfirmationProductsListTp1 = new ArrayList<RegularUserCartProductModel>();
		grabbedRegularConfirmationProductsListTp2 = new ArrayList<RegularUserCartProductModel>();

	}

}
