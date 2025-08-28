package com.crm.generic.fileUtility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonUtility {
	
	public String getDataFromJsonFile(String Key) throws IOException, ParseException
	{
		FileReader fileR=new FileReader("./TestData/CommonData.json");
		JSONParser parser=new JSONParser();
		Object jobj = parser.parse(fileR);
		
		//convert java into HashMap(down cast)
		
		JSONObject map=(JSONObject) jobj;
		 String data = (String) map.get(Key); //type casting
		return data;
		 
	}

}
