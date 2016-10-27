package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss31.uss31004.US31004ActivateAutomatedOnlyReleaseCronTest;
import com.tests.uss31.uss31004.US31004PartyHostBuysForCustomerTpTest;
import com.tests.uss31.uss31004.US31004ValidateCanceledAndReleasedOrdersInTpGridTest;
import com.tests.uss31.uss31004.US31004ValidatePostponedOrdersInTpGridTest;

@SuiteClasses({
	US31004ActivateAutomatedOnlyReleaseCronTest.class,
	US31004PartyHostBuysForCustomerTpTest.class,
	US31004ValidatePostponedOrdersInTpGridTest.class,
	US31004ValidateCanceledAndReleasedOrdersInTpGridTest.class,
})
@RunWith(Suite.class)
public class US31004Suite {

}
