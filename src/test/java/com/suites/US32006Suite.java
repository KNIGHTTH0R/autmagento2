package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss32.us32006.US32006ActivateTermPurchaseForAllProductsTest;
import com.tests.uss32.us32006.US32006ClosePartyTest;
import com.tests.uss32.us32006.US32006PlaceCustomerOrderForNotAllowedStylistTest;
import com.tests.uss32.us32006.US32006PlaceHostOrderForNotAllowedStylistTest;
import com.tests.uss32.us32006.US32006RegularOrderForNotAllowedCustomerTest;

@SuiteClasses({
	US32006ActivateTermPurchaseForAllProductsTest.class,
	US32006RegularOrderForNotAllowedCustomerTest.class,
	US32006PlaceCustomerOrderForNotAllowedStylistTest.class,
	US32006ClosePartyTest.class,
	US32006PlaceHostOrderForNotAllowedStylistTest.class

	
})
@RunWith(Suite.class)
public class US32006Suite {

}
