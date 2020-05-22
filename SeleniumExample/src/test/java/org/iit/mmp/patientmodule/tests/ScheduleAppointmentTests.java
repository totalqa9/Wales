package org.iit.mmp.patientmodule.tests;

import java.io.IOException;
import java.util.HashMap;

import org.iit.mmp.helper.HelperClass;
//import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.iit.mmp.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
//import jxl.read.biff.BiffException;

public class ScheduleAppointmentTests {
	WebDriver driver ;
	HelperClass helperObj;
	String URL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php";
	String filePath = "C:\\workspace\\SeleniumExample\\mmpData\\loginTestData.xls";
	
	
	

	@Test(dataProvider = "testData", description="US_004 Schedule Appointment",groups={"US_004","regression","sanity","patientmodule"})
	public void validateAppointmentDetails(String uName, String password) throws Exception
	{
		instantiateDriver();
		helperObj = new HelperClass(driver);
		helperObj.launchApplicationURL(URL);
		helperObj.login(uName, password);
		helperObj.moduleNavigation("Schedule Appointment");
		clickOnCreateAppointmentButton();
		HashMap<String,String> hMap = selectDoctor("Dr.Charlie");
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(validateAppointmentDetailsinHomePage(hMap));
		sa.assertTrue(validateAppointmentDetailsinSchedulePage(hMap));
		sa.assertAll();

	}

	@DataProvider (name="testData")
	public String [][] logiData() throws Exception, IOException{

		String [][] loginData = Utility.readXls(filePath);
		return loginData;
	}

	public void clickOnCreateAppointmentButton()
	{
		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
	}

	public HashMap<String, String> selectDoctor(String doctorName) throws Exception
	{
		HashMap<String,String> hMap= new HashMap<String,String>();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//h4[contains(text(),'"+doctorName+"')]/ancestor::td/button[@id='opener']")).click();
		driver = helperObj.switchToAFrameAvailable("myframe",20);
		String dateOfAppointment = Utility.getFutureDate(20);
		driver.findElement(By.id("datepicker")).sendKeys(dateOfAppointment);
		String time = "10Am";
		new Select(driver.findElement(By.id("time"))).selectByVisibleText(time);
		//Thread.sleep(3000);
		driver.findElement(By.xpath("//body")).click();
		driver.findElement(By.id("ChangeHeatName")).click();
		String symptoms= "Booking an Appointment "+doctorName +" on date:: "+dateOfAppointment+ " for symptom fever";
		// if symptoms contain the character :  getting the error message in validating the function
		driver.findElement(By.id("sym")).sendKeys(symptoms);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		hMap.put("dateOfAppointment", dateOfAppointment);
		hMap.put("time", time);
		hMap.put("symptoms", symptoms);
		doctorName = doctorName.substring(3);
		System.out.println(doctorName);
		hMap.put("doctorName", doctorName);
		return hMap;
	}
	public boolean validateAppointmentDetailsinHomePage(HashMap<String,String> hMap)
	{
		boolean result = false;
		helperObj.moduleNavigation("HOME");
		if( hMap.get("dateOfAppointment").equals(driver.findElement(By.xpath("//table[@class='table']//tr[1]/td[1]")).getText())
				&& hMap.get("time").equals(driver.findElement(By.xpath("//table[@class='table']//tr[1]/td[2]")).getText())
				&& hMap.get("symptoms").equals(driver.findElement(By.xpath("//table[@class='table']//tr[1]/td[3]")).getText())
				&& hMap.get("doctorName").equals(driver.findElement(By.xpath("//table[@class='table']//tr[1]/td[4]")).getText()))
		{
			result = true;
		}
		System.out.println(result);
		return result;
	}
	public boolean validateAppointmentDetailsinSchedulePage(HashMap<String,String> hMap)
	{
		boolean result = false;
		helperObj.moduleNavigation("Schedule Appointment");
		WebElement we1 = driver.findElement(By.xpath("//a[contains(text(),'Time')]"));
		WebElement we2 = driver.findElement(By.xpath("//a[contains(text(),'Provider')]"));
		WebElement we3 = driver.findElement(By.xpath("//a[contains(text(),'Symptoms')]"));

		String[] appointmentTime = we1.getText().split(":");
		String[] providerName = we2.getText().split(":");
		String[] symptoms = we3.getText().split(":",2);

		/*System.out.println(driver.findElement(By.xpath("//div[@class='col-md-3']//h3[@class='panel-title']")).getText());
		System.out.println(driver.findElement(By.xpath("//div[contains(@class,'list-group')]/child::a[contains(text(),'Time')]")).getText());
		System.out.println(driver.findElement(By.xpath("//div[contains(@class,'list-group')]/child::a[contains(text(),'Symptoms')]")).getText());
		System.out.println(driver.findElement(By.xpath("//div[contains(@class,'list-group')]/child::a[contains(text(),'Provider')]")).getText());
		System.out.println("**********");
		System.out.println(hMap.get("dateOfAppointment"));
		System.out.println(hMap.get("time"));
		System.out.println(hMap.get("symptoms"));
		System.out.println(hMap.get("doctorName"));
		System.out.println(driver.findElement(By.xpath("//div[@class='col-md-3']//h3[@class='panel-title']")).getText());
		System.out.println(appointmentTime[1].trim());
		
		System.out.println(providerName[1].trim());*/
		System.out.println(symptoms[1].trim());
		if( hMap.get("dateOfAppointment").equals(driver.findElement(By.xpath("//div[@class='col-md-3']//h3[@class='panel-title']")).getText())
				&& hMap.get("time").equals(appointmentTime[1].trim())
				&& hMap.get("symptoms").equals(symptoms[1].trim())
				&& hMap.get("doctorName").equals(providerName[1].trim()))
		{
			result = true;
		}
		System.out.println(result);
		return result;
		
	}

	public void instantiateDriver()
	{
		WebDriverManager.chromedriver().setup();
		driver  = new ChromeDriver();
		driver.manage().window().maximize();
	}

}
