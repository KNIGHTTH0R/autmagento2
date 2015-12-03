package com.steps.frontend;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import net.thucydides.core.annotations.Step;

import com.tools.requirements.AbstractSteps;

public class ReportsSteps extends AbstractSteps {

	private static final long serialVersionUID = 1L;

	/**
	 * the download directory is configured in pom to be "resources/downloads";
	 * we will clean that directory each time before another download
	 *
	 */
	@Step
	public void downloadProductsOrderedBySku() throws IOException {

		String projectBuildPath = System.getProperty("project.build.directory");
		System.out.println(projectBuildPath);
		projectBuildPath = projectBuildPath.replace("/target", "");
		System.out.println(projectBuildPath);

		File downloadsdirectory = new File(projectBuildPath + "/resources/downloads");
		FileUtils.cleanDirectory(downloadsdirectory);
		reportsPage().downloadProductsOrderedBySku();
	}

}
