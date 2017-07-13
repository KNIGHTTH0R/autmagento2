package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us5a.us5001a.US5001aCustRegistrationFromInvitationTest;
import com.tests.us5a.us5001a.US5001aFacebookAsociatedContactDeleteTest;
import com.tests.us5a.us5001a.US5001aFacebookCustomerDeleteTest;
import com.tests.us5a.us5001a.US5001aInviteFbFriendAppNotInstalledFbLoggedInTest;

@SuiteClasses({
	US5001aFacebookCustomerDeleteTest.class,
	US5001aFacebookAsociatedContactDeleteTest.class,
	US5001aInviteFbFriendAppNotInstalledFbLoggedInTest.class,
	US5001aCustRegistrationFromInvitationTest.class,	
})
@RunWith(Suite.class)
public class US5001aSuite {
	
}
