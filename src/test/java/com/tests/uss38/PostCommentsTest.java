package com.tests.uss38;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.FunctionalTest;
import com.connectors.mongo.MongoConnector;
import com.steps.external.SocialMedia.SocialMediaSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.SocialMediaConstansts;
import com.tools.data.socialMediaApiCommnets.CommentResponse;
import com.tools.persistance.MongoWriter;
import com.tools.utils.FieldGenerators;
import com.tools.utils.FieldGenerators.Mode;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class PostCommentsTest extends BaseTest {
	Map<String, String> comment = new HashMap<>();

	public String messageValue=FieldGenerators.generateRandomString(10, Mode.ALPHANUMERIC)+"test";
	CommentResponse postComm=new CommentResponse();
	
	
	@Steps
	public SocialMediaSteps socialMediaSteps;
	@Steps
	CustomVerification customVerification;
	

	@Before
	public void setUp() throws Exception {

		FunctionalTest.setup();
		MongoConnector.cleanCollection(getClass().getSimpleName()+"MSG");
		MongoConnector.cleanCollection(getClass().getSimpleName()+"ID");
	}
	
	@Test
		public void circleCommentsStatusPing(){
			comment.put("message", messageValue);
			postComm=given().contentType("application/json").body(comment).when().post("posts/1831408183552808_1831446426882317/comment?token="+SocialMediaConstansts.Token).then().statusCode(200).extract().as(CommentResponse.class);
			
			CustomVerification.verifyTrue("The repoonse id is null", postComm.getId()!=null);
			
			customVerification.printErrors();
			
	}
	
	@After
	public void saveData() {
		MongoWriter.saveStringValue(messageValue,getClass().getSimpleName()+"MSG");
		MongoWriter.saveStringValue(postComm.getId(),getClass().getSimpleName()+"ID");
		
	}
}
