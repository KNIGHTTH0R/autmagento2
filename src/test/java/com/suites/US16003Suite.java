package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss16.us16003DataPreparation.US16003AddNewContactToStyleCoachTest;
import com.tests.uss16.us16003DataPreparation.US16003AddNewContactWithCustomerEmailToSCTest;
import com.tests.uss16.us16003DataPreparation.US16003CreatePartyWithNewContactHostTest;
import com.tests.uss16.us16003DataPreparation.US16003RegularCustomerSetProductsInCartAndWishlist;
import com.tests.uss16.us16003DataPreparation.US16003SetUpNewBorrowFunctionalityTest;
import com.tests.uss16.us16003DataPreparation.US16003StyleCoachRegistrationTest;
import com.tests.uss16.us16004a.US16004ValidateOrderWithDefaultTopAndTopPackageEmailTest;
import com.tests.uss16.us16004a.US16004aCancelOrderDefaultTopAndCustomPackageTest;
import com.tests.uss16.us16004a.US16004aCancelOrderDefaultTopAndTopPackageTest;
import com.tests.uss16.us16004a.US16004aNewBorrowWithDefaultTopAndCustomPackageTest;
import com.tests.uss16.us16004a.US16004aNewBorrowWithDefaultTopAndTopPackageTest;
import com.tests.uss16.us16004a.US16004aPlaceBarrowOrderDefaultTopAndCustomPackageTest;
import com.tests.uss16.us16004a.US16004aPlaceBarrowOrderDefaultTopAndTopPackageTest;
import com.tests.uss16.us16004a.US16004aValidateOrderWithDefaultTopAndTopPackageBackOfficeTest;
import com.tests.uss39.StylistInventoryBorrowCustomPackageTest;
import com.tests.uss39.StylistInventoryBorrowTopPackageTest;
import com.tests.uss39.StylistInventoryReturnCustomPackageTest;
import com.tests.uss39.StylistInventoryReturnTopPackageTest;





@SuiteClasses({
	//data preparation
	US16003StyleCoachRegistrationTest.class,
	US16003CreatePartyWithNewContactHostTest.class,
	US16003AddNewContactWithCustomerEmailToSCTest.class,
	US16003AddNewContactToStyleCoachTest.class,
	US16003SetUpNewBorrowFunctionalityTest.class,
	US16003RegularCustomerSetProductsInCartAndWishlist.class, //need attention
//	US16003ChechEmailAndAcceptInvitationTest.class, //need attention
	
////	//stylist in Top, top=default, allowed to borrow=TOP package 
	
	
	US16004aNewBorrowWithDefaultTopAndTopPackageTest.class, //need attention 
	US16004aPlaceBarrowOrderDefaultTopAndTopPackageTest.class, // ok
	US16004aValidateOrderWithDefaultTopAndTopPackageBackOfficeTest.class,
	StylistInventoryBorrowTopPackageTest.class,
	StylistInventoryReturnTopPackageTest.class,
	
	US16004aCancelOrderDefaultTopAndTopPackageTest.class,
	US16004ValidateOrderWithDefaultTopAndTopPackageEmailTest.class,
//////	stylist in Top, top=default, allowed to borrow=custom package 
 
 

	US16004aNewBorrowWithDefaultTopAndCustomPackageTest.class,
	US16004aPlaceBarrowOrderDefaultTopAndCustomPackageTest.class,
	StylistInventoryBorrowCustomPackageTest.class,
	StylistInventoryReturnCustomPackageTest.class,
	
	US16004aCancelOrderDefaultTopAndCustomPackageTest.class,
/*
	//stylist in Top, top=default, allowed to borrow=not eligible 
	US16004aNewBorrowWithDefaultTopAndNotEligibleTest.class,
	
//	//stylist in Top, top=allowed, allowed to borrow=not eligible 
//	US16004bNewBorrowWithAllowedTopAndCustomPackageTest.class,
//	US16004bPlaceBarrowOrderAllowedTopAndCustomPackageTest.class,
//	US16004bCancelOrderAllowedTopAndCustomPackageTest.class,
//	US16004bNewBorrowWithAllowedTopAndTopPackageTest.class,
//	US16004bPlaceBarrowOrderAllowedTopAndTopPackageTest.class,
//	US16004bCancelOrderAllowedTopAndTopPackageTest.class,
//	US16004bNewBorrowWithAllowedTopAndNotEligibleTest.class,
//
//	//stylist in Top, top=denied, allowed to borrow =custom package
//	US16004cNewBorrowWithDeniedTopAndTopPackageTest.class,
//	US16004cNewBorrowWithDeniedTopAndCustomPackageTest.class,
//	US16004cPlaceBarrowOrderDeniedTopAndCustomPackageTest.class,
//	US16004cCancelOrderDeniedTopAndCustomPackageTest.class,
//	US16004cNewBorrowWithDeniedTopAndNotEligibleTest.class,

	*/
})


@RunWith(Suite.class)
public class US16003Suite {

}
