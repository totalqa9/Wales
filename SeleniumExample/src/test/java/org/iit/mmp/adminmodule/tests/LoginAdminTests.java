package org.iit.mmp.adminmodule.tests;



import java.util.HashMap;

import org.iit.mmp.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginAdminTests {

	WebDriver driver;
	Utility util;
	String URL = "http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/login.php";
	String filePath = "C:\\workspace\\SeleniumExample\\mmpData\\testData.xlsx";
	String moduleName = "Logout";
	HashMap <String, String> hMap;
	String name;
	String subject;
	String description;
	
	@Test //(dataProvider = "testData", description = "US_001 Login to the app as an admin", groups={"US_001", "adminModule"})
	//public void loginAdminTest(String uName, String password){
		public void loginAdminTest(){
		instantiateDriver();
		util = new Utility(driver);
		hMap = new HashMap<String, String> ();
		util.launchApplicationURL(URL);
		//getLoginPage();
		util.AdminLogin("shak", "9ol.<KI*");
		util.AdminModuleNavigation("Messages");
		HashMap <String, String > hMap = retrieveRecentMessageDetails();
		
		
		
		
		
	}
	
	public HashMap <String, String> retrieveRecentMessageDetails(){
		
	    name = driver.findElement(By.xpath("//table[@class='table']//tr[2]/td[1]")).getText();
	    subject = driver.findElement(By.xpath("//table[@class='table']//tr[2]/td[2]")).getText();
	    description = driver.findElement(By.xpath("//table[@class='table']//tr[3]/td[2]")).getText();
		hMap.put("Name", name);
		hMap.put("Subject", subject);
		hMap.put("Description", description);
		System.out.println(driver.findElement(By.xpath("//table[@class='table']//tr[2]/td[2]")).getText());
		System.out.println(driver.findElement(By.xpath("//table[@class='table']//tr[3]/td[2]")).getText());
		return hMap;
		
	}
		
	 public void instantiateDriver(){
		
		 WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		
		 
	 }
	
	
}
