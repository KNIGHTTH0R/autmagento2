package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us5.US5001FacebookCustomerDeleteTest;
import com.tests.us5.US5001FacebookLoginTest;
import com.tests.us5.US5001ValidateEmailTest;


/**
 * This suite does not work in staging-aut.
 * @author voicu.vac
 *
 */
@SuiteClasses({
	US5001FacebookLoginTest.class,
	US5001ValidateEmailTest.class,
	US5001FacebookCustomerDeleteTest.class,
})
@RunWith(Suite.class)
public class US5000Suite {

}
