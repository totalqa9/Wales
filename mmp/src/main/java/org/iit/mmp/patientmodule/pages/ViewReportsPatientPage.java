package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.utility.Utility;
import org.openqa.selenium.WebDriver;

public class ViewReportsPatientPage {

	WebDriver driver;
	HelperClass helperObj;

	String reportsBtn = "//a/button[contains(text(),'Reports')]" ;

	public ViewReportsPatientPage(WebDriver driver) {

		this.driver = driver ;
	}


	public boolean viewReports() throws Exception{

		boolean result = false;
		
		String expectedHeader = "View Reports";
		String header ;

		driver.findElement(By.xpath(reportsBtn)).click();

		WebElement we3 = driver.findElement(By.xpath("(//h3)[2]"));
		header = we3.getText();
		System.out.println(header);
		if(expectedHeader.equalsIgnoreCase(header)) {

			String resul = "matched";
			System.out.println(resul);
		}
		else {
			System.out.println("Not matched");
		}

		//String viewRep = "(//tr/td)[2]//li";
		//driver.findElement(By.xpath(viewRep)).click();

		/*Checking the number of tds and trs*/

		List<WebElement> list = driver.findElements(By.xpath("//tbody//tr//td"));
		System.out.println(list.size());



		for(int i=1;i<=list.size();i++) {
						
					
			String viewRep = "(//tr/td)["+ i +"]//li";
			By viewrep = By.xpath(viewRep);
			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(viewrep));
			
			driver.findElement(viewrep).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[contains(@class,'mfp-container')]")).click();
			
			
			


		}

		return result;

	}

	public boolean validatePatientReports(HashMap<String,String> hMap) {

		boolean Res = false;

		String reportsBtn = "//a/button[contains(text(),'Reports')]" ;
		driver.findElement(By.xpath(reportsBtn)).click();

		List<WebElement> list = driver.findElements(By.xpath("//tbody//tr//td"));
		System.out.println(list.size());

		int i = list.size();
		//String viewRep = "(//tr/td)["+ i +"]//li";
		String reptName = "(//tbody//tr//td)["+ i +"]//li//h4" ;
		WebElement rptName = driver.findElement(By.xpath(reptName));
		String name = rptName.getText();


		String reptDesc = "(//tbody//tr//td)["+ i +"]//li//div/p" ;
		WebElement rptDsc = driver.findElement(By.xpath(reptDesc));
		String desc = rptDsc.getText();
		String descFinal[] = desc.split(":", 2);

		System.out.println(name);
		System.out.println(desc);
		if(hMap.get("reportName").equals(name) 
				&& hMap.get("reportDesc").equals(descFinal[1].trim()))
		{
			Res = true;
		}

		System.out.println(Res);
		return Res;


	}

}
