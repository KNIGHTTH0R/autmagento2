package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss15.us15001.US15001CheckMailchimpConfigTest;
import com.tests.uss15.us15001.US15001CheckSubscriberMagentoConfigTest;
import com.tests.uss15.us15001.US15001ConfirmCustomerTest;
import com.tests.uss15.us15001.US15001SubscribeToNewsletterTest;

@SuiteClasses({
	US15001SubscribeToNewsletterTest.class,
	US15001ConfirmCustomerTest.class,
	US15001CheckSubscriberMagentoConfigTest.class,	
	US15001CheckMailchimpConfigTest.class,	
	
})
@RunWith(Suite.class)
public class US15001Suite {

}
