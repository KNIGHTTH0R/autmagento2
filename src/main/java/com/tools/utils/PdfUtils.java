package com.tools.utils;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;

public class PdfUtils {

	public static void main(String[] args) {
		
		// THIS WORKS ONLY WHEN RUNNING FROM CMD LINE
		
//		String basedir = System.getProperty("basedir");
//		String downloadsdirectory = basedir + "/resources/downloads";
//		System.out.println(downloadsdirectory);
//		
//		File folder = new File(downloadsdirectory);
//		File[] listOfFiles = folder.listFiles();
//		
//		for (File file : listOfFiles) {
//		    if (file.isFile()) {
//		        System.out.println(file.getName());
//		    }
//		}
//		
//		PdfUtils.readPdf(downloadsdirectory);
		
	}

	public static void readPdf(String file) {
		try {
			PDDocument document = null;
			document = PDDocument.load(new File(file));
			document.getClass();
			if (!document.isEncrypted()) {
				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);
				PDFTextStripper Tstripper = new PDFTextStripper();
				String st = Tstripper.getText(document);
				System.out.println("Text:" + st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
