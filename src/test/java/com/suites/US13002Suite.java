package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss13.us13002.US13002ConfirmCustomerTest;
import com.tests.uss13.us13002.US13002StyleCoachLeadDistributionTest;
import com.tests.uss13.us13002.US13002ValidateCustomerIsAssignedToStylist;
import com.tests.uss13.us13002.US13002ValidateStylistPropertiesTest;

@SuiteClasses({
	US13002StyleCoachLeadDistributionTest.class,
	US13002ConfirmCustomerTest.class,
	US13002ValidateCustomerIsAssignedToStylist.class,
	US13002ValidateStylistPropertiesTest.class,
})
@RunWith(Suite.class)
public class US13002Suite {

}
