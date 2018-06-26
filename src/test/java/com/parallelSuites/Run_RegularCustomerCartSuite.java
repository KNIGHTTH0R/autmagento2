package com.parallelSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.suites.US8001RegularCustomerSuite;
import com.suites.US8002RegularCustomerSuite;


@SuiteClasses({
	//CreateSpecificProducts
	US8001RegularCustomerSuite.class,
	US8002RegularCustomerSuite.class
})
@RunWith(Suite.class)
public class Run_RegularCustomerCartSuite {

}
