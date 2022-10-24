package org.command.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class BasicFileWriter {
	public void createDirectoryIfNotExists(String directoryName) {
		File theHtmlDirectory = new File(directoryName + "\\");
		if (!theHtmlDirectory.exists()) {
			theHtmlDirectory.mkdirs();
		}
	}
	
	public void saveFileAt(String data, String fileName) {
		PrintWriter printer = null;
		try {
			printer = new PrintWriter(new File(fileName));
			printer.write(data);
			printer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Failed to save file: "+e);
		} finally {
			if (printer != null) {
				printer.close();
			}
		}
	}
}
