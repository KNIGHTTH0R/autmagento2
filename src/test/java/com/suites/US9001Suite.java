package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us9.us9001.US9001PlaceHostOrderWithForthyDiscountsAndJbTest;
import com.tests.us9.us9001.US9001ValidateOrderBackOfficeTest;
import com.tests.uss10.us10001.US10001ClosePartyTest;
import com.tests.uss10.us10001.US10001UpdatePartyBonusesTest;

@SuiteClasses({
	
	US10001ClosePartyTest.class,
	US10001UpdatePartyBonusesTest.class,
	US9001PlaceHostOrderWithForthyDiscountsAndJbTest.class,
//	US9001CheckOrderOnStylecoachProfileTest.class,	
//	US9001ValidateOrderEmailTest.class,	
	US9001ValidateOrderBackOfficeTest.class,	
})
@RunWith(Suite.class)
public class US9001Suite {

}
