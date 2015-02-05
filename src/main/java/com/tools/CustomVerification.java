package com.tools;

import jxl.common.Assert;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.StepFailureException;

import org.apache.commons.lang3.StringUtils;
//import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tools.requirements.AbstractSteps;

public class CustomVerification extends AbstractSteps{
	private static final long serialVersionUID = 3609006291221433240L;
	private static final Logger LOGGER = LoggerFactory.getLogger(PageObject.class);
    StringBuffer verificationMessages;
    
	public CustomVerification(Pages pages){
		verificationMessages = new StringBuffer();
	}
	
	@Step
	public void verifyTrue(String message, boolean condition) throws StepFailureException{
		try{
			Assert.verify(condition,message);
		}catch(Exception e){
			verificationMessages.append("\n" + e.getMessage());
			LOGGER.info(message);
		}
	}
	
	@Step
	public void printErrors(){
		verifyNoErrors(verificationMessages.toString());
	}
	
	@Step
	public void verifyNoErrors(String veriStr){
		org.junit.Assert.assertTrue("Errors Count: " + StringUtils.countMatches(veriStr, "\n"), !veriStr.contains("\n"));
	}
	

}
