package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us5.us5001.US5001FacebookCustomerDeleteTest;


/**
 * This suite does not work in staging-aut.
 * @author voicu.vac
 *
 */
@SuiteClasses({
	US5001FacebookCustomerDeleteTest.class,
	
})
@RunWith(Suite.class)
public class US5000Suite {

}
