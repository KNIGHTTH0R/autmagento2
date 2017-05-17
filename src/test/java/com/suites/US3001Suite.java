package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3001.US3001BuyProductsForTheFirstTimeTest;
import com.tests.us3.us3001.US3001CheckOrderOnStylecoachProfileTest;
import com.tests.us3.us3001.US3001ReorderSfmValidVatNoSmbBillingShippingNotDeTest;
import com.tests.us3.us3001.US3001SfmValidVatNoSmbBillingShippingNotDeTest;


@SuiteClasses({
	US3001BuyProductsForTheFirstTimeTest.class,
	US3001SfmValidVatNoSmbBillingShippingNotDeTest.class,
	US3001CheckOrderOnStylecoachProfileTest.class,
//	US3001ValidateOrderEmailTest.class,
//	US3001ReorderSfmValidVatNoSmbBillingShippingNotDeTest.class,
	
	
})
@RunWith(Suite.class)
public class US3001Suite {

}