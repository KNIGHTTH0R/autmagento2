package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss17.us17001.US17001AddNewContactToStyleCoachTest;
import com.tests.uss17.us17001.US17001ConfirmCustomerTest;
import com.tests.uss17.us17001.US17001MarkStarterKitOrderAsPaidTest;
import com.tests.uss17.us17001.US17001RegularCustomerRegistrationTest;
import com.tests.uss17.us17001.US17001StyleCoachRegistrationTest;

@SuiteClasses({
	US17001StyleCoachRegistrationTest.class,
	US17001ConfirmCustomerTest.class,
	US17001MarkStarterKitOrderAsPaidTest.class,
	US17001AddNewContactToStyleCoachTest.class,
	US17001RegularCustomerRegistrationTest.class,
})
@RunWith(Suite.class)
public class US17001Suite {

}
