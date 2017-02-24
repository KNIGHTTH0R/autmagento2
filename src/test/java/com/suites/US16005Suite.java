package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss16.us16003DataPreparation.US16006ChechEmailAndAcceptInvitationTest;
import com.tests.uss16.us16003DataPreparation.US16006CreatePartyTest;
import com.tests.uss16.us16003DataPreparation.US16006RegularCustomerSetProductsInCartAndWishlistTest;
import com.tests.uss16.us16005.US16005CancelOrderAllowTopAndTopPackageTest;
import com.tests.uss16.us16005.US16005NewBorrowWithAllowAndCustomPackageTest;
import com.tests.uss16.us16005.US16005NewBorrowWithDefaultAndTopPackageTest;
import com.tests.uss16.us16005.US16005PlaceBorrowOrderAllowTopAndCustomPackageTest;
import com.tests.uss16.uss16006.US16006BorrowProcessIsDisabledTest;





@SuiteClasses({
	//data preparation
	US16006RegularCustomerSetProductsInCartAndWishlistTest.class,

	
	US16006CreatePartyTest.class,
	US16006ChechEmailAndAcceptInvitationTest.class,
//	
//	//stylist which is out Take of Phase period
//	US16005NewBorrowWithDefaultAndTopPackageTest.class,
	US16005NewBorrowWithAllowAndCustomPackageTest.class,
	US16005PlaceBorrowOrderAllowTopAndCustomPackageTest.class,
	US16005CancelOrderAllowTopAndTopPackageTest.class,
//	
//	//Borrow process is Disabled
	US16006BorrowProcessIsDisabledTest.class
	

	
})


@RunWith(Suite.class)
public class US16005Suite {

}
