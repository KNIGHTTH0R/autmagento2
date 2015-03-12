package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us0.US000GrabStylistPropertiesTest;
import com.tests.us3.us3005.US3005Test;
import com.tests.us3.us3006.US3006Test;

@SuiteClasses({
	US000GrabStylistPropertiesTest.class,
	US3005Test.class,
	US3006Test.class,
//	US000CreateCustomerTest.class,
//	US000CheckCustomerActivationTest.class,
//	US000ValidateStylistTest.class,
})
@RunWith(Suite.class)
public class DummySuite {

}
