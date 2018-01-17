package com.tools.customDrivers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sun.jna.Platform;

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
		
		/*DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.setExperimentalOption("name","emilian");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(options);*/
		
		/*String downloadFilepath = System.getProperty("basedir") + "/resources/downloads/";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("download.default_directory", downloadFilepath);
		chromePrefs.put("plugins.always_open_pdf_externally", true);
		chromePrefs.put("profile.default_content_settings.popups", 0);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);*/
		
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	    desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
	    desiredCapabilities.setCapability(CapabilityType.PLATFORM, Platform.LINUX);
	    desiredCapabilities.setCapability("name", "myTestName");

		return new ChromeDriver(desiredCapabilities);
		
		
		
		
		
		 
	}

}
