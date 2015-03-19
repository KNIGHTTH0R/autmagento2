package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7006.US7006CheckCustomerActivationTest;
import com.tests.us7.us7006.US7006EmailActivationTest;
import com.tests.us7.us7006.US7006UserRegistrationSpecificStylistLandingPageTest;

@SuiteClasses({

	US7006UserRegistrationSpecificStylistLandingPageTest.class,
	US7006EmailActivationTest.class,
	US7006CheckCustomerActivationTest.class,
})
@RunWith(Suite.class)
public class US7006Suite {

}
