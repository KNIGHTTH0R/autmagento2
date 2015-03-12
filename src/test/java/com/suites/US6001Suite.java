package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us6.US6001CheckStylistActivationTest;
import com.tests.us6.US6001StyleCoachRegistrationTest;


@SuiteClasses({
	US6001StyleCoachRegistrationTest.class,
	US6001CheckStylistActivationTest.class,
})
@RunWith(Suite.class)
public class US6001Suite {

}
