package org.iit.mmp.patientmodule.tests;
import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ViewReportsPatientPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
public class ViewReportPatientTest extends TestBase{

	HelperClass helperObj;
	@Test(description="US_006 Creation of Report",groups={"US_006","regression","sanity","patientmodule"})
	public void patientView() throws Exception {
     	helperObj = new HelperClass(driver);
		helperObj.launchApplicationURL("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		helperObj.captureScreenshot("US_006_LaunchApplication");
		helperObj.login("anya", "Anyam@15");
		helperObj.captureScreenshot("US_006_Login");
		helperObj.moduleNavigation("Profile");
		helperObj.captureScreenshot("US_006_Profile");
		ViewReportsPatientPage viewRep = new ViewReportsPatientPage(driver);
		boolean res = viewRep.viewReports();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(res,"Passed");
		helperObj.closeDriver();

	}

	
	


}
