package org.iit.mmp.patientmodule.pages;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.utility.Utility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.asserts.SoftAssert;



public class UpdateProfilePage {
	
	WebDriver driver;
	HelperClass helperObj;
	
	HashMap <String, String> hMap;
	Random rand;
	String URL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php";
	String actual = "Your Profile has been updated.";
	String expected = "";
	int fName, lName, license, ssn, address, age, weight, height, city, state, zipCode; 
	WebElement we;
	
	By userNameTB = By.id("username");
	By passwordTB = By.id("password");
	By submitBtn = By.name("submit");
	By editBtn = By.id("Ebtn");
	By firstNameTB = By.id("fname");
	By lastNameTB = By.id("lname");
	By licenseTB = By.id("licn");
	By SSNTB = By.id("ssn");
	By addressTB = By.id("addr");
	By ageTB = By.id("age");
	By weightTB = By.id("weight");
	By heightTB = By.id("height");
	By cityTB = By.id("city");
	By stateTB = By.id("state");
	By zipCodeTB = By.id("zip");
	By providerInfoTB = By.id("proinfo");
	By insuranceInfoTB = By.id("Insinfo");
	By saveBtn = By.id("Sbtn");
	
	By errorLabels = By.tagName("p");
	
	public UpdateProfilePage(WebDriver driver){
		
		this.driver = driver;
		helperObj = new HelperClass(driver);
		hMap = new HashMap <String, String> ();
		rand = new Random();
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

	
	public void loginTogetHomePage(String uName, String password){

		driver.findElement(userNameTB).sendKeys(uName);
		driver.findElement(passwordTB).sendKeys(password);
		driver.findElement(submitBtn).click();
	}
	
	public void clickEditButton(){

		System.out.println("clickEditButton");
		driver.findElement(editBtn).click();

	}
	/**
	 * This method will choose random number to edit that many fields only. 
	 * @throws IOException 
	 */
	
	public void editRandomFields() throws IOException{

		System.out.println("editData");
		//rand = new Random();
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
	
	public void editAllFields() throws IOException{

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

		we = driver.findElement(firstNameTB);
		System.out.println("Edit FirstName "+we.getText());
		String firstNameValue = "QAEditFirstName"+(char)(65+rand.nextInt(26));
		we.clear();
		we.sendKeys(firstNameValue);
		hMap.put("FName", firstNameValue);

	}
	public boolean validateFirstName(){
		
		boolean fNameUpdated = false;
		we = driver.findElement(firstNameTB);
		String primaryFNameValue = we.getAttribute("value");
		//primaryHMap.put("FName", primaryFNameValue);
		if(hMap.get("FName").equals(primaryFNameValue)){
			fNameUpdated = true;
		}
		return fNameUpdated;
		
	}

	public void editLastName(){

		we = driver.findElement(lastNameTB);
		String lastNameValue = "QAEditLastName"+(char)(65+rand.nextInt(26));
		we.clear();
		we.sendKeys(lastNameValue);
		hMap.put("LName", lastNameValue);
		
	}
	public boolean validateLastName(){
		
		boolean lNameUpdated = false;
		we = driver.findElement(lastNameTB);
		String primaryLNameValue = we.getAttribute("value");
		//primaryHMap.put("LName", primaryLNameValue);
		if(hMap.get("LName").equals(primaryLNameValue)){
			lNameUpdated = true;
		}
		return lNameUpdated;
	}

	public void editLicense() throws IOException{

		we = driver.findElement(licenseTB);
		String licenseValue = Calendar.getInstance().getTimeInMillis()%100000000+"";
		//String licenseValue = 10000000+ rand.nextInt(89999999)+"";
		System.out.println("Total no. digits in license is :"+licenseValue.length());
		System.out.println(licenseValue);
		we.clear();
		we.sendKeys(licenseValue);
		hMap.put("License", licenseValue);
		helperObj.captureScreenshot("licenseErr");
	}
	
	public boolean validateLicense(){
		
		boolean licenseUpdated = false;
		we = driver.findElement(licenseTB);
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

		we = driver.findElement(SSNTB);
		String ssnValue = Calendar.getInstance().getTimeInMillis()%1000000000+"";
		we.clear();
		we.sendKeys(ssnValue);
		hMap.put("SSN", ssnValue);
		
	}
	//How to check if it's already existing in the the database? --- The app is telling already.

	public boolean validateSSN(){
		
		boolean SSNUpdated = false;
		we = driver.findElement(SSNTB);
		String primarySSNValue =  we.getAttribute("value");
		//primaryHMap.put("SSN", primarySSNValue);
		if(hMap.get("SSN").equals(primarySSNValue)){
			SSNUpdated = true;
		}
		return SSNUpdated;
		
	}
	
	public void editAddress(){

		we = driver.findElement(addressTB);
		String addressValue = 1+rand.nextInt(999)+", QAEditAddress";
		we.clear();
		we.sendKeys(addressValue);
		hMap.put("Address", addressValue);

	}
	
	public boolean validateAddress(){
		
		boolean addressUpdated = false;
		we = driver.findElement(addressTB);
		String primaryAddressValue =  we.getAttribute("value");
		//primaryHMap.put("Address", primaryAddressValue);
		if(hMap.get("Address").equals(primaryAddressValue)){
			addressUpdated = true;
		}
		return addressUpdated;
		
	}

	public void editAge(){

		we = driver.findElement(ageTB);
		String ageValue = 18+rand.nextInt(82)+"";//18 and older only can login
		we.clear();
		we.sendKeys(ageValue);
		hMap.put("Age", ageValue);

	}
	
	public boolean validateAge(){
		
		boolean ageUpdated = false;
		we = driver.findElement(ageTB);
		String primaryAgeValue =  we.getAttribute("value");
		//primaryHMap.put("Age", primaryAgeValue);
		if(hMap.get("Age").equals(primaryAgeValue)){
			ageUpdated = true;
		}
		return ageUpdated;
		
	}
	public void editWeight(){

		we = driver.findElement(weightTB);
		String weightValue = 20+rand.nextInt(200)+"";//metric or US Standard? 
		we.clear();
		we.sendKeys(weightValue);
		hMap.put("Weight", weightValue);

	}
	
	public boolean validateWeight(){
		
		boolean weightUpdated = false;
		we = driver.findElement(weightTB);
		String primaryWeightValue =  we.getAttribute("value");
		//primaryHMap.put("Weight", primaryWeightValue);
		if(hMap.get("Weight").equals(primaryWeightValue)){
			weightUpdated = true;
		}
		return weightUpdated;
		
	}
	public void editHeight(){

		we = driver.findElement(heightTB);
		String heightValue = 30+rand.nextInt(200)+"";//metric or US standard?
		we.clear();
		we.sendKeys(heightValue);
		hMap.put("Height", heightValue);

	}
	
	public boolean validateHeight(){
		
		boolean heightUpdated = false;
		we = driver.findElement(heightTB);
		String primaryHeightValue =  we.getAttribute("value");
		//primaryHMap.put("Height", primaryHeightValue);
		if(hMap.get("Height").equals(primaryHeightValue)){
			heightUpdated = true;
		}
		return heightUpdated;
	
	}
	
	public void editCity(){

		int noOfChars = 7;
		we = driver.findElement(cityTB);
		String cityValue = Utility.getRandomString(noOfChars);
		we.clear();
		we.sendKeys(cityValue);
		hMap.put("City", cityValue);

	}
	
	public boolean validateCity(){
		
		boolean cityUpdated = false;
		we = driver.findElement(cityTB);
		String primaryCityValue =  we.getAttribute("value");
		//primaryHMap.put("City", primaryCityValue);
		if(hMap.get("City").equals(primaryCityValue)){
			cityUpdated = true;
		}
		return cityUpdated;
		
	}
	public void editState() throws IOException{

		we = driver.findElement(stateTB);
		String stateValue = Utility.getRandomState();
		we.clear();
		we.sendKeys(stateValue);
		hMap.put("State", stateValue);
		helperObj.captureScreenshot("stateErr");

	}
	
	public boolean validateState(){
		
		boolean stateUpdated = false;
		we = driver.findElement(stateTB);
		String primaryStateValue =  we.getAttribute("value");
		//primaryHMap.put("State", primaryStateValue);
		if(hMap.get("State").equals(primaryStateValue)){
			stateUpdated = true;
		}
		return stateUpdated;
		
	}
	public void editZipCode(){

		int noOfDigits = 5;
		we = driver.findElement(zipCodeTB);
		String zipCodeValue =  Utility.getRandomNoOfDigits(noOfDigits)+"";
		we.clear();
		we.sendKeys(zipCodeValue);
		hMap.put("ZipCode", zipCodeValue);
	}
	
	public boolean validateZipCode(){
		
		boolean zipCodeUpdated = false;
		we = driver.findElement(zipCodeTB);
		String primaryZipCodeValue =  we.getAttribute("value");
		//primaryHMap.put("ZipCode", primaryZipCodeValue);
		if(hMap.get("ZipCode").equals(primaryZipCodeValue)){
			zipCodeUpdated = true;
		}
		return zipCodeUpdated;
		
	}

	public void editProviderInfo(){

		int noOfChars = 6;
		we = driver.findElement(providerInfoTB);
		//String primaryProviderInfo = we.getText();
		//primaryHMap.put("ProviderInfo", primaryProviderInfo);
		String providerInfoValue = Utility.getRandomString(noOfChars);
		we.clear();
		we.sendKeys(providerInfoValue);
		hMap.put("ProviderInfo", providerInfoValue);

	}
	public void editInsuranceInfo(){

		int noOfChars = 10;
		we = driver.findElement(insuranceInfoTB);
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
	 * @throws IOException 
	 */

	public String clickOnSaveButton() throws IOException{
		
		String msg="";
		try{
					
			driver.findElement(saveBtn).click();
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
	 * @throws IOException 
	 */

	public String checkError() throws IOException{

		String msg = "";
		System.out.println("Inside the CheckError method");
		String errElement = "";
		List <WebElement> errElements = driver.findElements(errorLabels);
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
					helperObj.captureScreenshot("license");
				}
				if(webElement.getText().contains("state")){
					String state = (hMap.get("State")).replaceAll("\\s", "");//remove the space between the words
					driver.findElement(By.xpath(xpath)).clear();
					driver.findElement(By.xpath(xpath)).sendKeys(state);
					hMap.put("State", state);
					helperObj.captureScreenshot("State");
				}


			}

		}
		try{
			
			driver.findElement(saveBtn).click();
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
