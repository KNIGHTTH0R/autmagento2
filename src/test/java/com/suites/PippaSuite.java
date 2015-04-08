package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.poc.ActivateRule;
import com.poc.DeactivateRule;

@SuiteClasses({
//	US000Suite.class,
	US10CreateAllPartiesSuite.class,
	US001Suite.class,
	US002Suite.class,
	US3001Suite.class,
	US3002Suite.class,
	US3003Suite.class,
	US3004Suite.class,
	US3005Suite.class,
	US3006Suite.class,
	US3007Suite.class,
	US3008Suite.class,
	US3009Suite.class,
	
	//US 5 - Facebook login is external. System is not always up (facebook app)
	
	US6001Suite.class,
	US6002Suite.class,
	US6003Suite.class,
	
	US7001Suite.class,
	US7002Suite.class,
	US7003Suite.class,
	US7004Suite.class,
	US7005Suite.class,
	US7006Suite.class,
	US7007Suite.class,
	
	US8001Suite.class,
	US8002Suite.class,
	US8003Suite.class,
	
	
	ActivateRule.class,
	US4001Suite.class,
	US4002Suite.class,
	DeactivateRule.class,
	US10001AndUS10002ClosePartiesSuite.class,
	US9001Suite.class,
	US9002Suite.class,
	
})
@RunWith(Suite.class)
public class PippaSuite {

}
