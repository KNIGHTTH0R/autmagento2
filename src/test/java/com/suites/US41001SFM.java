package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss41.us41001_SFM.US41001SFMCheckExchangeDocumentsInNavTabTest;
import com.tests.uss41.us41001_SFM.US41001SFMCheckOriginalDocumentsInNavTabTest;
import com.tests.uss41.us41001_SFM.US41001SFMCreateSalesReturnInNavisionTest;
import com.tests.uss41.us41001_SFM.US41001SFMPayAndImportOrderInNavisionTest;
import com.tests.uss41.us41001_SFM.US41001SFMPostOrderInNavisionTest;
import com.tests.uss41.us41001_SFM.us41001SFMOrderDetails.US41001SFMCheckShipmentEmailTest;
import com.tests.uss41.us41001_SFM.us41001SFMOrderDetails.US41001SFMCreateInvoiceAndShipmentTest;

@SuiteClasses({
//	US41001ShopForMyselfOrderTest.class,
	US41001SFMPayAndImportOrderInNavisionTest.class,
	US41001SFMPostOrderInNavisionTest.class,
	US41001SFMCheckOriginalDocumentsInNavTabTest.class,
	US41001SFMCreateInvoiceAndShipmentTest.class,
	US41001SFMCreateSalesReturnInNavisionTest.class,
	US41001SFMCheckExchangeDocumentsInNavTabTest.class,
	US41001SFMCheckShipmentEmailTest.class,
	
})
@RunWith(Suite.class)
public class US41001SFM {

}
