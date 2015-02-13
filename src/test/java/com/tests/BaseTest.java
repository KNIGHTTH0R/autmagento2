package com.tests;

import java.io.File;
import java.net.UnknownHostException;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.connectors.gmail.GmailConnector;
import com.connectors.mongo.MongoConnector;
import com.tools.Constants;
import com.tools.CustomVerification;
import com.tools.EmailConstants;
import com.tools.data.email.EmailCredentialsModel;

public class BaseTest {
	@Managed(uniqueSession = true)
	public WebDriver webdriver;

	@ManagedPages(defaultUrl = Constants.BASE_URL)
	public Pages pages;
	
	public MongoConnector mongoConnector;
	public GmailConnector gmailConnector;
	
	@Steps 
	public CustomVerification customVerifications;
	
    @Before
    public void startComponents() {
    	
    	File pathToBinary = new File("C:\\user\\Programme\\FirefoxPortable\\App\\Firefox\\firefox.exe");
    	FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
    	FirefoxProfile firefoxProfile = new FirefoxProfile();       
    	WebDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);

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
