package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us9.us9002.US9002ClosePartyTest;
import com.tests.uss10.uss10008.US10008AddManuallyBonusOnPartyTest;

@SuiteClasses({	
//	US10008VerifyHostPartyCreationEmailTest.class,
//	US10008OrderForCustomerAsPartyHostTest.class,
//	US10008CloseVirginPartyAnfVerifyCommissionBonusesTest.class,

	
	/*US10008AddManuallyBonusOnPartyTest.class,*/
//	
//	US10008ReopenPartyTest.class,
//	US10008PlaceAnotherOrderForCustomerAsPartyHostTest.class,
/*	US10008CloseAgainPartyAnfVerifyCommissionBonusesTest.class,*/
	// place an host order 
	// cancel an tp order
	//verify host reword
	
	//US9002ClosePartyTest.class,
//	US10008AddManuallyBonusOnPartyTest.class,
})
@RunWith(Suite.class)
public class US10008Suite {

}
