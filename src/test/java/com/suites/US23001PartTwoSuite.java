package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss23.US23001ActivateTermPurchaseForAllProductsTest;
import com.tests.uss23.US23001GetMagAndNavStockBerforeTpOrderTest;
import com.tests.uss23.US23001PayAndImportTermPurchaseOrderTest;
import com.tests.uss23.US23001PlaceTermPurchaseOrderTest;
import com.tests.uss23.US23001TranferQuantityFromQSin1000Test;
import com.tests.uss23.US23001VerifyMagAndNavStockAfterTPOrderTest;
import com.tests.uss23.US23001VerifyStockSyncAfterOrderImportTest;


@SuiteClasses({

	//US23001VerifyStockSyncAfterOrderImportTest.class,
//	US23001ActivateTermPurchaseForAllProductsTest.class,
	US23001GetMagAndNavStockBerforeTpOrderTest.class,
	US23001PlaceTermPurchaseOrderTest.class,
	
	US23001VerifyMagAndNavStockAfterTPOrderTest.class,
	US23001TranferQuantityFromQSin1000Test.class,
//	US23001PayAndImportTermPurchaseOrderTest.class,
//	
})
@RunWith(Suite.class)
public class US23001PartTwoSuite {

}
