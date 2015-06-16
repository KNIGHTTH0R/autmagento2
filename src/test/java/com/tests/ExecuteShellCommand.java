package com.tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExecuteShellCommand {

	public static void main(String[] args) {

		ExecuteShellCommand obj = new ExecuteShellCommand();

//		String domainName = "google.com";

//		String command = "ping -n 3 " + domainName;
		String command = "ssh alexandra.badiu@staging.pippajean.com";

		String output = obj.executeCommand(command);

		System.out.println(output);
		System.out.println("done");
	}

	private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

}
