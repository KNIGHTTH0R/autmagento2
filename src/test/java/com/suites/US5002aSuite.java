package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us5a.us5002a.US5002aCustRegistrationFromInvitationTest;
import com.tests.us5a.us5002a.US5002aFacebookAsociatedContactDeleteTest;
import com.tests.us5a.us5002a.US5002aFacebookCustomerDeleteTest;
import com.tests.us5a.us5002a.US5002aInviteFbFriendAppInstalledFbLoggedOutTest;

@SuiteClasses({
	US5002aFacebookCustomerDeleteTest.class,
	US5002aFacebookAsociatedContactDeleteTest.class,
	US5002aInviteFbFriendAppInstalledFbLoggedOutTest.class,
	US5002aCustRegistrationFromInvitationTest.class,	
})
@RunWith(Suite.class)
public class US5002aSuite {
	
}
