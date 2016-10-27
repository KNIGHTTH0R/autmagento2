package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7004b.US7004bCheckCustomerActivationTest;
import com.tests.us7.us7004b.US7004bEmailActivationTest;
import com.tests.us7.us7004b.US7004bRegCustRegLandingPageNotPrefCountryTest;

@SuiteClasses({
	US7004bRegCustRegLandingPageNotPrefCountryTest.class,
	US7004bEmailActivationTest.class,
	US7004bCheckCustomerActivationTest.class,
})
@RunWith(Suite.class)
public class US7004bSuite {

}
