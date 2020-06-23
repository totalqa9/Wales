
package org.iit.mmp.patientmodule.tests;

import java.io.IOException;

import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.SendMessagePage;
import org.iit.mmp.utility.Utility;
import org.testng.IHookable;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import jxl.read.biff.BiffException;

public class SendMessagesTests extends TestBase implements IHookable{

	HelperClass helperObj;
	SendMessagePage SMPage;
	
	String filePath = System.getProperty("user.dir")+"\\Data\\loginTestData.xls";
	String URL;
	String urlAdmin;
	String name;
	String subject = "Symptoms";
	String message = "Please verify the symptoms";
	String actualMsg;
	String expectedMsg = "Messages Successfully sent.";
	String adminUName;
	String adminPassword;
	
	
	@Test (dataProvider = "testData", description="US_009 SendMessageTests",groups={"US_009","regression","sanity","patientmodule"})
	public void sendMessage(String uName, String password) throws Exception{
		
		instantiateDriver();
		helperObj = new HelperClass(driver);
		URL = pro.getProperty("URL");
		urlAdmin = pro.getProperty("urlAdmin");
		adminUName = pro.getProperty("adminUser");
		adminPassword = pro.getProperty("adminPassword");
		helperObj.launchApplicationURL(URL);
		helperObj.login(uName, password);
		SMPage = new SendMessagePage(driver);
		name = SMPage.retrieveFirstName();
		helperObj.switchToSideBar();
		helperObj.moduleNavigation("Messages");
		
		SMPage.sendMessage(subject, message);
		actualMsg = SMPage.validateSendMessage();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualMsg, expectedMsg);
		helperObj.moduleNavigation("Logout");
		boolean result = SMPage.validateMessageFromAdminModule(adminUName, adminPassword, urlAdmin, name, subject, message);
		sa.assertTrue(result);
		sa.assertAll();
		helperObj.closeDriver();
	}
	
	@DataProvider (name="testData")
	public String [][] loginData() throws BiffException, IOException{
		
		String [][] loginData = Utility.readXls(filePath);
		return loginData;
		
	}
	
	
}
