package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.us10006.US10006ChechEmailAndAcceptInvitationTest;
import com.tests.uss10.us10006.US10006ChechPartyInvitationEmailTest;
import com.tests.uss10.us10006.US10006CheckPartyWishlistAndBorrowProductTest;
import com.tests.uss10.us10006.US10006CheckTpProductAreNotOnPartyWishlistTest;
import com.tests.uss10.us10006.US10006ConfirmCustomerTest;
import com.tests.uss10.us10006.US10006CreatePartyWithNewContactHostTest;
import com.tests.uss10.us10006.US10006CustomerAddProductIntoWishlistTest;
import com.tests.uss10.us10006.US10006CustomerAddsTpProductIntoWishlistTest;
import com.tests.uss10.us10006.US10006InviteFriendsToPartyTest;
import com.tests.uss10.us10006.US10006MarkPlaceCustomerOrderAsPaidTest;
import com.tests.uss10.us10006.US10006OrderForCustomerAsPartyHostTest;
import com.tests.uss10.us10006.US10006RegisterFromInviteToPartyEmailTest;
import com.tests.uss10.us10006.US10006VerifyDashboardAndJbHistoryCompleteOrderTest;

@SuiteClasses({	
	US10006CreatePartyWithNewContactHostTest.class,
	US10006ChechEmailAndAcceptInvitationTest.class,
	US10006CustomerAddsTpProductIntoWishlistTest.class,
	US10006CheckTpProductAreNotOnPartyWishlistTest.class,
	US10006CustomerAddProductIntoWishlistTest.class,	
	US10006CheckPartyWishlistAndBorrowProductTest.class,
	US10006RegisterFromInviteToPartyEmailTest.class,
	US10006ConfirmCustomerTest.class,
	US10006InviteFriendsToPartyTest.class,
	US10006ChechPartyInvitationEmailTest.class,
	US10006OrderForCustomerAsPartyHostTest.class,
	US10006MarkPlaceCustomerOrderAsPaidTest.class,
	US10006VerifyDashboardAndJbHistoryCompleteOrderTest.class,
	
})
@RunWith(Suite.class)
public class US10006Suite {

}
