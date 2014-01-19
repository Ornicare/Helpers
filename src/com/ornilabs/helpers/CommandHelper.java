package com.ornilabs.helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandHelper {
	public static String executeCommand(String cmd) {

		String output = "";
		try {
			// Run "netsh" Windows command
			Process process = Runtime.getRuntime().exec(cmd);

			// Get input streams
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(
					process.getErrorStream()));

			// Read command standard output
			String s;
			while ((s = stdInput.readLine()) != null) {
				output += s + "\n";
			}

			// Read command errors
			while ((s = stdError.readLine()) != null) {
				output += s + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		return output;

	}
}
