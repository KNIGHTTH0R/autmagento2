package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7003.US7003CheckCustomerActivationTest;
import com.tests.us7.us7003.US7003RegistrationThankYouPageTest;

@SuiteClasses({
////	//setup
//	US7003RegularUserRegistrationThankYouPageTest.class,
//	US7003EmailActivationTest.class,
	//actual test
	US7003RegistrationThankYouPageTest.class,
	US7003CheckCustomerActivationTest.class,
})
@RunWith(Suite.class)
public class US7003Suite {

}
