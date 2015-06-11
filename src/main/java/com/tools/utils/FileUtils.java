package com.tools.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

	public static void moveFile(String source, String dest) {

		InputStream inStream = null;
		OutputStream outStream = null;

		try {

			File sourceFile = new File(source);
			File destFile = new File(dest);

			inStream = new FileInputStream(sourceFile);
			outStream = new FileOutputStream(destFile);

			byte[] buffer = new byte[1024];

			int length;
			while ((length = inStream.read(buffer)) > 0) {

				outStream.write(buffer, 0, length);

			}
			inStream.close();
			outStream.close();

			sourceFile.delete();

			System.out.println("Done!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
