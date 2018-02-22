package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsPlaceCustomerCartTest;
import com.tests.uss10.us10001.US10001CreatePartyWithCustomerHostTestVDV;
import com.tests.uss11.us11001.US11001PartyHostBuysForCustomerWithNoBonusTestVDV;
import com.tests.uss11.us11001.US11001ValidateOrderBackOfficeTestVDV;
import com.tests.uss11.us11001.US11001ValidateOrderEmailTestVDV;

@SuiteClasses({
	CreateProductsPlaceCustomerCartTest.class,
	US10001CreatePartyWithCustomerHostTestVDV.class,
	US11001PartyHostBuysForCustomerWithNoBonusTestVDV.class,
	US11001ValidateOrderBackOfficeTestVDV.class,
	US11001ValidateOrderEmailTestVDV.class
	 
})
@RunWith(Suite.class)
public class US11001_PlaceCustomer {

}
