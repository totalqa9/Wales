package org.iit.mmp.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ProjectConfiguration {
	
	public Properties loadProperites() throws Exception{
		
		Properties pro = new Properties();
		String filePath = System.getProperty("user.dir")+"\\config\\config.properties";
		System.out.println("Printing the file path from loadProperties method "+filePath);
		File src = new File(filePath);
		FileInputStream fis = new FileInputStream(src);
		pro.load(fis);
		return pro;
	}
	

}
