package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3002.US3002BuyProductsForTheFirstTimeTest;
import com.tests.us3.us3002.US3002SfmValidVatNoSmbBillingDeShippingAtTest;
import com.tests.us3.us3002.US3002ValidateOrderBackOfficeTest;

@SuiteClasses({
	US3002BuyProductsForTheFirstTimeTest.class,
	US3002SfmValidVatNoSmbBillingDeShippingAtTest.class,
//	US3002CheckOrderOnStylecoachProfileTest.class,
//	US3002ValidateOrderEmailTest.class,
	US3002ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US3002Suite {

}
