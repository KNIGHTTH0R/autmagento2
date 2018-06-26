package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsTestHostCart;
import com.tests.us9.us9001.US9001PlaceHostOrderWithForthyDiscountsAndJbTest;
import com.tests.us9.us9001.US9001aClosePartyTestVDV;
import com.tests.us9.us9002.US9002ValidateOrderBackOfficeTest;

@SuiteClasses({
	CreateProductsTestHostCart.class,
	US9001aClosePartyTestVDV.class,
	US9001PlaceHostOrderWithForthyDiscountsAndJbTest.class,
	US9002ValidateOrderBackOfficeTest.class,
//	US9002ValidateOrderEmailTest.class
	 
})
@RunWith(Suite.class)
public class US9002PlaceHost {

}
