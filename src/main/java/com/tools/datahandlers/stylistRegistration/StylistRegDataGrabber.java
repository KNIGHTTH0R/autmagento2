package com.tools.datahandlers.stylistRegistration;

import com.pages.frontend.checkout.cart.stylistRegistration.StylistRegistrationCartTotalModel;

public class StylistRegDataGrabber {

	public static StylistRegistrationCartTotalModel cartTotals = new StylistRegistrationCartTotalModel();

	public static void wipe() {
		cartTotals = new StylistRegistrationCartTotalModel();
	}

}
