package org.iit.mmp.patientmodule.tests;


import java.io.IOException;
import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.UpdateProfilePage;
import org.iit.mmp.utility.Utility;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class UpdateProfileTests extends TestBase{

	HelperClass helperObj;
	UpdateProfilePage UPPage;
	String URL;
	String loginDataFilePathXLS = System.getProperty("user.dir")+"\\Data\\loginTestData.xls";
	String loginDataFilePathXLSX = System.getProperty("user.dir")+"\\Data\\testData.xlsx";
	String actual = "Your Profile has been updated.";
	String expected;
	boolean resultAll;
	boolean resultSelected;
	
	SoftAssert sa;

	@Test (dataProvider="testData", description="US_005 UpdateProfile", groups={"US_005","regression","sanity","patientmodule"})
	public void UpdatePatientPofile(String uName, String password) throws Exception {

		
		instantiateDriver();
		helperObj = new HelperClass(driver);
		URL = pro.getProperty("URL");
		helperObj.launchApplicationURL(URL);
		UPPage = new UpdateProfilePage(driver);
		UPPage.loginTogetHomePage(uName, password);
		UPPage.clickOnNavigationTab("Profile"); //to getProfile
		UPPage.clickEditButton();
		UPPage.editAllFields();
		expected = UPPage.clickOnSaveButton();
		sa = new SoftAssert();
		sa.assertEquals(actual, expected);
		//Assert.assertEquals(actual, expected);
		resultAll = UPPage.validateUpdating();
		sa.assertTrue(resultAll);
		//Assert.assertTrue(resultAll);
		sa.assertTrue(UPPage.validateAfterLogout(uName, password, URL));
		//Assert.assertTrue(UPPage.validateAfterLogout(uName, password, URL));
		UPPage.clickEditButton();
		UPPage.editRandomFields();
		resultSelected = UPPage.validateUpdating();
		sa.assertTrue(resultSelected);
		sa.assertAll();
		//Assert.assertTrue(resultSelected);
		UPPage.clickOnNavigationTab("Logout");
		UPPage.closeDriver();
	}
	
	@DataProvider(name="testData")
	public String[][] loginTestData() throws Exception, IOException{
		
		String[][]  loginData = Utility.readXlsx(loginDataFilePathXLSX);
		return loginData;
		
	}
	
}
