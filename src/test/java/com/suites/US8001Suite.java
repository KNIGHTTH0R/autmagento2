package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us8.us8001.US8001CheckOrderOnCustomerProfileTest;
import com.tests.us8.us8001.US8001CustomerBuyWithForthyDiscountsAndJbTest;
import com.tests.us8.us8001.US8001ReorderWithForthyDiscountsAndJbTest;
import com.tests.us8.us8001.US8001ValidateOrderBackOfficeTest;
import com.tests.us8.us8001.US8001ValidateOrderEmailTest;
import com.tests.us8.us8001.US8001ValidateOrderInStylistsCustomerOrderReportTest;

@SuiteClasses({
	US8001CustomerBuyWithForthyDiscountsAndJbTest.class,
	US8001ValidateOrderBackOfficeTest.class,
	US8001ValidateOrderInStylistsCustomerOrderReportTest.class,
	US8001ReorderWithForthyDiscountsAndJbTest.class,
	US8001ValidateOrderEmailTest.class,
	US8001CheckOrderOnCustomerProfileTest.class,	
	
})
@RunWith(Suite.class)
public class US8001Suite {

}
