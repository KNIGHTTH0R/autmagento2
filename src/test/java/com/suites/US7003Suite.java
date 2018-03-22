package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7003.US7003CheckCustomerActivation;
import com.tests.us7.us7003.US7003CheckReceivedEmailsTest;
import com.tests.us7.us7003.US7003ConfirmCustomerTest;
import com.tests.us7.us7003.US7003RegularCustDykscSearchByPlzTest;
import com.tests.us7.us7003.US7003ValidateCustomerIsAssignedToStylist;

@SuiteClasses({

	US7003RegularCustDykscSearchByPlzTest.class,
	US7003ConfirmCustomerTest.class,
	US7003CheckCustomerActivation.class,
	US7003ValidateCustomerIsAssignedToStylist.class,
	US7003CheckReceivedEmailsTest.class,
})
@RunWith(Suite.class)
public class US7003Suite {

}
