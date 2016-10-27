package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3008.US3008SfmScenario3For001DifferenceTest;
import com.tests.us3.us3008.US3008ValidateOrderBackOfficeTest;

@SuiteClasses({
	US3008SfmScenario3For001DifferenceTest.class,
	US3008ValidateOrderBackOfficeTest.class,
})
@RunWith(Suite.class)
public class US3008Suite {

}
