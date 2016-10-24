package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us3.us3001.US3001ValidateOrderBackOfficeTest;
import com.tests.us3.us3002.US3002ValidateOrderBackOfficeTest;
import com.tests.us6.us6001b.US6001bValidateStarterSetOrderInBackendTest;
import com.tests.us8.us8001.US8001ValidateReorderBackOfficeTest;
import com.tests.us9.us9001.US9001ValidateOrderBackOfficeTest;
import com.tests.uss11.us11001.US11001ValidateOrderBackOfficeTest;

@SuiteClasses({
	US3001ValidateOrderBackOfficeTest.class,
	US3002ValidateOrderBackOfficeTest.class,
	US6001bValidateStarterSetOrderInBackendTest.class,
	US8001ValidateReorderBackOfficeTest.class,
	US9001ValidateOrderBackOfficeTest.class,
	US11001ValidateOrderBackOfficeTest.class,	
})
@RunWith(Suite.class)
public class NotificationsSuite {

}
