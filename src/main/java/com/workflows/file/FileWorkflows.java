package com.workflows.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileWorkflows {

	public static void writeLinesToInvalidContextCsv(String csvFileName, List<String> lines) throws IOException {

		String basedir = System.getProperty("basedir");
		File downloadsdirectory = new File(basedir + "/resources/" + csvFileName);
		FileUtils.writeLines(downloadsdirectory, lines, false);
	}

}
