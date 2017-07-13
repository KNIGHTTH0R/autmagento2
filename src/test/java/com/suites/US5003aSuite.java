package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us5a.us5003a.US5003aCustRegistrationFromInvitationTest;
import com.tests.us5a.us5003a.US5003aFacebookAsociatedContactDeleteTest;
import com.tests.us5a.us5003a.US5003aFacebookCustomerDeleteTest;
import com.tests.us5a.us5003a.US5003aInviteFbFriendAppInstalledFbLoggedInTest;
import com.tests.us5a.us5003a.US5003aRemovePippaAppFromFacebookTest;

@SuiteClasses({
	US5003aFacebookCustomerDeleteTest.class,
	US5003aFacebookAsociatedContactDeleteTest.class,
	US5003aInviteFbFriendAppInstalledFbLoggedInTest.class,
	US5003aRemovePippaAppFromFacebookTest.class,	
	US5003aCustRegistrationFromInvitationTest.class,
})
@RunWith(Suite.class)
public class US5003aSuite {
	
}
