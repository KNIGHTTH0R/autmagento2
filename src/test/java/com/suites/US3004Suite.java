package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3004.US3004CartSegmentationWithVatAndSmbBillingShippingAtTest;
import com.tests.us3004.US3004UserProfileOrderIdTest;
import com.tests.us3004.US3004ValidateOrderBackOfficeTest;
import com.tests.us3004.US3004ValidateOrderEmailTest;

@SuiteClasses({
	US3004CartSegmentationWithVatAndSmbBillingShippingAtTest.class,
	US3004UserProfileOrderIdTest.class,
	US3004ValidateOrderBackOfficeTest.class,
	US3004ValidateOrderEmailTest.class,
})
@RunWith(Suite.class)
public class US3004Suite {

}
