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
import com.tests.uss17.us17001.US17001ReasignContactsTest;
import com.tests.uss17.us17001.US17001RegularCustomerRegistrationTest;
import com.tests.uss17.us17001.US17001SecondRegularCustomerRegistrationTest;
import com.tests.uss17.us17001.US17001StyleCoachRegistrationTest;
import com.tests.uss17.us17001.US17001VerifyThatContactWasReassignedToUnqualifiedSelectedScTest;
import com.tests.uss17.us17001.US17001VerifyThatContactsWereReassignedToSelectedScTest;
import com.tests.uss17.us17001.US17001VerifyThatFirstCustContactIsReassignedToCustPreffScTest;
import com.tests.uss17.us17001.US17001VerifyThatOldStylistWasDeactivatedTest;

@SuiteClasses({
	US17001StyleCoachRegistrationTest.class,//create SC1 which will be canceled
	US17001RegularCustomerRegistrationTest.class, //create first customer account (customer1)
	US17001SecondRegularCustomerRegistrationTest.class, //create second customer account (customer2)
	US17001AddNewContactToStyleCoachTest.class, // add first contact to SC list
	US17001AddSecondNewContactToStyleCoachTest.class, //add the second contact to SC list, the first two contacts match by email
//	US17001ConfirmCustomerTest.class,
//	US17001ConfirmSecondCustomerTest.class,	
//	US17001AddThirdContactToStyleCoachTest.class,// add third contact to SC list
//	US17001AddForthContactToStyleCoachTest.class,// add forth contact to SC list, the third and forth contacts match by firstname and lastname
//	US17001ChangeFirstCustomersContextTest.class,//change the context of first customer to SC2
//	US17001ReasignContactsTest.class,// reassign and choose another SC from list
//	US17001VerifyThatContactsWereReassignedToSelectedScTest.class, // check that the contacts are reassigned to the selected SC
//	US17001VerifyThatFirstCustContactIsReassignedToCustPreffScTest.class,// asta merge la prefferd
//	US17001VerifyThatContactWasReassignedToUnqualifiedSelectedScTest.class,
//	US17001VerifyThatOldStylistWasDeactivatedTest.class,
})
@RunWith(Suite.class)
public class US17001Suite {

}
