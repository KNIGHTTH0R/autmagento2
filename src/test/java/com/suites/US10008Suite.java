package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.uss10008.US10008AddManuallyBonusOnPartyTest;
import com.tests.uss10.uss10008.US10008CloseAgainPartyAnfVerifyCommissionBonusesTest;
import com.tests.uss10.uss10008.US10008CloseVirginPartyAnfVerifyCommissionBonusesTest;
import com.tests.uss10.uss10008.US10008OrderForCustomerAsPartyHostTest;
import com.tests.uss10.uss10008.US10008PlaceAnotherOrderForCustomerAsPartyHostTest;
import com.tests.uss10.uss10008.US10008ReopenPartyTest;
import com.tests.uss10.uss10008.US10008VerifyHostPartyCreationEmailTest;

@SuiteClasses({	
//	US10008VerifyHostPartyCreationEmailTest.class,
//	US10008OrderForCustomerAsPartyHostTest.class,
//	US10008CloseVirginPartyAnfVerifyCommissionBonusesTest.class,
	US10008AddManuallyBonusOnPartyTest.class,
//	
//	US10008ReopenPartyTest.class,
//	US10008PlaceAnotherOrderForCustomerAsPartyHostTest.class,
	US10008CloseAgainPartyAnfVerifyCommissionBonusesTest.class,
	// place an host order 
	// cancel an tp order
	//verify host reword
})
@RunWith(Suite.class)
public class US10008Suite {

}
