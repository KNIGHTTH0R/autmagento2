package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss13.us13003.US13003ConfirmCustomerTest;
import com.tests.uss13.us13003.US13003HostLeadDistributionTest;
import com.tests.uss13.us13003.US13003ValidateCustomerIsAssignedToStylist;
import com.tests.uss13.us13003.US13003ValidateStylistPropertiesTest;

@SuiteClasses({
	US13003HostLeadDistributionTest.class,
	US13003ConfirmCustomerTest.class,
	US13003ValidateCustomerIsAssignedToStylist.class,
	US13003ValidateStylistPropertiesTest.class,
})
@RunWith(Suite.class)
public class US13003Suite {

}
