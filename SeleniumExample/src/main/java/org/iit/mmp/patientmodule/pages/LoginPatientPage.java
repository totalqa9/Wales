package org.iit.mmp.patientmodule.pages;

import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPatientPage extends TestBase{
	
	HelperClass helperObj;
	WebDriver driver;
	
	public LoginPatientPage(WebDriver driver){
		
		this.driver = driver;
		helperObj = new HelperClass(driver);
	}
	//page Elements
	By patientLoginMainBtn = By.linkText("Patient Login");
	By patientLoginBtn = By.linkText("Login");
		
		
	public void getPatientLoginpage(){
		
		driver.findElement(patientLoginMainBtn).click();
		driver.findElement(patientLoginBtn).click();
	}
	
		
}
