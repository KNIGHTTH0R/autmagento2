package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss32.us32003.US32003CheckRegularOrderTpRestrictionsWhenProcessDisabledTest;
import com.tests.uss32.us32003.US32003ClosePartyTest;
import com.tests.uss32.us32003.US32003CreatePartyWithStylistHostTest;
import com.tests.uss32.us32003.US32003DisableTermPurchaseForAllProductsTest;
import com.tests.uss32.us32003.US32003PlaceCustomerOrderWhenProcessDisabledTest;
import com.tests.uss32.us32003.US32003PlaceHostOrderWhenProcessDisabledTest;

@SuiteClasses({
	US32003DisableTermPurchaseForAllProductsTest.class,
	US32003CreatePartyWithStylistHostTest.class,
	US32003CheckRegularOrderTpRestrictionsWhenProcessDisabledTest.class,
	US32003PlaceCustomerOrderWhenProcessDisabledTest.class,
	US32003ClosePartyTest.class,
	US32003PlaceHostOrderWhenProcessDisabledTest.class

	
})
@RunWith(Suite.class)
public class US32003Suite {

}
