package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.poc.ActivateBuy3Get1ForShopForMyselfTest;
import com.poc.DeactivateBuy3Get1ForShopForMyself;
import com.tests.us4.us4002.US4002ShopForMyselfWithBuy3GetOneTest;
import com.tests.us4.us4002.US4002CheckOrderOnStylecoachProfileTest;
import com.tests.us4.us4002.US4002ValidateOrderBackOfficeTest;
import com.tests.us4.us4002.US4002ValidateOrderEmailTest;

@SuiteClasses({
	ActivateBuy3Get1ForShopForMyselfTest.class,
	US4002ShopForMyselfWithBuy3GetOneTest.class,
	US4002CheckOrderOnStylecoachProfileTest.class,
	US4002ValidateOrderEmailTest.class,
	US4002ValidateOrderBackOfficeTest.class,
	DeactivateBuy3Get1ForShopForMyself.class,
})
@RunWith(Suite.class)
public class US4002Suite {

}
