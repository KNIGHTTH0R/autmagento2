package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us9.us9005.US10009ClosePartyTest;
import com.tests.us9.us9005.US10009UpdatePartyBonusesTest;
import com.tests.us9.us9005.US9005PlaceHostOrderWithTpAndZeroAmountTest;
import com.tests.us9.us9005.US9005ValidateTpOrdersStatusesTest;

@SuiteClasses({
	
	US10009ClosePartyTest.class,
	US10009UpdatePartyBonusesTest.class,
	US9005PlaceHostOrderWithTpAndZeroAmountTest.class,
	US9005ValidateTpOrdersStatusesTest.class,	
})
@RunWith(Suite.class)
public class US9005Suite {

}
