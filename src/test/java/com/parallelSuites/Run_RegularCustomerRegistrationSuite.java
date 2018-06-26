package com.parallelSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.suites.US7001CustomerSuite;
import com.suites.US7002CustomerSuite;
import com.suites.US7003CustomerSuite;
import com.suites.US7004CustomerSuite;


@SuiteClasses({
	US7001CustomerSuite.class,
	US7002CustomerSuite.class,
	US7003CustomerSuite.class,
	US7004CustomerSuite.class,
	
})
@RunWith(Suite.class)
public class Run_RegularCustomerRegistrationSuite {

}
