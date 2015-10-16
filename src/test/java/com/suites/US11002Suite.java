package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.poc.ActivateBuyGet1ForPlaceACustomerOrderTest;
import com.poc.DeactivateBuy3Get1ForPlaceACustomerOrderTest;
import com.tests.uss11.us11002.US11002PartyHostBuysForCustomerWithBuy3Get1Test;
import com.tests.uss11.us11002.US11002CheckOrderOnStylecoachProfileTest;
import com.tests.uss11.us11002.US11002ValidateOrderBackOfficeTest;
import com.tests.uss11.us11002.US11002ValidateOrderEmailTest;

@SuiteClasses({
	ActivateBuyGet1ForPlaceACustomerOrderTest.class,
	US11002PartyHostBuysForCustomerWithBuy3Get1Test.class,
	US11002CheckOrderOnStylecoachProfileTest.class,	
	US11002ValidateOrderEmailTest.class,	
	US11002ValidateOrderBackOfficeTest.class,
	DeactivateBuy3Get1ForPlaceACustomerOrderTest.class,
})
@RunWith(Suite.class)
public class US11002Suite {

}
