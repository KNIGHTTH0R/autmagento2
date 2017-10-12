package com.parallelSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.suites.US10007SuitePartOne;
import com.suites.US9001Suite;
import com.suites.US9002Suite;
import com.suites.US9002bSuite;
import com.suites.US9004Suite;
import com.suites.US9005Suite;
import com.suites.US9006Suite;
import com.suites.US9007Suite;


@SuiteClasses({
	//create specific products
	//create specific parties
	
	US10007SuitePartOne.class,
	US9001Suite.class,
	US9002Suite.class,
	US9002bSuite.class,
	US9004Suite.class,
	US9005Suite.class,
	US9006Suite.class,
	US9007Suite.class,
})
@RunWith(Suite.class)
public class Run_PlaceHostOderSuite {

}
