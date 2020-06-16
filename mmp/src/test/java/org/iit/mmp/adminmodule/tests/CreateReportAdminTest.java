package org.iit.mmp.adminmodule.tests;
import org.iit.mmp.adminmodule.pages.CreateReportAdminPage;
import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
public class CreateReportAdminTest extends TestBase {
	HelperClass helperObj;
	@Test(description="US_006 Creation of Report",groups={"US_006","regression","sanity","adminmodule"})
	public void createReport()   {
		
		String reptName = "XRAY-Stomach report-2";
		String reptDesc = "This is the 2nd XRAY of Stomach ulcer";
		String uploadFilepath = System.getProperty("user.dir")+ "\\Data\\lung-article-703x441.jpg" ;
		helperObj = new HelperClass(driver);
		helperObj.launchApplicationURL("http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/login.php");
		helperObj.login("Thomas_444","Edison_444");
		helperObj.moduleNavigation("Patients");
		CreateReportAdminPage createRepo = new CreateReportAdminPage(driver);
		createRepo.patientSearchName("Anya");
		createRepo.searchRecord("Anya","210911222");
		createRepo.patientDetails("210911222");
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(createRepo.reportDetails(reptName,reptDesc,uploadFilepath), null);
		sa.assertAll();
		helperObj.closeDriver();
	}
}
