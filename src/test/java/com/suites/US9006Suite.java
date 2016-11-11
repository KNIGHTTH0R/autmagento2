package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us9.us9006.US90006ClosePartyTest;
import com.tests.us9.us9006.US9006DeactivateDiscountOnCartRuleTest;
import com.tests.us9.us9006.US9006PlaceHostOrderSpecialCaseTest;

@SuiteClasses({
	US90006ClosePartyTest.class,
	US9006PlaceHostOrderSpecialCaseTest.class,
	US9006DeactivateDiscountOnCartRuleTest.class,
	
})
@RunWith(Suite.class)
public class US9006Suite {

}
