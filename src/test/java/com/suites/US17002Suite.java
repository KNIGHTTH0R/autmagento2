package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss17.us17002.US17002AddNewContactToStyleCoachTest;
import com.tests.uss17.us17002.US17002ConfirmStylecoachTest;
import com.tests.uss17.us17002.US17002MarkStarterKitOrderAsPaidTest;
import com.tests.uss17.us17002.US17002ReasignContactsTest;
import com.tests.uss17.us17002.US17002StyleCoachRegistrationTest;
import com.tests.uss17.us17002.US17002VerifyThatFirstContactWasReassignedCorrectlyTest;
import com.tests.uss17.us17002.US17002VerifyThatOldStylistWasDeactivatedTest;

@SuiteClasses({
	US17002StyleCoachRegistrationTest.class,
	US17002ConfirmStylecoachTest.class,
	US17002MarkStarterKitOrderAsPaidTest.class,
	US17002AddNewContactToStyleCoachTest.class,
	US17002ReasignContactsTest.class,
	US17002VerifyThatFirstContactWasReassignedCorrectlyTest.class,
	US17002VerifyThatOldStylistWasDeactivatedTest.class,
	
})
@RunWith(Suite.class)
public class US17002Suite {

}
