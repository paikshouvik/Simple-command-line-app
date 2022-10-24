package org.command.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MetaData {
	private String urlName;
	private String last_fetch;
	private int noOfLinksCount;
	private int noOfImageCount;

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

//	public void setLast_fetch(String last_fetch) {
//		SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss");
//		try {
//			this.last_fetch = formatter.parse(last_fetch);
//		} catch (ParseException e) {
//		}
//	}

	public String getLast_fetch() {
		return last_fetch;
	}

	public void setLast_fetch(String last_fetch) {
		this.last_fetch = last_fetch;
	}

	public int getNoOfImageCount() {
		return noOfImageCount;
	}

	public void setNoOfImageCount(int noOfImageCount) {
		this.noOfImageCount = noOfImageCount;
	}

	public int getNoOfLinksCount() {
		return noOfLinksCount;
	}

	public void setNoOfLinksCount(int noOfLinksCount) {
		this.noOfLinksCount = noOfLinksCount;
	}

	@Override
	public String toString() {
		return "MetaData [urlName=" + urlName + ", last_fetch=" + last_fetch + ", noOfLinksCount=" + noOfLinksCount
				+ ", noOfImageCount=" + noOfImageCount + "]";
	}
	
}
