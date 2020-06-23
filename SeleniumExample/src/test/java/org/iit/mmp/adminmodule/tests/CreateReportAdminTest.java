package org.iit.mmp.adminmodule.tests;

import org.iit.mmp.adminmodule.pages.CreateReportAdminPage;
import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.testng.IHookable;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateReportAdminTest extends TestBase implements IHookable{
	HelperClass helperObj;
	String adminUser;
	String adminPassword;
	String urlAdmin;
	String searchPatient;
	String searchPatientSSN;
	String reptName = "XRAY-Stomach report-2";
	String reptDesc = "This is the 2nd XRAY of Stomach ulcer";
	String uploadFilepath;
	@Test(description="US_007 Creation of Report",groups={"US_007","regression","sanity","adminmodule"})
	public void createReport() throws Exception   {
		
		instantiateDriver();
		adminUser = pro.getProperty("adminUser");
		adminPassword = pro.getProperty("adminPassword");
		urlAdmin = pro.getProperty("urlAdmin");
		searchPatient = pro.getProperty("searchPatient");
		searchPatientSSN = pro.getProperty("searchPatientSSN");
		uploadFilepath = System.getProperty("user.dir")+ "\\Data\\lung-article-703x441.jpg" ;
		helperObj = new HelperClass(driver);
		helperObj.launchApplicationURL(urlAdmin);
		helperObj.adminLogin(adminUser,adminPassword);
		helperObj.moduleNavigation("Patients");
		CreateReportAdminPage createRepo = new CreateReportAdminPage(driver);
		createRepo.patientSearchName(searchPatient);
		createRepo.searchRecord(searchPatient,searchPatientSSN);
		createRepo.patientDetails(searchPatientSSN);
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(createRepo.reportDetails(reptName,reptDesc,uploadFilepath), null);
		sa.assertAll();
		helperObj.closeDriver();
	}

}
