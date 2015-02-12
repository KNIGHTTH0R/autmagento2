package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us4.US004CartSegmentationWithVatBillingDifferentFromShipping;
import com.tests.us4.US004UserProfileOrderIdTest;
import com.tests.us4.US004ValidateOrderBackOfficeTest;
import com.tests.us4.US004ValidateOrderEmailTest;

@SuiteClasses({
	US004CartSegmentationWithVatBillingDifferentFromShipping.class,
	US004UserProfileOrderIdTest.class,
	US004ValidateOrderEmailTest.class,
	US004ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US004Suite {

}
