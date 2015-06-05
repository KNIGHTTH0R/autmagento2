package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss13.us13001.US13001ConfirmCustomerTest;
import com.tests.uss13.us13001.US13001CustomerLeadDistributionTest;
import com.tests.uss13.us13001.US13001ValidateCustomerIsAssignedToStylist;
import com.tests.uss13.us13001.US13001ValidateStylistPropertiesTest;

@SuiteClasses({
	US13001CustomerLeadDistributionTest.class,
	US13001ConfirmCustomerTest.class,
	US13001ValidateCustomerIsAssignedToStylist.class,
	US13001ValidateStylistPropertiesTest.class,
})
@RunWith(Suite.class)
public class US13001Suite {

}
