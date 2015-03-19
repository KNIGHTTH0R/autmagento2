package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7005.US7005CheckCustomerActivationTest;
import com.tests.us7.us7005.US7005EmailActivationTest;
import com.tests.us7.us7005.US7005RegularKnownUserRegistrationLandingPageTest;

@SuiteClasses({
	US7005RegularKnownUserRegistrationLandingPageTest.class,
	US7005EmailActivationTest.class,
	US7005CheckCustomerActivationTest.class,
})
@RunWith(Suite.class)
public class US7005Suite {

}
