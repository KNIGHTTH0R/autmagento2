package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7004.US7004CheckCustomerActivationTest;
import com.tests.us7.us7004.US7004EmailActivationTest;
import com.tests.us7.us7004.US7004RegularUserRegistrationLandingPageTest;

@SuiteClasses({
	//US7004RegularUserRegistrationLandingPageTest.class,
	US7004EmailActivationTest.class,
	US7004CheckCustomerActivationTest.class,
})
@RunWith(Suite.class)
public class US7004Suite {

}
