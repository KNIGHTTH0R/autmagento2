package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.us10006.US10006CheckPartyWishlistAndBorrowProductTest;
import com.tests.uss10.us10006.US10006CustomerAddProductIntoWishlistTest;
import com.tests.uss10.us10006.US10006OrderForCustomerAsPartyHostTest;
import com.tests.uss10.us10006.US10006VerifyInviteForStylistHostEmailTest;

@SuiteClasses({	
	US10006VerifyInviteForStylistHostEmailTest.class,
	US10006OrderForCustomerAsPartyHostTest.class,
//	US10006MarkPlaceCustomerOrderAsPaidTest.class,
//	US10006VerifyDashboardAndJbHistoryCompleteOrderTest.class,
	US10006CustomerAddProductIntoWishlistTest.class,	
	US10006CheckPartyWishlistAndBorrowProductTest.class,
	
})
@RunWith(Suite.class)
public class US10006Suite {

}
