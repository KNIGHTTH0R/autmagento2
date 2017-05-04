package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7002.US7002CheckCustomerActivation;
import com.tests.us7.us7002.US7002CheckReceivedEmailsTest;
import com.tests.us7.us7002.US7002ConfirmCustomerTest;
import com.tests.us7.us7002.US7002RegularCustRegistrationOnContextTest;
import com.tests.us7.us7002.US7002ValidateCustomerIsAssignedToStylist;


@SuiteClasses({
//	US7002RegularCustRegistrationOnContextTest.class,
	US7002ConfirmCustomerTest.class,
	US7002CheckCustomerActivation.class,
	US7002CheckReceivedEmailsTest.class,
	US7002ValidateCustomerIsAssignedToStylist.class,
})
@RunWith(Suite.class)
public class US7002Suite {

}
