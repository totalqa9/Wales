package org.iit.mmp.patientmodule.tests;

import java.io.IOException;

import org.iit.mmp.base.TestBase;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.SearchSymptomsPage;
import org.iit.mmp.utility.Utility;
import org.testng.IHookable;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import jxl.read.biff.BiffException;

public class SearchSymptomsTests extends TestBase implements IHookable{
	
	HelperClass helperObj;
	SearchSymptomsPage searchpageObj;
	SoftAssert sa=new SoftAssert();
	boolean result;
	
	/**
	 * Updated by Nithya
	 * added validation statements
	 * @throws Exception
	 */
	@Test(description="US_010 Search Symptoms",groups={"PAT010","regression","sanity","patientmodule"})
	public void LoginTest() throws Exception {
		
		instantiateDriver();
		helperObj=new HelperClass(driver);
		helperObj.launchApplicationURL(pro.getProperty("URL"));
		helperObj.login(pro.getProperty("patientUser"), pro.getProperty("patientPassword"));
		searchpageObj = new SearchSymptomsPage(driver);
		searchpageObj.searchSymptoms(driver);
		helperObj.captureScreenshot("PAT010 Search Symptoms");
	}
	
	@Test(dataProvider="DP_Symptoms",description="Search Symptoms",groups= {"US_010","regression","sanity","patientmodule"} )
	public void SearchSymptoms(String DP_Symptoms) throws InterruptedException {
		
		searchpageObj=new SearchSymptomsPage(driver);
		result = searchpageObj.validateSymptoms(DP_Symptoms);
		sa.assertTrue(result);
	}
	@DataProvider(name="DP_Symptoms")
	public String[][] excelData() throws BiffException, IOException {
		
		String filepath=System.getProperty("user.dir")+"\\data\\Symptoms.xls";
		Utility excelXlsObj = new Utility();
		String[][] symptomData=excelXlsObj.readXlsFile(filepath,"Symptoms");
		return symptomData;
	}
	@AfterClass
	public void closeApp() {

		helperObj=new HelperClass(driver);
		helperObj.closeDriver();

	}

}
