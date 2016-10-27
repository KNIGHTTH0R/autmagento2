package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us8.us8008.US8008CustomerBuyWithTpAndZeroAmountTest;
import com.tests.us8.us8008.US8008ValidateOrdersStatusesTest;

@SuiteClasses({
	US8008CustomerBuyWithTpAndZeroAmountTest.class,
	US8008ValidateOrdersStatusesTest.class,	
	
})
@RunWith(Suite.class)
public class US8008Suite {

}
