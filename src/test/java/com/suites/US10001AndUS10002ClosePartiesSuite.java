package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.us10001.US10001ClosePartyTest;
import com.tests.uss10.us10001.US10001UpdatePartyBonusesTest;
import com.tests.uss10.us10002.US10002ClosePartyTest;
import com.tests.uss10.us10002.US10002UpdatePartyBonusesTest;

@SuiteClasses({	
	US10001ClosePartyTest.class,
	US10001UpdatePartyBonusesTest.class,
	US10002ClosePartyTest.class,	
	US10002UpdatePartyBonusesTest.class,
	
})
@RunWith(Suite.class)
public class US10001AndUS10002ClosePartiesSuite {

}
