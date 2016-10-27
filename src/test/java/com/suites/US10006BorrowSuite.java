package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.us10006.US10006ChechEmailAndAcceptInvitationTest;
import com.tests.uss10.us10006.US10006CheckPartyWishlistAndBorrowProductTest;
import com.tests.uss10.us10006.US10006CreatePartyWithNewContactHostTest;
import com.tests.uss10.us10006.US10006CustomerAddProductIntoWishlistTest;

@SuiteClasses({	
	US10006CreatePartyWithNewContactHostTest.class,
	US10006ChechEmailAndAcceptInvitationTest.class,
	US10006CustomerAddProductIntoWishlistTest.class,	
	US10006CheckPartyWishlistAndBorrowProductTest.class,
	
})
@RunWith(Suite.class)
public class US10006BorrowSuite {

}
