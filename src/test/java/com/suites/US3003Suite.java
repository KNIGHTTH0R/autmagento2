package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3003.US3003SfmValidVatNoSmbBillingShippingDeTest;
import com.tests.us3.us3003.US3003ValidateOrderBackOfficeTest;

@SuiteClasses({
	US3003SfmValidVatNoSmbBillingShippingDeTest.class,
//	US3003CheckOrderOnStylecoachProfileTest.class,
//	US3003ValidateOrderEmailTest.class,
	US3003ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US3003Suite {

}
