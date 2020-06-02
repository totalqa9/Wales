package org.iit.mmp.patientmodule.tests;

import java.io.IOException;

import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.LoginPatientPage;
import org.iit.mmp.utility.Utility;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import jxl.read.biff.BiffException;

public class LoginPatientTests extends TestBase{
	
	HelperClass helperObj;
	LoginPatientPage loginPage;
	
	String URL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/";
	String filePath = "C:\\Users\\Nithyakalyani\\iitMMPGit\\SeleniumExample\\mmpData\\loginTestData.xls";
	String moduleName = "Logout";
	
	
	@Test (dataProvider = "testData", description="US_001 Login to the app as a Patient",groups={"US_001","regression","sanity","patientmodule"})
	public void Login(String uName, String password) throws Exception{
		
		instantiateDriver();
		helperObj = new HelperClass(driver);
		helperObj.launchApplicationURL(URL);
		loginPage = new LoginPatientPage(driver);
		loginPage.getPatientLoginpage();
		helperObj.login(uName, password);
		helperObj.moduleNavigation(moduleName);
				
	}
	
	@DataProvider (name="testData")
	public String[][] loginData() throws IOException, Exception{
		
		String [][] loginData = Utility.readXls(filePath);
		return loginData;
		
	}
	
}
