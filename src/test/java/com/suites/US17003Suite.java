package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss17.us17003.US17003AddNewContactToStyleCoachTest;
import com.tests.uss17.us17003.US17003ReasignContactsTest;
import com.tests.uss17.us17003.US17003StyleCoachRegistrationTest;
import com.tests.uss17.us17003.US17003VerifyContactIsReassignedUpOnCanceledScHierarchyTest;
import com.tests.uss17.us17003.US17003VerifyThatOldStylistWasDeactivatedTest;

@SuiteClasses({
	US17003StyleCoachRegistrationTest.class,
	US17003AddNewContactToStyleCoachTest.class, //add a contact under created SC
	US17003ReasignContactsTest.class, //cancel the SC
	US17003VerifyThatOldStylistWasDeactivatedTest.class,
	US17003VerifyContactIsReassignedUpOnCanceledScHierarchyTest.class, //verify that the contact is reassigned to a SC from hierarchy(in our case on Master)
	
})
@RunWith(Suite.class)
public class US17003Suite {

}
