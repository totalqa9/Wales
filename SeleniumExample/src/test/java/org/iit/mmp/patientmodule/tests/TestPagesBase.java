package org.iit.mmp.patientmodule.tests;

import org.iit.mmp.adminmodule.pages.MessagesAdminModulePage;
import org.iit.mmp.base.TestBase;
import org.iit.mmp.patientmodule.pages.LoginPatientPage;
import org.iit.mmp.patientmodule.pages.RegistrationPatientPage;
import org.iit.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.iit.mmp.patientmodule.pages.SendMessagePage;
import org.iit.mmp.patientmodule.pages.UpdateProfilePage;
import org.iit.mmp.patientmodule.pages.ViewInformationPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestPagesBase extends TestBase{
	
	LoginPatientPage loginPage;
	RegistrationPatientPage RPPage;
	ScheduleAppointmentPage SAPage;
	ViewInformationPage VIPage;
	UpdateProfilePage UPPage;
	SendMessagePage SMPage;
	protected MessagesAdminModulePage MAMPage;
	
	
	@BeforeMethod
	public void preTest() throws Exception{
		
		instantiateDriver();
				
	}
	
	@AfterMethod
	public void postTest(){
		
		//driver.close();
	}
	
	

}
