package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.poc.ActivateBuyGet1ForPlaceACustomerOrder;
import com.poc.DeactivateBuy3Get1ForPlaceACustomerOrder;
import com.tests.uss11.us11002.US11002OrderForCustomerAsPartyHostTest;
import com.tests.uss11.us11002.US11002UserProfileOrderIdTest;
import com.tests.uss11.us11002.US11002ValidateOrderBackOfficeTest;
import com.tests.uss11.us11002.US11002ValidateOrderEmailTest;

@SuiteClasses({
	ActivateBuyGet1ForPlaceACustomerOrder.class,
	US11002OrderForCustomerAsPartyHostTest.class,
	US11002UserProfileOrderIdTest.class,	
	US11002ValidateOrderEmailTest.class,	
	US11002ValidateOrderBackOfficeTest.class,
	DeactivateBuy3Get1ForPlaceACustomerOrder.class,
})
@RunWith(Suite.class)
public class US11002Suite {

}
