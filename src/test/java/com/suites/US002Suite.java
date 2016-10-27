package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us2.US002CartSegmentationLogicTest;
import com.tests.us2.US002ValidateOrderBackOfficeTest;


@SuiteClasses({
	US002CartSegmentationLogicTest.class,
//	US002CheckOrderOnStylecoachProfileTest.class,
//	US002ValidateOrderEmailTest.class,
	US002ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US002Suite {

}
