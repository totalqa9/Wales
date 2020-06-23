package org.iit.mmp.adminmodule.pages;



import java.util.List;

import org.iit.mmp.helper.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPatientPage {
	
	WebDriver driver;
	HelperClass helperObj;
	String patientName = "Ria";
	
	By searchTB = By.id("search");
	By searchBtn = By.xpath("//input[@value='search']");
	By columns = By.xpath("//*[@id="+"show"+"]/table/thead/tr/th[1]");
	By rows = By.xpath("");
	
	public SearchPatientPage(WebDriver driver){
		this.driver = driver;
		helperObj = new HelperClass(driver);
		
	}
	
	public void searchPatient(String patientName){
		
		List<WebElement> columnElements = driver.findElements(By.xpath("//*[@id="+"show"+"]/table/thead/tr/th"));
		System.out.println(columnElements.size());
		//driver.findElement(By.xpath(""));
		
	}

}
