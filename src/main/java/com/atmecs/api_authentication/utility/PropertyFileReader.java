package com.atmecs.api_authentication.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader 
{
	public static Properties properties;
	
	public static Properties readProperties(String filePath) 
	{
		properties = new Properties();

		try 
		{
			properties.load(new FileInputStream(filePath));
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("File not found in the given location" + e.getMessage());
		} 
		catch (IOException e)
		{
			System.out.println("Input Output Exception " + e.getMessage());
		}

		return properties;
		}
	
	
}
