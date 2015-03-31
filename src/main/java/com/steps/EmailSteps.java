package com.steps;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import com.tools.requirements.AbstractSteps;

public class EmailSteps extends AbstractSteps {

	private static final long serialVersionUID = 7847714736572013908L;

	@Step
	public void printEmailContent(String email) {
		System.out.println("message: " + email);
	}

	@Step
	public void validateEmailContent(String orderId, String message) {
		Assert.assertTrue("Failure: Message does not contain the total price", message.contains(orderId));
	}

	@Step
	public void validateEmail(String expected, String message) {
		Assert.assertTrue("Failure: Message does not contain the expected value: " + expected, message.contains(expected));
	}

	@Step
	public void validateURL(String URL, String context) {
		Assert.assertTrue("Failure: URL does not contain the context provided", URL.contains(context));
	}

	public String grabConfirmationLink(String message) {
		String resultURL = null;
		String[] lines = message.split("\n");

		for (String stringNow : lines) {
			if (stringNow.contains("Bestätigungslink")) {
				String[] subLines = stringNow.split("\"");
				resultURL = subLines[3];
				break;
			}
		}

		return resultURL;
	}

	public String grabPartyLink(String message) {
		String resultURL = null;
		String[] lines = message.split("\n");
		for (String stringNow : lines) {
			if (stringNow.contains("customer/party/confirm")) {
				resultURL = stringNow;
				break;
			}

		}
		return resultURL;
	}

	public String extractUrlFromEmailMessage(String email) {
		int matchStart = 0;
		int matchEnd = 0;
		int occ = 0;
		Pattern urlPattern = Pattern.compile("(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		Matcher matcher = urlPattern.matcher(email);
		while (matcher.find() & occ < 1) {
			occ++;
			matchStart = matcher.start(1);
			matchEnd = matcher.end();
			// now you have the offsets of a URL match
		}
		String link = email.substring(matchStart, matchEnd);
		return link;
	}
}
