package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us9.us9002.US9002ActivateBuyGet1Test;
import com.tests.us9.us9002.US9002ClosePartyTest;
import com.tests.us9.us9002.US9002DeactivateBuy3Get1Test;
import com.tests.us9.us9002.US9002PlaceHostOrderWithForthyDiscountsJbAndBuy3Get1Test;
import com.tests.us9.us9002.US9002ValidateOrderBackOfficeTest;
import com.tests.uss10.uss10007.US10007AddManuallyBonusOnPartyTest;
import com.tests.uss10.uss10008.US10008AddManuallyBonusOnPartyTest;

@SuiteClasses({
	
	US9002ActivateBuyGet1Test.class,
	US9002ClosePartyTest.class,
	US10008AddManuallyBonusOnPartyTest.class,
	US9002PlaceHostOrderWithForthyDiscountsJbAndBuy3Get1Test.class,
//	US9002CheckOrderOnStylecoachProfileTest.class,	
//	US9002ValidateOrderEmailTest.class,	
	US9002ValidateOrderBackOfficeTest.class,
	US9002DeactivateBuy3Get1Test.class,
	
})
@RunWith(Suite.class)
public class US9002Suite {
	

}
