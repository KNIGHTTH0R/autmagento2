package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us8.us8002.US8002CustomerBuyWithVoucherTest;
import com.tests.us8.us8002.US8002UserProfileOrderIdTest;
import com.tests.us8.us8002.US8002ValidateOrderBackOfficeTest;
import com.tests.us8.us8002.US8002ValidateOrderEmailTest;
import com.tests.us8.us8002.US8002ValidateOrderInStylistsCustomerOrderReportTest;

@SuiteClasses({
	US8002CustomerBuyWithVoucherTest.class,
	US8002UserProfileOrderIdTest.class,	
	US8002ValidateOrderEmailTest.class,	
	US8002ValidateOrderBackOfficeTest.class,	
	US8002ValidateOrderInStylistsCustomerOrderReportTest.class,	
	
})
@RunWith(Suite.class)
public class US8002Suite {

}
