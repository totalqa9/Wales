package org.iit.mmp.base;


import java.util.Properties;
//import java.util.concurrent.TimeUnit;

import org.iit.mmp.config.ProjectConfiguration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.IHookCallBack;
import org.testng.ITestResult;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;

public class TestBase {
	
	protected  WebDriver driver;
	protected  Properties pro;
	
		
	public void instantiateDriver() throws Exception{

		System.out.println("First line of the instantiate method");
		ProjectConfiguration pConf = new ProjectConfiguration();
		pro = pConf.loadProperites();
		String browser = pro.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("firefox")){	
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}
		driver.manage().window().maximize();
		System.out.println("LastLine of the instatiate method of TestBase");
	}
	/**
	 * screenShot method is invoked whenever the Testcase is Failed.
	 * @param name
	 * @param driver
	 * @return
	 */
	@Attachment(value = "Screenshot of {0}", type = "image/png")
	public byte[] saveScreenshot(String name, WebDriver driver) {
		return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
 
	public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
		iHookCallBack.runTestMethod(iTestResult);
		if (iTestResult.getThrowable() != null) {
			this.saveScreenshot(iTestResult.getName(), driver);
		}
	}
	

}
