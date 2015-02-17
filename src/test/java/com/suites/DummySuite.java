package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us0.US000CheckCustomerActivationTest;
import com.tests.us0.US000CreateCustomerTest;
import com.tests.us0.US000GrabStylistPropertiesTest;
import com.tests.us0.US000ValidateStylistTest;

@SuiteClasses({
	US000GrabStylistPropertiesTest.class,
//	US000CreateCustomerTest.class,
//	US000CheckCustomerActivationTest.class,
//	US000ValidateStylistTest.class,
})
@RunWith(Suite.class)
public class DummySuite {

}
