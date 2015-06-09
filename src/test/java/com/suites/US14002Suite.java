package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss14.us14002.US14002ChangeCustomerAddressTest;
import com.tests.uss14.us14002.US14002ConfirmCustomerTest;
import com.tests.uss14.us14002.US14002CustomerDistributionDurringCheckoutTest;
import com.tests.uss14.us14002.US14002HostLeadDistributionTest;
import com.tests.uss14.us14002.US14002ValidateCustomerIsAssignedToStylist;
import com.tests.uss14.us14002.US14002ValidateStylistPropertiesTest;

@SuiteClasses({
	US14002HostLeadDistributionTest.class,
	US14002ConfirmCustomerTest.class,
	US14002ChangeCustomerAddressTest.class,
	US14002CustomerDistributionDurringCheckoutTest.class,
	US14002ValidateCustomerIsAssignedToStylist.class,
	US14002ValidateStylistPropertiesTest.class,
})
@RunWith(Suite.class)
public class US14002Suite {

}
