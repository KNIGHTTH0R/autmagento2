package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss14.us14002.US14002ChangeCustomerAddressTest;
import com.tests.uss14.us14002.US14002ConfirmCustomerTest;
import com.tests.uss14.us14002.US14002HostLeadDistributionTest;

@SuiteClasses({
	US14002HostLeadDistributionTest.class,
	US14002ConfirmCustomerTest.class,
	US14002ChangeCustomerAddressTest.class,
})
@RunWith(Suite.class)
public class US14002Suite {

}
