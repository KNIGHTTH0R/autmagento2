package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.us7.us7001.US7001CheckReceivedEmailsTest;
import com.tests.us7.us7001b.US7001bCheckReceivedEmailsTest;
import com.tests.us7.us7002.US7002CheckReceivedEmailsTest;
import com.tests.us7.us7008.US7008CheckReceivedEmailsTest;
import com.tests.us7.uss70010.US70010CheckReceivedEmailsTest;

@SuiteClasses({
//	US3001ValidateOrderEmailTest.class,
//	US3003ValidateOrderEmailTest.class,
//	US3004ValidateOrderEmailTest.class,
//	US3007ValidateOrderEmailTest.class,
//	US3009ValidateOrderEmailTest.class,
//	
//	US4001ValidateOrderEmailTest.class,
//	US4002ValidateOrderEmailTest.class,

	US7001CheckReceivedEmailsTest.class,
	US7001bCheckReceivedEmailsTest.class,
	US7002CheckReceivedEmailsTest.class,
//	US7008CheckReceivedEmailsTest.class,
//	US70010CheckReceivedEmailsTest.class,
	
//	US8001ValidateOrderEmailTest.class,	
//	US8002ValidateOrderEmailTest.class,
//	US8003ValidateOrderEmailTest.class,	
//	US8004ValidateOrderEmailTest.class,	
//	US8005ValidateOrderEmailTest.class,
//	US8006ValidateOrderEmailTest.class,
//	
//	US11001ValidateOrderEmailTest.class,
//	US11002ValidateOrderEmailTest.class,
//	US11005ValidateOrderEmailTest.class,
//	
//	US9001ValidateOrderEmailTest.class,
//	US9002ValidateOrderEmailTest.class,
//	
//	US16001ValidateOrderEmailTest.class,
	

})
@RunWith(Suite.class)
public class VerifyOrderEmailsSuite {

}
