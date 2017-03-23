package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3001.US3001BuyProductsForTheFirstTimeTest;
import com.tests.us7.us7002.US7002RegularCustRegistrationOnContextTest;

@SuiteClasses({
	US7002RegularCustRegistrationOnContextTest.class,
})
@RunWith(Suite.class)
public class QuickTestsSuite3 {
	
}
