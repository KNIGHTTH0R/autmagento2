package com.tests;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.pages.Pages;

import org.openqa.selenium.WebDriver;

import com.tools.Constants;

public class BaseTest {
	@Managed(uniqueSession = true)
	public WebDriver webdriver;

	@ManagedPages(defaultUrl = Constants.URL_WEB_MAIL)
	public Pages pages;
}
