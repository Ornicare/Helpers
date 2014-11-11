package com.ornilabs.helpers.interceptors;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Date;

public abstract class PrintStreamInterceptor extends PrintStream {

	protected PrintStream originalPrintStream;
	private String outputFile;

	public PrintStreamInterceptor(PrintStream originalPrintStream,
			String outputFile) {
		super(originalPrintStream);
		this.originalPrintStream = originalPrintStream;
		this.outputFile = outputFile;
	}

	public void println(String o) {
		originalPrintStream.println(o);
		addText(o + "\n");
	}

	public void print(String o) {
		originalPrintStream.print(o);
		addText(o);
	}

	// Object
	public void println(Object o) {
		originalPrintStream.println(o);
		addText(o.toString() + '\n');

	}

	public void print(Object o) {
		originalPrintStream.print(o);
		addText(o.toString());
	}

	// Char
	public void println(char o) {
		originalPrintStream.println(o);
		addText(o + "\n");
	}

	public void print(char o) {
		originalPrintStream.print(o);
		addText(String.valueOf(o));
	}

	// Double
	public void println(double o) {
		originalPrintStream.println(o);
		addText(String.valueOf(o + "\n"));
	}

	public void print(double o) {
		originalPrintStream.print(o);
		addText(String.valueOf(o));
	}

	// Float
	public void println(float o) {
		originalPrintStream.println(o);
		addText(String.valueOf(o + "\n"));
	}

	public void print(float o) {
		originalPrintStream.print(o);
		addText(String.valueOf(o));
	}

	// Long
	public void println(long o) {
		originalPrintStream.println(o);
		addText(String.valueOf(o + "\n"));
	}

	public void print(long o) {
		originalPrintStream.print(o);
		addText(String.valueOf(o));
	}

	// Int
	public void println(int o) {
		originalPrintStream.println(o);
		addText(String.valueOf(o + "\n"));
	}

	public void print(int o) {
		originalPrintStream.print(o);
		addText(String.valueOf(o));
	}

	// Boolean
	public void println(boolean o) {
		originalPrintStream.println(o);
		addText(String.valueOf(o + "\n"));
	}

	public void print(boolean o) {
		originalPrintStream.print(o);
		addText(String.valueOf(o));
	}

	// Vide
	public void println() {
		addText("\n");
		originalPrintStream.println();
	}

	private synchronized void addText(String text) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(
					outputFile, true)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(String s : text.split("\\r?\\n")) {
			out.print(new Date() +"	|		");
			out.println(s);
		}
		out.close();
	}
}
