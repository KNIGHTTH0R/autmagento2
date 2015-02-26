package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3004.US3004Test;
import com.tests.us3004.US3004UserProfileOrderIdTest;
import com.tests.us3004.US3004ValidateOrderBackOfficeTest;
import com.tests.us3004.US3004ValidateOrderEmailTest;

@SuiteClasses({
	US3004Test.class,
	US3004UserProfileOrderIdTest.class,
	US3004ValidateOrderEmailTest.class,
	US3004ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US3004Suite {

}
