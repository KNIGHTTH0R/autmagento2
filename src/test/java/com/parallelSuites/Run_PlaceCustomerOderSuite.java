package com.parallelSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.suites.US11001PlaceCustomerSuite;
import com.suites.US11002PlaceCustomerSuite;


@SuiteClasses({
	US11001PlaceCustomerSuite.class,
	US11002PlaceCustomerSuite.class
})
@RunWith(Suite.class)
public class Run_PlaceCustomerOderSuite {

}
