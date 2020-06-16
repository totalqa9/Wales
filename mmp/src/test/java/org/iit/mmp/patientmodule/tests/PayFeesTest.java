package org.iit.mmp.patientmodule.tests;

import java.io.IOException;

import org.iit.mmp.adminmodule.pages.CreateFeesAdminPage;
import org.iit.mmp.adminmodule.pages.ScheduleAppointmentAdminPage;
import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.PayFeesPage;
import org.iit.mmp.utility.Utility;
import org.testng.annotations.Test;



public class PayFeesTest extends TestBase {
	
	Utility util;
	HelperClass helperObj;
	PayFeesPage objPFPP;
	CreateFeesAdminPage objCFAP;
	ScheduleAppointmentAdminPage objSAAP;
	
	@Test(description="US_006 Pay Fees Page", groups= {"US-006","regression","sanity","patientmodule"})
	public void validatePayFeesTests() throws InterruptedException, IOException
	{
		//login to admin portal
		helperObj=new HelperClass(driver);
        helperObj.launchApplicationAdminURL();
        helperObj.adminLogin();
        helperObj.moduleNavigation("Patient");
        helperObj.searchPatient("Ria");
        
        //schedule appointment
        Thread.sleep(3000);
        helperObj.navigateToPatientServices("Create Visit");
        objSAAP=new ScheduleAppointmentAdminPage(driver);
        objSAAP.scheduleAppointment("Becky");
		
		//create fee
		helperObj.searchPatient("Ria");
        Thread.sleep(3000);
        helperObj.navigateToPatientServices("Create Fee");
        objCFAP= new CreateFeesAdminPage(driver);
        objCFAP.createFees("06/08/2020","Xray");
        objCFAP.readSuccessMessage();
		
		//Login to patient portal and pay fee
        helperObj.launchApplicationPatientURL();
        helperObj.patientLogin();
        helperObj.moduleNavigation("Fees");
        Thread.sleep(3000);
        objPFPP=new PayFeesPage(driver);
        objPFPP.payFee();
        helperObj.moduleNavigation("Logout");
	}
}
	
	


