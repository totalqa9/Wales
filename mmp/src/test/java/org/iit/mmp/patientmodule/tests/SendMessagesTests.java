package org.iit.mmp.patientmodule.tests;

import java.io.IOException;

import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.SendMessagePage;
import org.iit.mmp.utility.Utility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import jxl.read.biff.BiffException;

public class SendMessagesTests extends TestBase{

	HelperClass helperObj;
	SendMessagePage SMPage;
	
	String filePath = System.getProperty("user.dir")+"\\data\\loginTestData.xls";
	String URL ="http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php";
	String urlAdminLogin = "http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/login.php";
	String name;
	String subject = "Symptoms";
	String message = "Please verify the symptoms";
	String actualMsg;
	String expectedMsg = "Messages Successfully sent.";
	String adminUName = "shak";
	String adminPassword = "9ol.<KI*";
	
	
	@Test (dataProvider = "testData", description="US_009 SendMessageTests",groups={"US_009","regression","sanity","patientmodule"})
	public void sendMessage(String uName, String password) throws Exception{
		
		instantiateDriver();
		helperObj = new HelperClass(driver);
		helperObj.launchApplicationURL(URL);
		helperObj.login(uName, password);
		SMPage = new SendMessagePage(driver);
		name = SMPage.retrieveFirstName();
		helperObj.moduleNavigation("Messages");
		
		SMPage.sendMessage(subject, message);
		actualMsg = SMPage.validateSendMessage();
		Assert.assertEquals(actualMsg, expectedMsg);
		helperObj.moduleNavigation("Logout");
		Assert.assertTrue(SMPage.validateMessageFromAdminModule(adminUName, adminPassword, urlAdminLogin, name, subject, message));
		
	}
	
	@DataProvider (name="testData")
	public String [][] loginData() throws Exception, IOException{
		
		String [][] loginData = Utility.readXls(filePath);
		return loginData;
		
	}
	
	
}
