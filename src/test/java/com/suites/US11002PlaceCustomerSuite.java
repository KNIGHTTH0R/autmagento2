package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsPlaceCustomerCartTest;
import com.tests.uss10.us10001.US10001CreatePartyWithCustomerHostTestVDV;
import com.tests.uss11.us11002.US11002PartyHostBuysForCustomerWithVoucherTestVDV;

@SuiteClasses({
	CreateProductsPlaceCustomerCartTest.class,
	US10001CreatePartyWithCustomerHostTestVDV.class,
	US11002PartyHostBuysForCustomerWithVoucherTestVDV.class,

//	US11002ValidateOrderEmailTest.class
	 
})
@RunWith(Suite.class)
public class US11002PlaceCustomerSuite {

}
