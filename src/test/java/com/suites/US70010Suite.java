package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.uss70010.US70010CheckCustomerActivation;
import com.tests.us7.uss70010.US70010CheckReceivedEmailsTest;
import com.tests.us7.uss70010.US70010KoboRegistrationTest;
import com.tests.us7.uss70010.US70010ValidateCustomerIsAssignedToStylist;


@SuiteClasses({
	US70010KoboRegistrationTest.class,
	US70010CheckCustomerActivation.class,
	US70010CheckReceivedEmailsTest.class,
	US70010ValidateCustomerIsAssignedToStylist.class,
})
@RunWith(Suite.class)
public class US70010Suite {

}
