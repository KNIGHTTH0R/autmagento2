package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss32.us32001.US32001ActivateTermPurchaseOnlyForNotAvailableTest;
import com.tests.uss32.us32001.US32001ClosePartyTest;
import com.tests.uss32.us32001.US32001CreatePartyWithStylistHostTest;
import com.tests.uss32.us32001.US32001PlaceCustomerOrderAllowedOnlyForNotAvailableTPTest;
import com.tests.uss32.us32001.US32001PlaceHostOrderAllowedOnlyForNotAvailableTPTest;
import com.tests.uss32.us32001.US32001RegularOrderAllowedOnlyForNotAvailableTPTest;

@SuiteClasses({
	US32001ActivateTermPurchaseOnlyForNotAvailableTest.class,
	US32001CreatePartyWithStylistHostTest.class,
	US32001RegularOrderAllowedOnlyForNotAvailableTPTest.class,
	US32001PlaceCustomerOrderAllowedOnlyForNotAvailableTPTest.class,
//	US32001ClosePartyTest.class,
//	US32001PlaceHostOrderAllowedOnlyForNotAvailableTPTest.class

	
})
@RunWith(Suite.class)
public class US32001Suite {

}
