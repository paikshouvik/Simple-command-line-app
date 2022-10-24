package org.command.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;

import org.command.models.MetaData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HtmlDownloadHelper {
	public boolean downloadPage(String url, String fileName) throws Exception {
		StringBuffer sb = new StringBuffer();
		URLConnection urlConnection = new URL(url).openConnection();
		urlConnection.setReadTimeout(5000);
		urlConnection.setConnectTimeout(5000);
		String line;
		try {
			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
				// sb.append("\r\n");
			}
		} catch (Exception e) {
			throw e;
		}
		BasicFileWriter fw = new BasicFileWriter();
		fw.saveFileAt(sb.toString(), fileName);
		return true;
	}

	public MetaData downloadUsingJsoup(String url, String fileName) {
		Document doc = null;
		MetaData metadata = null;
		BasicFileWriter filewriter = new BasicFileWriter();
		try {
			doc = Jsoup.connect(url).get();
			Elements links = doc.select("a[href]");
			Elements media = doc.select("[src]");
			Elements imports = doc.select("link[href]");
			Elements actions = doc.select("form[action]");
			Elements img = doc.getElementsByTag("img");
            //System.out.println("################"+img.size()+"#################");
            
			//System.out.println("Links: " + links.size());
			//System.out.println("Media: " + media.size());
			//System.out.println("Imports: " + imports.size());
			//System.out.println("Form Links: " + actions.size());
			
			metadata = new MetaData();
			long millis = System.currentTimeMillis();  
			java.util.Date date = new java.util.Date(millis);  
			SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss");
			String datestring = formatter.format(date);
			metadata.setLast_fetch(datestring);
			metadata.setNoOfImageCount(img.size());
			metadata.setNoOfLinksCount(links.size() + imports.size());
			metadata.setUrlName(getShortUrlSiteName(url));
			filewriter.saveFileAt(doc.toString(),fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return metadata;

	}
	private static String getShortUrlSiteName(String url) {
		url = url.trim();
		if (url.startsWith("https://")) {
			return url.substring("https://".length());
		}
		if (url.startsWith("http://")) {
			return url.substring("http://".length());
		}
		return url;
	}
}
