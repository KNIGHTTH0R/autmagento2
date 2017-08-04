package com.steps.external.SocialMedia;

import java.util.List;

import org.junit.Assert;

import com.tools.CustomVerification;
import com.tools.constants.SocialMediaConstansts;
import com.tools.data.socialMediaApi.MeModel;
import com.tools.data.socialMediaApiCommnets.Data;
import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class SocialMediaSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void validateName(String name, String exectedName) {
		CustomVerification.verifyTrue("The name is not displayed correctly", name.contentEquals(exectedName));
	}
	
	@Step
	public void validateFriendName(String friendName, String expected) {
		CustomVerification.verifyTrue("The Frind name is not displayed correctly", friendName.contentEquals(expected));
	}

	@Step
	public void validateFacebookGroupIdIsNotEmpty(String facebook_group_id) {
		Assert.assertTrue("the facebook_group_id is not displayed", !facebook_group_id.isEmpty());
		
	}

	@Step
	public void validateFacebook_nameIsNotEmpty(String facebook_name) {
		Assert.assertTrue("the facebook_name is not displayed", !facebook_name.isEmpty());
		
	}

	@Step
	public void validateFacebook_privacyIsNotEmpty(String facebook_privacy) {
		Assert.assertTrue("the facebook_privacy is not displayed", !facebook_privacy.isEmpty());
		
	}

	@Step
	public void validateStatus(String status, String expectedStatus) {
		CustomVerification.verifyTrue("Failure: the video status is not displayed correctly", status.contains(expectedStatus));
		
	}

	@Step
	public void validateVideoId(String object_id, String expectedId) {
		CustomVerification.verifyTrue("Failure: the video id is not displayed correctly", object_id.contains(expectedId));
		
	}

	@Step
	public void validateStartTime(String start_time, String broadcast_start_time) {
		Assert.assertTrue("Failure: the video start time is not displayed correctly",start_time.contains(broadcast_start_time));
		
	}

	@Step
	public void validateDuration(String duration, String expectedDuration) {
		Assert.assertTrue("Failure: the video duration is not displayed correctly", duration.contains(expectedDuration));
		
	}

	@Step
	public void validateIdIsNotEmpty(String id) {
		Assert.assertTrue("the name is not displayed correctly", !id.isEmpty());

		
	}

	@Step
	public void validateCreatedTimeIsNotEmpty(String created_time) {
		Assert.assertTrue("the name is not displayed correctly", !created_time.isEmpty());

		
	}

	@Step
	public void validateMessage(String message, String messageValue) {
		CustomVerification.verifyTrue("The comment does not displayed expected message",
				message.contentEquals(messageValue));
	}

	@Step
	public void validateFromName(String name, String expectedName) {
		CustomVerification.verifyTrue("The expeditor name is not dipslayed correctly",
				name.contentEquals(expectedName));
		
	}


	public void validateComments(Data[] data, String messageValue, String messageId) {
		boolean found = false;
		for (Data msg : data) {
			if (msg.getId().contentEquals(messageId)) {
				found = true;
				validateMessage(msg.getMessage(), messageValue);
				validateFromName(msg.getFrom().getName(), SocialMediaConstansts.Name);
			}

		}
		Assert.assertTrue("The comment with id: " + messageId + "was not found", found);
		
	}

	@Step
	public void validateReplyComment(Data[] data, String replyMessageValue, String replyMessageId, String messageId) {
		boolean messageFound = false;
		boolean replyFound = false;
		for (Data d : data) {
			if (d.getId().contentEquals(messageId)) {
				messageFound = true;

				if (d.getComments() != null) {
					List<Data> replyData = d.getComments().getData();
					for (Data reply : replyData) {
						if (reply.getMessage().contentEquals(replyMessageValue)) {
							replyFound = true;
							break;
						}
					}
					Assert.assertTrue("The reply  with value: " + replyMessageValue + " was not found", replyFound);
				}
				break;
			}
		}

		Assert.assertTrue("The comment with id: " + messageId + " was not found", messageFound);
		
	}

	@Step
	public void validateURI(String url,String uri) {
		CustomVerification.verifyTrue("The uri is not displayed in response(url)", url.contains(uri));
	}
}
