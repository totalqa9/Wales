package org.iit.mmp.patientmodule.tests;

import java.io.IOException;
import java.util.HashMap;

import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.iit.mmp.utility.Utility;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



public class ScheduleAppointmentTests  extends TestBase{
	
	HelperClass helperObj;
	ScheduleAppointmentPage SAPage;
	
	String URL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php";
	String filePath = System.getProperty("user.dir")+"\\data\\loginTestData.xls";
	String doctorName = "Dr.Charlie";
	
	@Test(dataProvider = "testData", description="US_004 Schedule Appointment",groups={"US_004","regression","sanity","patientmodule"})
	public void validateAppointmentDetails(String uName, String password) throws Exception, IOException {
		
		instantiateDriver();
		helperObj = new HelperClass(driver);
		helperObj.launchApplicationURL(URL);
		helperObj.login(uName, password);
		helperObj.moduleNavigation("Schedule Appointment");
		SAPage = new ScheduleAppointmentPage(driver);
		SAPage.clickOnCreateAppointmentButton();
		HashMap<String,String> hMap = SAPage.selectDoctor(doctorName);
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(SAPage.validateAppointmentDetailsinHomePage(hMap));
		sa.assertTrue(SAPage.validateAppointmentDetailsinSchedulePage(hMap));
		sa.assertAll();

	}

	@DataProvider (name="testData")
	public String [][] logiData() throws Exception, IOException{

		String [][] loginData = Utility.readXls(filePath);
		return loginData;
	}

	

}
