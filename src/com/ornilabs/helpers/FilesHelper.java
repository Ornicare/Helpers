package com.ornilabs.helpers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FilesHelper {
	public static void writeInFile(String filePath, String data) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(filePath, "UTF-8");
		writer.print(data);
		writer.close();
	}
}
