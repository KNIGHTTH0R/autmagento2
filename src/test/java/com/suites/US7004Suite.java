package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7004.US7004CheckCustomerActivationTest;
import com.tests.us7.us7004.US7004CheckReceivedEmailsTest;
import com.tests.us7.us7004.US7004ConfirmCustomerTest;
import com.tests.us7.us7004.US7004RegularCustDykscSearchByNameTest;
import com.tests.us7.us7004.US7004ValidateCustomerIsAssignedToStylist;

@SuiteClasses({

	US7004RegularCustDykscSearchByNameTest.class,
	US7004ConfirmCustomerTest.class,
	US7004CheckCustomerActivationTest.class,
	US7004ValidateCustomerIsAssignedToStylist.class,
	US7004CheckReceivedEmailsTest.class,
})
@RunWith(Suite.class)
public class US7004Suite {

}
