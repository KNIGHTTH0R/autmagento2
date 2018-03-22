package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsSFMTest;
import com.tests.us3.us3001vdv.US3001SfmOrderWithNoDiscountTest;
import com.tests.us3.us3001vdv.US3001ValidateOrderBackOfficeTest;
import com.tests.us3.us3001vdv.US3001ValidateOrderEmailTest;

@SuiteClasses({
	CreateProductsSFMTest.class,
	US3001SfmOrderWithNoDiscountTest.class,
	US3001ValidateOrderBackOfficeTest.class,
//	US3001ValidateOrderEmailTest.class
	 
})
@RunWith(Suite.class)
public class US3001_SFM {

}
