package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3003.US3003CartSegmentationWithVatBillingShippingDeTest;
import com.tests.us3003.US3003UserProfileOrderIdTest;
import com.tests.us3003.US3003ValidateOrderBackOfficeTest;
import com.tests.us3003.US3003ValidateOrderEmailTest;

@SuiteClasses({
	US3003CartSegmentationWithVatBillingShippingDeTest.class,
	US3003UserProfileOrderIdTest.class,
	US3003ValidateOrderEmailTest.class,
	US3003ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US3003Suite {

}
