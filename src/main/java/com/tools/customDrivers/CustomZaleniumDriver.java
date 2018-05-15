package com.tools.customDrivers;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CustomZaleniumDriver {

//	@Override
//	public WebDriver newDriver() {
//		// TODO Auto-generated method stub
//		return setChromeDesktop();
//	}
//	
//	@Override
//	public boolean takesScreenshots() {
//		// TODO Auto-generated method stub
//		return false;
//	}
	
	public WebDriver setChromeDesktop() {

		DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
		// Add the WebDriver proxy capability.
		desiredCapabilities.setCapability("name", "Emilian");
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(desiredCapabilities);
		
		
		
		
		 
	}

}
