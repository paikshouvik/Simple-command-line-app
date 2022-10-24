package org.command;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.command.models.MetaData;
import org.command.service.FetchService;
import org.command.service.LsService;
import org.command.service.impl.FetchServiceImpl;
import org.command.service.impl.LsServiceImpl;
import org.command.utils.BasicFileReader;
import org.command.utils.HtmlDownloadHelper;
import org.command.utils.UrlValidation;

public class Application implements Runnable {
	private static HashMap<String, MetaData> metaDataMap;
	private static boolean isBreak;
	private static FetchService fetchservice = new FetchServiceImpl();
	private static LsService lsservice = new LsServiceImpl();
	private static BasicFileReader fileReader = new BasicFileReader();
	private static HtmlDownloadHelper downloadhelper = new HtmlDownloadHelper();
	private static final String PROPERTIES_FILE = "application.properties";

	public static void main(String[] args) {

		System.out.println("-------------------");
		System.out.println(
				"This is a command line program that can fetch web pages and saves them to disk for later retrieval and browsing.");
		System.out.println("Type 'help' to know how to use this app.");
		System.out.println("-------------------");

		metaDataMap = null;
		System.out.println("Application started.");
		Scanner sc = new Scanner(System.in);
		LsService lsservice = new LsServiceImpl();

		// Get the properties from properties file
		Properties properties = fileReader.readApplicationProperties();
		String metadatafilename = properties.getProperty("app.metadata.path") + "\\"
				+ properties.getProperty("app.metadata.filename");

		// Load meta data from meta data file (@ mentioned in properties file)
		try {
			metaDataMap = (HashMap<String, MetaData>) fetchservice.loadMetaDataFromFile(metadatafilename, metaDataMap);
		} catch (Exception e) {

		}
		if (metaDataMap == null) {
			metaDataMap = new HashMap<String, MetaData>();
		}

		// store meta data to metadata file define in properties
		Application application = new Application();
		Thread thread = new Thread(application);
		thread.start();

		isBreak = false;
		while (!isBreak) {
			System.out.print("$> ");
			String commandLineInput = sc.nextLine();

			if (!(commandLineInput == null || commandLineInput.length() == 0)) {
				commandLineInput = commandLineInput.trim();
			}

			if (commandLineInput == null || commandLineInput.length() == 0) {
				System.out.println("please enter a valid command line input");
			} else if (commandLineInput.equals("ls")) {
				lsservice.ls(properties, metaDataMap);
			} else if (commandLineInput.startsWith("./fetch") && commandLineInput.contains(" --metadata ")) {
				fetchservice.fetchmetadata(commandLineInput, properties, metaDataMap);
			} else if (commandLineInput.startsWith("./fetch")) {
				fetchservice.fetch(commandLineInput, properties, metaDataMap);
			} else if ((commandLineInput.trim()).equals("help")) {
				help();
			} else {
				System.out.println("please enter a valid command line input");
			}
		}

	}

	public static void help() {
		System.out.println("- help                          : This help message");
		System.out.println("- ls                            : This command shows list of downloaded html files");
		System.out.println("- ./fetch                       : This can fetch web pages and saves them to disk");
		System.out.println("- ./fetch --metadata            : Record metadata about what was fetched");
		// System.out.printf("\n\n");
	}

	@Override
	public void run() {

		FetchService fetchservice = new FetchServiceImpl();
		BasicFileReader fileReader = new BasicFileReader();

		Properties properties = fileReader.readApplicationProperties();
		String metadatafilename = properties.getProperty("app.metadata.path") + "\\"
				+ properties.getProperty("app.metadata.filename");
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e1) {
		}

		// get the start time
		long start = System.currentTimeMillis();

		// get the end time
		long end = System.currentTimeMillis();

		long timeduration = 20000;
		long execution_time_threshold = 3000;

		if (metaDataMap != null) {
			// synchronized (metaDataMap) {
			while (!isBreak) {
				try {
					// System.out.println("********************** RUN
					// ****************************");
					start = System.currentTimeMillis();
					fetchservice.saveMetaDataToFile(metadatafilename, metaDataMap);
					end = System.currentTimeMillis();
					long sleeptime = (end - start) * 100;
					if (sleeptime < execution_time_threshold) {
						sleeptime = execution_time_threshold;
					}
					Thread.sleep(sleeptime);
				} catch (InterruptedException e) {
					System.out.println("Save Meta Data To File Error: " + e);
				}
			}
			// }
		} else {

		}
	}

}
