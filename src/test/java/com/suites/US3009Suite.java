package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3009.US3009Test;
import com.tests.us3.us3009.US3009UserProfileOrderIdTest;
import com.tests.us3.us3009.US3009ValidateOrderBackOfficeTest;
import com.tests.us3.us3009.US3009ValidateOrderEmailTest;

@SuiteClasses({
	US3009Test.class,
	US3009UserProfileOrderIdTest.class,
	US3009ValidateOrderEmailTest.class,
	US3009ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US3009Suite {

}
