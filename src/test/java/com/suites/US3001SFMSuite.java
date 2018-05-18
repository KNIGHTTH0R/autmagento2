package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsSFMTest;
import com.tests.us3.us3001vdv.US3001SfmOrderWithNoDiscountTestVDV;

@SuiteClasses({
	CreateProductsSFMTest.class,
	US3001SfmOrderWithNoDiscountTestVDV.class,
//	US3001ValidateOrderBackOfficeTestVDV.class,
//	US3001ValidateOrderEmailTestVDV.class
	 
})
@RunWith(Suite.class)
public class US3001SFMSuite {

}
