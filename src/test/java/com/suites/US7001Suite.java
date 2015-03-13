package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7001.US7001CheckCustomerActivation;
import com.tests.us7.us7001.US7001CheckReceivedEmailsTest;
import com.tests.us7.us7001.US7001ConfirmCustomerTest;
import com.tests.us7.us7001.US7001RegularCustomerRegistrationTest;
import com.tests.us7.us7001.US7001ValidateCustomerIsAssignedToStylist;


@SuiteClasses({
	US7001RegularCustomerRegistrationTest.class,
	US7001ConfirmCustomerTest.class,
	US7001CheckCustomerActivation.class,
	US7001CheckReceivedEmailsTest.class,
	US7001ValidateCustomerIsAssignedToStylist.class,
})
@RunWith(Suite.class)
public class US7001Suite {

}