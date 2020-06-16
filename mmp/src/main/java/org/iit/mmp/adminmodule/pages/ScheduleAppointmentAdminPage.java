package org.iit.mmp.adminmodule.pages;

import java.io.IOException;
import java.util.HashMap;

import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


	
	public class ScheduleAppointmentAdminPage {		
		
		WebDriver driver;
		HelperClass helperObj;
		
		
		By datepicker=By.xpath("//input[@id='datepicker']");
		By appoitmentTime=By.id("time");
		By continueB=By.id("ChangeHeatName");
		By sForm=By.name("sym");
		By sySumbit=By.xpath("//input[@value='Submit']");
		
		
		
		
		public ScheduleAppointmentAdminPage(WebDriver driver) throws IOException
		{
			this.driver=driver;
			helperObj=new HelperClass(driver);
		}
			
			//create visit schedule appointment
			public HashMap<String, String> scheduleAppointment(String doctorName) throws InterruptedException
			{
				HashMap<String,String> hMap= new HashMap<String,String>();
				driver.findElement(By.xpath("//h4[contains(text(),'"+doctorName+"')]/ancestor::td/button[@id='opener']")).click();
				driver = helperObj.switchToAFrameAvailable("myframe",20);
				String dateOfAppointment = Utility.getFutureDate(10);
				driver.findElement(datepicker).sendKeys(dateOfAppointment);
				String time = "10Am";
				new Select(driver.findElement(appoitmentTime)).selectByVisibleText(time);
				Thread.sleep(2000);
				driver.findElement(continueB).click();
				
				String symptoms= "Booking an Appointment "+doctorName +"on date::"+dateOfAppointment+ "for symptom fever";
				Thread.sleep(2000);
				driver.findElement(sForm).clear();
				driver.findElement(sForm).sendKeys(symptoms);
				driver.findElement(sySumbit).click();
				
				hMap.put("dateOfAppointment", dateOfAppointment);
				System.out.println(dateOfAppointment);
				return hMap;
			}
			
	}
