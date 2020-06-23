package org.iit.mmp.patientmodule.tests;
import java.util.HashMap;

import org.iit.mmp.adminmodule.pages.CreateFeesAdminPage;
import org.iit.mmp.adminmodule.pages.ScheduleAppointmentAdminPage;
import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.PayFeesPage;
import org.iit.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.iit.mmp.utility.Utility;
import org.testng.IHookable;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PayFeesTests extends TestBase implements IHookable{
	
	Utility util;
	HelperClass helperObj;
	PayFeesPage objPFPP;
	CreateFeesAdminPage objCFAP;
	ScheduleAppointmentAdminPage objSAAP;
	ScheduleAppointmentPage SAP;
	HashMap <String, String> hMap;
	SoftAssert sa;
	
	String urlAdmin, adminUser, adminPassword, patientName, SSN, doctorName, URL, patientUser, patientPassword;
	String date, serviceName;
	
	String resultExpected = "Fee Successfully Entered.";
	/**
	 * Updated by Nithya - 
	 * Converted all the methods with parameters to pass the userName, password and the URL
	 * Added a condition statement to click correct patient by passing the SSN which is stored in property file
	 * Added the validation statements, to validate appointment scheduled is correctly displayed in the patient
	 * home page.
	 * 
	 * @throws Exception
	 */
	
	@Test(description="US_006 Pay Fees Page", groups= {"US-006","regression","sanity","patientmodule"})
	public void validatePayFeesTests() throws Exception
	{
		//login to admin portal
		instantiateDriver();
		helperObj=new HelperClass(driver);
		SAP = new ScheduleAppointmentPage(driver);
		sa = new SoftAssert();
		urlAdmin = pro.getProperty("urlAdmin");
		adminUser = pro.getProperty("adminUser");
		adminPassword = pro.getProperty("adminPassword");
		patientName = pro.getProperty("patientName");
		doctorName = pro.getProperty("doctorName");
		SSN = pro.getProperty("SSN");
		date = pro.getProperty("date");
		serviceName = pro.getProperty("serviceName");
		URL = pro.getProperty("URL");
		patientUser = pro.getProperty("patientUser");
		patientPassword = pro.getProperty("patientPassword");
		
        helperObj.launchApplicationURL(urlAdmin);
        helperObj.adminLogin(adminUser, adminPassword);
        
        /*helperObj.moduleNavigation("Patients");
        helperObj.searchPatient(patientName, SSN);
        
        //schedule appointment
        Thread.sleep(3000);
        helperObj.navigateToPatientServices("Create Visit");
        objSAAP=new ScheduleAppointmentAdminPage(driver);
        hMap = objSAAP.scheduleAppointment(doctorName);
        boolean actual = SAP.validateAppointmentDetailsinHomePage(hMap);
        sa.assertTrue(actual);*/
 		
       //create fee
        helperObj.moduleNavigation("Patients");
        helperObj.searchPatient(patientName, SSN);
        Thread.sleep(3000);
        helperObj.navigateToPatientServices("Create Fee");
        objCFAP= new CreateFeesAdminPage(driver);
        String fee = objCFAP.createFees(date,serviceName);
        System.out.println(fee);
        String resultActual = objCFAP.readSuccessMessage();
        sa.assertEquals(resultActual, resultExpected);
        
		//Login to patient portal and pay fee
        helperObj.launchApplicationURL(URL);
        helperObj.login(patientUser, patientPassword);
        helperObj.moduleNavigation("Fees");
        Thread.sleep(3000);
        objPFPP=new PayFeesPage(driver);
        //objPFPP.validateCreateFee();
        System.out.println("fee is: "+fee);
        objPFPP.payFee(fee);
        helperObj.moduleNavigation("Logout");
        helperObj.closeDriver();
	}

}
