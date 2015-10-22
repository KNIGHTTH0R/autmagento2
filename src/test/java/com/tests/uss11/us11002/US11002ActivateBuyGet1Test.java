package com.tests.uss11.us11002;

import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.poc.ActivateBuyGet1ForPlaceACustomerOrderTest;
import com.tools.requirements.Application;

@WithTag(name = "US11.2 Party Host Buys For Customer With Buy 3 Get 1 For 50% ", type = "Scenarios")
@Story(Application.PlaceACustomerOrderCart.US11_2.class)
@RunWith(ThucydidesRunner.class)
public class US11002ActivateBuyGet1Test extends ActivateBuyGet1ForPlaceACustomerOrderTest {

	@Test
	public void us11002ActivateBuyGet1Test() {
	}
}
