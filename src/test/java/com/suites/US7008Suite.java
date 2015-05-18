package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7008.US7008CheckCustomerActivation;
import com.tests.us7.us7008.US7008CheckReceivedEmailsTest;
import com.tests.us7.us7008.US7008KoboRegistrationTest;
import com.tests.us7.us7008.US7008ValidateCustomerIsAssignedToStylist;


@SuiteClasses({
	US7008KoboRegistrationTest.class,
	US7008CheckCustomerActivation.class,
	US7008CheckReceivedEmailsTest.class,
	US7008ValidateCustomerIsAssignedToStylist.class,
})
@RunWith(Suite.class)
public class US7008Suite {

}
