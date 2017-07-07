package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss10.uss10002.US10002AddManuallyBonusOnPartyTest;

@SuiteClasses({	
	US10002AddManuallyBonusOnPartyTest.class,
})
@RunWith(Suite.class)
public class US10002Suite {

}
