package org.iit.mmp.patientmodule.tests;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import org.iit.mmp.adminmodule.pages.CreateReportAdminPage;
import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.*;

public class ValidateReportsEndToEndTest extends TestBase {
	
	HelperClass helperObj;
	
	@Test(description="US_006 Creation of Report",groups={"US_006","regression","End to End"})
	
	public void ValidateReport() throws Exception{
		
		String reptName = "XRAY-Stomach report";
		String reptDesc = "This is XRAY of Stomach ulcer";
		String uploadFilepath = System.getProperty("user.dir")+ "\\Data\\lung-article-703x441.jpg" ;

		
		helperObj = new HelperClass(driver);
		helperObj.launchApplicationURL("http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/login.php");
		helperObj.login("Thomas_444","Edison_444");
		helperObj.moduleNavigation("Patients");
		CreateReportAdminPage createRepo = new CreateReportAdminPage(driver);
		createRepo.patientSearchName("Ria");
		createRepo.searchRecord("Ria","462379048");
		createRepo.patientDetails("462379048");
		HashMap<String, String> hMap = createRepo.reportDetail(reptName,reptDesc,uploadFilepath);
				
		//Log in to Patient portal to validate the data entered from Admin portal
		
		helperObj.launchApplicationURL("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		helperObj.captureScreenshot("US_006_LaunchApplication");
		helperObj.login("ria1","Ria12345");
		helperObj.captureScreenshot("US_006_Login");
		helperObj.moduleNavigation("Profile");
		helperObj.captureScreenshot("US_006_Profile");
		ViewReportsPatientPage viewRep = new ViewReportsPatientPage(driver);
		boolean result = viewRep.validatePatientReports(hMap);
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(result,"Passed");
		helperObj.closeDriver();
		
		
		
	}
	
	

}
