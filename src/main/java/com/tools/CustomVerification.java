package com.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
//import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.steps.backend.ImportOrdersSteps;
import com.tools.data.NavOrderImportReport;
import com.tools.requirements.AbstractSteps;

import jxl.common.Assert;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.StepFailureException;

public class CustomVerification extends AbstractSteps{
	private static final long serialVersionUID = 3609006291221433240L;
	private static final Logger LOGGER = LoggerFactory.getLogger(PageObject.class);
    static StringBuffer verificationMessages;
    public static int count=0;
    public static int count2=0;
    public static Map<String, String> errorData = new HashMap<String, String>();
    public static List<NavOrderImportReport> errorDataList=new ArrayList<NavOrderImportReport>();
	
    public CustomVerification(Pages pages){
		verificationMessages = new StringBuffer();
	}
	
	@Step
	public static void verifyTrue(String message, boolean condition) throws StepFailureException{
		
		try{
			Assert.verify(condition,message);
		}catch(Exception e){
			verificationMessages.append("\n" + e.getMessage());
			LOGGER.info(message);
			
			
		}
	}
	
	@Step
	public static void verifyTrueForOrderImport(String message, boolean condition) throws StepFailureException{
		
		try{
			Assert.verify(condition,message);
		}catch(Exception e){
			NavOrderImportReport error=new NavOrderImportReport();
			String incrementid=ImportOrdersSteps.getOrderIncrementId();
			// errorData.put(Integer.toString(count2++), incrementid+"|"+message );
		
			if(message.contains("GT_diff_not_adjusted")){
				error.setGrandTotal(message.substring(message.indexOf('[')+1, message.indexOf(']')));
			}
			error.setOrderIncrementId(incrementid);
			error.setErrorMessage(message);
			count++;
			verificationMessages.append("\n" + e.getMessage());
			LOGGER.info(message);	
			errorDataList.add(error);
		}
		
	}
	
	public int returnErrorCounter(){
		int x=count;
		count=0;
		return x;
		
	}
	
	@Step
	@Screenshots(onlyOnFailures=true)
	public void  printErrors(){
		verifyNoErrors(verificationMessages.toString());
	}
	
	@Step
	public void  verifyNoErrors(String veriStr){
		org.junit.Assert.assertTrue("Errors Count: " + StringUtils.countMatches(veriStr, "\n"), !veriStr.contains("\n"));
		
	}
	

}
