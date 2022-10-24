package org.command.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.command.models.MetaData;
import org.command.service.FetchService;
import org.command.utils.BasicFileReader;
import org.command.utils.BasicFileWriter;
import org.command.utils.HtmlDownloadHelper;
import org.command.utils.UrlValidation;

public class FetchServiceImpl implements FetchService {

	private static UrlValidation urlValidation = new UrlValidation();
	private static HtmlDownloadHelper downloadhelper = new HtmlDownloadHelper();
	
	
	private MetaData fetchServerData(String filePath, String url) {
		File urlfile = new File(filePath + "\\" + url);
		if (!urlfile.exists()) {
		}
		return null;
	}

	/*
	@Override
	public MetaData fetchMetadata(String url, String metadatafilename, Map<String, MetaData> metaDataMap) {
		if (metaDataMap == null) {
			new BasicFileReader().loadMetaDataFromFile(metadatafilename, metaDataMap);
		}
		return metaDataMap.getOrDefault(url, null);
	}
	*/

	
	public Map<String, MetaData> loadMetaDataFromFile(String metadatafilename, Map<String, MetaData> metaDataMap) {
		 Map<String, MetaData> map = new BasicFileReader().loadMetaDataFromFile(metadatafilename, metaDataMap);
		return map;
	}

	
	public void saveMetaDataToFile(String metadatafilename, Map<String, MetaData> metaDataMap) {
		StringBuffer sb = new StringBuffer();
		if (metaDataMap != null) {
			for (String key : metaDataMap.keySet()) {
				MetaData data = metaDataMap.get(key);
				sb.append(data.getUrlName() + ", " + data.getLast_fetch() + ", " + data.getNoOfLinksCount() + ", "
						+ data.getNoOfImageCount());
				sb.append("\r\n");
			}
			new BasicFileWriter().saveFileAt(sb.toString(), metadatafilename);
		}
	}
	
	
	public void fetch(String commandLineInput, Properties properties, HashMap<String, MetaData> metaDataMap) {

		try {
			String arr[] = commandLineInput.split(" ");
			String htmlPathName = properties.getProperty("app.html.path");
			System.out.println("-------------------------------------------");
			for (int i = 0; i < arr.length; i++) {
				if (urlValidation.isValidURL(arr[i])) {
					// System.out.println("");
					try {
						String fileName = properties.getProperty("app.html.path") + "\\" + urlValidation.getShortUrlSiteName(arr[i])
								+ ".html";
						MetaData metaobj = downloadhelper.downloadUsingJsoup((arr[i]), fileName);
						if (metaobj != null) {
							System.out.println("Download complete: " + metaobj.getUrlName() + ".html");
						}else {
							System.out.println("Download failed: " + metaobj.getUrlName() + ".html");
						}
						if (metaobj != null) {
							metaDataMap.put(metaobj.getUrlName(), metaobj);
						}
					} catch (Exception e) {
						System.out.println("Download Error: " + e);
					}

				}

			}
			System.out.println("-------------------------------------------");
		} catch (Exception e) {
			System.out.println("fetch method error: " + e);
		}

	}

	public void fetchmetadata(String commandLineInput, Properties properties, HashMap<String, MetaData> metaDataMap) {
		try {
			String arr[] = commandLineInput.split(" ");
			System.out.println("-------------------------------------------");
			for (int i = 0; i < arr.length; i++) {
				if (urlValidation.isValidURL(arr[i])) {
					System.out.println();
					MetaData obj = metaDataMap.getOrDefault(urlValidation.getShortUrlSiteName(arr[i]), null);
					if (obj != null) {
						metaDataMap.put(obj.getUrlName(), obj);
						System.out.println("site: " + obj.getUrlName());
						System.out.println("num_links: " + obj.getNoOfLinksCount());
						System.out.println("images: " + obj.getNoOfImageCount());
						System.out.println("last_fetch: " + obj.getLast_fetch());
					} else {
						System.out
								.println("Corresponding meta data does not exist for: " + urlValidation.getShortUrlSiteName(arr[i]));
					}

					System.out.println("-------------------------------------------");
				}else if(arr[i].contains("./fetch") || arr[i].contains("--metadata")) {
					
				}
				else {
					System.out.println("Invalid URL : meta data retrieval failed "+ arr[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("fetch metadata method error: " + e);
		}

	}
}
