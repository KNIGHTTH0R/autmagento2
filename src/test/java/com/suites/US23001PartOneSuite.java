package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss23.US23001BuyProductsOnShopforMyselfTest;
import com.tests.uss23.US23001GetMagAndNavStockBerforeOrderTest;
import com.tests.uss23.US23001PayAndImportOrderInNavisionTest;
import com.tests.uss23.US23001VerifyMagAndNavStockAfterOrderTest;


@SuiteClasses({
	US23001GetMagAndNavStockBerforeOrderTest.class,
	US23001BuyProductsOnShopforMyselfTest.class,
	US23001VerifyMagAndNavStockAfterOrderTest.class,
	US23001PayAndImportOrderInNavisionTest.class,
})
@RunWith(Suite.class)
public class US23001PartOneSuite {

}
