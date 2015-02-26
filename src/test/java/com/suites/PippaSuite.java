package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses({
	US000Suite.class,
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
//	US4001Suite.class,
//	US4002Suite.class,
})
@RunWith(Suite.class)
public class PippaSuite {

}
