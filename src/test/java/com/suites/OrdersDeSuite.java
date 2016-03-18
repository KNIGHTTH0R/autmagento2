package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.us10001.US10001CreatePartyWithStylistHostTest;
import com.tests.uss10.us10002.US10002CreatePartyWithCustomerHostTest;
import com.tests.uss10.us10002b.US10002bCreatePartyWithCustomerHostTest;
import com.tests.uss11.us11003.US11003VerifyProductsInPlaceACustomerOrderModal;

@SuiteClasses({
	
	US10001CreatePartyWithStylistHostTest.class,
	US10002CreatePartyWithCustomerHostTest.class,
	US10002bCreatePartyWithCustomerHostTest.class,
	US001Suite.class,
	US002Suite.class,
	US3001Suite.class,
	US3002Suite.class,
	US3003Suite.class,
	US3004Suite.class,
	US3005Suite.class,
	US3006Suite.class,
	US3007Suite.class,
	US3008Suite.class,
	US3009Suite.class,
	
	US6001Suite.class,
	US6001bSuite.class,
	
	
	
	US8001Suite.class,
	US8002Suite.class,
	US8003Suite.class,	
	US8004Suite.class,
	US8005Suite.class,
	US8006Suite.class,
	
	US4001Suite.class,
	US4002Suite.class,
	
	US9001Suite.class,
	US9002Suite.class,
	US9002bSuite.class,
	
	
	
	US11001Suite.class,
	US11002Suite.class,
	US11003VerifyProductsInPlaceACustomerOrderModal.class,
	US11004Suite.class,
	US11005Suite.class,
	US11006Suite.class,
	
	US12001Suite.class,
	
	US13001Suite.class,
	US13002Suite.class,
	US13003Suite.class,
	US13004Suite.class,
	US13005Suite.class,
	US13006Suite.class,
	US13007Suite.class,
	
	US16001Suite.class,
	US16002Suite.class,
	
	
	
})
@RunWith(Suite.class)
public class OrdersDeSuite {

}
