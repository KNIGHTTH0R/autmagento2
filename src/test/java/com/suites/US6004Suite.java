package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us6.us6004.US6004CheckCustomerActivationTest;
import com.tests.us6.us6004.US6004StylistRegistrationStopToStep4Test;


@SuiteClasses({
	US6004StylistRegistrationStopToStep4Test.class,
	US6004CheckCustomerActivationTest.class,
	
})
@RunWith(Suite.class)
public class US6004Suite {

}
