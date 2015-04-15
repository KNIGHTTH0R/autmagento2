package com.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.pages.Pages;

import org.junit.Before;
import org.openqa.selenium.WebDriver;

import com.connectors.gmail.GmailConnector;
import com.connectors.mongo.MongoConnector;
import com.tools.EmailConstants;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoTableKeys;
import com.tools.persistance.MongoWriter;

public class BaseTest {
	@Managed(uniqueSession = true)
	public WebDriver webdriver;

	@ManagedPages(defaultUrl = "http://staging-aut.pippajean.com/customer/account/login/")
//	@ManagedPages(defaultUrl = UrlConstants.BASE_URL_AUT)
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
        
    	//Context and environment - clean and setup to mongo
        MongoConnector.cleanCollection(MongoTableKeys.TEST_CONFIG);
        String contextValue = System.getProperty("runContext");
        String envValue = System.getProperty("runEnv");
        MongoWriter.saveEnvContext(envValue, contextValue);

        String baseUrl = "";
        Properties prop = new Properties();
		InputStream input = null;
        
        try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "config.properties");
			prop.load(input);
			baseUrl = prop.getProperty("baseUrl");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        
        
        System.out.println("Base URL: " + baseUrl);
//        MongoConnector.cleanCollection(MongoTableKeys.TEST_CONFIG);
        MongoWriter.saveEnvContextUrl(envValue, contextValue, baseUrl);
        
        EmailCredentialsModel emailDefaults = new EmailCredentialsModel();
        emailDefaults.setHost(EmailConstants.RECEIVING_HOST);
        emailDefaults.setProtocol(EmailConstants.PROTOCOL);
        emailDefaults.setUsername(EmailConstants.EMAIL_DEF_USERNAME);
        emailDefaults.setPassword(EmailConstants.EMAIL_DEF_PASSWORD);
        
        gmailConnector = new GmailConnector(emailDefaults);
    }
}
