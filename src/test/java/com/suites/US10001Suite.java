package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.us10001.US10001ClosePartyTest;
import com.tests.uss10.us10001.US10001CreatePartyWithStylistHostTest;
import com.tests.uss10.us10001.US10001UpdatePartyBonusesTest;

@SuiteClasses({
	US10001CreatePartyWithStylistHostTest.class,
	US10001UpdatePartyBonusesTest.class,
	US10001ClosePartyTest.class,
	
})
@RunWith(Suite.class)
public class US10001Suite {

}
