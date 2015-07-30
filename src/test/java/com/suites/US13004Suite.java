package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss13.us13004.US13004ConfirmCustomerTest;
import com.tests.uss13.us13004.US13004CustomerLeadDykscPlzAndCountryTest;
import com.tests.uss13.us13004.US13004ValidateCustomerIsAssignedToStylist;
import com.tests.uss13.us13004.US13004ValidateStylistPropertiesTest;

@SuiteClasses({
	US13004CustomerLeadDykscPlzAndCountryTest.class,
	US13004ConfirmCustomerTest.class,
	US13004ValidateCustomerIsAssignedToStylist.class,
	US13004ValidateStylistPropertiesTest.class,
})
@RunWith(Suite.class)
public class US13004Suite {

}
