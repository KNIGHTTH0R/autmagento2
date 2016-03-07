package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss16.us16002.US16002ActivateFreeShippingRuleTest;
import com.tests.uss16.us16002.US16002DeactivatefreeShippingRuleTest;
import com.tests.uss16.us16002.US16002StyleCoachBorrowsWithFreeShippingTest;

@SuiteClasses({
	US16002ActivateFreeShippingRuleTest.class,
	US16002StyleCoachBorrowsWithFreeShippingTest.class,	
	US16002DeactivatefreeShippingRuleTest.class,	
})
@RunWith(Suite.class)
public class US16002Suite {

}
