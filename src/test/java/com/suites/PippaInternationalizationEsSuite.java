package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.poc.ActivateRule;
import com.poc.DeactivateBuy3Get1ForHost;
import com.poc.DeactivateBuy3Get1ForRegular;
import com.poc.DeactivateRule;

@SuiteClasses({
	
	US10CreateAllPartiesSuite.class,
	DeactivateRule.class,
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
	
	US6001Suite.class,
	US6001bSuite.class,
	US6002Suite.class,
	US6002bSuite.class,
	
	US7001Suite.class,
	US7001bSuite.class,
	US7002Suite.class,
//	US7003Suite.class,
	US7004Suite.class,
	US7004bSuite.class,
//	US7005Suite.class,
	US7006Suite.class,
	US7006bSuite.class,
	US7007Suite.class,	
	US7008Suite.class,
	US7009Suite.class,
	US70010Suite.class,
	US70011Suite.class,
	US70012Suite.class,
	
	DeactivateBuy3Get1ForRegular.class,
	US8001Suite.class,
	US8002Suite.class,
	US8003Suite.class,	
	
	ActivateRule.class,
	US4001Suite.class,
	US4002Suite.class,
	DeactivateRule.class,
	US10001AndUS10002ClosePartiesSuite.class,
	
	DeactivateBuy3Get1ForHost.class,
	US9001Suite.class,
	US9002Suite.class,
	
	US10005Suite.class,
	US10006Suite.class,
	
	US11001Suite.class,
	US11002Suite.class,
	US12001Suite.class,
	US16001Suite.class,
	
	
})
@RunWith(Suite.class)
public class PippaInternationalizationEsSuite {

}
