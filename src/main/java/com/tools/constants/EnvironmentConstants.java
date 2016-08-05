package com.tools.constants;

import com.tools.persistance.MongoReader;

public class EnvironmentConstants {
	
	public static final String USERNAME = MongoReader.geteEnvConstantsItem("USERNAME");
	public static final String PASSWORD = MongoReader.geteEnvConstantsItem("PASSWORD");
	public static final String EXPORT_JOB_TRIGGER_URL = MongoReader.geteEnvConstantsItem("EXPORT_JOB_TRIGGER_URL");
	public static final String REOPEN_MONTH_JOB = MongoReader.geteEnvConstantsItem("REOPEN_MONTH_JOB");
	public static final String REINDEX_SC_CONTEXT_JOB = MongoReader.geteEnvConstantsItem("REINDEX_SC_CONTEXT_JOB");
	public static final String IMPORT_ALL_JOB = MongoReader.geteEnvConstantsItem("IMPORT_ALL_JOB");
	public static final String CHANGE_EMAIL_JOB_TRIGGER_URL = MongoReader.geteEnvConstantsItem("CHANGE_EMAIL_JOB_TRIGGER_URL");
	public static final String CHANGE_TP_DELIVERY_URL = MongoReader.geteEnvConstantsItem("CHANGE_TP_DELIVERY_URL");
	public static final String RUN_IP_SCRIPT_JOB_URL = MongoReader.geteEnvConstantsItem("RUN_IP_SCRIPT_JOB_URL");
	public static final String JOB_TOKEN = MongoReader.geteEnvConstantsItem("JOB_TOKEN");
	public static final String RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT = MongoReader.geteEnvConstantsItem("RUN_SCHEDULED_ORDERS_PROCESS_SCRIPT");
	public static final String RUN_POSTPONE_CANCEL_EMAIL_SCRIPT = MongoReader.geteEnvConstantsItem("RUN_POSTPONE_CANCEL_EMAIL_SCRIPT");
	
}
