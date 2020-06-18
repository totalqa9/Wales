package org.iit.mmp.helper;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.iit.mmp.config.ProjectConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperClass {


	int i = 0;
	WebDriver driver;

	public HelperClass(WebDriver driver){

		this.driver = driver;

	}

	public  void moduleNavigation(String moduleName)
	{
		driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]")).click();
	}

	public  void launchApplicationURL(String url)	{

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	public void login(String uName, String password){

		System.out.println("login");
		//driver.findElement(By.linkText("Patient Login")).click();
		//driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).sendKeys(uName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("submit")).click();
	}
	public void AdminLogin(String uName, String password){

		driver.findElement(By.id("username")).sendKeys(uName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("admin")).click();

	}
	public void AdminModuleNavigation(String moduleName){

		driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]")).click();

	}
	public void takeScreenShot(String fLocation){

		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		System.out.println(srcFile.getAbsolutePath());
		File destFile = new File(fLocation);
		try {
			FileHandler.copy(srcFile, destFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
	} 
    
	public void captureScreenshot(String tc_Name) throws IOException
	{

		TakesScreenshot tsh = (TakesScreenshot)driver;
		File sourceFile = tsh.getScreenshotAs(OutputType.FILE);
		System.out.println(sourceFile.getAbsolutePath());
		String destinationPath = System.getProperty("user.dir")+"\\screenshots\\"+tc_Name+"_"+
				Calendar.getInstance().getTimeInMillis()%1000000000+".jpg";
		File destFile = new File(destinationPath); 
		FileUtils.copyFile(sourceFile,destFile);

	}
	public WebDriver switchToAFrameAvailable(String frameId,int timeinSecs)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeinSecs);
		driver = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
		return driver;
	}
	public void searchDoctor(String drName)
	{
		driver.findElement(By.xpath("//h4[contains(text(),'"+drName+"')]/ancestor::td/button")).click();
	}

	//write symptoms
	public void inputSymptoms(String symptom)
	{
		driver.findElement(By.xpath("//textarea[@name='sym']")).clear();
		driver.findElement(By.xpath("//textarea[@name='sym']")).sendKeys(symptom);
		driver.findElement(By.xpath("//form[@name='symptoms']/descendant::div/input[@value='Submit']")).click();
	}

	public void searchPatient(String pName) throws InterruptedException
	{

		driver.findElement(By.id("search")).sendKeys(pName);
		driver.findElement(By.className("tfbutton")).click();
		Thread.sleep(3000);
		List<WebElement> pLis=driver.findElements(By.xpath("//div[@id='show']/descendant::table/tbody/tr/td/a"));
		for(int i=0;i<pLis.size();i++)
		{ 
			try
			{
				if(pLis.get(i).getTagName().contains(pName));

				{
					pLis.get(i).click();
					break;
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}

	}
	public void patientLogin() throws IOException 
	{
		ProjectConfiguration pConfig = new ProjectConfiguration();	
		Properties pro = pConfig.loadProperties();  
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(pro.getProperty("patientUser"));
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pro.getProperty("patientPassword"));
		driver.findElement(By.xpath("//input[@type='submit']")).click();

    }
	public void adminLogin() throws IOException 
	{
		ProjectConfiguration pConfig = new ProjectConfiguration();	
		Properties pro = pConfig.loadProperties();  
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(pro.getProperty("adminUser"));
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pro.getProperty("adminPassword"));
		driver.findElement(By.xpath("//input[@type='submit']")).click();

	}
	public void navigateToPatientServices(String serviceName)
	{
		driver.findElement(By.xpath("//input[@value='"+serviceName+"']")).click();
	}
	public void launchApplicationAdminURL() throws IOException
	{
		ProjectConfiguration pConfig = new ProjectConfiguration();	
		Properties pro = pConfig.loadProperties();
		driver.get(pro.getProperty("adminPortalUrl"));

	}

	public void launchApplicationPatientURL() throws IOException
	{
		ProjectConfiguration pConfig = new ProjectConfiguration();	
		Properties pro = pConfig.loadProperties();
		driver.get(pro.getProperty("patientPortalUrl"));
	}
    public void closeDriver() {
		
		driver.findElement(By.xpath("//span[contains(text(), 'Logout')]")).click();
		driver.close();
	}

}
