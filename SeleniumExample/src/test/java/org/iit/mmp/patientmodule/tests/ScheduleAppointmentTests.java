package org.iit.mmp.patientmodule.tests;

import java.io.IOException;
import java.util.HashMap;

import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.iit.mmp.utility.Utility;
import org.testng.IHookable;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



public class ScheduleAppointmentTests  extends TestBase implements IHookable{
	
	HelperClass helperObj;
	ScheduleAppointmentPage SAPage;
	
	String URL;
	String filePath = System.getProperty("user.dir")+"\\Data\\loginTestData.xls";
	String doctorName = "Dr.Charlie";
	
	@Test(dataProvider = "testData", description="US_004 Schedule Appointment",groups={"US_004","regression","sanity","patientmodule"})
	public void validateAppointmentDetails(String uName, String password) throws Exception, IOException {
		
		instantiateDriver();
		helperObj = new HelperClass(driver);
		URL = pro.getProperty("URL");
		helperObj.launchApplicationURL(URL);
		helperObj.login(uName, password);
		helperObj.moduleNavigation("Schedule Appointment");
		SAPage = new ScheduleAppointmentPage(driver);
		SAPage.clickOnCreateAppointmentButton();
		Thread.sleep(3000);
		HashMap<String,String> hMap = SAPage.selectDoctor(doctorName);
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(SAPage.validateAppointmentDetailsinHomePage(hMap));
		sa.assertTrue(SAPage.validateAppointmentDetailsinSchedulePage(hMap));
		sa.assertAll();
		helperObj.moduleNavigation("Logout");
		helperObj.closeDriver();

	}

	@DataProvider (name="testData")
	public String [][] logiData() throws Exception, IOException{

		String [][] loginData = Utility.readXls(filePath);
		return loginData;
	}

	

}
