package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us8a.us8002a.US8002aCustomerBuySinglePomAndMultiProductsWithKoBoTest;
import com.tests.us8a.us8002a.US8002aVerifyKoboSingleArticleAndPomFlagsTest;

@SuiteClasses({
	US8002aCustomerBuySinglePomAndMultiProductsWithKoBoTest.class,
	US8002aVerifyKoboSingleArticleAndPomFlagsTest.class,	
	
})
@RunWith(Suite.class)
public class US8002aSuite {

}