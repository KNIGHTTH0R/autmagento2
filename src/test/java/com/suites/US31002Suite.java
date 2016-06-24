package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss31.uss31002.US31002PartyHostBuysForCustomerTpTest;
import com.tests.uss31.uss31002.US31002ValidateCanceledAndReleasedOrdersInTpGridTest;
import com.tests.uss31.uss31002.US31002ValidatePostponedOrdersInTpGridTest;

@SuiteClasses({
	US31002PartyHostBuysForCustomerTpTest.class,
	US31002ValidatePostponedOrdersInTpGridTest.class,
	US31002ValidateCanceledAndReleasedOrdersInTpGridTest.class,
})
@RunWith(Suite.class)
public class US31002Suite {

}
