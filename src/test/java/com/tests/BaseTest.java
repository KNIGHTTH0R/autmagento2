package com.tests;

import java.net.UnknownHostException;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.pages.Pages;

import org.junit.Before;
import org.openqa.selenium.WebDriver;

import com.connectors.mongo.MongoConnector;
import com.tools.Constants;

public class BaseTest {
	@Managed(uniqueSession = true)
	public WebDriver webdriver;

	@ManagedPages(defaultUrl = Constants.BASE_URL)
	public Pages pages;
	
	public MongoConnector mongoConnector;
	
    @Before
    public void startComponents() {

        try {
            System.out.println("--------------------------------- Test Start---------------------------------------");

            mongoConnector = new MongoConnector();
            System.out.println("Connected to Mongo DB");
        }
        catch (UnknownHostException e) {
            System.out.println("Error: Mongo connection could not be initialized");
            e.printStackTrace();
        }
    }
}
