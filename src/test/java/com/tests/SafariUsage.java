package com.tests;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.annotations.findby.By;

public class SafariUsage {
	 
	@Test
	public void safariUsage() throws Exception {
	
		
		// Change this to match the location of your server
	    URL server = new URL("http://172.22.4.79:82/wd/hub");

	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setBrowserName("firefox");
	    capabilities.setCapability("name", "Emilian");
	    System.out.println("Connecting to " + server);

	    WebDriver driver = new RemoteWebDriver(server, capabilities);

	    driver.get("http://www.google.com");

	    driver.quit();
		
	}
//	
//	public static void main(String[] args)
//	{
//	//	WebDriver driver = new SafariDriver();
//		WebDriver driver = new ChromeDriver();
//		driver.get("http://store.demoqa.com");
// 
//		//Find some element on DemoQa.com
//		WebElement element = driver.findElement(By.id("login"));
//		element.click();
// 
//	}
 
}