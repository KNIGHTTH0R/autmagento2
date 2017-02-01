package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss16.us16004.US16004AddNewContactToStyleCoachTest;
import com.tests.uss16.us16004.US16004BorrowFunctionalityForAllowedStylistInTopTest;
import com.tests.uss16.us16004.US16004ConfirmCustomerTest;
import com.tests.uss16.us16004.US16004RegularCustomerRegistrationTest;
import com.tests.uss16.us16004.US16004RegularCustomerSetProductsInCartAndWishlist;
import com.tests.uss16.us16004.US16004StyleCoachRegistrationTest;





@SuiteClasses({

	US16004StyleCoachRegistrationTest.class,
	US16004AddNewContactToStyleCoachTest.class,
	US16004RegularCustomerRegistrationTest.class,
	US16004ConfirmCustomerTest.class,
	US16004RegularCustomerSetProductsInCartAndWishlist.class,
	US16004BorrowFunctionalityForAllowedStylistInTopTest.class,
	
	
})


@RunWith(Suite.class)
public class US16004Suite {

}
