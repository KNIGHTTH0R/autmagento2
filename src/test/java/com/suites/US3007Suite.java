package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3007.US3007SfmNoVatNoSmbBillingShippingAtTest;
import com.tests.us3.us3007.US3007CheckOrderOnStylecoachProfileTest;
import com.tests.us3.us3007.US3007ValidateOrderBackOfficeTest;
import com.tests.us3.us3007.US3007ValidateOrderEmailTest;

@SuiteClasses({
	US3007SfmNoVatNoSmbBillingShippingAtTest.class,
	US3007CheckOrderOnStylecoachProfileTest.class,
	US3007ValidateOrderEmailTest.class,
	US3007ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US3007Suite {

}
