package com.tools.datahandler;

import java.util.ArrayList;
import java.util.List;

import com.tools.data.UrlModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.BorrowCartTotalsModel;
import com.tools.data.frontend.BorrowedCartModel;
import com.tools.data.frontend.ShippingModel;

public class BorrowDataGrabber {

	public static UrlModel urlModel = new UrlModel();
	public static OrderModel orderModel = new OrderModel();

	public static BorrowCartTotalsModel borrowCartGrabbedCartTotals = new BorrowCartTotalsModel();
	public static ShippingModel borrowCartShippingTotals = new ShippingModel();
	public static ShippingModel borrowCartConfirmationTotals = new ShippingModel();
	public static List<BorrowedCartModel> grabbedBorrowCartProductsList = new ArrayList<BorrowedCartModel>();
	public static List<BorrowedCartModel> grabbedBorrowShippingProductsList = new ArrayList<BorrowedCartModel>();
	public static List<BorrowedCartModel> grabbedBorrowConfirmationProductsList = new ArrayList<BorrowedCartModel>();

	public static void wipe() {

		urlModel = new UrlModel();
		orderModel = new OrderModel();
		borrowCartGrabbedCartTotals = new BorrowCartTotalsModel();
		borrowCartShippingTotals = new ShippingModel();
		borrowCartConfirmationTotals = new ShippingModel();
		grabbedBorrowCartProductsList = new ArrayList<BorrowedCartModel>();
		grabbedBorrowShippingProductsList = new ArrayList<BorrowedCartModel>();
		grabbedBorrowConfirmationProductsList = new ArrayList<BorrowedCartModel>();

	}
}
