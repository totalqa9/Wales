package org.iit.mmp.patientmodule.tests;
import java.io.IOException;

import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ViewInformationPage;
import org.iit.mmp.utility.Utility;
import org.testng.Assert;
import org.testng.IHookable;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import jxl.read.biff.BiffException;


public class ViewInformationTests extends TestBase implements IHookable{
	
	HelperClass helperObj;
	ViewInformationPage VIPage;

	String filePath = System.getProperty("user.dir")+"\\Data\\loginTestData.xls";

	String URL;
	String NavTab = "Information";
	
	@Test(dataProvider = "testData", description="US_005 View the information",groups={"US_005","regression","sanity","patientmodule"})
	public void validateInformationMsg(String uName, String password) throws Exception
	{
		instantiateDriver();
		helperObj = new HelperClass(driver);
		URL = pro.getProperty("URL");
		helperObj.launchApplicationURL(URL);
		helperObj.login(uName, password);
		helperObj.moduleNavigation(NavTab);
		VIPage = new ViewInformationPage(driver);
		boolean result = VIPage.validateInformation();
		Assert.assertTrue(result);
		helperObj.moduleNavigation("Logout");
		helperObj.closeDriver();
	}

	@DataProvider (name="testData")
	public String [][] loginData() throws BiffException, IOException{

		
		String [][] loginData = Utility.readXls(filePath);
		return loginData;
	}
	
}
