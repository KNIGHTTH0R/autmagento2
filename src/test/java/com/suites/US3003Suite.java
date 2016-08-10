package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3003.US3003DeleteCustomerAddressesTest;
import com.tests.us3.us3003.US3003SfmValidVatNoSmbBillingShippingDeTest;
import com.tests.us3.us3003.US3003ValidateOrderBackOfficeTest;
import com.tests.us3.us3003.US3003ValidateOrderEmailTest;

@SuiteClasses({
	US3003DeleteCustomerAddressesTest.class,
	US3003SfmValidVatNoSmbBillingShippingDeTest.class,
//	US3003CheckOrderOnStylecoachProfileTest.class,
	US3003ValidateOrderBackOfficeTest.class,
	US3003ValidateOrderEmailTest.class,
})
@RunWith(Suite.class)
public class US3003Suite {

}
