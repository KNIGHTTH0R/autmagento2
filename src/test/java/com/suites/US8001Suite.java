package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us8.us8001.US8001CustomerBuyWithForthyDiscountsAndJbTest;
import com.tests.us8.us8001.US8001UserProfileOrderIdTest;
import com.tests.us8.us8001.US8001ValidateOrderBackOfficeTest;
import com.tests.us8.us8001.US8001ValidateOrderEmailTest;

@SuiteClasses({
	US8001CustomerBuyWithForthyDiscountsAndJbTest.class,
	US8001UserProfileOrderIdTest.class,	
	US8001ValidateOrderEmailTest.class,	
	US8001ValidateOrderBackOfficeTest.class,	
	
})
@RunWith(Suite.class)
public class US8001Suite {

}
