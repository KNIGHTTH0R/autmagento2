package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us5a.us5004a.US5004aCustRegistrationFromInvitationTest;
import com.tests.us5a.us5004a.US5004aFacebookAsociatedContactDeleteTest;
import com.tests.us5a.us5004a.US5004aFacebookCustomerDeleteTest;
import com.tests.us5a.us5004a.US5004aInviteFbFriendAppNotInstalledFbLoggedOutTest;
import com.tests.us5a.us5004a.US5004aRemoveCustomerPippaAppFromFacebookTest;
import com.tests.us5a.us5004a.US5004aRemoveStylistPippaAppFromFacebookTest;

@SuiteClasses({
	US5004aFacebookCustomerDeleteTest.class,
	US5004aFacebookAsociatedContactDeleteTest.class,
	US5004aRemoveStylistPippaAppFromFacebookTest.class,
	US5004aInviteFbFriendAppNotInstalledFbLoggedOutTest.class,
	US5004aRemoveCustomerPippaAppFromFacebookTest.class,	
	US5004aCustRegistrationFromInvitationTest.class,
})
@RunWith(Suite.class)
public class US5004aSuite {
	
}
