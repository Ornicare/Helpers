package com.ornilabs.helpers.interceptors;

import java.io.PrintStream;

public class OutInterceptor extends PrintStreamInterceptor {

	public OutInterceptor(PrintStream originalPrintStream, String outputFile) {
		super(originalPrintStream, outputFile);
		System.setOut(this);
	}

	public void finalize() {
		System.setOut(originalPrintStream);
	}

}
