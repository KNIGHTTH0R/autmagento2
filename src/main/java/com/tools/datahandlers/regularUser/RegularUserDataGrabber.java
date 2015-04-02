package com.tools.datahandlers.regularUser;

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

	public static RegularUserCartTotalsModel regularUserGrabbedCartTotals = new RegularUserCartTotalsModel();
	public static ShippingModel regularUserShippingTotals = new ShippingModel();
	public static ShippingModel regularUserConfirmationTotals = new ShippingModel();
	public static List<RegularUserCartProductModel> grabbedRegularCartProductsList = new ArrayList<RegularUserCartProductModel>();
	public static List<RegularUserCartProductModel> grabbedRegularShippingProductsList = new ArrayList<RegularUserCartProductModel>();
	public static List<RegularUserCartProductModel> grabbedRegularConfirmationProductsList = new ArrayList<RegularUserCartProductModel>();

	public static void wipe() {

		urlModel = new UrlModel();
		orderModel = new OrderModel();

		regularUserGrabbedCartTotals = new RegularUserCartTotalsModel();
		grabbedRegularCartProductsList = new ArrayList<RegularUserCartProductModel>();
		grabbedRegularShippingProductsList = new ArrayList<RegularUserCartProductModel>();
		regularUserShippingTotals = new ShippingModel();
		regularUserConfirmationTotals = new ShippingModel();
		grabbedRegularConfirmationProductsList = new ArrayList<RegularUserCartProductModel>();

	}

}
