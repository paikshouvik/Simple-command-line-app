package org.command.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import org.command.models.MetaData;

public class BasicFileReader {
	public Properties readApplicationProperties() {
		try{
			//System.out.println("user dir: "+System.getProperty("user.dir"));
			ClassLoader cl = this.getClass().getClassLoader();
			InputStream input = cl.getResourceAsStream("application.properties");
			Properties prop = new Properties();
			// load a properties file
			prop.load(input);
			
			
			// get the property value and print it out
			BasicFileWriter filewritter = new BasicFileWriter();
			filewritter.createDirectoryIfNotExists(prop.getProperty("app.html.path"));
			filewritter.createDirectoryIfNotExists(prop.getProperty("app.metadata.path"));

			// System.out.println(prop.getProperty("app.html.path"));
			// System.out.println(prop.getProperty("app.metadata.path"));
			// System.out.println(prop.getProperty("app.metadata.filename"));

			return prop;
		} catch (IOException ex) {
			System.out.println(
					"" + this.getClass().getName() + ": " + "Read application properties method failed because: " + ex);
		}
		return null;
	}

	public List<String> listOfFile(String fileName) {
		File folder = new File(fileName);
		File[] listOfFiles = folder.listFiles();
		List<String> list = new ArrayList<String>();
		if(listOfFiles!=null) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					list.add(listOfFiles[i].getName());
				} else if (listOfFiles[i].isDirectory()) {

				}
			}
		}
		return list;
	}

	public Map<String, MetaData> loadMetaDataFromFile(String metaDataFileName, Map<String, MetaData> metaDataMap) {
		if (metaDataMap == null) {
			metaDataMap = new HashMap<String, MetaData>();
		}
		//System.out.println(metaDataMap);
		
		try (Scanner scanner = new Scanner(new File(metaDataFileName))) {
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String arr[] = line.split(", ");
				// System.out.println(line);
				MetaData objdata = new MetaData();

				for (int i = 0; i < arr.length; i++) {
					if (i == 0) {
						objdata.setUrlName(arr[i]);
					} else if (i == 1) {
						//System.out.println();
						// SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss");
						objdata.setLast_fetch((arr[i]));
					} else if (i == 2) {
						try {
							objdata.setNoOfLinksCount(Integer.parseInt(arr[i]));
						} catch (Exception e) {
							objdata.setNoOfLinksCount(0);
						}
					} else if (i == 3) {
						try {
							objdata.setNoOfImageCount(Integer.parseInt(arr[i]));
						} catch (Exception e) {
							objdata.setNoOfImageCount(0);
						}
					}

					metaDataMap.put(objdata.getUrlName(), objdata);
				}
			}
		} catch (IOException e) {
			if (metaDataMap == null) {
				metaDataMap = new HashMap<String, MetaData>();
			}
		}
		//System.out.println(metaDataMap);
		return metaDataMap;
		
	}

}
