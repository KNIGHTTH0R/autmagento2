package com.tests.us4.us4001;

import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.runner.RunWith;

import com.poc.DeactivateBuy3Get1ForShopForMyself;
import com.tools.requirements.Application;

@WithTag(name = "US4.1 Shop for myself with JB,MMB and Buy 3 get 1 for 50 %", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US4_1.class)
@RunWith(ThucydidesRunner.class)
public class DeactivateBuy3Get1Test extends DeactivateBuy3Get1ForShopForMyself {
}
