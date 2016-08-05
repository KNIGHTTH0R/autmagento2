package com.tools.requirements;

import net.thucydides.core.pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.tools.constants.TimeConstants;

public class AbstractPage extends PageObject {

	public void elementjQueryClick(String element) {
		evaluateJavascript("var dd =jQuery(\" " + element + " \").eq(0);dd.click(); ");
	}

	public void scrollToPageTop() {
		evaluateJavascript("window.scrollTo(document.body.scrollHeight,0);");
	}

	public void scrollPageDown() {
		getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
	}

	public void elementFocus(String element) {
		evaluateJavascript("var element =jQuery(' " + element + " ');element.focus();");
	}

	public void scrollToElement(String element) {
		evaluateJavascript("document.getElementById(\"" + element + "\").scrollIntoView();");
	}

	public void elementjQueryMouseOver(String element) {
		evaluateJavascript("var dd =jQuery(' " + element + " ').eq(1);dd.mouseover(); ");
	}

	/**
	 * Wait for document ready state for {@link Constants}.PAGE_LOAD_MAX_RETRY
	 * time.
	 */
	public void waitForPageToLoad() {
		int retry = 0;
		do {
			waitABit(TimeConstants.WAIT_TIME_SMALL);
			retry++;
		} while (retry <= TimeConstants.PAGE_LOAD_MAX_RETRY
				&& evaluateJavascript("return document.readyState").equals("complete") != true);
	}

	public void navigate(String URL) {
		if (!URL.contains("www.pippajean.com")) {
			getDriver().get(URL);
			getDriver().manage().window().maximize();
		}
	}

}
