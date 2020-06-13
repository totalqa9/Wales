package org.iit.mmp.helper;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
		System.out.println("Inside Capturing Screenshot method");
		TakesScreenshot tsh = (TakesScreenshot)driver;
		File sourceFile = tsh.getScreenshotAs(OutputType.FILE);
		System.out.println(sourceFile.getAbsolutePath());
		String destinationPath = System.getProperty("user.dir")+"\\screenshots\\"+tc_Name+"_"+
											Calendar.getInstance().getTimeInMillis()%1000000000+".jpg";
		File destFile = new File(destinationPath); 
		FileUtils.copyFile(sourceFile,destFile);
		System.out.println(destinationPath);
		System.out.println("Exiting Screenshot");
		
	}
	public WebDriver switchToAFrameAvailable(String frameId,int timeinSecs)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeinSecs);
		driver = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
		return driver;
	}
	public void switchToSideBar(){
		driver.findElement(By.xpath("//div[@class='left-sidebar']")).click();
	}
	public void highLightElement(WebElement ele){
		
		JavascriptExecutor js =(JavascriptExecutor)driver;

		js.executeScript("arguments[0].setAttribute('style', 'background:yellow; border:2px solid red;')", ele);
		
		}


}
