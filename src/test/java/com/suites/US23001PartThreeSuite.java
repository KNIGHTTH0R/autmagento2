package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss23.US23001BuyRestockProductTest;
import com.tests.uss23.US23001PayAndImportRestockOrderTest;
import com.tests.uss23.US23001VerifyStockSyncAfterTpOrderImportTest;


@SuiteClasses({
	US23001VerifyStockSyncAfterTpOrderImportTest.class,
	US23001BuyRestockProductTest.class,
	US23001PayAndImportRestockOrderTest.class,

	
})
@RunWith(Suite.class)
public class US23001PartThreeSuite {

}
