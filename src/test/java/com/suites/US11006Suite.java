package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss11.us11006.US11006OrderForCustomerWithVoucherPartiallyOnShippingTest;
import com.tests.uss11.us11006.US11006ValidateOrderBackOfficeTest;

@SuiteClasses({
	US11006OrderForCustomerWithVoucherPartiallyOnShippingTest.class,
	US11006ValidateOrderBackOfficeTest.class,	
})
@RunWith(Suite.class)
public class US11006Suite {

}
