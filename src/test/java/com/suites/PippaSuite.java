package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses({
	US000Suite.class,
	US001Suite.class,
	US002Suite.class,
	US003Suite.class,
	US004Suite.class,
})
@RunWith(Suite.class)
public class PippaSuite {

}
