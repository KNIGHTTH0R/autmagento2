package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsTestHostCart;
import com.tests.us9.us9001vdv.US9001ClosePartyTestVDV;
import com.tests.us9.us9001vdv.US9001PlaceHostOrderWithNoBonnusTestVDV;
import com.tests.us9.us9001vdv.US9001ValidateOrderBackOfficeTestVDV;

@SuiteClasses({
	CreateProductsTestHostCart.class,
	US9001ClosePartyTestVDV.class,
	US9001PlaceHostOrderWithNoBonnusTestVDV.class,
	US9001ValidateOrderBackOfficeTestVDV.class,
//	US9001ValidateOrderEmailTestVDV.class
	 
})
@RunWith(Suite.class)
public class US9001PlaceHost {

}
