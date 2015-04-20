package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us9.us9001.US9001PartyHostBuyWithForthyDiscountsAndJbTest;
import com.tests.us9.us9001.US9001UserProfileOrderIdTest;
import com.tests.us9.us9001.US9001ValidateOrderBackOfficeTest;
import com.tests.us9.us9001.US9001ValidateOrderEmailTest;

@SuiteClasses({
	US9001PartyHostBuyWithForthyDiscountsAndJbTest.class,
	US9001UserProfileOrderIdTest.class,	
	US9001ValidateOrderEmailTest.class,	
	US9001ValidateOrderBackOfficeTest.class,	
})
@RunWith(Suite.class)
public class US9001Suite {

}
