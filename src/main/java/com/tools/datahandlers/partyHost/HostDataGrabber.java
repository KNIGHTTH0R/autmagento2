package com.tools.datahandlers.partyHost;

import java.util.ArrayList;
import java.util.List;

import com.tools.data.UrlModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.HostCartTotalsModel;
import com.tools.data.frontend.RegularUserCartTotalsModel;
import com.tools.data.frontend.ShippingModel;

public class HostDataGrabber {

	public static UrlModel urlModel = new UrlModel();
	public static OrderModel orderModel = new OrderModel();

	public static HostCartTotalsModel hostGrabbedCartTotals = new HostCartTotalsModel();
	public static ShippingModel hostShippingTotals = new ShippingModel();
	public static ShippingModel hostConfirmationTotals = new ShippingModel();
	public static List<HostCartProductModel> grabbedHostCartProductsList = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostShippingProductsList = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostConfirmationProductsList = new ArrayList<HostCartProductModel>();

	public static void wipe() {

		urlModel = new UrlModel();
		orderModel = new OrderModel();

		hostGrabbedCartTotals = new HostCartTotalsModel();
		grabbedHostCartProductsList = new ArrayList<HostCartProductModel>();
		grabbedHostShippingProductsList = new ArrayList<HostCartProductModel>();
		hostShippingTotals = new ShippingModel();
		hostConfirmationTotals = new ShippingModel();
		grabbedHostConfirmationProductsList = new ArrayList<HostCartProductModel>();
		
	}

}
