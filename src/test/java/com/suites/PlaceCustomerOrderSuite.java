package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsTest;
import com.tests.uss10.us10001b.US10001bCreatePartyWithStylistHostTest;
import com.tests.uss10.us10002b.US10002bCreatePartyWithCustomerHostTest;
import com.tests.uss10.uss10007.US10007CreatePartyWithStylistHostTest;
import com.tests.uss11.us11008.US10009CreatePartyWithStylistHostTest;


@SuiteClasses({
	CreateProductsTest.class,
	US10001bCreatePartyWithStylistHostTest.class,
	US10002bCreatePartyWithCustomerHostTest.class,
	US10007CreatePartyWithStylistHostTest.class,
	US10009CreatePartyWithStylistHostTest.class,
	US11001Suite.class,
	US11002Suite.class,
	US11004Suite.class,
	US11005Suite.class,
	US11006Suite.class,
	US11007Suite.class,
	US11008Suite.class,
	US11009Suite.class,
	
})
@RunWith(Suite.class)
public class PlaceCustomerOrderSuite {

}
