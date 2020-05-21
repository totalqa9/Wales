package org.iit.mmp.patientmodule.tests;

import java.io.IOException;
import java.util.HashMap;
import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
public class ScheduleAppointmentTests extends TestBase{
	 
	HelperClass helperObj;
	@Test(description="US_005 Schedule Appointment",groups={"US_005","regression","sanity","patientmodule"})
	public void validateAppointmentDetails() throws InterruptedException, IOException
	{
	 
		helperObj = new HelperClass(driver);
		helperObj.launchApplicationURL("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		helperObj.captureScreenshot("US_005_LaunchApplication");
		helperObj.login("ria1","Ria12345");
		helperObj.captureScreenshot("US_005_Login");
		helperObj.moduleNavigation("Schedule Appointment");
		helperObj.captureScreenshot("US_005_ScheduleAppointment");
		ScheduleAppointmentPage sPage = new ScheduleAppointmentPage(driver);
		sPage.clickOnCreateAppointmentButton();
		helperObj.captureScreenshot("US_005_CreateAppointment");
		HashMap<String,String> hMap = sPage.selectDoctor("Dr.Charlie");
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(sPage.validateAppointmentDetailsinHomePage(hMap));
		helperObj.captureScreenshot("US_005_validateAppointmentHomePage");
		sa.assertTrue(sPage.validateAppointmentDetailsinSchedulePage(hMap));
		helperObj.captureScreenshot("US_005_validateAppointmentSchedulePage");
		sa.assertAll();
	}
}