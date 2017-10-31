package com.tests.uss41;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.ValidationSteps;
import com.steps.backend.BackEndSteps;
import com.steps.backend.ImportExport.ImportExportSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.Credentials;
import com.tools.data.NavOrderImportReport;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US 40 Update online SC and klout score", type = "Scenarios")
@RunWith(SerenityRunner.class)
public class US41001CreateFile extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public ValidationSteps validationSteps;
	@Steps
	public ImportExportSteps importExportSteps;

	public String incrementId;

	@Before
	public void setUp() throws Exception {
		incrementId = "10026761800";
	}

	@Test
	public void us41001UploadOrdersFromCsvFileTest() {
		// String
		// path="C:\\Users\\emilianmelian\\Desktop\\OrderImport\\cm_uppload_sample.csv";
		String eol = System.getProperty("line.separator");

		try (Writer writer = new FileWriter("C:/Users/emilianmelian/Desktop/OrderImport/cm_uppload_sample.csv")) {
			writer.flush();
			//table header
			writer.append("Order Increment Id").append(',').append("Shipping Track Number").append(eol);
		
			//table body content
			writer.append(incrementId).append(',').append("24435").append(eol);

		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}

	}

}