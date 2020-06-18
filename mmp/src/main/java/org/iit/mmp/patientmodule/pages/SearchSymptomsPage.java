package org.iit.mmp.patientmodule.pages;



	import java.util.List;

import org.iit.mmp.helper.HelperClass;
import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;

	public class SearchSymptomsPage {
		
		//Identify all the webelements using locators
		WebDriver driver;
		By searchlink=By.xpath("//span[contains(text(),'Search Symptoms')]");
		By panelTitle=By.xpath("//h3[@class='panel-title']");
		By searchbox=By.id("search");
		By rowData=By.xpath("//table[@class='table']//tbody//tr/td");
		By searchButton=By.xpath("//input[@name='submit']");
		
		
      /**
       * parameterized constructor for passing the driver object
       */
	  public SearchSymptomsPage(WebDriver driver)
		{
		this.driver=driver;
	    }

		/**
		 *Search symptoms page loading
		 * @param driver
		 */
		public void searchSymptoms(WebDriver driver)
		{
			HelperClass helpclassObj=new HelperClass(driver);
			helpclassObj.moduleNavigation("Search Symptoms");
		//	driver.findElement(searchlink).click();
			String title=driver.getTitle();
			System.out.println("Title of the page is:" +title);
			if (title.contains("search Symptoms"))
			{
				System.out.println("############ Search Symptoms page loaded successfully #############");
			}
			else 
				System.out.println("############ Page not loaded properly #############");
			
		}
		
		
		/**
		 * Reading the symptoms data from Excel file and validating it
		 * @param DP_Symptoms
		 * @throws InterruptedException
		 */
	public void validateSymptoms(String DP_Symptoms) throws InterruptedException {
		driver.findElement(searchbox).clear();
		driver.findElement(searchbox).sendKeys(DP_Symptoms);
		driver.findElement(searchButton).click();
		Thread.sleep(2000);
        List<WebElement> sympRow = driver.findElements(rowData);
		System.out.println("#### Row size is:######" + sympRow.size());


		if (sympRow.size() == 3) {

			System.out.println("Diagnosis data is displayed for your " +DP_Symptoms+ " symptom.");
		} else {
			System.out.println("No diagnosis information available for your " +DP_Symptoms+ " symptom.");
		}

	}
}

