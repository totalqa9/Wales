package org.iit.mmp.patientmodule.tests;

import java.util.HashMap;

import org.iit.mmp.adminmodule.pages.CreateReportAdminPage;
import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ViewReportsPatientPage;
import org.testng.IHookable;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ViewReportsEndToEndTest extends TestBase implements IHookable{
	
	HelperClass helperObj;
	
	@Test(description="US_006 Creation of Report",groups={"US_006","regression","End to End"})
	
	public void ValidateReport() throws Exception{
		
		String reptName = "XRAY-Stomach report";
		String reptDesc = "This is XRAY of Stomach ulcer";
		String uploadFilepath = System.getProperty("user.dir")+ "\\Data\\lung-article-703x441.jpg" ;

		instantiateDriver();
		helperObj = new HelperClass(driver);
		helperObj.launchApplicationURL(pro.getProperty("urlAdmin"));
		helperObj.adminLogin(pro.getProperty("adminUser"),pro.getProperty("adminPassword"));
		helperObj.moduleNavigation("Patients");
		CreateReportAdminPage createRepo = new CreateReportAdminPage(driver);
		createRepo.patientSearchName(pro.getProperty("patientName"));
		createRepo.searchRecord(pro.getProperty("patientName"),pro.getProperty("SSN"));
		createRepo.patientDetails(pro.getProperty("SSN"));
		HashMap<String, String> hMap = createRepo.reportDetail(reptName,reptDesc,uploadFilepath);
				
		//Log in to Patient portal to validate the data entered from Admin portal
		
		helperObj.launchApplicationURL(pro.getProperty("URL"));
		helperObj.captureScreenshot("US_006_LaunchApplication");
		helperObj.login(pro.getProperty("patientUser"),pro.getProperty("patientPassword"));
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
