package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss36.US36001AddNewContactToStyleCoachTest;
import com.tests.uss36.US36001AddSecondNewContactToStyleCoachTest;
import com.tests.uss36.US36001AddThirdContactToStyleCoachTest;
import com.tests.uss36.US36001ResetAnActiveAccountTest;
import com.tests.uss36.US36001ResetContactButtonActionTest;
import com.tests.uss36.US36001StyleCoachRegistrationTest;
import com.tests.uss36.US36001SyncBackendMagentoToSos;
import com.tests.uss36.US36001ValidateContactInfoSyncAfterResetAnActiveUserTest;
import com.tests.uss36.US36001ValidateContactInfoSyncInSosAfterResetContactTest;
import com.tests.uss36.US36001ValidateContatInfoSyncInSosAfterResetAcountTest;

@SuiteClasses({
	US36001StyleCoachRegistrationTest.class,
	US36001AddNewContactToStyleCoachTest.class,
	US36001AddSecondNewContactToStyleCoachTest.class,
	US36001AddThirdContactToStyleCoachTest.class,
	US36001SyncBackendMagentoToSos.class,
	US36001ValidateContatInfoSyncInSosAfterResetAcountTest.class,
	US36001ResetContactButtonActionTest.class,
	US36001ValidateContactInfoSyncInSosAfterResetContactTest.class,
	US36001ResetAnActiveAccountTest.class,
	US36001ValidateContactInfoSyncAfterResetAnActiveUserTest.class,

})
@RunWith(Suite.class)
public class US36001Suite {

}
