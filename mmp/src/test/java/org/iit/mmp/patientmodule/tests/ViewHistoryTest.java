package org.iit.mmp.patientmodule.tests;

import java.io.IOException;

import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ViewHistoryPage;
import org.iit.mmp.utility.Utility;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ViewHistoryTest extends TestBase {
	HelperClass helperObj;
	ViewHistoryPage HisPage;

	String filePath = System.getProperty("user.dir")+"\\data\\loginTestData.xls";
	String URL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php";

	@Test(dataProvider = "testData", description="US_008 View History",groups={"US_008","regression","sanity","patientmodule"})
	public void viewHistory(String uName, String password) throws IOException 
	{
		instantiateDriver();
		helperObj = new HelperClass(driver);
		helperObj.launchApplicationURL(URL);
		helperObj.login(uName, password);
		helperObj.moduleNavigation("Profile");
		ViewHistoryPage viewHS = new ViewHistoryPage(driver);
		viewHS.viewHistoryButton();
		viewHS.panelHistory();

	}
	@DataProvider (name="testData")
	public String [][] loginData() throws Exception{
		
		String [][] loginData = Utility.readXls(filePath);
		return loginData;
	}

}


