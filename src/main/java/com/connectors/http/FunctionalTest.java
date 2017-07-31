package com.connectors.http;

import org.junit.BeforeClass;

import com.jayway.restassured.RestAssured;

public class FunctionalTest {
	@BeforeClass
    public static void setup() {
       
        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/facebook/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://staging-labs.api-social-media.pippajean.com";
        }
        RestAssured.baseURI = baseHost; 

    }

}
