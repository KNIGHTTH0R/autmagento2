package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss13.us13005.US13005ConfirmCustomerTest;
import com.tests.uss13.us13005.US13005StyleCoachLeadDykscPlzAndCountryTest;
import com.tests.uss13.us13005.US13005ValidateCustomerIsAssignedToStylist;
import com.tests.uss13.us13005.US13005ValidateStylistPropertiesTest;

@SuiteClasses({
	US13005StyleCoachLeadDykscPlzAndCountryTest.class,
	US13005ConfirmCustomerTest.class,
	US13005ValidateCustomerIsAssignedToStylist.class,
	US13005ValidateStylistPropertiesTest.class,
})
@RunWith(Suite.class)
public class US13005Suite {

}
