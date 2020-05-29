package org.iit.mmp.adminmodule.pages;

import java.util.HashMap;

import org.iit.mmp.helper.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MessagesAdminModulePage {
	
	WebDriver driver;
	HelperClass helperObj;
		
	String name, subject, description;
	HashMap <String, String> hMap;
	By patientName = By.xpath("//table[@class='table']//tr[2]/td[1]");
	By subjectMessage = By.xpath("//table[@class='table']//tr[2]/td[2]");
	By descriptionMessage = By.xpath("//table[@class='table']//tr[3]/td[2]");
	
	public MessagesAdminModulePage(WebDriver driver){
		this.driver = driver;
		helperObj = new HelperClass(driver);
		hMap = new HashMap <String, String>();
		
	}
	
	public HashMap <String, String> retrieveRecentMessageDetails(){

		name = driver.findElement(patientName).getText();
		subject = driver.findElement(subjectMessage).getText();
		description = driver.findElement(descriptionMessage).getText();
		hMap.put("Name", name);
		hMap.put("Subject", subject);
		hMap.put("Description", description);
		System.out.println(driver.findElement(By.xpath("//table[@class='table']//tr[2]/td[2]")).getText());
		System.out.println(driver.findElement(By.xpath("//table[@class='table']//tr[3]/td[2]")).getText());
		return hMap;

	}
	public boolean validateMessageFromAdminModule(HashMap<String, String> hMap, String name, String subject, String description){

		boolean result = false;
		if(hMap.get("Subject").equals(subject) && hMap.get("Description").equals(description) &&hMap.get("Name").equals(name)){

			System.out.println("Passed");
			result = true;

		}
		return result;
	}


}
