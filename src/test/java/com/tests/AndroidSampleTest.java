package com.tests;

import io.appium.java_client.android.AndroidDriver;

import java.net.URL;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.steps.backend.BackEndSteps;
import com.steps.backend.products.BackendProductDetailsSteps;
import com.steps.backend.products.BackendProductListSteps;
import com.tools.env.constants.Credentials;
import com.tools.requirements.Application;

@WithTag(name = "US3", type = "backend")
@Story(Application.Shop.ForMyselfCart.class)
@RunWith(ThucydidesRunner.class)
public class AndroidSampleTest {

	@Managed(uniqueSession = false)
	public WebDriver webdriver;

	@ManagedPages(defaultUrl = "http://staging-aut.pippajean.com/customer/account/login/")
	public Pages pages;

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public BackendProductListSteps backendProductListSteps;
	@Steps
	public BackendProductDetailsSteps backendProductDetailsSteps;

	@Before
	public void setUp() throws Exception {

		// setting up capabilites to run calculator app
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "droid");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("appPackage", "com.android.calculator2");

		webdriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@Test
	public void androidSampleTest() {
		// TODO change hardcoded params when need this
		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);

	}
}
