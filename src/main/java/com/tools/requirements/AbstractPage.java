package com.tools.requirements;

import net.thucydides.core.pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class AbstractPage extends PageObject {

	public void elementjQueryClick(String element) {

		evaluateJavascript("var dd =jQuery(' " + element + " ').eq(0);dd.click(); ");
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

	public void elementjQueryMouseOver(String element) {
		evaluateJavascript("var dd =jQuery(' " + element + " ').eq(1);dd.mouseover(); ");
	}
	
	public void changeClassName() {
		evaluateJavascript("#addContactForm input[name*='firstname'].class = 'input-text';");
	}

}
