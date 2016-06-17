package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss31.uss31001.US31001PartyHostBuysForCustomerTpTest;
import com.tests.uss31.uss31001.US31001ValidateCanceledAndPaymentCompleteOrdersInTpGridTest;
import com.tests.uss31.uss31001.US31001ValidateCanceledAndReleasedOrdersInTpGridTest;
import com.tests.uss31.uss31001.US31001ValidateOrdersInTpGridTest;
import com.tests.uss31.uss31001.US31001ValidatePostponedOrdersInTpGridTest;

@SuiteClasses({
	US31001PartyHostBuysForCustomerTpTest.class,
	US31001ValidateOrdersInTpGridTest.class,
	US31001ValidatePostponedOrdersInTpGridTest.class,
	US31001ValidateCanceledAndReleasedOrdersInTpGridTest.class,
	US31001ValidateCanceledAndPaymentCompleteOrdersInTpGridTest.class
})
@RunWith(Suite.class)
public class US30001Suite {

}
