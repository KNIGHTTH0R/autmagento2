package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.uss10007.US10007AddManuallyBonusOnPartyTest;
import com.tests.uss10.uss10007.US10007ClosePartyAnfVerifyCommissionBonusesTest;

@SuiteClasses({	
//	US10007CreateFolowUpPartyForActivePartyTest.class,
//	US10007CreateSecondFolowUpPartyForActivePartyTest.class,
	US10007ClosePartyAnfVerifyCommissionBonusesTest.class,
	US10007AddManuallyBonusOnPartyTest.class,
//	US10007VerifyInviteForStylistHostEmailTest.class,
//	US10007VerifyFollowUpPartyCreationEmailTest.class,
	
})
@RunWith(Suite.class)
public class US10007SuitePartOne {
	
}
