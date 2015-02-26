package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us4002.US4002Test;
import com.tests.us4002.US4002UserProfileOrderIdTest;
import com.tests.us4002.US4002ValidateOrderBackOfficeTest;
import com.tests.us4002.US4002ValidateOrderEmailTest;

@SuiteClasses({
	US4002Test.class,
	US4002UserProfileOrderIdTest.class,
	US4002ValidateOrderEmailTest.class,
	US4002ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US4002Suite {

}
