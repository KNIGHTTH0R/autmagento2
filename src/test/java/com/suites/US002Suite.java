package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us2.US002CartSegmentationLogicTest;
import com.tests.us2.US002UserProfileOrderIdTest;
import com.tests.us2.US002ValidateOrderBackOfficeTest;
import com.tests.us2.US002ValidateOrderEmailTest;


@SuiteClasses({
	US002CartSegmentationLogicTest.class,
	US002UserProfileOrderIdTest.class,
	US002ValidateOrderBackOfficeTest.class,
	US002ValidateOrderEmailTest.class,
})
@RunWith(Suite.class)
public class US002Suite {

}
