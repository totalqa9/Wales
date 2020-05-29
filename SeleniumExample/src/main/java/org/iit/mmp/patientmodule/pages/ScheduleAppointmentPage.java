package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;

import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ScheduleAppointmentPage {
	
	WebDriver driver;
	HelperClass helperObj;
	
	 //Page elements
	By createAppointmentButton = By.xpath("//input[@value='Create new appointment']");
	By datePickerID = By.id("datepicker");
	By timeID = By.id("time");
	By anyPlace = By.xpath("//body");
	By okbutton = By.id("ChangeHeatName");
	By symID=By.id("sym");
	By submitXpath = By.xpath("//input[@value='Submit']");
	By dateOfAppointmentHP = By.xpath("//table[@class='table']//tr[1]/td[1]");
	By timeHP = By.xpath("//table[@class='table']//tr[1]/td[2]");
	By symptomsHP = By.xpath("//table[@class='table']//tr[1]/td[3]");
	By doctorNameHP = By.xpath("//table[@class='table']//tr[1]/td[4]");
	By dateOfAppointmentSP = By.xpath("(//h3[@class='panel-title'])[2]");
	By timeSP = By.xpath("//a[contains(text(),'Time :')]");
	By symptomsSP = By.xpath("//a[contains(text(),'Symptoms:')]");
	By doctorNameSP = By.xpath("//a[contains(text(),'Provider:')]");
	String doctorXpath =  "//h4[contains(text(),'%%doctorName%%')]/ancestor::td/button[@id='opener']";
	
	
	 // Page Initialization through constructor
	public ScheduleAppointmentPage(WebDriver driver)
	{
		this.driver = driver;
		helperObj = new HelperClass(driver);
	}
		
	// Page Actions
	
	
	public void clickOnCreateAppointmentButton() {
		
		driver.findElement(createAppointmentButton).click();
	}
	
	public HashMap<String, String> selectDoctor(String doctorName) throws InterruptedException {
		
		HashMap<String,String> hMap= new HashMap<String,String>();
		driver.findElement(By.xpath(doctorXpath.replace("%%doctorName%%", doctorName))).click();
		driver = helperObj.switchToAFrameAvailable("myframe",20);
		String dateOfAppointment = Utility.getFutureDate(20);
		driver.findElement(datePickerID).sendKeys(dateOfAppointment);
		String time = "10Am";
		new Select(driver.findElement(timeID)).selectByVisibleText(time);
		driver.findElement(anyPlace).click();
		//Thread.sleep(2000);
		driver.findElement(okbutton).click();
		String symptoms= "Booking an Appointment "+doctorName +"on date::"+dateOfAppointment+ "for symptom fever";
		driver.findElement(symID).sendKeys(symptoms);
		driver.findElement(submitXpath).click();
		hMap.put("dateOfAppointment", dateOfAppointment);
		hMap.put("time", time);
		hMap.put("symptoms", symptoms);
		hMap.put("doctorName", doctorName);
		return hMap;
	}
	
	public boolean validateAppointmentDetailsinHomePage(HashMap<String,String> hMap) {
		boolean result = false;
		helperObj.moduleNavigation("HOME");
		if( hMap.get("dateOfAppointment").equals(driver.findElement(dateOfAppointmentHP).getText())
				&& hMap.get("time").equals(driver.findElement(timeHP).getText())
				&& hMap.get("symptoms").equals(driver.findElement(symptomsHP).getText())
				&& hMap.get("doctorName").contains(driver.findElement(doctorNameHP).getText()))
		{
			result = true;
		}
		return result;
	}
	
	public boolean validateAppointmentDetailsinSchedulePage(HashMap<String,String> hMap)
	{
		boolean result = false;
		helperObj.moduleNavigation("Schedule Appointment");
		WebElement we1 = driver.findElement(timeSP);
		WebElement we2 = driver.findElement(doctorNameSP);
		WebElement we3 = driver.findElement(symptomsSP);
		String appTime[] = we1.getText().split(":");
		String providerName[] =we2.getText().split(":");
		String symptoms[] = we3.getText().split(":",2);
		if( hMap.get("dateOfAppointment").equals(driver.findElement(dateOfAppointmentSP).getText().trim())
				&& hMap.get("time").equals(appTime[1].trim())
				&& hMap.get("symptoms").equals(symptoms[1].trim())
				&& hMap.get("doctorName").contains(providerName[1]))  
		{
			result = true;
		}

		return result;
	}
}
