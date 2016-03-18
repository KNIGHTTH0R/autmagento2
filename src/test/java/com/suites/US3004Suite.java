package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3004.US3004SfmValidVatSmbBillingShippingAtTest;
import com.tests.us3.us3004.US3004ValidateOrderBackOfficeTest;

@SuiteClasses({
	US3004SfmValidVatSmbBillingShippingAtTest.class,
//	US3004CheckOrderOnStylecoachProfileTest.class,
//	US3004ValidateOrderEmailTest.class,
	US3004ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US3004Suite {

}
