package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3009.US3009SfmNoVatNoSmbBillingShippingDeTest;
import com.tests.us3.us3009.US3009ValidateOrderBackOfficeTest;

@SuiteClasses({
	US3009SfmNoVatNoSmbBillingShippingDeTest.class,
//	US3009CheckOrderOnStylecoachProfileTest.class,
//	US3009ValidateOrderEmailTest.class,
	US3009ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US3009Suite {

}
