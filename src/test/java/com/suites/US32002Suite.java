package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss32.us32002.US32002ActivateTermPurchaseForAllProductsTest;
import com.tests.uss32.us32002.US32002ClosePartyTest;
import com.tests.uss32.us32002.US32002CreatePartyWithStylistHostTest;
import com.tests.uss32.us32002.US32002PlaceCustomerOrderAllowedForTP;
import com.tests.uss32.us32002.US32002PlaceHostOrderAllowedForTP;
import com.tests.uss32.us32002.US32002RegularOrderAllowedForTPTest;

@SuiteClasses({
	US32002ActivateTermPurchaseForAllProductsTest.class,
	US32002CreatePartyWithStylistHostTest.class,
	US32002RegularOrderAllowedForTPTest.class,
	US32002PlaceCustomerOrderAllowedForTP.class,
	US32002ClosePartyTest.class,
	US32002PlaceHostOrderAllowedForTP.class

	
})
@RunWith(Suite.class)
public class US32002Suite {

}
