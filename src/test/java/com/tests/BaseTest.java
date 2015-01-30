package com.tests;

import java.net.UnknownHostException;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.pages.Pages;

import org.junit.Before;
import org.openqa.selenium.WebDriver;

import com.connectors.gmail.GmailConnector;
import com.connectors.mongo.MongoConnector;
import com.tools.Constants;
import com.tools.EmailConstants;
import com.tools.data.EmailCredentialsModel;

public class BaseTest {
	@Managed(uniqueSession = true)
	public WebDriver webdriver;

	@ManagedPages(defaultUrl = Constants.BASE_URL)
	public Pages pages;
	
	public MongoConnector mongoConnector;
	public GmailConnector gmailConnector;
	
    @Before
    public void startComponents() {

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
}
