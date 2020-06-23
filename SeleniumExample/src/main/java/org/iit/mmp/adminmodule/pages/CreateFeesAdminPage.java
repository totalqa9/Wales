package org.iit.mmp.adminmodule.pages;

import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateFeesAdminPage {
	WebDriver driver;


	By appointment=By.id("app_date");
	By service=By.id("service");
	By submitB=By.xpath("//input[@value='submit']");


	public CreateFeesAdminPage(WebDriver driver) 
	{
		this.driver=driver;
	}
	public String createFees (String dateOfAppointment, String serviceName) throws IOException
	{ 
		System.out.println(driver.getCurrentUrl());
		Select appt=new Select(driver.findElement(appointment));
		appt.selectByVisibleText(dateOfAppointment);
		Select sev=new Select(driver.findElement(service));
		sev.selectByVisibleText(serviceName);
		String fee ="";
		for(int i=0; i<5;i++){
			try{
				//driver.navigate().refresh(); will clear all the above values and by default fee is null
				fee = driver.findElement(By.xpath("//div[@id='show']/input")).getAttribute("value");
				System.out.println("Fee value fetched from the app is: "+driver.findElement(By.xpath("//div[@id='show']/input")).getAttribute("value"));
				break;
			}
			catch(Exception e){
				System.out.println("Trying to avoid the staleElementException "+e.getMessage());
			}
		}
		driver.findElement(submitB).click();
		return fee;
	}

	public String readSuccessMessage()
	{
		String text = "";
		try{
			Alert aler=driver.switchTo().alert();
			text=aler.getText();
			System.out.println("Success message is: "+text);
			aler.accept();
		}
		catch(Exception e){
			System.out.println("No Alert is present "+e.getMessage());
		}
		return text;
	}

}
