package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss17.us17002.US17002ChangeCustomersContextTest;
import com.tests.uss17.us17002.US17002ConfirmCustomerTest;
import com.tests.uss17.us17002.US17002ConfirmSecondStylecoachTest;
import com.tests.uss17.us17002.US17002MarkSecondStarterKitOrderAsPaidTest;
import com.tests.uss17.us17002.US17002MarkStarterKitOrderAsPaidTest;
import com.tests.uss17.us17002.US17002ReasignContactsTest;
import com.tests.uss17.us17002.US17002RegularCustomerRegistrationTest;
import com.tests.uss17.us17002.US17002SetCustomersPrefferdToBeQuitTest;
import com.tests.uss17.us17002.US17002StyleCoachRegistrationTest;
import com.tests.uss17.us17002.US17002StyleCoachRegistrationToBecomeCustomersPrefferedTest;
import com.tests.uss17.us17002.US17002VerifyThatContactWasReassignedCorrectlyTest;

@SuiteClasses({
	US17002StyleCoachRegistrationTest.class,
	US17002MarkStarterKitOrderAsPaidTest.class,
//	US17002ConfirmStylecoachTest.class,
	US17002StyleCoachRegistrationToBecomeCustomersPrefferedTest.class,
	US17002ConfirmSecondStylecoachTest.class,
	US17002MarkSecondStarterKitOrderAsPaidTest.class,
	US17002RegularCustomerRegistrationTest.class,
	US17002ConfirmCustomerTest.class,
	US17002ChangeCustomersContextTest.class,
	US17002SetCustomersPrefferdToBeQuitTest.class,
	US17002ReasignContactsTest.class,
	US17002VerifyThatContactWasReassignedCorrectlyTest.class,
	
})
@RunWith(Suite.class)
public class US17002Suite {

}
