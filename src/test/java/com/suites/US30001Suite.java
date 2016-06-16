package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;





import com.tests.uss30.US11007ValidateCanceledAndReleasedOrdersInTpGridTest;
import com.tests.uss30.US30001PartyHostBuysForCustomerTpTest;
import com.tests.uss30.US30001ValidateOrdersInTpGridTest;
import com.tests.uss30.US30001ValidatePostponedOrdersInTpGridTest;

@SuiteClasses({
	US30001PartyHostBuysForCustomerTpTest.class,
	US30001ValidateOrdersInTpGridTest.class,
	US30001ValidatePostponedOrdersInTpGridTest.class,
	US11007ValidateCanceledAndReleasedOrdersInTpGridTest.class,
})
@RunWith(Suite.class)
public class US30001Suite {

}
