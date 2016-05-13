package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss15.us15004.US15004ApplyCreditMemoOnOrderTest;
import com.tests.uss15.us15004.US15004CancelCreditMemoTest;
import com.tests.uss15.us15004.US15004CheckSubscriberMagentoConfigTest;
import com.tests.uss15.us15004.US15004CompleteOrderTest;
import com.tests.uss15.us15004.US15004ConfirmCustomerTest;
import com.tests.uss15.us15004.US15004OrderZzzProductsForCustomerTest;
import com.tests.uss15.us15004.US15004VerifyDashboardAndJbHistoryAfterCmOrderTest;
import com.tests.uss15.us15004.US15004VerifyDashboardAndJbHistoryCompleteOrderTest;
import com.tests.uss15.us15004.US15004VerifyDashboardAndJbHistoryMemoAppliedOrderTest;

@SuiteClasses({
	
	US15004OrderZzzProductsForCustomerTest.class,
	US15004ConfirmCustomerTest.class,
	US15004CheckSubscriberMagentoConfigTest.class,
//	US15004CheckMailchimpConfigWithZeroRevenuesTest.class,
	US15004CompleteOrderTest.class,
	US15004VerifyDashboardAndJbHistoryCompleteOrderTest.class,	
//	US15004CheckMailchimpConfigTest.class,
	US15004ApplyCreditMemoOnOrderTest.class,
	US15004VerifyDashboardAndJbHistoryMemoAppliedOrderTest.class,
//	US15004CheckMailchimpAfterCmTest.class,
	US15004CancelCreditMemoTest.class,
	US15004VerifyDashboardAndJbHistoryAfterCmOrderTest.class,
//	us15004CheckMailchimpAfterCancelCmTest.class,
	
	
})
@RunWith(Suite.class)
public class US15004Suite {

}
