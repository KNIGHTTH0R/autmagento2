package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss11.us11008.US11008PartyHostBuyWithTpAndZeroAmountTest;
import com.tests.uss11.us11008.US11008ValidateTpOrdersStatusesTest;

@SuiteClasses({
	US11008PartyHostBuyWithTpAndZeroAmountTest.class,
	US11008ValidateTpOrdersStatusesTest.class,	
})
@RunWith(Suite.class)
public class US11008Suite {

}
