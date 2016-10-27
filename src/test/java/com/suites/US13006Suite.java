package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss13.us13006.US13006ConfirmCustomerTest;
import com.tests.uss13.us13006.US13006HostLeadDykscPlzAndCountryTest;
import com.tests.uss13.us13006.US13006ValidateCustomerIsAssignedToStylist;
import com.tests.uss13.us13006.US13006ValidateStylistPropertiesTest;

@SuiteClasses({
	US13006HostLeadDykscPlzAndCountryTest.class,
	US13006ConfirmCustomerTest.class,
	US13006ValidateCustomerIsAssignedToStylist.class,
	US13006ValidateStylistPropertiesTest.class,
})
@RunWith(Suite.class)
public class US13006Suite {

}
