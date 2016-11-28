package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss26.uss26001.US26001VerifyProdNotAvailableForTheMomentInAvReportTest;
import com.tests.uss26.uss26001.US26001VerifyProdNotAvailableInAvailabilityReportTest;
import com.tests.uss26.uss26001.US26001VerifyTpProductsForAllowedForTpStylistTest;
import com.tests.uss26.uss26001.US26001VerifyTpProductsForNotAllowedForTpStylistTest;

@SuiteClasses({
	US26001VerifyProdNotAvailableForTheMomentInAvReportTest.class,
	US26001VerifyProdNotAvailableInAvailabilityReportTest.class,
	US26001VerifyTpProductsForAllowedForTpStylistTest.class,
	US26001VerifyTpProductsForNotAllowedForTpStylistTest.class,
})
@RunWith(Suite.class)
public class US26001Suite {

}
