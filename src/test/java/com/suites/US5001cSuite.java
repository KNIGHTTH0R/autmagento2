package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us5c.us5001c.US5001cCheckAcceptedInvitationOnHostPageTest;
import com.tests.us5c.us5001c.US5001cCreatePartyWithStylistHostTest;
import com.tests.us5c.us5001c.US5001cFacebookAsociatedContactDeleteTest;
import com.tests.us5c.us5001c.US5001cFacebookCustomerDeleteTest;
import com.tests.us5c.us5001c.US5001cPartyGuestAcceptInvitationUsingFBDataTest;
import com.tests.us5c.us5001c.US5001cRemoveInviteePippaAppFromFacebookTest;



@SuiteClasses({
//	US5001cFacebookCustomerDeleteTest.class,
//	US5001cFacebookAsociatedContactDeleteTest.class,
	US5001cCreatePartyWithStylistHostTest.class,
	US5001cRemoveInviteePippaAppFromFacebookTest.class,
	US5001cPartyGuestAcceptInvitationUsingFBDataTest.class,
	US5001cCheckAcceptedInvitationOnHostPageTest.class,	
})
@RunWith(Suite.class)
public class US5001cSuite {
	
}
