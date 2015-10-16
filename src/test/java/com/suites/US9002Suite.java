package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.poc.ActivateBuyGet1ForHostTest;
import com.poc.DeactivateBuy3Get1ForHostTest;
import com.tests.us9.us9002.US9002PlaceHostOrderWithForthyDiscountsJbAndBuy3Get1Test;
import com.tests.us9.us9002.US9002CheckOrderOnStylecoachProfileTest;
import com.tests.us9.us9002.US9002ValidateOrderBackOfficeTest;
import com.tests.us9.us9002.US9002ValidateOrderEmailTest;

@SuiteClasses({
	ActivateBuyGet1ForHostTest.class,
	US9002PlaceHostOrderWithForthyDiscountsJbAndBuy3Get1Test.class,
	US9002CheckOrderOnStylecoachProfileTest.class,	
	US9002ValidateOrderEmailTest.class,	
	US9002ValidateOrderBackOfficeTest.class,
	DeactivateBuy3Get1ForHostTest.class,
})
@RunWith(Suite.class)
public class US9002Suite {

}
