package org.iit.mmp.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	
   public static String generateRandom(int n,int range)
	{
		String str = "";
		for(int j = 0 ; j < 5;j++)
		{
				str = str+ 9;	 
		}
		System.out.println(str);
			
		return str+new Random().nextInt(range);
	}
	public static String getFutureDate(int days,String pattern)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		
		Date d = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String date = sdf.format(d);
		return date;
	}
	public static String getFutureDate(int days)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		
		Date d = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
		String date = sdf.format(d);
		return date;
	}
	 
	public static String[][] readXls(String filePath)
	{
		String[][] str = new String[10][10];
		return str;
	}
	public static String[][] readXlsx(String filePath)
	{
		String[][] str = new String[10][10];
		return str;
	}
	 
}









