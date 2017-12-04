package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss41.us41001_Borrow.us41001BorrowOrderDetails.US41001RegularCreateInvoiceAndShipmentTest;
import com.tests.uss41.us41001_Regular.US41001CustomerBuyWithForthyDiscountsAndJbTest;
import com.tests.uss41.us41001_Regular.US41001RegularCheckExchangeDocumentsInNavTabTest;
import com.tests.uss41.us41001_Regular.US41001RegularCheckOriginalDocumentsInNavTabTest;
import com.tests.uss41.us41001_Regular.US41001RegularCreateSalesReturnInNavisionTest;
import com.tests.uss41.us41001_Regular.US41001RegularPayAndImportOrderInNavisionTest;
import com.tests.uss41.us41001_Regular.US41001RegularPostOrderInNavisionTest;
import com.tests.uss41.us41001_Regular.us41001RegularNavTab.US41001RegularCheckReceivedEmailsSendFromNavTabTest;
import com.tests.uss41.us41001_Regular.us41001RegularNavTab.US41001RegularSendDocumentsViaNavTabToAddressTest;
import com.tests.uss41.us41001_Regular.us41001RegularOrderDetails.US41001RegularCheckShipmentEmailTest;

@SuiteClasses({
	US41001CustomerBuyWithForthyDiscountsAndJbTest.class,
	US41001RegularPayAndImportOrderInNavisionTest.class,
	US41001RegularPostOrderInNavisionTest.class,
	US41001RegularCheckOriginalDocumentsInNavTabTest.class,
	
	//US41001UploadOrdersFromCsvFileTest.class,
	US41001RegularCreateInvoiceAndShipmentTest.class,
	US41001RegularSendDocumentsViaNavTabToAddressTest.class,
	
	US41001RegularCreateSalesReturnInNavisionTest.class,
	US41001RegularCheckExchangeDocumentsInNavTabTest.class,
	//US41001RegularCheckReceivedEmailsTest.class,
	US41001RegularCheckShipmentEmailTest.class,
	US41001RegularCheckReceivedEmailsSendFromNavTabTest.class,
})
@RunWith(Suite.class)
public class US41001RegularSuite {

}
