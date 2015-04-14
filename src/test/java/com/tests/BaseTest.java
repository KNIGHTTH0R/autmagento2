package com.tests;

import java.net.MalformedURLException;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.pages.Pages;

import org.junit.Before;
import org.openqa.selenium.WebDriver;

import com.connectors.gmail.GmailConnector;
import com.connectors.mongo.MongoConnector;
import com.tools.EmailConstants;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.env.stagingaut.Constants;

public class BaseTest {
	@Managed(uniqueSession = true)
//	@Managed(uniqueSession = true, driver="htmlunit")
	public WebDriver webdriver;

	@ManagedPages(defaultUrl = Constants.BASE_URL)
	public Pages pages;
	
	public MongoConnector mongoConnector;
	public GmailConnector gmailConnector;
	
	
    @Before
    public void startComponents() throws MalformedURLException {
        try {
            System.err.println("--------------------------------- Test Start---------------------------------------");

            mongoConnector = new MongoConnector();
            System.out.println("Connect to Mongo DB...");
            System.out.println("DB: " + mongoConnector.getDbAddress());
        }
        catch (Exception e) {
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
}
