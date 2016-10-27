package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us8.us8006.US8006CustomerBuyWithVoucherPartiallyOnShippingTest;
import com.tests.us8.us8006.US8006ValidateOrderBackOfficeTest;

@SuiteClasses({
	US8006CustomerBuyWithVoucherPartiallyOnShippingTest.class,
//	US8006CheckOrderOnCustomerProfileTest.class,	
//	US8006ValidateOrderEmailTest.class,	
	US8006ValidateOrderBackOfficeTest.class,	
//	US8006ValidateOrderInStylistsCustomerOrderReportTest.class,	
	
})
@RunWith(Suite.class)
public class US8006Suite {

}
