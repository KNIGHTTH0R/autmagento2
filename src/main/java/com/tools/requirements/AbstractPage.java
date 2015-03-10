package com.tools.requirements;

import net.thucydides.core.pages.PageObject;

public class AbstractPage extends PageObject{
	
	public void elementjQueryClick(String element) {
		
		  evaluateJavascript("var dd =jQuery(' " + element
		    + " ').eq(0);dd.click(); ");
		 }
	

}
