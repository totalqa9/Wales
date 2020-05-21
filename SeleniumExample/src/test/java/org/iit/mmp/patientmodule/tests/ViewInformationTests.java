package org.iit.mmp.patientmodule.tests;

import java.io.IOException;
import java.util.Arrays;

import org.iit.mmp.helper.HelperClass;


import org.iit.mmp.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.read.biff.BiffException;

public class ViewInformationTests {
	
	WebDriver driver;
	Utility util;
	String filePath = "C:\\workspace\\SeleniumExample\\mmpData\\loginTestData.xls";
	String URL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php";
	String NavTab = "Information";
	String expected = "Manage My Patient (MMP) is a medical practice management"
			+ " solution that boosts productivity by automating the day-to-day "
			+ "tasks that can slow an office manager down. Central delivers much"
			+ " more than medical billing software. Sure, it has the tools to "
			+ "help generate cleaner claims and reduce denials, but our "
			+ "easy-to-use practice management software also streamlines "
			+ "your workflow to deliver seamless handoffs across departments. "
	        + "Manage My Patient (MMP) becomes your practice’s command center, "
			+ "delivering robust, real-time analytics through customizable reports and"
			+ " dashboards to ensure you know how your business is performing on the metrics that matter most.";
	String actual = "";

	@Test(dataProvider = "testData", description="US_005 View the information",groups={"US_005","regression","sanity","patientmodule"})
	public void validateInformationMsg(String uName, String password)
	{
		instantiateDriver();
		util = new Utility(driver);
		util.launchApplicationURL(URL);
		util.login(uName, password);
		util.moduleNavigation(NavTab);
		boolean result = validateInformation();
		Assert.assertTrue(result);
	}

	@DataProvider (name="testData")
	public String [][] loginData() throws BiffException, IOException{
		
		String [][] loginData = HelperClass.readXls(filePath);
		return loginData;
	}
	
	public void instantiateDriver()
	{
		WebDriverManager.chromedriver().setup();
		driver  = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public boolean validateInformation()
	{
		boolean result = true;
		String actualText = "";
		String[] expectedList = expected.split(" ");
		actual = (driver.findElement(By.xpath("//div[@class='panel-title']")).getText()).trim();
		String[] textList = actual.split("\n");
		System.out.println(textList.length);
		for (int i=0;i<textList.length; i++) {
			textList[i] = textList[i].trim();
			if (!(textList[i].isEmpty())){
				actualText = actualText+" "+textList[i];
			}
		}
		System.out.println(actualText);
		actualText = actualText.trim();
		String[] actualList = actualText.split(" ");
		result = Arrays.equals(expectedList, actualList);
		System.out.println(result);
		
		return result;
		
		
		
	}


}
