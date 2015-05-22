package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss12.US12001ChechUsesPerCouponAfterSubscriptionUpgradeTest;
import com.tests.uss12.US12001StylistActivationTest;
import com.tests.uss12.US12001KoboSubscriptionOrderEmailTest;
import com.tests.uss12.US12001KoboSubscriptionTest;
import com.tests.uss12.US12001KoboSubscriptionUpgradeOrderEmailTest;
import com.tests.uss12.US12001KoboSubscriptionUpgradeTest;
import com.tests.uss12.US12001MarkAsPaidKoboOrderTest;
import com.tests.uss12.US12001StyleCoachRegistrationTest;


@SuiteClasses({
	US12001StyleCoachRegistrationTest.class,
	US12001StylistActivationTest.class,
	US12001KoboSubscriptionTest.class,
	US12001MarkAsPaidKoboOrderTest.class,
	US12001KoboSubscriptionOrderEmailTest.class,
	US12001KoboSubscriptionUpgradeTest.class,
	US12001KoboSubscriptionUpgradeOrderEmailTest.class,
	US12001ChechUsesPerCouponAfterSubscriptionUpgradeTest.class,
	
})
@RunWith(Suite.class)
public class US12001Suite {

}
