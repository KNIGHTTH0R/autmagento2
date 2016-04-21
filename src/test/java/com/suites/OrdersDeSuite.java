package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.us10001.US10001CreatePartyWithStylistHostTest;
import com.tests.uss10.us10001b.US10001bCreatePartyWithStylistHostTest;
import com.tests.uss10.us10002.US10002CreatePartyWithCustomerHostTest;
import com.tests.uss10.us10002b.US10002bCreatePartyWithCustomerHostTest;
import com.tests.uss11.us11001.US11001CreatePartyWithStylistHostTest;
import com.tests.uss11.us11002.US11002CreatePartyWithCustomerHostTest;
import com.tests.uss11.us11003.US11003VerifyProductsInPlaceACustomerOrderModal;
import com.tests.uss11.us11004.US11004CreatePartyWithStylistHostTest;
import com.tests.uss11.us11005.US11005CreatePartyWithStylistHostTest;

@SuiteClasses({
	
	US3002Suite.class,
	
	US10001CreatePartyWithStylistHostTest.class,
	US10001bCreatePartyWithStylistHostTest.class,
	US10002CreatePartyWithCustomerHostTest.class,
	US10002bCreatePartyWithCustomerHostTest.class,
	US11001CreatePartyWithStylistHostTest.class,
	US11002CreatePartyWithCustomerHostTest.class,
	US11004CreatePartyWithStylistHostTest.class,
	US11005CreatePartyWithStylistHostTest.class,
	
	US001Suite.class,
	US002Suite.class,
	US3001Suite.class,
	US3002Suite.class,
	US3003Suite.class,
	US3004Suite.class,
//	US3005Suite.class,
//	US3006Suite.class,
//	US3007Suite.class,
//	US3008Suite.class,
	US3009Suite.class,
	US30010Suite.class,
	
	US6001Suite.class,
	US6001bSuite.class,
	
	US8001Suite.class,
	US8002Suite.class,
	US8003Suite.class,	
	US8004Suite.class,
	US8005Suite.class,
	US8006Suite.class,
	US8007Suite.class,
	
//	US4001Suite.class,
	US4002Suite.class,
	
	US16001Suite.class,
	US16002Suite.class,
	
	US11001Suite.class,
	US11002Suite.class,
	US11003VerifyProductsInPlaceACustomerOrderModal.class,
	US11004Suite.class,
	US11005Suite.class,
	US11006Suite.class,
	US11007Suite.class,
	
	US9001Suite.class,
	US9002Suite.class,
	US9002bSuite.class,
	US9004Suite.class,
	
	US15004Suite.class,
	US12001Suite.class,
	

	
	
	
})
@RunWith(Suite.class)
public class OrdersDeSuite {

}
