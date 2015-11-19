package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss15.us15002.US15002ApplyCreditMemoOnOrderTest;
import com.tests.uss15.us15002.US15002CancelCreditMemoTest;
import com.tests.uss15.us15002.US15002CancelOrderTest;
import com.tests.uss15.us15002.US15002ChangeCustomersEmailTest;
import com.tests.uss15.us15002.US15002CheckMailchimpConfigTest;
import com.tests.uss15.us15002.US15002CheckRevenuesZeroInMailchimpConfigTest;
import com.tests.uss15.us15002.US15002CheckSubscriberMagentoConfigTest;
import com.tests.uss15.us15002.US15002ConfirmCustomerTest;
import com.tests.uss15.us15002.US15002KoboRegistrationNewsletterSubscribeTest;
import com.tests.uss15.us15002.US15002SubscribedCustomerBuyWithContactBoosterTest;
import com.tests.uss15.us15002.US15002UncancelOrderTest;

@SuiteClasses({
	
	US15002KoboRegistrationNewsletterSubscribeTest.class,
	US15002ConfirmCustomerTest.class,
	US15002ChangeCustomersEmailTest.class,
	US15002SubscribedCustomerBuyWithContactBoosterTest.class,
	US15002CheckSubscriberMagentoConfigTest.class,	
	US15002CheckRevenuesZeroInMailchimpConfigTest.class,
	US15002CancelOrderTest.class,
	US15002CheckRevenuesZeroInMailchimpConfigTest.class,
	US15002UncancelOrderTest.class,
	US15002CheckMailchimpConfigTest.class,
	US15002ApplyCreditMemoOnOrderTest.class,
	US15002CheckRevenuesZeroInMailchimpConfigTest.class,
	US15002CancelCreditMemoTest.class,
	US15002CheckMailchimpConfigTest.class,	
	
})
@RunWith(Suite.class)
public class US15002Suite {

}
