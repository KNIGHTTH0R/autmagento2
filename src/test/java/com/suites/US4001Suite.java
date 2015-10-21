package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us4.us4001.US4001ActivateBuy3Get1Test;
import com.tests.us4.us4001.US4001DeactivateBuy3Get1Test;
import com.tests.us4.us4001.US4001CheckOrderOnStylecoachProfileTest;
import com.tests.us4.us4001.US4001ShopForMyselfWithJbMmbAndBuy3GetOneTest;
import com.tests.us4.us4001.US4001ValidateOrderBackOfficeTest;
import com.tests.us4.us4001.US4001ValidateOrderEmailTest;

@SuiteClasses({
	US4001ActivateBuy3Get1Test.class,
	US4001ShopForMyselfWithJbMmbAndBuy3GetOneTest.class,
	US4001CheckOrderOnStylecoachProfileTest.class,
	US4001ValidateOrderEmailTest.class,
	US4001ValidateOrderBackOfficeTest.class,
	US4001DeactivateBuy3Get1Test.class,
})
@RunWith(Suite.class)
public class US4001Suite {

}
