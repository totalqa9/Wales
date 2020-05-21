package org.iit.mmp.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ProjectConfiguration {
	
	public Properties loadProperties() throws IOException
	{
		
		  String filepath = System.getProperty("user.dir")+"\\config\\config.properties";
		
		  File src=new File(filepath);

		  // Create  FileInputStream object 
		  FileInputStream fis=new FileInputStream(src);

		  // Create Properties class object to read properties file
		  Properties pro=new Properties();

		  // Load file so we can use into our script 
		  pro.load(fis);

		  return pro;

	}

}
