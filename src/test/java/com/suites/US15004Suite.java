package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss15.us15004.US15004ApplyCreditMemoOnOrderTest;
import com.tests.uss15.us15004.US15004CancelCreditMemoTest;
import com.tests.uss15.us15004.US15004CancelOrderTest;
import com.tests.uss15.us15004.US15004CheckMailchimpConfigTest;
import com.tests.uss15.us15004.US15004CheckMailchimpConfigWithZeroRevenuesTest;
import com.tests.uss15.us15004.US15004CheckSubscriberMagentoConfigTest;
import com.tests.uss15.us15004.US15004CompleteOrderTest;
import com.tests.uss15.us15004.US15004ConfirmCustomerTest;
import com.tests.uss15.us15004.US15004OrderForCustomerTest;

@SuiteClasses({
	
	US15004OrderForCustomerTest.class,
	US15004ConfirmCustomerTest.class,
	//here the email should be changed
	US15004CheckSubscriberMagentoConfigTest.class,
	US15004CheckMailchimpConfigWithZeroRevenuesTest.class,
	US15004CancelOrderTest.class,
	US15004CheckMailchimpConfigWithZeroRevenuesTest.class,
	US15004CompleteOrderTest.class,
	US15004CheckMailchimpConfigTest.class,
	US15004ApplyCreditMemoOnOrderTest.class,
	US15004CheckMailchimpConfigWithZeroRevenuesTest.class,
	US15004CancelCreditMemoTest.class,
	US15004CheckMailchimpConfigTest.class,
	
	
})
@RunWith(Suite.class)
public class US15004Suite {

}
