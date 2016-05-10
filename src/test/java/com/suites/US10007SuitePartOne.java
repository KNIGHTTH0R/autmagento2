package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.uss10007.US10007AddManuallyBonusOnPartyTest;
import com.tests.uss10.uss10007.US10007ClosePartyAnfVerifyCommissionBonusesTest;
import com.tests.uss10.uss10007.US10007CreateFolowUpPartyForActivePartyTest;
import com.tests.uss10.uss10007.US10007CreateSecondFolowUpPartyForActivePartyTest;
import com.tests.uss10.uss10007.US10007OrderForCustomerAsPartyHostTest;
import com.tests.uss10.uss10007.US10007PlaceSecondOrderAsPartyHostTest;
import com.tests.uss10.uss10007.US10007PlaceThirdOrderAsPartyHostTest;
import com.tests.uss10.uss10007.US10007VerifyFollowUpPartyCreationEmailTest;
import com.tests.uss10.uss10007.US10007VerifyInviteForStylistHostEmailTest;

@SuiteClasses({	
	US10007OrderForCustomerAsPartyHostTest.class,
	US10007PlaceSecondOrderAsPartyHostTest.class,
	US10007PlaceThirdOrderAsPartyHostTest.class,	
	US10007CreateFolowUpPartyForActivePartyTest.class,
	US10007CreateSecondFolowUpPartyForActivePartyTest.class,
	US10007ClosePartyAnfVerifyCommissionBonusesTest.class,
	US10007AddManuallyBonusOnPartyTest.class,
	US10007VerifyInviteForStylistHostEmailTest.class,
	US10007VerifyFollowUpPartyCreationEmailTest.class,
	
})
@RunWith(Suite.class)
public class US10007SuitePartOne {

}
