package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsSFMTest;
import com.tests.us3.us3003vdv.US3003SfmWithLbAppliedTestVDV;
import com.tests.us3.us3003vdv.US3003ValidateOrderBackOfficeTestVDV;
import com.tests.us3.us3003vdv.US3003ValidateOrderEmailTestVDV;

@SuiteClasses({
	CreateProductsSFMTest.class,
	US3003SfmWithLbAppliedTestVDV.class,
	US3003ValidateOrderBackOfficeTestVDV.class,
	//US3003ValidateOrderEmailTestVDV.class
	 
})
@RunWith(Suite.class)
public class US3003SFMSuite {

}
