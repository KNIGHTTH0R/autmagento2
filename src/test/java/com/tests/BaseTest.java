package com.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.connectors.gmail.GmailConnector;
import com.connectors.mongo.MongoConnector;
import com.tools.Constants;
import com.tools.CustomVerification;
import com.tools.EmailConstants;
import com.tools.data.email.EmailCredentialsModel;

public class BaseTest {
	@Managed(uniqueSession = true)
//	@Managed(uniqueSession = true, driver="htmlunit")
	public WebDriver webdriver;

	@ManagedPages(defaultUrl = Constants.BASE_URL)
	public Pages pages;
	
	public MongoConnector mongoConnector;
	public GmailConnector gmailConnector;
	
	@Steps 
	public CustomVerification customVerifications;
	
    @Before
    public void startComponents() throws MalformedURLException {
        try {
            System.err.println("--------------------------------- Test Start---------------------------------------");

            mongoConnector = new MongoConnector();
            System.out.println("Connected to Mongo DB");
        }
        catch (UnknownHostException e) {
            System.out.println("Error: Mongo connection could not be initialized");
            e.printStackTrace();
        }
        
        EmailCredentialsModel emailDefaults = new EmailCredentialsModel();
        emailDefaults.setHost(EmailConstants.RECEIVING_HOST);
        emailDefaults.setProtocol(EmailConstants.PROTOCOL);
        emailDefaults.setUsername(EmailConstants.USERNAME);
        emailDefaults.setPassword(EmailConstants.PASSWORD);
        
        gmailConnector = new GmailConnector(emailDefaults);
    }
    
    
    
	/**
	 * See CustomVerification -  for multiple assertions
	 */
	@After
	public void printErrors(){
		customVerifications.printErrors();
	}
}
