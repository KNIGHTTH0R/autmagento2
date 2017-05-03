package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsTest;
import com.tests.us6.us6006.US6006ScBuyProductwithSpecialPriceTest;

@SuiteClasses({
//	CreateProductsTest.class,
//	BorrowCartSuite.class, 
	
	
	US6001Suite.class,
	US6001bSuite.class,
	US6002Suite.class,
	US6002bSuite.class,
	US6003Suite.class,
	US6006ScBuyProductwithSpecialPriceTest.class,
})
@RunWith(Suite.class)
public class QuickTestsSuite3 {
	
}
