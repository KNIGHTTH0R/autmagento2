package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.us10003.US10003CheckInviteEmailAndRegistratiionLinkTest;
import com.tests.uss10.us10003.US10003ClosePartyTest;
import com.tests.uss10.us10003.US10003VerifyHostPartyCreationEmailTest;

@SuiteClasses({
	US10003VerifyHostPartyCreationEmailTest.class,
	US10003CheckInviteEmailAndRegistratiionLinkTest.class,
	US10003ClosePartyTest.class,	

	
})
@RunWith(Suite.class)
public class US10003Suite {

}
