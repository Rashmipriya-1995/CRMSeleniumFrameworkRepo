package com.crm.generic.fileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	
	public String getDataFromPropertiesFile(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream("./TestData/CommonData.properties");//java object of the physical file
		Properties prop=new Properties();
		prop.load(fis);
		String data = prop.getProperty(key);// load all keys
		return data;
		
	}

}
