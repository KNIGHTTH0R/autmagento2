package com.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.products.BackendProductDetailsSteps;
import com.steps.backend.products.BackendProductListSteps;
import com.tools.constants.Credentials;
import com.tools.requirements.Application;

@WithTag(name = "US3", type = "backend")
@Story(Application.Shop.ForMyselfCart.class)
@RunWith(SerenityRunner.class)
public class AddChildToBundleProductTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public BackendProductListSteps backendProductListSteps;
	@Steps
	public BackendProductDetailsSteps backendProductDetailsSteps;

	@Test
	public void addChildToBundleProductTest() {
		//TODO change hardcoded params when need this
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnProducts();
		backendProductListSteps.findProduct("EM004JN");
		backendProductListSteps.openProductDetails("EM004JN");
		backendProductDetailsSteps.selectBundleItemMenu();
		backendProductDetailsSteps.addChildToBundleProduct("YKCHNZWSC");
		
	}
}
