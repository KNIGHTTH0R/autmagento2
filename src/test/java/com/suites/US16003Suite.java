package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss16.us16003DataPreparation.US16003AddNewContactToStyleCoachTest;
import com.tests.uss16.us16003DataPreparation.US16003ChechEmailAndAcceptInvitationTest;
import com.tests.uss16.us16003DataPreparation.US16003ConfirmCustomerTest;
import com.tests.uss16.us16003DataPreparation.US16003CreatePartyWithNewContactHostTest;
import com.tests.uss16.us16003DataPreparation.US16003RegularCustomerRegistrationTest;
import com.tests.uss16.us16003DataPreparation.US16003RegularCustomerSetProductsInCartAndWishlist;
import com.tests.uss16.us16003DataPreparation.US16003StyleCoachRegistrationTest;
import com.tests.uss16.us16004a.US16004ValidateOrderWithDefaultTopAndTopPackageEmailTest;
import com.tests.uss16.us16004a.US16004aCancelOrderDefaultTopAndCustomPackageTest;
import com.tests.uss16.us16004a.US16004aCancelOrderDefaultTopAndTopPackageTest;
import com.tests.uss16.us16004a.US16004aNewBorrowWithDefaultTopAndCustomPackageTest;
import com.tests.uss16.us16004a.US16004aNewBorrowWithDefaultTopAndTopPackageTest;
import com.tests.uss16.us16004a.US16004aPlaceBarrowOrderDefaultTopAndCustomPackageTest;
import com.tests.uss16.us16004a.US16004aPlaceBarrowOrderDefaultTopAndTopPackageTest;
import com.tests.uss16.us16004a.US16004aValidateOrderWithDefaultTopAndTopPackageBackOfficeTest;
import com.tests.uss16.us16004b.US16004bCancelOrderAllowedTopAndCustomPackageTest;
import com.tests.uss16.us16004b.US16004bCancelOrderAllowedTopAndTopPackageTest;
import com.tests.uss16.us16004b.US16004bNewBorrowWithAllowedTopAndCustomPackageTest;
import com.tests.uss16.us16004b.US16004bNewBorrowWithAllowedTopAndNotEligibleTest;
import com.tests.uss16.us16004b.US16004bNewBorrowWithAllowedTopAndTopPackageTest;
import com.tests.uss16.us16004b.US16004bPlaceBarrowOrderAllowedTopAndCustomPackageTest;
import com.tests.uss16.us16004b.US16004bPlaceBarrowOrderAllowedTopAndTopPackageTest;
import com.tests.uss16.us16004c.US16004cCancelOrderDeniedTopAndCustomPackageTest;
import com.tests.uss16.us16004c.US16004cNewBorrowWithDeniedTopAndCustomPackageTest;
import com.tests.uss16.us16004c.US16004cNewBorrowWithDeniedTopAndNotEligibleTest;
import com.tests.uss16.us16004c.US16004cNewBorrowWithDeniedTopAndTopPackageTest;
import com.tests.uss16.us16004c.US16004cPlaceBarrowOrderDeniedTopAndCustomPackageTest;





@SuiteClasses({
	//data preparation
	US16003StyleCoachRegistrationTest.class,
	US16003AddNewContactToStyleCoachTest.class,
	US16003RegularCustomerRegistrationTest.class,
	US16003ConfirmCustomerTest.class,
	US16003RegularCustomerSetProductsInCartAndWishlist.class,
	US16003CreatePartyWithNewContactHostTest.class,
	US16003ChechEmailAndAcceptInvitationTest.class,
	
	//stylist in Top, top=allowed, allowed to borrow=TOP package 
	US16004aNewBorrowWithDefaultTopAndTopPackageTest.class,
	US16004aPlaceBarrowOrderDefaultTopAndTopPackageTest.class,
	US16004ValidateOrderWithDefaultTopAndTopPackageEmailTest.class,
	US16004aValidateOrderWithDefaultTopAndTopPackageBackOfficeTest.class,
	US16004aCancelOrderDefaultTopAndTopPackageTest.class,
	
//	stylist in Top, top=allowed, allowed to borrow=custom package 
	US16004aNewBorrowWithDefaultTopAndCustomPackageTest.class,
	US16004aPlaceBarrowOrderDefaultTopAndCustomPackageTest.class,
	US16004aCancelOrderDefaultTopAndCustomPackageTest.class,

	//stylist in Top, top=allowed, allowed to borrow=not eligible 

	US16004bNewBorrowWithAllowedTopAndCustomPackageTest.class,
	US16004bPlaceBarrowOrderAllowedTopAndCustomPackageTest.class,
	US16004bCancelOrderAllowedTopAndCustomPackageTest.class,
	US16004bNewBorrowWithAllowedTopAndTopPackageTest.class,
	US16004bPlaceBarrowOrderAllowedTopAndTopPackageTest.class,
	US16004bCancelOrderAllowedTopAndTopPackageTest.class,
	US16004bNewBorrowWithAllowedTopAndNotEligibleTest.class,
	
	
	//stylist in Top, top=denied, allowed to borrow =custom package
	US16004cNewBorrowWithDeniedTopAndTopPackageTest.class,
	US16004cNewBorrowWithDeniedTopAndCustomPackageTest.class,
	US16004cPlaceBarrowOrderDeniedTopAndCustomPackageTest.class,
	US16004cCancelOrderDeniedTopAndCustomPackageTest.class,
	US16004cNewBorrowWithDeniedTopAndNotEligibleTest.class,
	
})


@RunWith(Suite.class)
public class US16003Suite {

}
