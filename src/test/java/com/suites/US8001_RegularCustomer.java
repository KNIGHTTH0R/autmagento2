package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.VdVCreateProductsTestRegularCart;
import com.tests.us8.us8001vdv.US8001CustomerBuyProductsWithNoBonnusTestVDV;
import com.tests.us8.us8001vdv.US8001ValidateOrderBackOfficeTestVDV;

@SuiteClasses({
	VdVCreateProductsTestRegularCart.class,
	US8001CustomerBuyProductsWithNoBonnusTestVDV.class,
	US8001ValidateOrderBackOfficeTestVDV.class,
//	US8001ValidateOrderEmailTestVDV.class,
	 
})
@RunWith(Suite.class)
public class US8001_RegularCustomer {

}
