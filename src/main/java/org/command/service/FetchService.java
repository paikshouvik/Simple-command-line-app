package org.command.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.command.models.MetaData;

public interface FetchService {

	void fetchmetadata(String commandLineInput, Properties properties, HashMap<String, MetaData> metaDataMap);
	
	void fetch(String commandLineInput, Properties properties, HashMap<String, MetaData> metaDataMap);
	
	void saveMetaDataToFile(String metadatafilename, Map<String, MetaData> metaDataMap);
	
	Map<String, MetaData>  loadMetaDataFromFile(String metadatafilename, Map<String, MetaData> metaDataMap);
}
