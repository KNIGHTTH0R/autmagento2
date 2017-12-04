package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss41.us41001_PlaceCustomer.US41001PlaceCustomerCheckExchangeDocumentsInNavTabTest;
import com.tests.uss41.us41001_PlaceCustomer.US41001PlaceCustomerCheckOriginalDocumentsInNavTabTest;
import com.tests.uss41.us41001_PlaceCustomer.US41001PlaceCustomerCreateSalesReturnInNavisionTest;
import com.tests.uss41.us41001_PlaceCustomer.us41001PlaceCustomerDataFlow.US41001PlaceCustomerCheckReceivedEmailsTest;
import com.tests.uss41.us41001_PlaceCustomer.us41001PlaceCustomerDataFlow.US41001PlaceCustomerUploadOrdersFromCsvFileTest;
import com.tests.uss41.us41001_PlaceCustomer.us41001PlaceCustomerNavTab.US41001ContactCheckRecivedDocumentsMailTest;
import com.tests.uss41.us41001_PlaceCustomer.us41001PlaceCustomerNavTab.US41001PlaceCustomerSendDocumentsViaNavTabToContactTest;
import com.tests.uss41.us41001_PlaceCustomer.us41001PlaceCustomerNavTab.US41001SylistCheckRecivedDocumentsMailTest;

@SuiteClasses({
//	US41001OrderOnlyZzzProductsForCustomerTest.class,
	/*US41001PlaceCustomerPayAndImportOrderInNavisionTest.class,
	US41001PlaceCustomerPostOrderInNavisionTest.class,*/
	US41001PlaceCustomerCheckOriginalDocumentsInNavTabTest.class,
	US41001PlaceCustomerUploadOrdersFromCsvFileTest.class,
	US41001PlaceCustomerSendDocumentsViaNavTabToContactTest.class,
	US41001PlaceCustomerCreateSalesReturnInNavisionTest.class,
	US41001PlaceCustomerCheckExchangeDocumentsInNavTabTest.class,
	US41001PlaceCustomerCheckReceivedEmailsTest.class,
	US41001ContactCheckRecivedDocumentsMailTest.class,
	US41001SylistCheckRecivedDocumentsMailTest.class,
	
})
@RunWith(Suite.class)
public class US41001PlaceCustomer {

}
