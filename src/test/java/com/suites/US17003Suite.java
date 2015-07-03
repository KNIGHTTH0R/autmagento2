package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss17.us17003.US17003ConfirmStylecoachTest;
import com.tests.uss17.us17003.US17003ConfirmStylecoachToBeReassignedTest;
import com.tests.uss17.us17003.US17003MarkStarterKitOrderAsPaidTest;
import com.tests.uss17.us17003.US17003ReasignContactsTest;
import com.tests.uss17.us17003.US17003StyleCoachRegistrationTest;
import com.tests.uss17.us17003.US17003StyleCoachRegistrationWithKnownSponsorTest;
import com.tests.uss17.us17003.US17003VerifyThatOldStylistWasDeactivatedTest;

@SuiteClasses({
	US17003StyleCoachRegistrationTest.class,
	US17003ConfirmStylecoachTest.class,
	US17003MarkStarterKitOrderAsPaidTest.class,
	US17003StyleCoachRegistrationWithKnownSponsorTest.class,
	US17003ConfirmStylecoachToBeReassignedTest.class,
	US17003ReasignContactsTest.class,
	US17003VerifyThatOldStylistWasDeactivatedTest.class,
	
})
@RunWith(Suite.class)
public class US17003Suite {

}
