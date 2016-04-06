package com.tools.datahandler;

import java.util.ArrayList;
import java.util.List;

import com.tools.data.UrlModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.HostCartProductModel;
import com.tools.data.frontend.HostCartTotalsModel;
import com.tools.data.frontend.ShippingModel;

public class HostDataGrabber {

	public static UrlModel urlModel = new UrlModel();
	public static OrderModel orderModel = new OrderModel();
	public static OrderModel orderModelTp1 = new OrderModel();
	public static OrderModel orderModelTp2 = new OrderModel();

	public static HostCartTotalsModel hostGrabbedCartTotals = new HostCartTotalsModel();
	public static ShippingModel hostShippingTotals = new ShippingModel();
	public static ShippingModel hostShippingTotalsTp0 = new ShippingModel();
	public static ShippingModel hostShippingTotalsTp1 = new ShippingModel();
	public static ShippingModel hostShippingTotalsTp2 = new ShippingModel();
	public static ShippingModel hostConfirmationTotals = new ShippingModel();
	public static ShippingModel hostConfirmationTotalsTp0 = new ShippingModel();
	public static ShippingModel hostConfirmationTotalsTp1 = new ShippingModel();
	public static ShippingModel hostConfirmationTotalsTp2 = new ShippingModel();
	public static List<HostCartProductModel> grabbedHostCartProductsList = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostShippingProductsList = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostShippingProductsListTp0 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostShippingProductsListTp1 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostShippingProductsListTp2 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostConfirmationProductsList = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostConfirmationProductsListTp0 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostConfirmationProductsListTp1 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostConfirmationProductsListTp2 = new ArrayList<HostCartProductModel>();

	public static void wipe() {

		urlModel = new UrlModel();
		orderModel = new OrderModel();
		orderModelTp1 = new OrderModel();
		orderModelTp2 = new OrderModel();
		
		hostGrabbedCartTotals = new HostCartTotalsModel();
		grabbedHostCartProductsList = new ArrayList<HostCartProductModel>();
		grabbedHostShippingProductsList = new ArrayList<HostCartProductModel>();
		grabbedHostShippingProductsListTp0 = new ArrayList<HostCartProductModel>();
		grabbedHostShippingProductsListTp1 = new ArrayList<HostCartProductModel>();
		grabbedHostShippingProductsListTp2 = new ArrayList<HostCartProductModel>();
		hostShippingTotals = new ShippingModel();
		hostShippingTotalsTp0 = new ShippingModel();
		hostShippingTotalsTp1 = new ShippingModel();
		hostShippingTotalsTp2 = new ShippingModel();
		hostConfirmationTotals = new ShippingModel();
		hostConfirmationTotalsTp0 = new ShippingModel();
		hostConfirmationTotalsTp1 = new ShippingModel();
		hostConfirmationTotalsTp2 = new ShippingModel();
		grabbedHostConfirmationProductsList = new ArrayList<HostCartProductModel>();
		grabbedHostConfirmationProductsListTp0 = new ArrayList<HostCartProductModel>();
		grabbedHostConfirmationProductsListTp1 = new ArrayList<HostCartProductModel>();
		grabbedHostConfirmationProductsListTp2 = new ArrayList<HostCartProductModel>();

	}

}
