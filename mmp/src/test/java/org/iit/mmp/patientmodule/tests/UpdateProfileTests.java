package org.iit.mmp.patientmodule.tests;


import java.io.IOException;

import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.UpdateProfilePage;
import org.iit.mmp.utility.Utility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class UpdateProfileTests extends TestBase{

	HelperClass helperObj;
	UpdateProfilePage UPPage;
	String URL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php";
	String filePath = System.getProperty("user.dir")+"\\data\\loginTestData.xls";
	String loginDataFilePathXLSX = System.getProperty("user.dir")+"\\data\\testData.xlsx";
	String actual = "Your Profile has been updated.";
	String expected = "";

	@Test (dataProvider="testData", description="US_005 UpdateProfile", groups={"US_005","regression","sanity","patientmodule"})
	public void UpdatePatientPofile(String uName, String password) throws Exception {

		instantiateDriver();
		helperObj = new HelperClass(driver);
		helperObj.launchApplicationURL(URL);
		UPPage = new UpdateProfilePage(driver);
		UPPage.loginTogetHomePage(uName, password);
		UPPage.clickOnNavigationTab("Profile"); //to getProfile
		UPPage.clickEditButton();
		UPPage.editAllFields();
		expected = UPPage.clickOnSaveButton();
		Assert.assertEquals(actual, expected);
		UPPage.validateUpdating();
		UPPage.validateAfterLogout(uName, password);
		UPPage.clickEditButton();
		UPPage.editRandomFields();
		UPPage.validateUpdatingRandomFields();
		UPPage.clickOnNavigationTab("Logout");
	}
	
	@DataProvider(name="testData")
	public String[][] loginTestData() throws Exception, IOException{
		
		String[][]  loginData = Utility.readXlsx(loginDataFilePathXLSX);
		return loginData;
		
	}
	
}
