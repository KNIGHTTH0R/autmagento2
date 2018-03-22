package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7005.US7005CheckCustomerActivationTest;
import com.tests.us7.us7005.US7005CheckReceivedEmailsTest;
import com.tests.us7.us7005.US7005ConfirmCustomerTest;
import com.tests.us7.us7005.US7005RegCustRegUnderSpecificBEStylistTest;
import com.tests.us7.us7005.US7005ValidateCustomerIsAssignedToStylist;

@SuiteClasses({
	US7005RegCustRegUnderSpecificBEStylistTest.class,
	US7005ConfirmCustomerTest.class,
	US7005CheckCustomerActivationTest.class,
	US7005ValidateCustomerIsAssignedToStylist.class,
	US7005CheckReceivedEmailsTest.class,
})
@RunWith(Suite.class)
public class US7005Suite {

}
