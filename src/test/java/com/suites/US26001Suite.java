package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss26.US26001VerifyProdNotAvailableForTheMomentInAvReportTest;
import com.tests.uss26.US26001VerifyProdNotAvailableInAvailabilityReportTest;

@SuiteClasses({
	US26001VerifyProdNotAvailableForTheMomentInAvReportTest.class,
	US26001VerifyProdNotAvailableInAvailabilityReportTest.class,
})
@RunWith(Suite.class)
public class US26001Suite {

}
