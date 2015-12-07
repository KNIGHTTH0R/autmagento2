package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss25.US25001AddNewContactPlzValidationTest;
import com.tests.uss25.US25001BorrowCartPlzValidationTest;
import com.tests.uss25.US25001ClosePartyTest;
import com.tests.uss25.US25001CreatePartyWithNewContactPlzValidationTest;
import com.tests.uss25.US25001HostOrderPlzValidationTest;
import com.tests.uss25.US25001OrderForCustomerPlzValidationTest;
import com.tests.uss25.US25001PlaceCustomerOrderPlzValidationTest;
import com.tests.uss25.US25001RegularCartPlzValidationTest;
import com.tests.uss25.US25001RegularCustRegPlzRegistrationTest;
import com.tests.uss25.US25001ShopForMyselfPlzValidationTest;
import com.tests.uss25.US25001StylistRegistrationPlzValidationTest;

@SuiteClasses({
	US25001CreatePartyWithNewContactPlzValidationTest.class,
	US25001StylistRegistrationPlzValidationTest.class,
	US25001RegularCustRegPlzRegistrationTest.class,
	US25001AddNewContactPlzValidationTest.class,
//	US25001KoboCampaignRegistrationPlzValidationTest.class,
	US25001ShopForMyselfPlzValidationTest.class,
	US25001OrderForCustomerPlzValidationTest.class,
	US25001BorrowCartPlzValidationTest.class,
	US25001RegularCartPlzValidationTest.class,
	US25001PlaceCustomerOrderPlzValidationTest.class,
	US25001ClosePartyTest.class,
	US25001HostOrderPlzValidationTest.class,
	
})
@RunWith(Suite.class)
public class US25001InvalidPlzValidationsSuite {

}
