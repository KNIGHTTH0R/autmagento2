package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss17.us17001.US17001AddForthContactToStyleCoachTest;
import com.tests.uss17.us17001.US17001AddNewContactToStyleCoachTest;
import com.tests.uss17.us17001.US17001AddSecondNewContactToStyleCoachTest;
import com.tests.uss17.us17001.US17001AddThirdContactToStyleCoachTest;
import com.tests.uss17.us17001.US17001ChangeFirstCustomersContextTest;
import com.tests.uss17.us17001.US17001ConfirmCustomerTest;
import com.tests.uss17.us17001.US17001ConfirmSecondCustomerTest;
import com.tests.uss17.us17001.US17001ConfirmStylecoachTest;
import com.tests.uss17.us17001.US17001MarkStarterKitOrderAsPaidTest;
import com.tests.uss17.us17001.US17001ReasignContactsTest;
import com.tests.uss17.us17001.US17001RegularCustomerRegistrationTest;
import com.tests.uss17.us17001.US17001SecondRegularCustomerRegistrationTest;
import com.tests.uss17.us17001.US17001StyleCoachRegistrationTest;
import com.tests.uss17.us17001.US17001VerifyThatContactWasReassignedToUnqualifiedSelectedScTest;
import com.tests.uss17.us17001.US17001VerifyThatContactsWereReassignedToSelectedScTest;
import com.tests.uss17.us17001.US17001VerifyThatFirstCustContactIsReassignedToCustPreffScTest;
import com.tests.uss17.us17001.US17001VerifyThatOldStylistWasDeactivatedTest;

@SuiteClasses({
	US17001StyleCoachRegistrationTest.class,
	US17001MarkStarterKitOrderAsPaidTest.class,
	US17001ConfirmStylecoachTest.class,
	US17001RegularCustomerRegistrationTest.class,
	US17001SecondRegularCustomerRegistrationTest.class,
	US17001AddNewContactToStyleCoachTest.class,
	US17001AddSecondNewContactToStyleCoachTest.class,
	US17001ConfirmCustomerTest.class,
	US17001ConfirmSecondCustomerTest.class,	
	US17001AddThirdContactToStyleCoachTest.class,
	US17001AddForthContactToStyleCoachTest.class,
	US17001ChangeFirstCustomersContextTest.class,
	US17001ReasignContactsTest.class,
	US17001VerifyThatContactsWereReassignedToSelectedScTest.class,
	US17001VerifyThatFirstCustContactIsReassignedToCustPreffScTest.class,
	US17001VerifyThatContactWasReassignedToUnqualifiedSelectedScTest.class,
	US17001VerifyThatOldStylistWasDeactivatedTest.class,
})
@RunWith(Suite.class)
public class US17001Suite {

}
