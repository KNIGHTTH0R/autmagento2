package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.uss70011.US70011CheckCustomerActivation;
import com.tests.us7.uss70011.US70011CheckReceivedEmailsTest;
import com.tests.us7.uss70011.US70011KoboCampaignRegistrationOnMasterTest;
import com.tests.us7.uss70011.US70011ValidateCustomerIsAssignedToStylist;


@SuiteClasses({
//	US70011KoboCampaignRegistrationOnMasterTest.class,
	US70011CheckCustomerActivation.class,
	US70011CheckReceivedEmailsTest.class,
	US70011ValidateCustomerIsAssignedToStylist.class,
})
@RunWith(Suite.class)
public class US70011Suite {

}
