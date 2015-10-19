package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss22.US22001VerifySCPerformanceNewIpLogicBackendTest;
import com.tests.uss22.US22001VerifySCPerformanceNewIpLogicFrontendTest;

@SuiteClasses({
	US22001VerifySCPerformanceNewIpLogicBackendTest.class,
	US22001VerifySCPerformanceNewIpLogicFrontendTest.class,
})
@RunWith(Suite.class)
public class US22001Suite {

}
