package com.atmecs.api_authentication.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileReader
{
	String jsonFile;
	public JsonFileReader(String jsonFile)
	{
		this.jsonFile = jsonFile;
	}
	
	public Object parse()
	{
		JSONParser parser = new JSONParser();
		
		Object object = null;
		try 
		{
			object = parser.parse(new FileReader(new File(jsonFile)));
		} 				
		catch (IOException | ParseException e) 
		{
			e.printStackTrace();
		}
		return object;
		
	}
}
