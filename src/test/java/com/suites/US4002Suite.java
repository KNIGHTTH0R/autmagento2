package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us4.us4002.US4002ActivateBuy3Get1Test;
import com.tests.us4.us4002.US4002CheckOrderOnStylecoachProfileTest;
import com.tests.us4.us4002.US4002DeactivateBuy3Get1Test;
import com.tests.us4.us4002.US4002ShopForMyselfWithBuy3GetOneTest;
import com.tests.us4.us4002.US4002ValidateOrderBackOfficeTest;

@SuiteClasses({
	US4002ActivateBuy3Get1Test.class,
	US4002ShopForMyselfWithBuy3GetOneTest.class,
//	US4002CheckOrderOnStylecoachProfileTest.class,

	/*US4002ValidateOrderBackOfficeTest.class,
	US4002DeactivateBuy3Get1Test.class,*/
//	US4002ValidateOrderEmailTest.class,
})
@RunWith(Suite.class)
public class US4002Suite {

}
