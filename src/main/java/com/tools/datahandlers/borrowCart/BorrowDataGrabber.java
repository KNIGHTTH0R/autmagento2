package com.tools.datahandlers.borrowCart;

import java.util.ArrayList;
import java.util.List;

import com.tools.data.UrlModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.BorrowProductModel;
import com.tools.data.frontend.BorrowCartTotalsModel;
import com.tools.data.frontend.ShippingModel;

public class BorrowDataGrabber {

	public static UrlModel urlModel = new UrlModel();
	public static OrderModel orderModel = new OrderModel();

	public static BorrowCartTotalsModel borrowCartGrabbedCartTotals = new BorrowCartTotalsModel();
	public static ShippingModel borrowCartShippingTotals = new ShippingModel();
	public static ShippingModel borrowCartConfirmationTotals = new ShippingModel();
	public static List<BorrowProductModel> grabbedBorrowCartProductsList = new ArrayList<BorrowProductModel>();
	public static List<BorrowProductModel> grabbedBorrowShippingProductsList = new ArrayList<BorrowProductModel>();
	public static List<BorrowProductModel> grabbedBorrowConfirmationProductsList = new ArrayList<BorrowProductModel>();

	public static void wipe() {

		urlModel = new UrlModel();
		orderModel = new OrderModel();
		borrowCartGrabbedCartTotals = new BorrowCartTotalsModel();
		borrowCartShippingTotals = new ShippingModel();
		borrowCartConfirmationTotals = new ShippingModel();
		grabbedBorrowCartProductsList = new ArrayList<BorrowProductModel>();
		grabbedBorrowShippingProductsList = new ArrayList<BorrowProductModel>();
		grabbedBorrowConfirmationProductsList = new ArrayList<BorrowProductModel>();

	}
}
