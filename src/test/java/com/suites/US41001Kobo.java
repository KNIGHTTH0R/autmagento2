package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss41.us41001_Kobo.US41001InitialKoboSubscriptionTest;
import com.tests.uss41.us41001_Kobo.US41001KoboCheckExchangeDocumentsInNavTabTest;
import com.tests.uss41.us41001_Kobo.US41001KoboCheckOriginalDocumentsInNavTabTest;
import com.tests.uss41.us41001_Kobo.US41001KoboNavisionSubscriptionTest;
import com.tests.uss41.us41001_Kobo.US41001KoboPayAndImportOrderInNavisionTest;
import com.tests.uss41.us41001_Kobo.US41001KoboPostOrderInNavisionTest;
import com.tests.uss41.us41001_Kobo.US41001StyleCoachRegistrationTest;
import com.tests.uss41.us41001_Kobo.us41001KoboDataFlow.US41001KoboCheckReceivedEmailsTest;
import com.tests.uss41.us41001_Kobo.us41001KoboDataFlow.US41001KoboUploadOrdersFromCsvFileTest;

@SuiteClasses({
	US41001StyleCoachRegistrationTest.class,
	US41001InitialKoboSubscriptionTest.class,
	US41001KoboPayAndImportOrderInNavisionTest.class,
	US41001KoboPostOrderInNavisionTest.class,
	US41001KoboCheckOriginalDocumentsInNavTabTest.class,
	US41001KoboUploadOrdersFromCsvFileTest.class,
	US41001KoboNavisionSubscriptionTest.class,
	US41001KoboCheckExchangeDocumentsInNavTabTest.class,
//	US41001KoboCheckReceivedEmailsTest.class,
	
})
@RunWith(Suite.class)
public class US41001Kobo {

}
