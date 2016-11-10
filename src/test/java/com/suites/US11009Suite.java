package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss11.us11009.US11009DeactivateDiscountOnCartRuleTest;
import com.tests.uss11.us11009.US11009PartyHostBuysForCustomerSpecialCaseTest;

@SuiteClasses({
	US11009PartyHostBuysForCustomerSpecialCaseTest.class,
	US11009DeactivateDiscountOnCartRuleTest.class,	
})
@RunWith(Suite.class)
public class US11009Suite {

}
