package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss14.us14003.US14003AddAddressToCustomerTest;
import com.tests.uss14.us14003.US14003ConfirmCustomerTest;
import com.tests.uss14.us14003.US14003CustomerDistributionDurringCheckoutTest;
import com.tests.uss14.us14003.US14003CustomerLeadDistributionTest;
import com.tests.uss14.us14003.US14003ValidateCustomerIsAssignedToStylist;
import com.tests.uss14.us14003.US14003ValidateStylistPropertiesTest;

@SuiteClasses({
	US14003CustomerLeadDistributionTest.class,
	US14003ConfirmCustomerTest.class,
	US14003AddAddressToCustomerTest.class,
	US14003CustomerDistributionDurringCheckoutTest.class,
	US14003ValidateCustomerIsAssignedToStylist.class,
	US14003ValidateStylistPropertiesTest.class,
})
@RunWith(Suite.class)
public class US14003Suite {

}
