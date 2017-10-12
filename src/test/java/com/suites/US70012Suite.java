package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.uss70012.US70012CheckCustomerActivation;
import com.tests.us7.uss70012.US70012CheckReceivedEmailsTest;
import com.tests.us7.uss70012.US70012KoboCampaignRegistrationUnderContextTest1;
import com.tests.us7.uss70012.US70012PlacePomOrderTest;
import com.tests.us7.uss70012.US70012ValidateCustomerIsAssignedToStylist;

@SuiteClasses({
	//US70012KoboCampaignRegistrationUnderContextTest.class,
//	US70012KoboCampaignRegistrationUnderContextTest1.class,
	US70012PlacePomOrderTest.class,
	US70012CheckCustomerActivation.class,
	US70012CheckReceivedEmailsTest.class,
	US70012ValidateCustomerIsAssignedToStylist.class,
})
@RunWith(Suite.class)
public class US70012Suite {

}
