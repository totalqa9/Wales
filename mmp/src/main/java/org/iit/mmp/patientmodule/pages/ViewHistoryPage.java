package org.iit.mmp.patientmodule.pages;

import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ViewHistoryPage {

	WebDriver driver;
	HelperClass helperObj ;
	
	By button = By.xpath("//button[contains(text(),'View History')]");

	
	public ViewHistoryPage(WebDriver driver)
	{
		this.driver = driver;
		helperObj = new HelperClass(driver);
	}
	
	
	public void viewHistoryButton() 
	{
		driver.findElement(button).click();
	}
	
	
	public void panelHistory() {
		WebElement panel = driver.findElement(By.id("wrapper"));
		System.out.println(panel.findElements(By.tagName("a")).size());

		for (int i=1;i<panel.findElements(By.tagName("a")).size();i++)
		{

			String clickonLinkTab = Keys.chord(Keys.CONTROL,Keys.ENTER);
			panel.findElements(By.tagName("a")).get(i).sendKeys(clickonLinkTab);      
		}

	}
}

