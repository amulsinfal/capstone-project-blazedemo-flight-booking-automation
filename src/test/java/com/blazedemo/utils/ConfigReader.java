package com.blazedemo.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static String getValue(String key) {
		
		if(key == null || key.isEmpty()) {
			throw new RuntimeException("Key provided is blank or null.");
		}
		Properties properties = new Properties();
		FileInputStream fis = null;

		try {
			String configFile = System.getProperty("user.dir") + 
					"/src/test/resources/properties/config.properties";
			fis = new FileInputStream(configFile);
			properties.load(fis);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("config.properties file not found: " + e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException("Error reading config.properties file: " + e.getMessage());
		}

		String value = properties.getProperty(key);
		if (value == null){
			throw new RuntimeException("Key provided is not present on the config.properties file.");
		} else if (value.trim().isEmpty()) {
			throw new RuntimeException("Value of '" + key + "' is blank in config.properties file.");
		} else {
			return properties.getProperty(key).trim();
		}
	}
}
