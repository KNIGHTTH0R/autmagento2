package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss14.us14001.US14001ChangeCustomerAddressTest;
import com.tests.uss14.us14001.US14001ConfirmCustomerTest;
import com.tests.uss14.us14001.US14001CustomerDistributionDurringCheckoutTest;
import com.tests.uss14.us14001.US14001StyleCoachLeadDistributionTest;
import com.tests.uss14.us14001.US14001ValidateCustomerIsAssignedToStylist;
import com.tests.uss14.us14001.US14001ValidateStylistPropertiesTest;

@SuiteClasses({
	US14001StyleCoachLeadDistributionTest.class,
	US14001ConfirmCustomerTest.class,
	US14001ChangeCustomerAddressTest.class,
	US14001CustomerDistributionDurringCheckoutTest.class,
	US14001ValidateCustomerIsAssignedToStylist.class,
	US14001ValidateStylistPropertiesTest.class,
})
@RunWith(Suite.class)
public class US14001Suite {

}
