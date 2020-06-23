package org.iit.mmp.patientmodule.tests;

import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ViewReportsPatientPage;
import org.testng.IHookable;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ViewReportPatientTest extends TestBase implements IHookable {
	
	HelperClass helperObj;
	String patientUserName, patientPassword, URL;
		
	@Test(description="US_007 Creation of Report",groups={"US_007","regression","sanity","patientmodule"})
	public void patientView() throws Exception {
		
		instantiateDriver();
     	helperObj = new HelperClass(driver);
     	URL = pro.getProperty("URL");
     	patientUserName = pro.getProperty("patientUser2");
     	patientPassword = pro.getProperty("patientPassword2");
		helperObj.launchApplicationURL(URL);
		helperObj.captureScreenshot("US_007_LaunchApplication");
		helperObj.login(patientUserName, patientPassword);
		helperObj.captureScreenshot("US_007_Login");
		helperObj.moduleNavigation("Profile");
		helperObj.captureScreenshot("US_007_Profile");
		ViewReportsPatientPage viewRep = new ViewReportsPatientPage(driver);
		boolean res = viewRep.viewReports();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(res,"Passed");
		helperObj.closeDriver();

	}


}
