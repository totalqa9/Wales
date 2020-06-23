
package org.iit.mmp.patientmodule.tests;

import java.io.IOException;

import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.LoginPatientPage;
import org.iit.mmp.utility.Utility;
import org.testng.IHookable;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;

public class LoginPatientTests extends TestBase implements IHookable{
	
	HelperClass helperObj;
	LoginPatientPage loginPage;
	
	String URL;
	String filePath = System.getProperty("user.dir")+"\\Data\\loginTestData.xls";
	String moduleName = "Logout";
	
	
	@Test (dataProvider = "testData", description="US_001 Login to the app as a Patient",groups={"US_001","regression","sanity","patientmodule"})
	public void Login(String uName, String password) throws Exception{
		
		instantiateDriver();
		helperObj = new HelperClass(driver);
		URL = pro.getProperty("NAMTGURL");
		helperObj.launchApplicationURL(URL);
		loginPage = new LoginPatientPage(driver);
		loginPage.getPatientLoginpage();
		helperObj.login(uName, password);
		helperObj.moduleNavigation(moduleName);
		helperObj.closeDriver();
				
	}
	
	@DataProvider (name="testData")
	public String[][] loginData() throws IOException, BiffException{
		
		String [][] loginData = Utility.readXls(filePath);
		return loginData;
		
	}
	
}
