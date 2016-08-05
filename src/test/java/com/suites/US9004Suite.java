package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us9.us9004.US9004PlaceHostOrderWithTpTest;
import com.tests.us9.us9004.US9004ValidateOrdersInBackendTest;
import com.tests.uss10.us10001b.US10001bClosePartyTest;
import com.tests.uss10.us10001b.US10001bUpdatePartyBonusesTest;

@SuiteClasses({
	
//	US10001bClosePartyTest.class,
	US10001bUpdatePartyBonusesTest.class,
	US9004PlaceHostOrderWithTpTest.class,
	US9004ValidateOrdersInBackendTest.class,	
})
@RunWith(Suite.class)
public class US9004Suite {

}
