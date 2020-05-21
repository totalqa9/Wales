package org.iit.mmp.patientmodule.tests;

import java.io.IOException;
import java.util.HashMap;

import org.iit.mmp.adminmodule.tests.LoginAdminTests;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.utility.Utility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.read.biff.BiffException;

public class SendMessagesTests {

	WebDriver driver;
	Utility util;
	String filePath = "C:\\workspace\\SeleniumExample\\mmpData\\loginTestData.xls";
	String URL ="http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php";
	String urlAdminLogin = "http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/login.php";
	String subject = "";
	String message = "";
	String actualMsg="";
	String expectedMsg = "Messages Successfully sent.";
	//HashMap <String, String> hMapExpected;
	
	@Test (dataProvider = "testData", description="US_009 SendMessageTests",groups={"US_009","regression","sanity","patientmodule"})
	public void sendMessage(String uName, String password){
		
		instantiateDriver();
		util = new Utility(driver);
		util.login(uName, password);
		util.moduleNavigation("Messages");
		sendMessage();
		validateMessageFromPatientModule();
		util.moduleNavigation("Logout");
		//validateMessageFromAdminModule(uName, password);
	}
	
	@DataProvider (name="testData")
	public String [][] loginData() throws BiffException, IOException{
		
		String [][] loginData = HelperClass.readXls(filePath);
		return loginData;
		
	}
	public void sendMessage(){
		
		
		driver.findElement(By.id("subject")).sendKeys(subject);
		driver.findElement(By.id("message")).sendKeys(message);
		driver.findElement(By.xpath("//input[@value='Send']")).click();
		try{
			Alert alert = driver.switchTo().alert();
			actualMsg = alert.getText();
			alert.accept();
		} catch(Exception e){
			System.out.println("Alert Not Present : "+e.getMessage());
		}
	}
	
	public void validateMessageFromPatientModule(){
		
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	
	public void validateMessageFromAdminModule(String uName, String password){
		
		util.launchApplicationURL(urlAdminLogin);
		util.AdminLogin(uName, password);
		util.moduleNavigation("Messages");
		LoginAdminTests adminObj = new LoginAdminTests();
		HashMap <String, String> hMap = adminObj.retrieveRecentMessageDetails();
		
		
		
		
		
		
		
	}
	public void instantiateDriver(){
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
}
