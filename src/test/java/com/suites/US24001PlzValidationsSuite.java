package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss24.US24001AddNewContactPlzValidationTest;
import com.tests.uss24.US24001BorrowCartPlzValidationTest;
import com.tests.uss24.US24001ClosePartyTest;
import com.tests.uss24.US24001CreatePartyWithNewContactPlzValidationTest;
import com.tests.uss24.US24001HostOrderPlzValidationTest;
import com.tests.uss24.US24001OrderForCustomerPlzValidationTest;
import com.tests.uss24.US24001PlaceCustomerOrderPlzValidationTest;
import com.tests.uss24.US24001RegularCartPlzValidationTest;
import com.tests.uss24.US24001RegularCustRegPlzRegistrationTest;
import com.tests.uss24.US24001ShopForMyselfPlzValidationTest;
import com.tests.uss24.US24001StylistRegistrationPlzValidationTest;

@SuiteClasses({
	
	US24001CreatePartyWithNewContactPlzValidationTest.class,
	US24001StylistRegistrationPlzValidationTest.class,
	US24001RegularCustRegPlzRegistrationTest.class,
	US24001AddNewContactPlzValidationTest.class,
//	US24001KoboCampaignRegistrationPlzValidationTest.class,
	US24001ShopForMyselfPlzValidationTest.class,
	US24001OrderForCustomerPlzValidationTest.class,
	US24001BorrowCartPlzValidationTest.class,
	US24001RegularCartPlzValidationTest.class,
	US24001PlaceCustomerOrderPlzValidationTest.class,
	US24001ClosePartyTest.class,
	US24001HostOrderPlzValidationTest.class,
	
})
@RunWith(Suite.class)
public class US24001PlzValidationsSuite {

}
