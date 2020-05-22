package org.iit.mmp.patientmodule.tests;

import java.io.IOException;
//import java.util.concurrent.TimeUnit;

import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPatientTests {
	
	WebDriver driver;
	HelperClass helperObj;
	Utility util;
	String URL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/";
	String filePath = "C:\\workspace\\SeleniumExample\\mmpData\\testData.xlsx";
	String moduleName = "Logout";
	
	
	@Test (dataProvider = "testData", description="US_001 Login to the app as a Patient",groups={"US_001","regression","sanity","patientmodule"})
	public void Login(String uName, String password){
		
		
		instantiateDriver();
		helperObj = new HelperClass(driver);
		helperObj.launchApplicationURL(URL);
		getLoginpage();
		helperObj.login(uName, password);
		helperObj.moduleNavigation(moduleName);
				
	}
	@DataProvider (name="testData")
	public String[][] loginData() throws IOException{
		
		String [][] loginData = Utility.readXlsx(filePath);
		return loginData;
		
	}
	public void instantiateDriver(){
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
				
	}
	
	public void getLoginpage(){
		
		driver.findElement(By.linkText("Patient Login")).click();
		driver.findElement(By.linkText("Login")).click();
	}
	
	
	

}
