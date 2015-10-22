package com.tests.us9.us9002;

import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.poc.ActivateBuyGet1ForHostTest;
import com.tools.requirements.Application;

@WithTag(name = "US9.2 Place Host Order With 40% Discount, JB and Buy 3 get 1 for 50% Test", type = "Scenarios")
@Story(Application.HostCart.US9_2.class)
@RunWith(ThucydidesRunner.class)
public class US9002ActivateBuyGet1Test extends ActivateBuyGet1ForHostTest {

	@Test
	public void us9002ActivateBuyGet1Test() {
	}
}
