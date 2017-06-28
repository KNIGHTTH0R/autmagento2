package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss32.us32007.US32007ClosePartyTest;
import com.tests.uss32.us32007.US32007CreatePartyWithStylistHostTest;
import com.tests.uss32.us32007.US32007DisableTermPurchaseForAllProductsTest;
import com.tests.uss32.us32007.US32007PlaceCustomerOrderForNotAllowedStylistTest;
import com.tests.uss32.us32007.US32007PlaceHostOrderForNotAllowedStylistTest;
import com.tests.uss32.us32007.US32007RegularOrderForNotAllowedCustomerTest;

@SuiteClasses({
	US32007DisableTermPurchaseForAllProductsTest.class,
	US32007CreatePartyWithStylistHostTest.class,
	US32007RegularOrderForNotAllowedCustomerTest.class,
	US32007PlaceCustomerOrderForNotAllowedStylistTest.class,
	US32007ClosePartyTest.class,
	US32007PlaceHostOrderForNotAllowedStylistTest.class

	
})
@RunWith(Suite.class)
public class US32007Suite {

	

}
