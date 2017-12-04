package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss41.us41001_StarterKit.US41001SCCheckExchangeDocumentsInNavTabTest;
import com.tests.uss41.us41001_StarterKit.US41001SCCheckOriginalDocumentsInNavTabTest;
import com.tests.uss41.us41001_StarterKit.US41001SCCreateSalesReturnInNavisionTest;
import com.tests.uss41.us41001_StarterKit.US41001SCPayAndImportOrderInNavisionTest;
import com.tests.uss41.us41001_StarterKit.US41001SCPostOrderInNavisionTest;
import com.tests.uss41.us41001_StarterKit.US41001ScRegistrationNewCustomerTest;
import com.tests.uss41.us41001_StarterKit.us41001SCDataFlow.US41001SCCheckReceivedEmailsTest;
import com.tests.uss41.us41001_StarterKit.us41001SCDataFlow.US41001SCUploadOrdersFromCsvFileTest;

@SuiteClasses({
	//US41001ScRegistrationNewCustomerTest.class,
	US41001SCPayAndImportOrderInNavisionTest.class,
	US41001SCPostOrderInNavisionTest.class,
	US41001SCCheckOriginalDocumentsInNavTabTest.class,
	US41001SCUploadOrdersFromCsvFileTest.class,
	US41001SCCreateSalesReturnInNavisionTest.class,
	US41001SCCheckExchangeDocumentsInNavTabTest.class,
	US41001SCCheckReceivedEmailsTest.class,
	
})
@RunWith(Suite.class)
public class US41001StarterKitSuite {

}
