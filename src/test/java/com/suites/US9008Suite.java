package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us9.us9008.US9008CancelAnOrderWithTp;
import com.tests.us9.us9008.US9008PlaceHostOrderWithTpTest;
import com.tests.us9.us9008.US9008ValidateHostRewordAfterCancelAnOrderWithTpTest;
import com.tests.us9.us9008.US9008ValidateOrdersInBackendTest;


@SuiteClasses({
//	US10008CreatePartyWithExistingContactHostTest.class,
//	US10008OrderForCustomerAsPartyHostTest.class,
//	US10008PlaceAnotherOrderForCustomerAsPartyHostTest.class,

	
	//1 and 2 data preparation for :
//	US10008CloseVirginPartyAnfVerifyCommissionBonusesTest.class,
//	US10008CloseAgainPartyAnfVerifyCommissionBonusesTest.class,
//	US10008AddManuallyBonusOnPartyTest.class,
	
	US9008PlaceHostOrderWithTpTest.class,
	US9008ValidateOrdersInBackendTest.class,
	US9008CancelAnOrderWithTp.class,
	US9008ValidateHostRewordAfterCancelAnOrderWithTpTest.class,
	 
})
@RunWith(Suite.class)
public class US9008Suite {

}
