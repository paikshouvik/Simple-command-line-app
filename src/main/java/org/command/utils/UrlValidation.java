package org.command.utils;

public class UrlValidation {
	
	public String getShortUrlSiteName(String url) {
		url = url.trim();
		char lastchar = url.charAt(url.length() - 1);
		while (!(lastchar - 'a' >= 0 && lastchar - 'a' < 26) && !(lastchar - 'A' >= 0 && lastchar - 'A' < 26)) {
			url = url.substring(0, url.length() - 1);
			lastchar = url.charAt(url.length() - 1);
		}
		if (url.startsWith("https://")) {
			return url.substring("https://".length());
		}
		if (url.startsWith("http://")) {
			return url.substring("http://".length());
		}
		return url;
	}

	public boolean isValidURL(String url) {
		url = url.trim();
		if (url.startsWith("https://")) {
			return true;
		}
		if (url.startsWith("http://")) {
			return true;
		}
		return false;
	}
}
