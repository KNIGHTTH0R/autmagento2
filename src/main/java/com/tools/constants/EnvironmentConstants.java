package com.tools.constants;

import com.tools.persistance.MongoReader;

public class EnvironmentConstants {
	
	public static final String USERNAME = MongoReader.geteEnvironmentConstantsItem("USERNAME");
	public static final String PASSWORD = MongoReader.geteEnvironmentConstantsItem("PASSWORD");
	public static final String USERNAME_JENKINS_COMM = MongoReader.geteEnvironmentConstantsItem("USERNAME_JENKINS_COMM");
	public static final String PASSWORD_JENKINS_COMM = MongoReader.geteEnvironmentConstantsItem("PASSWORD_JENKINS_COMM");
	public static final String EXPORT_JOB_TRIGGER_URL = MongoReader.geteEnvironmentConstantsItem("EXPORT_JOB_TRIGGER_URL");
	public static final String REOPEN_MONTH_JOB = MongoReader.geteEnvironmentConstantsItem("REOPEN_MONTH_JOB");
	public static final String REINDEX_SC_CONTEXT_JOB = MongoReader.geteEnvironmentConstantsItem("REINDEX_SC_CONTEXT_JOB");
	public static final String IMPORT_ALL_JOB = MongoReader.geteEnvironmentConstantsItem("IMPORT_ALL_JOB");
	public static final String CHANGE_EMAIL_JOB_TRIGGER_URL = MongoReader.geteEnvironmentConstantsItem("CHANGE_EMAIL_JOB_TRIGGER_URL");
	public static final String CHANGE_TP_DELIVERY_URL = MongoReader.geteEnvironmentConstantsItem("CHANGE_TP_DELIVERY_URL");
	public static final String RUN_IP_SCRIPT_JOB_URL = MongoReader.geteEnvironmentConstantsItem("RUN_IP_SCRIPT_JOB_URL");
	public static final String JOB_TOKEN = MongoReader.geteEnvironmentConstantsItem("JOB_TOKEN");
	public static final String RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT = MongoReader.geteEnvironmentConstantsItem("RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT");
	public static final String RUN_POSTPONE_CANCEL_EMAIL_SCRIPT = MongoReader.geteEnvironmentConstantsItem("RUN_POSTPONE_CANCEL_EMAIL_SCRIPT");
	//ip overview report
	public static final String RUN_ORDER_IMPORT = MongoReader.geteEnvironmentConstantsItem("RUN_ORDER_IMPORT");
	public static final String RUN_CREDITMEMO_IMPORT = MongoReader.geteEnvironmentConstantsItem("RUN_CREDITMEMO_IMPORT");
	public static final String RUN_SEND_IP_REPORT = MongoReader.geteEnvironmentConstantsItem("RUN_SEND_IP_REPORT");
	
	//starterset
	public static final String STARTERSET = MongoReader.geteEnvironmentConstantsItem("STARTERSET");
	public static final String STARTERSETSPECIAL = MongoReader.geteEnvironmentConstantsItem("STARTERSETSPECIAL");
	public static final String STARTERKITPRICE = MongoReader.geteEnvironmentConstantsItem("STARTERKITPRICE");
	public static final String STARTERKIT_SPECIALPRICE = MongoReader.geteEnvironmentConstantsItem("STARTERKIT_SPECIALPRICE");
	

	//comission and magento  soap API
//	public static final String COMMISSION_URL = MongoReader.geteEnvironmentConstantsItem("COMMISSION_URL");
//	public static final String SOAP_URL=MongoReader.geteEnvironmentConstantsItem("SOAP_URL");
//	public static final String SOAP_USERNAME=MongoReader.geteEnvironmentConstantsItem("SOAP_USERNAME");
//	public static final String SOAP_PASSWORD=MongoReader.geteEnvironmentConstantsItem("SOAP_PASSWORD");
	
	//pippajean-uphgrade env  - uncomment following for testing without running the test from cmd line
//	public static final String COMMISSION_URL = "http://commission.evozon.com/api/";
//	public static final String SOAP_URL="https://pippajean-upgrade.evozon.com/";

//	public static final String SOAP_USERNAME="automation";
//	public static final String SOAP_PASSWORD="aut1234";
	
//	public static final String SOAP_USERNAME="navision";
//	public static final String SOAP_PASSWORD=";Bn8NzlGhi[z";
//	
	
	
	// staging-aut env  - uncomment following for testing without running the test from cmd line
//	public static final String COMMISSION_URL = "http://commission-staging-aut.pippajean.com/api/";
//	public static final String SOAP_URL="http://aut-pippajean.evozon.com/";
//	public static final String SOAP_USERNAME="stagingaut";
//	public static final String SOAP_PASSWORD="stagingaut1";
	
//	public static final String SOAP_USERNAME="sos";
//	public static final String SOAP_PASSWORD="emilian1";
	
	//CLOUD: staging-aut env  - uncomment following for testing without running the test from cmd line
	
	public static final String COMMISSION_URL = "commission-aut.pippajean.com";
	public static final String SOAP_URL="https://staging-aut.pippajean.com/";
		
	public static final String SOAP_USERNAME="navision";
	public static final String SOAP_PASSWORD=";Bn8NzlGhi[z";
//	
	
	
	
}
