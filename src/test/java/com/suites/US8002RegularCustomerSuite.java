package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.VdVCreateProductsTestRegularCart;
import com.tests.us8.us8002.US8002CustomerBuyWithLbAndThirtyDiscountTestVDV;
import com.tests.us8.us8002.US8002ValidateOrderBackOfficeTestVDV;

@SuiteClasses({
	VdVCreateProductsTestRegularCart.class,
	US8002CustomerBuyWithLbAndThirtyDiscountTestVDV.class,
	US8002ValidateOrderBackOfficeTestVDV.class,
	//US8002ValidateOrderEmailTest.class,
	 
})
@RunWith(Suite.class)
public class US8002RegularCustomerSuite {

}
