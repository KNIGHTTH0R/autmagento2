package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.us10001b.US10001bCreatePartyWithStylistHostTest;
import com.tests.uss10.us10002b.US10002bCreatePartyWithCustomerHostTest;
import com.tests.uss10.uss10007.US10007CreatePartyWithStylistHostTest;
import com.tests.uss10.uss10008.US10008CreatePartyWithExistingContactHostTest;

@SuiteClasses({
	
	US10001bCreatePartyWithStylistHostTest.class,
	US10002bCreatePartyWithCustomerHostTest.class,
	US10007CreatePartyWithStylistHostTest.class,
	US10008CreatePartyWithExistingContactHostTest.class,
//	US30001GetInitialOpenIps.class,
	
	US3001Suite.class,
//	US3003Suite.class,
	US3004Suite.class,
//	US3006Suite.class,
	US3007Suite.class,
//	US3009Suite.class,
	US30010Suite.class,
	
	US6001Suite.class,
//	US6001bSuite.class,
	
//	US8001Suite.class,
	US8002Suite.class,
	US8003Suite.class,	
//	US8004Suite.class,
	US8005Suite.class,
	US8006Suite.class,
	US8007Suite.class,
//	US8008Suite.class,
	
//	US4001Suite.class,
	US4002Suite.class,
	

	
	US11001Suite.class,
	US11002Suite.class,
//	US11003VerifyProductsInPlaceACustomerOrderModal.class,
	US11004Suite.class,
//	US11005Suite.class,
	US11006Suite.class,
	US11007Suite.class,
//	US11008Suite.class,
//	
	US10007SuitePartOne.class,
//	US10008Suite.class,
//	
//	US9001Suite.class,
//	US9002Suite.class,
//	US9002bSuite.class,
	US9004Suite.class,
//	US9005Suite.class,
//	
//	US15004Suite.class,
//	US12001Suite.class,
	
//	US30001VerifyOpenIpsAfterNewOrdersTest.class,
	
})
@RunWith(Suite.class)
public class OrdersDeSuite {

}
