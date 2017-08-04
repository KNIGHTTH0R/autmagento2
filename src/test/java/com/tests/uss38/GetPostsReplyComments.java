package com.tests.uss38;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.FunctionalTest;
import com.steps.external.SocialMedia.SocialMediaSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.SocialMediaConstansts;
import com.tools.data.socialMediaApiCommnets.Data;
import com.tools.data.socialMediaApiCommnets.PostsCommentsModel;
import com.tools.persistance.MongoReader;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class GetPostsReplyComments extends BaseTest {
	@Steps
	public SocialMediaSteps socialMediaSteps;
	@Steps
	CustomVerification customVerification;
	
	private String replyMessageValue,messageId,replyMessageId;

	@Before
	public void setUp() throws Exception {
		FunctionalTest.setup();
		replyMessageValue = MongoReader.grabStringValue("PostReplyCommentsTest" + "MSG").get(0);
		replyMessageId = MongoReader.grabStringValue("PostReplyCommentsTest" + "ID").get(0);
		messageId = MongoReader.grabStringValue("PostCommentsTest" + "ID").get(0);
	}
	

	@Test
	public void getPostsReplyComments() {

		PostsCommentsModel status = given().when()
				.get("posts/1831408183552808_1831446426882317/comments?token=" + SocialMediaConstansts.Token).then().statusCode(200).extract()
				.as(PostsCommentsModel.class);

		Data[] data = status.getData();
		
		socialMediaSteps.validateReplyComment(data,replyMessageValue,replyMessageId,messageId);
		
		customVerification.printErrors();
	}
}
