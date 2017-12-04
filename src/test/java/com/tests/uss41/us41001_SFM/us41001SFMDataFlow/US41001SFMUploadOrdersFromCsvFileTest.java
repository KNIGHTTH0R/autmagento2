package com.tests.uss41.us41001_SFM.us41001SFMDataFlow;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApacheHttpHelper;
import com.steps.ValidationSteps;
import com.steps.backend.BackEndSteps;
import com.steps.backend.ImportExport.ImportExportSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.Credentials;
import com.tools.constants.EnvironmentConstants;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US 40 Update online SC and klout score", type = "Scenarios")
@RunWith(SerenityRunner.class)
public class US41001SFMUploadOrdersFromCsvFileTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public ValidationSteps validationSteps;
	@Steps
	public ImportExportSteps importExportSteps;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void us41001SFMUploadOrdersFromCsvFileTest() throws Exception {
		String path="C:\\Users\\emilianmelian\\Desktop\\OrderImport\\cm_uppload_sample.csv";

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnSystemTab();
		importExportSteps.selectDataFlowProfileMenu();
		importExportSteps.selectImportOrderDataCsv();
		importExportSteps.selectUploadFileTab();
		importExportSteps.uploadFile(path);
		importExportSteps.runProfile("cm_uppload_sample.csv");
		
		ApacheHttpHelper.sendGet(EnvironmentConstants.SALES_ORDER_DATA_IMPORT_UPDATE_ON_CLOUD_AUT, EnvironmentConstants.USERNAME,
				EnvironmentConstants.PASSWORD);

	}

}