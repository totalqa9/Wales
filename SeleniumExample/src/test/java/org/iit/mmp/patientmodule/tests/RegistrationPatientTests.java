package org.iit.mmp.patientmodule.tests;

import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.RegistrationPatientPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RegistrationPatientTests extends TestBase {
	
	String URL;
	String filePath = System.getProperty("user.dir")+"\\Data\\testData.xlsx";
	HelperClass helperObj;
	RegistrationPatientPage RPPage;
	
		
	@Test(description="US_001 Registration of the Page",groups={"US_001","regression","sanity","patientmodule"})
	public void validateRegistration() throws Exception
	{  
		instantiateDriver();
		helperObj = new HelperClass(driver);
		URL = pro.getProperty("URL");
		helperObj.launchApplicationURL(URL);
		RPPage = new RegistrationPatientPage(driver);
		RPPage.clickRegisterButton();
		RPPage.fillData();
		String actual = RPPage.clickOnSaveButton();
		//String actual = RPPage.readSuccessMessage();
		String expected ="Thank you for registering with MMP. ";
		Assert.assertEquals(actual, expected);
	}
	
	
	
}
