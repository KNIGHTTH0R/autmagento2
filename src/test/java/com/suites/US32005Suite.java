package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss32.us32005.US32005ActivateTermPurchaseOnlyForNotAvailableTest;
import com.tests.uss32.us32005.US32005ClosePartyTest;
import com.tests.uss32.us32005.US32005PlaceCustomerOrderForNotAllowedStylistTest;
import com.tests.uss32.us32005.US32005PlaceHostOrderForNotAllowedStylistTest;
import com.tests.uss32.us32005.US32005RegularOrderForNotAllowedCustomerTest;

@SuiteClasses({
	US32005ActivateTermPurchaseOnlyForNotAvailableTest.class,
	US32005RegularOrderForNotAllowedCustomerTest.class,
	US32005PlaceCustomerOrderForNotAllowedStylistTest.class,
	US32005ClosePartyTest.class,
	US32005PlaceHostOrderForNotAllowedStylistTest.class

	
})
@RunWith(Suite.class)
public class US32005Suite {

}
