package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss17.us17002.US17002ChangeCustomersContextTest;
import com.tests.uss17.us17002.US17002ConfirmCustomerTest;
import com.tests.uss17.us17002.US17002ReasignContactsTest;
import com.tests.uss17.us17002.US17002RegularCustomerRegistrationTest;
import com.tests.uss17.us17002.US17002SetCustomersPrefferdToBeQuitTest;
import com.tests.uss17.us17002.US17002StyleCoachRegistrationTest;
import com.tests.uss17.us17002.US17002StyleCoachRegistrationToBecomeCustomersPrefferedTest;
import com.tests.uss17.us17002.US17002VerifyThatContactWasReassignedCorrectlyTest;

@SuiteClasses({
	US17002StyleCoachRegistrationTest.class,
	US17002StyleCoachRegistrationToBecomeCustomersPrefferedTest.class,
	US17002RegularCustomerRegistrationTest.class, //customer registers under SC1 
	US17002ConfirmCustomerTest.class,
	US17002ChangeCustomersContextTest.class, //customer changes the preffered SC to SC2
	US17002SetCustomersPrefferdToBeQuitTest.class, //SC2 status = quit
	US17002ReasignContactsTest.class, // cancel SC1 (the contact of customer is under SC1, not under SC2)
	US17002VerifyThatContactWasReassignedCorrectlyTest.class, //the contact is reassigned to Master
	
})
@RunWith(Suite.class)
public class US17002Suite {

}
