package org.iit.mmp.patientmodule.tests;


import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.utility.Utility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class UpdateProfileTests {

	WebDriver driver;
	WebElement we;
	HashMap <String, String> hMap;
	HelperClass helperObj;
	//HashMap <String, String> primaryHMap;
	Random rand;
	String URL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php";
	//String uName = "jasmine";
	//String pword = "9ol.<KI*";
	String actual = "Your Profile has been updated.";
	String expected = "";
	int fName, lName, license, ssn, address, age, weight, height, city, state, zipCode; 
	String loginDataFilePathXLS = "C:\\workspace\\SeleniumExample\\mmpData\\loginTestData.xls";
	String loginDataFilePathXLSX = "C:\\workspace\\SeleniumExample\\mmpData\\testData.xlsx";

	@Test (dataProvider="testData", description="US_005 UpdateProfile", groups={"US_005","regression","sanity","patientmodule"})
	public void UpdatePatientPofile(String uName, String password) throws Exception {

		rand = new Random();
		hMap = new HashMap<String, String>();
		//primaryHMap = new HashMap<String, String>();
		launchApplication(); 
		loginTogetHomePage(uName, password);
		clickOnNavigationTab("Profile"); //to getProfile
		clickEditButton();
		editAllFields();
		expected = clickOnSaveButton();
		Assert.assertEquals(actual, expected);
		validateUpdating();
		validateAfterLogout(uName, password);
		clickEditButton();
		editRandomFields();
		validateUpdatingRandomFields();
		clickOnNavigationTab("Logout");
	}
	
	@DataProvider(name="testData")
	public String[][] loginTestData() throws Exception, IOException{
		
		String[][]  loginData = Utility.readXlsx(loginDataFilePathXLSX);
		return loginData;
		
	}
	public void validateAfterLogout(String uName, String password) {
		
		clickOnNavigationTab("Logout");
		driver.navigate().to(URL);
		loginTogetHomePage(uName, password);
		clickOnNavigationTab("Profile");
		validateUpdating();
		
	}
	
	public void clickOnNavigationTab(String tabTitle){
		
		String xpath = "//span[contains(text(),'"+tabTitle+"')]";	
		driver.findElement(By.xpath(xpath)).click();
	}


	public void launchApplication(){

		System.out.println("Launching the Application");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

	}

	public void loginTogetHomePage(String uName, String password){


		driver.findElement(By.id("username")).sendKeys(uName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("submit")).click();
	}
	
	public void clickEditButton(){

		System.out.println("clickEditButton");
		driver.findElement(By.id("Ebtn")).click();

	}
	/**
	 * This method will choose random number to edit that many fields only. 
	 */
	
	public void editRandomFields(){

		System.out.println("editData");
		rand = new Random();
		//choosing a random number to update that many number of fields
		int number = 1+rand.nextInt(13);
		System.out.println("We are going to update "+number+" fields");

		for(int i=1;i<=number;i++){
			
			//String field = dataFields[num]; This version of java takes only int in cases, not String types.
			int num = 1+rand.nextInt(13);
			System.out.println("editing the field no."+num );
			switch(num){
			case 1 :
				editFirstName();
				fName++;
				break;
			case 2 :
				editLastName();
				lName++;
				break;
			case 3 :
				editLicense();
				license++;
				break;
			case 4 :
				editSSN();
				ssn++;
				break;
			case 5 :
				editAddress();
				address++;
				break;
			case 6 :
				editAge();
				age++;
				break;
			case 7 :
				editWeight();
				weight++;
				break;
			case 8 :
				editHeight();
				height++;
				break;
			case 9 :
				editCity();
				city++;
				break;
			case 10 :
				editState();
				state++;
				break;
			case 11 :
				editZipCode();
				zipCode++;
				break;
			case 12 :
				editProviderInfo();
				break;
			case 13 :
				editInsuranceInfo();
				break;
			}	
		}	
		
	}
	
	

	public void editAllFields(){

		editFirstName();
		editLastName();
		editLicense();
		editSSN();
		editAddress();
		editAge();
		editWeight();
		editHeight();
		editCity();
		editState();
		editZipCode();
		editProviderInfo();
		editInsuranceInfo();

	}
	
	public void validateUpdating(){
		
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(validateFirstName());
		sa.assertTrue(validateLastName());
		sa.assertTrue(validateLicense());
		sa.assertTrue(validateSSN());
		sa.assertTrue(validateAddress());
		sa.assertTrue(validateAge());
		sa.assertTrue(validateWeight());
		sa.assertTrue(validateHeight());
		sa.assertTrue(validateCity());
		sa.assertTrue(validateState());
		sa.assertTrue(validateZipCode());
		sa.assertAll();
	}
	public void validateUpdatingRandomFields(){
		
		SoftAssert sa =  new SoftAssert();
		if(fName>0){ sa.assertTrue(validateFirstName());}
		if(lName>0){ sa.assertTrue(validateLastName());}
		if(license>0){ sa.assertTrue(validateLicense());}
		if(ssn>0){ sa.assertTrue(validateSSN());}
		if(address>0){ sa.assertTrue(validateAddress());}
		if(age>0){ sa.assertTrue(validateAge());}
		if(weight>0){ sa.assertTrue(validateWeight());}
		if(height>0){ sa.assertTrue(validateHeight());}
		if(city>0){ sa.assertTrue(validateCity());}
		if(state>0){ sa.assertTrue(validateState());}
		if(zipCode>0){ sa.assertTrue(validateZipCode());}
		sa.assertAll();
		
	}
	public void editFirstName(){

		we = driver.findElement(By.id("fname"));
		System.out.println("Edit FirstName "+we.getText());
		String firstNameValue = "QAEditFirstName"+(char)(65+rand.nextInt(26));
		we.clear();
		we.sendKeys(firstNameValue);
		hMap.put("FName", firstNameValue);

	}
	public boolean validateFirstName(){
		
		boolean fNameUpdated = false;
		we = driver.findElement(By.id("fname"));
		String primaryFNameValue = we.getAttribute("value");
		//primaryHMap.put("FName", primaryFNameValue);
		if(hMap.get("FName").equals(primaryFNameValue)){
			fNameUpdated = true;
		}
		return fNameUpdated;
		
	}

	public void editLastName(){

		we = driver.findElement(By.id("lname"));
		String lastNameValue = "QAEditLastName"+(char)(65+rand.nextInt(26));
		we.clear();
		we.sendKeys(lastNameValue);
		hMap.put("LName", lastNameValue);
		
	}
	public boolean validateLastName(){
		
		boolean lNameUpdated = false;
		we = driver.findElement(By.id("lname"));
		String primaryLNameValue = we.getAttribute("value");
		//primaryHMap.put("LName", primaryLNameValue);
		if(hMap.get("LName").equals(primaryLNameValue)){
			lNameUpdated = true;
		}
		return lNameUpdated;
	}

	public void editLicense(){

		we = driver.findElement(By.id("licn"));
		String licenseValue = Calendar.getInstance().getTimeInMillis()%100000000+"";
		//String licenseValue = 10000000+ rand.nextInt(89999999)+"";
		System.out.println("Total no. digits in license is :"+licenseValue.length());
		System.out.println(licenseValue);
		we.clear();
		we.sendKeys(licenseValue);
		hMap.put("License", licenseValue);
	}
	
	public boolean validateLicense(){
		
		boolean licenseUpdated = false;
		we = driver.findElement(By.id("licn"));
		String primaryLicenseValue = we.getAttribute("value");
		//primaryHMap.put("License", primaryLicenseValue);
		if(hMap.get("License").equals(primaryLicenseValue)){
			licenseUpdated = true;
		}
		System.out.println("primaryLicenseValue is "+primaryLicenseValue);
		System.out.println("Hash Map Value is "+hMap.get("License"));
		return licenseUpdated;
	}

	public void editSSN(){

		we = driver.findElement(By.id("ssn"));
		String ssnValue = Calendar.getInstance().getTimeInMillis()%1000000000+"";
		we.clear();
		we.sendKeys(ssnValue);
		hMap.put("SSN", ssnValue);
	}
	//How to check if it's already existing in the the database? --- The app is telling already.

	public boolean validateSSN(){
		
		boolean SSNUpdated = false;
		we = driver.findElement(By.id("ssn"));
		String primarySSNValue =  we.getAttribute("value");
		//primaryHMap.put("SSN", primarySSNValue);
		if(hMap.get("SSN").equals(primarySSNValue)){
			SSNUpdated = true;
		}
		return SSNUpdated;
		
	}
	
	public void editAddress(){

		we = driver.findElement(By.id("addr"));
		String addressValue = 1+rand.nextInt(999)+", QAEditAddress";
		we.clear();
		we.sendKeys(addressValue);
		hMap.put("Address", addressValue);

	}
	
	public boolean validateAddress(){
		
		boolean addressUpdated = false;
		we = driver.findElement(By.id("addr"));
		String primaryAddressValue =  we.getAttribute("value");
		//primaryHMap.put("Address", primaryAddressValue);
		if(hMap.get("Address").equals(primaryAddressValue)){
			addressUpdated = true;
		}
		return addressUpdated;
		
	}

	public void editAge(){

		we = driver.findElement(By.id("age"));
		String ageValue = 18+rand.nextInt(82)+"";//18 and older only can login
		we.clear();
		we.sendKeys(ageValue);
		hMap.put("Age", ageValue);

	}
	
	public boolean validateAge(){
		
		boolean ageUpdated = false;
		we = driver.findElement(By.id("age"));
		String primaryAgeValue =  we.getAttribute("value");
		//primaryHMap.put("Age", primaryAgeValue);
		if(hMap.get("Age").equals(primaryAgeValue)){
			ageUpdated = true;
		}
		return ageUpdated;
		
	}
	public void editWeight(){

		we = driver.findElement(By.id("weight"));
		String weightValue = 20+rand.nextInt(200)+"";//metric or US Standard? 
		we.clear();
		we.sendKeys(weightValue);
		hMap.put("Weight", weightValue);

	}
	
	public boolean validateWeight(){
		
		boolean weightUpdated = false;
		we = driver.findElement(By.id("weight"));
		String primaryWeightValue =  we.getAttribute("value");
		//primaryHMap.put("Weight", primaryWeightValue);
		if(hMap.get("Weight").equals(primaryWeightValue)){
			weightUpdated = true;
		}
		return weightUpdated;
		
	}
	public void editHeight(){

		we = driver.findElement(By.id("height"));
		String heightValue = 30+rand.nextInt(200)+"";//metric or US standard?
		we.clear();
		we.sendKeys(heightValue);
		hMap.put("Height", heightValue);

	}
	
	public boolean validateHeight(){
		
		boolean heightUpdated = false;
		we = driver.findElement(By.id("height"));
		String primaryHeightValue =  we.getAttribute("value");
		//primaryHMap.put("Height", primaryHeightValue);
		if(hMap.get("Height").equals(primaryHeightValue)){
			heightUpdated = true;
		}
		return heightUpdated;
	
	}
	
	public void editCity(){

		int noOfChars = 7;
		we = driver.findElement(By.id("city"));
		String cityValue = Utility.getRandomString(noOfChars);
		we.clear();
		we.sendKeys(cityValue);
		hMap.put("City", cityValue);

	}
	
	public boolean validateCity(){
		
		boolean cityUpdated = false;
		we = driver.findElement(By.id("city"));
		String primaryCityValue =  we.getAttribute("value");
		//primaryHMap.put("City", primaryCityValue);
		if(hMap.get("City").equals(primaryCityValue)){
			cityUpdated = true;
		}
		return cityUpdated;
		
	}
	public void editState(){

		we = driver.findElement(By.id("state"));
		String stateValue = Utility.getRandomState();
		we.clear();
		we.sendKeys(stateValue);
		hMap.put("State", stateValue);

	}
	
	public boolean validateState(){
		
		boolean stateUpdated = false;
		we = driver.findElement(By.id("state"));
		String primaryStateValue =  we.getAttribute("value");
		//primaryHMap.put("State", primaryStateValue);
		if(hMap.get("State").equals(primaryStateValue)){
			stateUpdated = true;
		}
		return stateUpdated;
		
	}
	public void editZipCode(){

		int noOfDigits = 5;
		we = driver.findElement(By.id("zip"));
		String zipCodeValue =  Utility.getRandomNoOfDigits(noOfDigits)+"";
		we.clear();
		we.sendKeys(zipCodeValue);
		hMap.put("ZipCode", zipCodeValue);
	}
	
	public boolean validateZipCode(){
		
		boolean zipCodeUpdated = false;
		we = driver.findElement(By.id("zip"));
		String primaryZipCodeValue =  we.getAttribute("value");
		//primaryHMap.put("ZipCode", primaryZipCodeValue);
		if(hMap.get("ZipCode").equals(primaryZipCodeValue)){
			zipCodeUpdated = true;
		}
		return zipCodeUpdated;
		
	}

	public void editProviderInfo(){

		int noOfChars = 6;
		we = driver.findElement(By.id("proinfo"));
		//String primaryProviderInfo = we.getText();
		//primaryHMap.put("ProviderInfo", primaryProviderInfo);
		String providerInfoValue = Utility.getRandomString(noOfChars);
		we.clear();
		we.sendKeys(providerInfoValue);
		hMap.put("ProviderInfo", providerInfoValue);

	}
	public void editInsuranceInfo(){

		int noOfChars = 10;
		we = driver.findElement(By.id("Insinfo"));
		//String primaryInsuranceInfo =  we.getAttribute("value");
		//primaryHMap.put("InsuranceInfo", primaryInsuranceInfo);
		String providerInsuranceInfoValue = Utility.getRandomString(noOfChars);
		we.clear();
		we.sendKeys(providerInsuranceInfoValue);
		hMap.put("InsuranceInfo", providerInsuranceInfoValue);

	}


	/**
	 * clickOnSaveButton to click the save button. In a happy path, alert for update confirmation will happen.
	 * Otherwise, we need to catch the errors to fix it before clicking save. 
	 * @return a string message from the alert for successful update, or message from the checkError(). 
	 */

	public String clickOnSaveButton(){
		
		String msg="";
		try{
					
			driver.findElement(By.id("Sbtn")).click();
			Alert alert = driver.switchTo().alert();
			msg = alert.getText();
			alert.accept();
		}
		catch(Exception e){
			System.out.println("Exception got: "+e.getMessage());
			msg = checkError();
			
		}
		return msg;
		
	}
	/**
	 * This method checks for any invalid entry (error messages) in the field and send the valid ones.
	 * This will look for displayed error messages (labels)  and catch the corresponding input text box to 
	 * enter the value again. In the app, there are 2 bugs 1)- sometimes 8 digits license number is not taken,
	 * in that cases, trying to enter the license number as 12345678. 2) Though the state name was valid, if there
	 * are 2 words (like New Jersey, New Mexico etc), the app is throwing an error message to enter the valid 
	 * state name. So the state name is being sent with no space in between. 
	 * 
	 *  
	 * @return - a String message retrieved from the alert update confirmation. 
	 */

	public String checkError(){

		String msg = "";
		System.out.println("Inside the CheckError method");
		String errElement = "";
		List <WebElement> errElements = driver.findElements(By.tagName("p"));
		System.out.println("No. Of Error Elements Present "+errElements.size());
		for (WebElement webElement : errElements) {

			if(webElement.isDisplayed()){

				System.out.println(webElement.getText());
				errElement = webElement.getAttribute("id");
				System.out.println(errElement);
				String xpath = "//p[@id='"+errElement+"']/preceding-sibling::input";
				if(webElement.getText().contains("license")){
					driver.findElement(By.xpath(xpath)).sendKeys("12345678");
					hMap.put("License", "12345678");
				}
				if(webElement.getText().contains("state")){
					String state = (hMap.get("State")).replaceAll("\\s", "");
					driver.findElement(By.xpath(xpath)).clear();
					driver.findElement(By.xpath(xpath)).sendKeys(state);
					hMap.put("State", state);
				}


			}

		}
		try{
			
			driver.findElement(By.id("Sbtn")).click();
			Alert alert = driver.switchTo().alert();
			msg = alert.getText();
			alert.accept();
		}
		catch(Exception e){
			System.out.println("Exception got: "+e.getMessage());
			
		}
		return msg;

	}


}
