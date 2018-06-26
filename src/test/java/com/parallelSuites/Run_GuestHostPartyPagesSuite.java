package com.parallelSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.suites.US10009PartySuite;
import com.suites.US10010PartySuite;
import com.suites.US10011PartySuite;


@SuiteClasses({
	US10009PartySuite.class,
	US10010PartySuite.class,
	US10011PartySuite.class
	
})
@RunWith(Suite.class)
public class Run_GuestHostPartyPagesSuite {

}
