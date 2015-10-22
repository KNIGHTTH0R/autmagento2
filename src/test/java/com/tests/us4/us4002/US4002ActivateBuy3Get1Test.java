package com.tests.us4.us4002;

import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.poc.ActivateBuy3Get1ForShopForMyselfTest;
import com.tools.requirements.Application;

@WithTag(name = "US4.2 Shop for myself with Buy 3 get 1 for 50 %", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US4_2.class)
@RunWith(ThucydidesRunner.class)
public class US4002ActivateBuy3Get1Test extends ActivateBuy3Get1ForShopForMyselfTest {

	@Test
	public void us4002ActivateBuy3Get1Test() {

	}
}
