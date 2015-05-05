package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7006b.US7006bCheckCustomerActivationTest;
import com.tests.us7.us7006b.US7006bEmailActivationTest;
import com.tests.us7.us7006b.US7006bUserRegistrationSpecificStylistLandingPageTest;

@SuiteClasses({

	US7006bUserRegistrationSpecificStylistLandingPageTest.class,
	US7006bEmailActivationTest.class,
	US7006bCheckCustomerActivationTest.class,
})
@RunWith(Suite.class)
public class US7006bSuite {

}
