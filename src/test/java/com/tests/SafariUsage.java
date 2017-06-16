package com.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.annotations.findby.By;

public class SafariUsage {
	 
	public static void main(String[] args)
	{
		WebDriver driver = new SafariDriver();
		driver.get("http://store.demoqa.com");
 
		//Find some element on DemoQa.com
		WebElement element = driver.findElement(By.id("login"));
		element.click();
 
	}
 
}