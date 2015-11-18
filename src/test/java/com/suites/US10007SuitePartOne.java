package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.uss10007.US10007ClosePartyAnfVerifyCommissionBonusesTest;
import com.tests.uss10.uss10007.US10007CreateFolowUpPartyForActivePartyTest;
import com.tests.uss10.uss10007.US10007CreateSecondFolowUpPartyForActivePartyTest;
import com.tests.uss10.uss10007.US10007OrderForCustomerAsPartyHostTest;
import com.tests.uss10.uss10007.US10007PlacePippaTermPurchaseOrderAsPartyHostTest;
import com.tests.uss10.uss10007.US10007PlaceTermPurchaseOrderAsPartyHostTest;

@SuiteClasses({	
	US10007OrderForCustomerAsPartyHostTest.class,
	US10007PlacePippaTermPurchaseOrderAsPartyHostTest.class,
	US10007PlaceTermPurchaseOrderAsPartyHostTest.class,	
	US10007CreateFolowUpPartyForActivePartyTest.class,
	US10007CreateSecondFolowUpPartyForActivePartyTest.class,
	US10007ClosePartyAnfVerifyCommissionBonusesTest.class,
	
})
@RunWith(Suite.class)
public class US10007SuitePartOne {

}
