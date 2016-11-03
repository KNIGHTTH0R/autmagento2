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
	
	//starterset
	public static final String STARTERSET = MongoReader.geteEnvironmentConstantsItem("STARTERSET");
	public static final String STARTERKITPRICE = MongoReader.geteEnvironmentConstantsItem("STARTERKITPRICE");
	public static final String STARTERKIT_SPECIALPRICE = MongoReader.geteEnvironmentConstantsItem("STARTERKIT_SPECIALPRICE");
	
}
