package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us8.us8004.US8004CustomerBuyWithContactBoosterTest;
import com.tests.us8.us8004.US8004CheckOrderOnCustomerProfileTest;
import com.tests.us8.us8004.US8004ValidateOrderBackOfficeTest;
import com.tests.us8.us8004.US8004ValidateOrderEmailTest;
import com.tests.us8.us8004.US8004ValidateOrderInStylistsCustomerOrderReportTest;

@SuiteClasses({
	US8004CustomerBuyWithContactBoosterTest.class,
	US8004CheckOrderOnCustomerProfileTest.class,	
	US8004ValidateOrderEmailTest.class,	
	US8004ValidateOrderBackOfficeTest.class,
	US8004ValidateOrderInStylistsCustomerOrderReportTest.class,
	
})
@RunWith(Suite.class)
public class US8004Suite {

}
