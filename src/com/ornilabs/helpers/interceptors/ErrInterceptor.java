package com.ornilabs.helpers.interceptors;

import java.io.PrintStream;

public class ErrInterceptor extends PrintStreamInterceptor {

	public ErrInterceptor(PrintStream originalPrintStream, String outputFile) {
		super(originalPrintStream, outputFile);
		System.setErr(this);
	}

	public void finalize() {
		System.setErr(originalPrintStream);
	}

}
