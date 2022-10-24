package org.command.service;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.command.models.MetaData;

public interface LsService {
	void ls(Properties properties, HashMap<String, MetaData> metaDataMap);
}
