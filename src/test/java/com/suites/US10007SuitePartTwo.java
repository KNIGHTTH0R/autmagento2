package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.uss10007.US10007CloseFollowUpPartyAnfVerifyCommissionBonusesTest;
import com.tests.uss10.uss10007.US10007OrderForCustomerAsFollowUpPartyHostTest;

@SuiteClasses({	
	US10007OrderForCustomerAsFollowUpPartyHostTest.class,
	US10007CloseFollowUpPartyAnfVerifyCommissionBonusesTest.class,

	
})
@RunWith(Suite.class)
public class US10007SuitePartTwo {

}
