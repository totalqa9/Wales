package org.iit.mmp.patientmodule.tests;

import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.RegistrationPatientPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RegistrationPatientTests extends TestBase {
	
	String URL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php";
	String filePath = "C:\\Users\\Nithyakalyani\\iitMMPGit\\SeleniumExample\\mmpData\\testData.xlsx";
	HelperClass helperObj;
	RegistrationPatientPage RPPage;
	
		
	@Test(description="US_001 Registration of the Page",groups={"US_001","regression","sanity","patientmodule"})
	public void validateRegistration() throws Exception
	{  
		instantiateDriver();
		helperObj = new HelperClass(driver);
		helperObj.launchApplicationURL(URL);
		RPPage = new RegistrationPatientPage(driver);
		RPPage.clickRegisterButton();
		RPPage.fillData();
		RPPage.clickOnSaveButton();
		String actual = RPPage.readSuccessMessage();
		String expected ="Thank you for registering with MMP.";
		Assert.assertEquals(actual, expected);
	}
	
	
	
}
