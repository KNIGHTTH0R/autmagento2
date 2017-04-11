package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.CreateProductsTest;


@SuiteClasses({
	CreateProductsTest.class,
	US3001Suite.class,
	US3003Suite.class,
	US3004Suite.class,
	US3006Suite.class,
	US3007Suite.class,
	US3009Suite.class,
	US30010Suite.class,
	US4001Suite.class,
	US4002Suite.class,
})
@RunWith(Suite.class)
public class ShopForMyself {

}
