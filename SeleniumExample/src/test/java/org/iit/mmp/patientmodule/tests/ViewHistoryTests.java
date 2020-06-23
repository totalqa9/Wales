package org.iit.mmp.patientmodule.tests;



import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ViewHistoryPage;
import org.iit.mmp.utility.Utility;
import org.testng.IHookable;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ViewHistoryTests extends TestBase implements IHookable{
	
	HelperClass helperObj;
	ViewHistoryPage VHPage;

	String filePath = System.getProperty("user.dir")+"\\data\\loginTestData.xls";
	String URL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php";

	/** added logout and driver.quit - by Nithya- yet to add validations
	 * @param uName
	 * @param password
	 * @throws Exception
	 */
	@Test(dataProvider = "testData", description="US_008 View History",groups={"US_008","regression","sanity","patientmodule"})
	public void viewHistory(String uName, String password) throws Exception 
	{
		instantiateDriver();
		helperObj = new HelperClass(driver);
		helperObj.launchApplicationURL(URL);
		helperObj.login(uName, password);
		helperObj.moduleNavigation("Profile");
		VHPage = new ViewHistoryPage(driver);
		VHPage.clickOnViewHistoryButton();
		VHPage.panelHistory();
		helperObj.moduleNavigation("Logout");
		driver.quit();

	}
	@DataProvider (name="testData")
	public String [][] loginData() throws Exception{
		
		String [][] loginData = Utility.readXls(filePath);
		return loginData;
	}


}
