package org.iit.mmp.patientmodule.tests;
import java.io.IOException;
import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.utility.Utility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class LoginPatientModule extends TestBase{

	 
	@Test(description="US_002_Login_Patient",groups={"US_002","regression","sanity","patientmodule"})
	public void initiateBrowser() throws IOException
	{
		HelperClass helpObj = new HelperClass(driver);
		helpObj.launchApplicationURL("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/");
		driver.findElement(By.xpath("//*[@id=\"navigation\"]/li[2]/a")).click();
		driver.findElement(By.xpath("//a[@class='button button-alt'][contains(text(),'Login')]")).click();
		helpObj.captureScreenshot("US_002_Login_Patient");
	}
	@Test(dataProvider="testData", description="US_002_Login_Patient",groups={"US_002","regression","sanity","patientmodule"})
	public void patientLogin(String username, String password) throws InterruptedException
	{

		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"login\"]/form/p[6]/input")).click();

		boolean result=isAlertPresent();
		if (result) {		

			String actual=alertHandler();

			String expected = "Wrong username and password.";
			SoftAssert sa = new SoftAssert();
			sa.assertEquals(actual, expected, "Login Failed");
		}
		else {
			System.out.println("Sucessful Login");
		}

	}
 	@DataProvider(name="testData")
	public  Object[][] getData() {
		String str=System.getProperty("user.dir");
		System.out.println("Directory path "+str);

		String excelPath=str + "\\data\\LoginData.xlsx";
		Object data[][]=testData(excelPath,"Sheet1");
		return data;
	}

	public  Object[][] testData(String excelPath, String sheetName)
	{
		Utility.ExcelUtils(excelPath, sheetName);
		int rowCount= Utility.getRowCount();
		int colNum= Utility.getcolCount();
		//Defining an object array
		Object data[][]=new Object[rowCount-1][colNum];

		for (int i=1; i<rowCount; i++)
		{
			for (int j=0; j<colNum; j++)
			{
				String cellData=Utility.getCellDataString(i, j);
				data[i-1][j]=cellData;
			}
		}
		return data;
	}

	@Test(description="US_002_Login_Patient",groups={"US_002","regression","sanity","patientmodule"})
	public String alertHandler() throws InterruptedException
	{

		Alert alrt  = driver.switchTo().alert();
		System.out.println("Message for alrt "+alrt); 
		String msg = alrt.getText();
		alrt.accept();
		Thread.sleep(1000);
		return msg;
	}	

	//check if alert is present or not
	public boolean isAlertPresent() 
	{ 
		try 
		{ 
			driver.switchTo().alert(); 
			return true; 
		}   // try 
		catch (NoAlertPresentException Ex) 
		{ 
			return false; 
		}   // catch 
	}   // isAlertPresent()

}
