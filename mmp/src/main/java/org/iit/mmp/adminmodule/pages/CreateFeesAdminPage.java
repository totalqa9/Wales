package org.iit.mmp.adminmodule.pages;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateFeesAdminPage{
	WebDriver driver;
	
	
	By appointment=By.id("app_date");
	By service=By.id("service");
	By submitB=By.xpath("//input[@value='submit']");
	
	
	public CreateFeesAdminPage(WebDriver driver) 
	{
		this.driver=driver;
		
		
	}
	 
	
	public HashMap<String,String> createFees (String dateOfAppointment, String serviceName) throws IOException
	{ 
	
		HashMap<String,String> hMap= new HashMap<String,String>();
		Select appt=new Select(driver.findElement(appointment));
	    appt.selectByVisibleText(dateOfAppointment);
	    Select sev=new Select(driver.findElement(service));
	    sev.selectByVisibleText(serviceName);
	    driver.findElement(submitB).click();
	    
	    hMap.put("serviceName", serviceName);
	    System.out.println(serviceName);
		return hMap;
	}
	
	public String readSuccessMessage()
	{
		Alert aler=driver.switchTo().alert();
		String text=aler.getText();
		aler.accept();
		return text;
	}

}
