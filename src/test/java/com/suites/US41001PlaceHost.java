package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss41.us41001_PlaceHost.US41001CreatePartyWithStylistHostTest;
import com.tests.uss41.us41001_PlaceHost.US41001PlaceHostCheckExchangeDocumentsInNavTabTest;
import com.tests.uss41.us41001_PlaceHost.US41001PlaceHostCheckOriginalDocumentsInNavTabTest;
import com.tests.uss41.us41001_PlaceHost.US41001PlaceHostCreateSalesReturnInNavisionTest;
import com.tests.uss41.us41001_PlaceHost.US41001PlaceHostOrderWithForthyDiscountsAndJbTest;
import com.tests.uss41.us41001_PlaceHost.US41001PlaceHostPayAndImportOrderInNavisionTest;
import com.tests.uss41.us41001_PlaceHost.US41001PlaceHostPostOrderInNavisionTest;
import com.tests.uss41.us41001_PlaceHost.us41001PlaceHostDataFlow.US41001PlaceHostCheckReceivedEmailsTest;
import com.tests.uss41.us41001_PlaceHost.us41001PlaceHostDataFlow.US41001PlaceHostUploadOrdersFromCsvFileTest;

@SuiteClasses({
//	US41001CreatePartyWithStylistHostTest.class,
//	US41001PlaceHostOrderWithForthyDiscountsAndJbTest.class,
	US41001PlaceHostPayAndImportOrderInNavisionTest.class,
	US41001PlaceHostPostOrderInNavisionTest.class,
	US41001PlaceHostCheckOriginalDocumentsInNavTabTest.class,
	US41001PlaceHostUploadOrdersFromCsvFileTest.class,
	US41001PlaceHostCreateSalesReturnInNavisionTest.class,
	US41001PlaceHostCheckExchangeDocumentsInNavTabTest.class,
	US41001PlaceHostCheckReceivedEmailsTest.class,
	
})
@RunWith(Suite.class)
public class US41001PlaceHost {

}
