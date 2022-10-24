package org.command.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.command.models.MetaData;
import org.command.service.LsService;
import org.command.utils.BasicFileReader;

public class LsServiceImpl implements LsService {

	
	
	private List<String> getListOfHtml(String directoryname) {
		BasicFileReader fileReader = new BasicFileReader();
		List<String> list = fileReader.listOfFile(directoryname);
		return list;
	}
	
	public void ls(Properties properties, HashMap<String, MetaData> metaDataMap) {
		
		List<String> list = getListOfHtml(properties.getProperty("app.html.path")+"\\");
		for (String key:  metaDataMap.keySet()) {
			String name = metaDataMap.get(key)!=null ? metaDataMap.get(key).getUrlName() : null;
			if(name!=null) {
				System.out.println(name+".html");
			}
			
		}
	}

}
