package com.steps.frontend;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class ReportsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	@Step
	public void downloadProductsOrderedBySku() throws IOException {

		File downloadsdirectory = new File("F:/CIRemote/JenkinsCIPippaJean/workspace/RUN_SINGLE_TEST/resources/downloads");
		FileUtils.cleanDirectory(downloadsdirectory);
		reportsPage().downloadProductsOrderedBySku();
	}

}
