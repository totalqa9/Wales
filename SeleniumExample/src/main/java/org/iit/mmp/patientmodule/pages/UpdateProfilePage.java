
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


public class UpdateProfilePage {

	WebDriver driver;
	HelperClass helperObj;

	HashMap <String, String> hMap;
	HashMap <String, String> hMap1;
	Random rand;
	String actual = "Your Profile has been updated.";
	String expected;
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

	public UpdateProfilePage(WebDriver driver){

		this.driver = driver;
		helperObj = new HelperClass(driver);
		hMap = new HashMap <String, String> ();
		hMap1 = new HashMap <String, String> ();
		rand = new Random();
	}

	public boolean validateAfterLogout(String uName, String password, String URL) {

		boolean result = false;
		helperObj.moduleNavigation("Logout");
		driver.navigate().to(URL);
		loginTogetHomePage(uName, password);
		helperObj.moduleNavigation("Profile");
		result = validateUpdating();
		return result;

	}
	public void clickOnNavigationTab(String tabTitle){

		String xpath = "//span[contains(text(),'"+tabTitle+"')]";	
		helperObj.highLightElement(driver.findElement(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).click();
	}

	//Added this again
	public void loginTogetHomePage(String uName, String password){

		driver.findElement(userNameTB).sendKeys(uName);
		driver.findElement(passwordTB).sendKeys(password);
		driver.findElement(submitBtn).click();
	}

	public void clickEditButton(){

		System.out.println("clickEditButton");
		helperObj.highLightElement(driver.findElement(editBtn));
		driver.findElement(editBtn).click();

	}
	/**
	 * This method will choose random number to edit that many fields only. 
	 * @throws IOException 
	 */

	public void editRandomFields() throws IOException{

		System.out.println("editData");
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
				break;
			case 2 :
				editLastName();
				break;
			case 3 :
				editLicense();
				break;
			case 4 :
				editSSN();
				break;
			case 5 :
				editAddress();
				break;
			case 6 :
				editAge();
				break;
			case 7 :
				editWeight();
				break;
			case 8 :
				editHeight();
				break;
			case 9 :
				editCity();
				break;
			case 10 :
				editState();
				break;
			case 11 :
				editZipCode();
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

	/**
	 * After updating all the fields, getting the field values and storing it in a hash map to validate
	 * @return HashMap of displayed field values
	 */
	public HashMap <String, String> getFieldsValue(){

		//clickEditButton();
		hMap1.put("FName", driver.findElement(firstNameTB).getAttribute("value"));
		hMap1.put("LName", driver.findElement(lastNameTB).getAttribute("value"));
		hMap1.put("License", driver.findElement(licenseTB).getAttribute("value"));
		hMap1.put("SSN", driver.findElement(SSNTB).getAttribute("value"));
		hMap1.put("Address", driver.findElement(addressTB).getAttribute("value"));
		hMap1.put("Age", driver.findElement(ageTB).getAttribute("value"));
		hMap1.put("Weight", driver.findElement(weightTB).getAttribute("value"));
		hMap1.put("Height", driver.findElement(heightTB).getAttribute("value"));
		hMap1.put("City", driver.findElement(cityTB).getAttribute("value"));
		hMap1.put("State", driver.findElement(stateTB).getAttribute("value"));
		hMap1.put("ZipCode", driver.findElement(zipCodeTB).getAttribute("value"));

		//TRying
		//Assert.assertEquals(hMap1, hMap, "Hash Map Values match");
		return hMap1;
	}

	public boolean validateUpdating(){

		boolean result = false;
		if (getFieldsValue().equals(hMap))
			result = true;

		System.out.println("Validate Updating Result is "+result);
		return result;
	}
	public void editFirstName(){

		we = driver.findElement(firstNameTB);
		helperObj.highLightElement(we);
		System.out.println("Edit FirstName "+we.getText());
		String firstNameValue = "QAEditFirstName"+(char)(65+rand.nextInt(26));
		we.clear();
		we.sendKeys(firstNameValue);
		hMap.put("FName", firstNameValue);
	}
	public void editLastName(){

		we = driver.findElement(lastNameTB);
		helperObj.highLightElement(we);
		String lastNameValue = "QAEditLastName"+(char)(65+rand.nextInt(26));
		we.clear();
		we.sendKeys(lastNameValue);
		hMap.put("LName", lastNameValue);
	}
	public void editLicense() throws IOException{

		we = driver.findElement(licenseTB);
		helperObj.highLightElement(we);
		String licenseValue = Calendar.getInstance().getTimeInMillis()%100000000+"";
		//String licenseValue = 10000000+ rand.nextInt(89999999)+"";
		System.out.println("Total no. digits in license is :"+licenseValue.length());
		System.out.println(licenseValue);
		we.clear();
		we.sendKeys(licenseValue);
		hMap.put("License", licenseValue);
		helperObj.captureScreenshot("licenseErr");
	}
	public void editSSN(){

		we = driver.findElement(SSNTB);
		helperObj.highLightElement(we);
		String ssnValue = Calendar.getInstance().getTimeInMillis()%1000000000+"";
		we.clear();
		we.sendKeys(ssnValue);
		hMap.put("SSN", ssnValue);
	}
	public void editAddress(){

		we = driver.findElement(addressTB);
		helperObj.highLightElement(we);
		String addressValue = 1+rand.nextInt(999)+", QAEditAddress";
		we.clear();
		we.sendKeys(addressValue);
		hMap.put("Address", addressValue);
	}
	public void editAge(){

		we = driver.findElement(ageTB);
		helperObj.highLightElement(we);
		String ageValue = 18+rand.nextInt(82)+"";//18 and older only can login
		we.clear();
		we.sendKeys(ageValue);
		hMap.put("Age", ageValue);
	}
	public void editWeight(){

		we = driver.findElement(weightTB);
		helperObj.highLightElement(we);
		String weightValue = 20+rand.nextInt(200)+"";//metric or US Standard? 
		we.clear();
		we.sendKeys(weightValue);
		hMap.put("Weight", weightValue);
	}
	public void editHeight(){

		we = driver.findElement(heightTB);
		helperObj.highLightElement(we);
		String heightValue = 30+rand.nextInt(200)+"";//metric or US standard?
		we.clear();
		we.sendKeys(heightValue);
		hMap.put("Height", heightValue);
	}
	public void editCity(){

		int noOfChars = 7;
		we = driver.findElement(cityTB);
		helperObj.highLightElement(we);
		String cityValue = Utility.getRandomString(noOfChars);
		we.clear();
		we.sendKeys(cityValue);
		hMap.put("City", cityValue);
	}
	public void editState() throws IOException{

		we = driver.findElement(stateTB);
		helperObj.highLightElement(we);
		String stateValue = Utility.getRandomState();
		we.clear();
		we.sendKeys(stateValue);
		hMap.put("State", stateValue);
		helperObj.captureScreenshot("stateErr");
	}
	public void editZipCode(){

		int noOfDigits = 5;
		we = driver.findElement(zipCodeTB);
		helperObj.highLightElement(we);
		String zipCodeValue =  Utility.getRandomNoOfDigits(noOfDigits)+"";
		we.clear();
		we.sendKeys(zipCodeValue);
		hMap.put("ZipCode", zipCodeValue);
	}
	public void editProviderInfo(){

		int noOfChars = 6;
		we = driver.findElement(providerInfoTB);
		helperObj.highLightElement(we);
		String providerInfoValue = Utility.getRandomString(noOfChars);
		we.clear();
		we.sendKeys(providerInfoValue);
	}
	public void editInsuranceInfo(){

		int noOfChars = 10;
		we = driver.findElement(insuranceInfoTB);
		helperObj.highLightElement(we);
		String providerInsuranceInfoValue = Utility.getRandomString(noOfChars);
		we.clear();
		we.sendKeys(providerInsuranceInfoValue);
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
			helperObj.highLightElement(driver.findElement(saveBtn));	
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
		List <WebElement> errElements = driver.findElements(By.tagName("p"));
		System.out.println("No. Of Error Elements Present "+errElements.size());
		for (WebElement webElement : errElements) {

			if(webElement.isDisplayed()){

				System.out.println(webElement.getText());
				errElement = webElement.getAttribute("id");
				System.out.println(errElement);
				String xpath = "//p[@id='"+errElement+"']/preceding-sibling::input";
				if(webElement.getText().contains("license")){
					helperObj.highLightElement(driver.findElement(By.xpath(xpath)));
					driver.findElement(By.xpath(xpath)).sendKeys("12345678");
					hMap.put("License", "12345678");
					helperObj.captureScreenshot("license");
				}
				if(webElement.getText().contains("state")){
					String state = (hMap.get("State")).replaceAll("\\s", "");
					driver.findElement(By.xpath(xpath)).clear();
					helperObj.highLightElement(driver.findElement(By.xpath(xpath)));
					driver.findElement(By.xpath(xpath)).sendKeys(state);
					hMap.put("State", state);
					helperObj.captureScreenshot("State");
				}
				if((webElement.getText().contains("appear"))||(webElement.getText().contains("SSN"))){
					String value = Utility.getRandomNoOfDigits(9)+"";
					driver.findElement(By.xpath(xpath)).clear();
					driver.findElement(By.xpath(xpath)).sendKeys(value);
					hMap.put("SSN",value);
					
				}
			}
		}
		try{
			helperObj.highLightElement(driver.findElement(saveBtn));
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

