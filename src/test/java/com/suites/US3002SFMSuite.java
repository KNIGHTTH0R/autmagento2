package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsSFMTest;
import com.tests.us3.us3002vdv.US3002SfmOrderWithLBandMarketingDiscountTestVDV;
import com.tests.us3.us3002vdv.US3002ValidateOrderBackOfficeTestVDV;

@SuiteClasses({
	CreateProductsSFMTest.class,
	US3002SfmOrderWithLBandMarketingDiscountTestVDV.class,
	US3002ValidateOrderBackOfficeTestVDV.class,
	//US3002ValidateOrderEmailTestVDV.class
	 
})
@RunWith(Suite.class)
public class US3002SFMSuite {

}
