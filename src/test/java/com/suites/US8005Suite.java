package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us8.us8005.US8005CustomerBuyWithShippingVoucherTest;
import com.tests.us8.us8005.US8005ValidateOrderBackOfficeTest;
import com.tests.us8.us8005.US8005ValidateOrderEmailTest;
import com.tests.us8.us8005.US8005ValidateOrderInStylistsCustomerOrderReportTest;

@SuiteClasses({
	US8005CustomerBuyWithShippingVoucherTest.class,
//	US8005CheckOrderOnCustomerProfileTest.class,	
	US8005ValidateOrderEmailTest.class,	
	US8005ValidateOrderBackOfficeTest.class,	
	US8005ValidateOrderInStylistsCustomerOrderReportTest.class,	
	
})
@RunWith(Suite.class)
public class US8005Suite {

}
