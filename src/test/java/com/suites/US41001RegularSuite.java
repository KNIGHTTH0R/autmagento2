package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss41.us41001_Regular.US41001CustomerBuyWithForthyDiscountsAndJbTest;
import com.tests.uss41.us41001_Regular.US41001RegularCheckOriginalDocumentsInNavTabTest;
import com.tests.uss41.us41001_Regular.US41001RegularPayAndImportOrderInNavisionTest;
import com.tests.uss41.us41001_Regular.US41001RegularPostOrderInNavisionTest;
import com.tests.uss41.us41001_Regular.us41001RegularDataFlow.US41001RegularCheckReceivedEmailsTest;
import com.tests.uss41.us41001_Regular.us41001RegularDataFlow.US41001UploadOrdersFromCsvFileTest;

@SuiteClasses({
	US41001CustomerBuyWithForthyDiscountsAndJbTest.class,
	US41001RegularPayAndImportOrderInNavisionTest.class,
	US41001RegularPostOrderInNavisionTest.class,
	US41001RegularCheckOriginalDocumentsInNavTabTest.class,
	
	US41001UploadOrdersFromCsvFileTest.class,
	US41001RegularCheckReceivedEmailsTest.class,
})
@RunWith(Suite.class)
public class US41001RegularSuite {

}
