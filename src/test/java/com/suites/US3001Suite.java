package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3001.US3001CartSegmentationWithVatTest;
import com.tests.us3001.US3001UserProfileOrderIdTest;
import com.tests.us3001.US3001ValidateOrderBackOfficeTest;
import com.tests.us3001.US3001ValidateOrderEmailTest;

@SuiteClasses({
	US3001CartSegmentationWithVatTest.class,
	US3001UserProfileOrderIdTest.class,
	US3001ValidateOrderEmailTest.class,
	US3001ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US3001Suite {

}
