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
	public static OrderModel orderModelTp3 = new OrderModel();
	public static OrderModel orderModelTp4 = new OrderModel();
	public static OrderModel orderModelTp5 = new OrderModel();
	public static OrderModel orderModelTp6 = new OrderModel();
	public static OrderModel orderModelTp7 = new OrderModel();
	public static OrderModel orderModelTp8 = new OrderModel();

	public static HostCartTotalsModel hostGrabbedCartTotals = new HostCartTotalsModel();
	public static ShippingModel hostShippingTotals = new ShippingModel();
	public static ShippingModel hostShippingTotalsTp0 = new ShippingModel();
	public static ShippingModel hostShippingTotalsTp1 = new ShippingModel();
	public static ShippingModel hostShippingTotalsTp2 = new ShippingModel();
	public static ShippingModel hostShippingTotalsTp3 = new ShippingModel();
	public static ShippingModel hostShippingTotalsTp4 = new ShippingModel();
	public static ShippingModel hostShippingTotalsTp5 = new ShippingModel();
	public static ShippingModel hostShippingTotalsTp6 = new ShippingModel();
	public static ShippingModel hostShippingTotalsTp7 = new ShippingModel();
	public static ShippingModel hostShippingTotalsTp8 = new ShippingModel();
	public static ShippingModel hostConfirmationTotals = new ShippingModel();
	public static ShippingModel hostConfirmationTotalsTp0 = new ShippingModel();
	public static ShippingModel hostConfirmationTotalsTp1 = new ShippingModel();
	public static ShippingModel hostConfirmationTotalsTp2 = new ShippingModel();
	public static ShippingModel hostConfirmationTotalsTp3 = new ShippingModel();
	public static ShippingModel hostConfirmationTotalsTp4 = new ShippingModel();
	public static ShippingModel hostConfirmationTotalsTp5 = new ShippingModel();
	public static ShippingModel hostConfirmationTotalsTp6= new ShippingModel();
	public static ShippingModel hostConfirmationTotalsTp7 = new ShippingModel();
	public static ShippingModel hostConfirmationTotalsTp8 = new ShippingModel();
	public static List<HostCartProductModel> grabbedHostCartProductsList = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostShippingProductsList = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostShippingProductsListTp0 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostConfirmationProductsList = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostShippingProductsListTp1 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostShippingProductsListTp2 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostShippingProductsListTp3 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostShippingProductsListTp4 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostShippingProductsListTp5 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostShippingProductsListTp6 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostShippingProductsListTp7 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostShippingProductsListTp8 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostConfirmationProductsListTp0 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostConfirmationProductsListTp1 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostConfirmationProductsListTp2 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostConfirmationProductsListTp3 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostConfirmationProductsListTp4 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostConfirmationProductsListTp5 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostConfirmationProductsListTp6 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostConfirmationProductsListTp7 = new ArrayList<HostCartProductModel>();
	public static List<HostCartProductModel> grabbedHostConfirmationProductsListTp8 = new ArrayList<HostCartProductModel>();

	public static void wipe() {

		urlModel = new UrlModel();
		orderModel = new OrderModel();
		orderModelTp1 = new OrderModel();
		orderModelTp2 = new OrderModel();
		orderModelTp3 = new OrderModel();
		orderModelTp4 = new OrderModel();
		orderModelTp5 = new OrderModel();
		orderModelTp6 = new OrderModel();
		orderModelTp7 = new OrderModel();
		orderModelTp8 = new OrderModel();
		
		hostGrabbedCartTotals = new HostCartTotalsModel();
		grabbedHostCartProductsList = new ArrayList<HostCartProductModel>();
		grabbedHostShippingProductsList = new ArrayList<HostCartProductModel>();
		grabbedHostShippingProductsListTp0 = new ArrayList<HostCartProductModel>();
		grabbedHostShippingProductsListTp1 = new ArrayList<HostCartProductModel>();
		grabbedHostShippingProductsListTp2 = new ArrayList<HostCartProductModel>();
		grabbedHostShippingProductsListTp3 = new ArrayList<HostCartProductModel>();
		grabbedHostShippingProductsListTp4 = new ArrayList<HostCartProductModel>();
		grabbedHostShippingProductsListTp5 = new ArrayList<HostCartProductModel>();
		grabbedHostShippingProductsListTp6 = new ArrayList<HostCartProductModel>();
		grabbedHostShippingProductsListTp7 = new ArrayList<HostCartProductModel>();
		grabbedHostShippingProductsListTp8= new ArrayList<HostCartProductModel>();
		hostShippingTotals = new ShippingModel();
		hostShippingTotalsTp0 = new ShippingModel();
		hostShippingTotalsTp1 = new ShippingModel();
		hostShippingTotalsTp2 = new ShippingModel();
		hostShippingTotalsTp3 = new ShippingModel();
		hostShippingTotalsTp4 = new ShippingModel();
		hostShippingTotalsTp5 = new ShippingModel();
		hostShippingTotalsTp6 = new ShippingModel();
		hostShippingTotalsTp7 = new ShippingModel();
		hostShippingTotalsTp8 = new ShippingModel();
		hostConfirmationTotals = new ShippingModel();
		hostConfirmationTotalsTp0 = new ShippingModel();
		hostConfirmationTotalsTp1 = new ShippingModel();
		hostConfirmationTotalsTp2 = new ShippingModel();
		hostConfirmationTotalsTp3 = new ShippingModel();
		hostConfirmationTotalsTp4 = new ShippingModel();
		hostConfirmationTotalsTp5 = new ShippingModel();
		hostConfirmationTotalsTp6= new ShippingModel();
		hostConfirmationTotalsTp7 = new ShippingModel();
		hostConfirmationTotalsTp8 = new ShippingModel();
		grabbedHostConfirmationProductsList = new ArrayList<HostCartProductModel>();
		grabbedHostConfirmationProductsListTp0 = new ArrayList<HostCartProductModel>();
		grabbedHostConfirmationProductsListTp1 = new ArrayList<HostCartProductModel>();
		grabbedHostConfirmationProductsListTp2 = new ArrayList<HostCartProductModel>();
		grabbedHostConfirmationProductsListTp3 = new ArrayList<HostCartProductModel>();
		grabbedHostConfirmationProductsListTp4 = new ArrayList<HostCartProductModel>();
		grabbedHostConfirmationProductsListTp5 = new ArrayList<HostCartProductModel>();
		grabbedHostConfirmationProductsListTp6 = new ArrayList<HostCartProductModel>();
		grabbedHostConfirmationProductsListTp7 = new ArrayList<HostCartProductModel>();
		grabbedHostConfirmationProductsListTp8 = new ArrayList<HostCartProductModel>();

	}

}
