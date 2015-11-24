package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss15.us15003.US15003ApplyCreditMemoOnOrderTest;
import com.tests.uss15.us15003.US15003CancelCreditMemoTest;
import com.tests.uss15.us15003.US15003CancelOrderTest;
import com.tests.uss15.us15003.US15003ChangeCustomersEmailTest;
import com.tests.uss15.us15003.US15003CheckMailchimpConfigTest;
import com.tests.uss15.us15003.US15003CheckRevenuesZeroInMailchimpConfigTest;
import com.tests.uss15.us15003.US15003CheckSubscriberMagentoConfigTest;
import com.tests.uss15.us15003.US15003ConfirmCustomerTest;
import com.tests.uss15.us15003.US15003KoboSubscriptionTest;
import com.tests.uss15.us15003.US15003MarkAsPaidKoboOrderTest;
import com.tests.uss15.us15003.US15003MarkStarterKitOrderAsPaidTest;
import com.tests.uss15.us15003.US15003StyleCoachRegistrationTest;
import com.tests.uss15.us15003.US15003SubscribedStyleCoachCheckoutProcessTest;
import com.tests.uss15.us15003.US15003UncancelOrderTest;

@SuiteClasses({
	
	US15003StyleCoachRegistrationTest.class,
	US15003ConfirmCustomerTest.class,
	US15003ChangeCustomersEmailTest.class,
	US15003MarkStarterKitOrderAsPaidTest.class,
	US15003KoboSubscriptionTest.class,
	US15003MarkAsPaidKoboOrderTest.class,
	US15003SubscribedStyleCoachCheckoutProcessTest.class,
	US15003CheckSubscriberMagentoConfigTest.class,
	US15003CheckRevenuesZeroInMailchimpConfigTest.class,
	US15003CancelOrderTest.class,
	US15003CheckRevenuesZeroInMailchimpConfigTest.class,
	US15003UncancelOrderTest.class,
	US15003CheckMailchimpConfigTest.class,
	US15003ApplyCreditMemoOnOrderTest.class,
	US15003CheckRevenuesZeroInMailchimpConfigTest.class,
	US15003CancelCreditMemoTest.class,
	US15003CheckMailchimpConfigTest.class,
	
})
@RunWith(Suite.class)
public class US15003Suite {

}
