package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.uss10010.US10010AccessGuestPageFromPartyDetailsAndAcceptInvitationUsingFbTest;
import com.tests.uss10.uss10010.US10010AddManuallyGuestsToPartyFromHostPartyPageTest;
import com.tests.uss10.uss10010.US10010CreatePartyWithStylistHostTest;


@SuiteClasses({
	US10010CreatePartyWithStylistHostTest.class,
	US10010AccessGuestPageFromPartyDetailsAndAcceptInvitationUsingFbTest.class,
//	US10010AddManuallyGuestsToPartyFromHostPartyPageTest.class,

})
@RunWith(Suite.class)
public class US10010 {

}
