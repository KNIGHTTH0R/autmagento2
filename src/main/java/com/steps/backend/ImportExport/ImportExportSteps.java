package com.steps.backend.ImportExport;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

import com.tools.requirements.AbstractSteps;

import net.thucydides.core.annotations.Step;

public class ImportExportSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void selectDataFlowProfileMenu() {
		importExportPage().selectDataFlowProfileMenu();
	}

	@Step
	public void selectUploadFileTab() {
		importExportPage().selectUploadFileTab();

	}

	@Step
	public void uploadFile(String path) {
		importExportPage().uploadFile(path);
		importExportPage().saveAndContinue();
	}

	@Step
	public void selectImportOrderDataCsv() {
		importExportPage().selectImportDataCsv();
	}

	@Step
	public void runProfile(String path) {
		importExportPage().selectRunProfileTab();
		importExportPage().selectUploadedFile(path);
		importExportPage().clickOnRunProfileBtn();
		waitABit(5000);
	}
	
	
	private static String radomGeneratorTrackNumber(){
		Random rand = new Random();
		int  n = rand.nextInt(40000) + 1;
		return Integer.toString(n);
	}

	@Step
	public void createOrderFile(String incrementId) {
		String eol = System.getProperty("line.separator");
		String trackNo=radomGeneratorTrackNumber();
		
		try (Writer writer = new FileWriter("C:/Users/emilianmelian/Desktop/OrderImport/cm_uppload_sample.csv")) {
			writer.flush();
			// table header
			writer.append("Order Increment Id").append(',').append("Shipping Track Number").append(eol);

			// table body content
			writer.append(incrementId).append(',').append(trackNo).append(eol);

		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}
	}
	

}
