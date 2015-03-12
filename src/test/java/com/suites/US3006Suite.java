package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3006.US3006Test;
import com.tests.us3.us3006.US3006UserProfileOrderIdTest;
import com.tests.us3.us3006.US3006ValidateOrderBackOfficeTest;
import com.tests.us3.us3006.US3006ValidateOrderEmailTest;

@SuiteClasses({
	US3006Test.class,
	US3006UserProfileOrderIdTest.class,
	US3006ValidateOrderEmailTest.class,
	US3006ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US3006Suite {

}
