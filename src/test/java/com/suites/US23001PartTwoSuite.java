package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss23.US23001VerifyStockSyncAfterOrderImportTest;


@SuiteClasses({
	US23001VerifyStockSyncAfterOrderImportTest.class,
})
@RunWith(Suite.class)
public class US23001PartTwoSuite {

}
