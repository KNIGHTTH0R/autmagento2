package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss21.uss21001.US21001CheckClosedMonthFrontendRewardsOnStylistTest;
import com.tests.uss21.uss21001.US21001CheckStylistBonusesAfterClosedMonthTest;
import com.tests.uss21.uss21001.US21001CheckStylistBonusesBeforeClosedMonthTest;
import com.tests.uss21.uss21001.US21001CloseMonthTest;

@SuiteClasses({
	US21001CheckStylistBonusesBeforeClosedMonthTest.class,
	US21001CloseMonthTest.class,
	US21001CheckStylistBonusesAfterClosedMonthTest.class,
	US21001CheckClosedMonthFrontendRewardsOnStylistTest.class,
})
@RunWith(Suite.class)
public class US21001Suite {

}
