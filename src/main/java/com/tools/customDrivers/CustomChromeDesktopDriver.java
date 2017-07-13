package com.tools.customDrivers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import net.thucydides.core.webdriver.DriverSource;

public class CustomChromeDesktopDriver implements DriverSource{

	@Override
	public WebDriver newDriver() {
		// TODO Auto-generated method stub
		return setChromeDesktop();
	}
	
	@Override
	public boolean takesScreenshots() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private WebDriver setChromeDesktop() {
//		Map<String, Object> prefs = new HashMap<String, Object>();
//		prefs.put("profile.default_content_setting_values.notifications", 2);
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("prefs", prefs);
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--disable-notifications");
//		System.setProperty("webdriver.chrome.driver","C:/Users/emilianmelian/git/pippajeanautotester/resources/chromedriver.exe");
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		return new ChromeDriver(options);
		
		
		
		 
	}

}
