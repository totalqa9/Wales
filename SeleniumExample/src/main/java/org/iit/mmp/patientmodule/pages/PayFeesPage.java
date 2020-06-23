package org.iit.mmp.patientmodule.pages;

import java.util.List;
import java.util.Random;

import org.iit.mmp.helper.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PayFeesPage {
	WebDriver driver;
	Random rand;
	HelperClass helperObj;

	By pay=By.linkText("Pay Now");
	By amounts=By.id("amount");
	By continueB=By.xpath("//input[@value='Continue']");
	By cuName=By.id("name");
	By cardType=By.id("card_name");
	By cid=By.id("cid");
	By cardMonth=By.id("cardMonth");
	By cardYear=By.id("cardYear");
	By cvv=By.id("cvv");
	By submitB=By.xpath("//input[@value='submit']");
	By selectOptions = By.xpath("//select[@id='amount']/option[text()='$50']");


	public PayFeesPage(WebDriver driver)
	{
		this.driver=driver;
		helperObj = new HelperClass(driver);
		rand = new Random();
	}

	public void navigateToPay()
	{
		driver.findElement(pay).click();
	}
	/**
	 * Updated by Nithya
	 * Scrolling down to the exact location of the select menu to avoid no such element
	 * @param amount
	 * @throws Exception
	 */
	public void selectPayment(String amount) throws Exception
	{
		WebElement we = driver.findElement(amounts);
		helperObj.scrollIntoViewOfWebElement(we);
		Select payment=new Select(we);
		Thread.sleep(3000);
		amount = "$"+amount;
		System.out.println(amount+ "is the fee amount");
		payment.selectByVisibleText("$50");
		//driver.findElement(By.xpath("//select[@id='amount']/option[text()='$50']")).click();
		driver.findElement(continueB).click();
	}

	public void inputCardInfo()
	{
		String cardHolderName="Wales"+rand.nextInt(100)+"";
		driver.findElement(cuName).sendKeys(cardHolderName);

		Select cards=new Select(driver.findElement(cardType));
		List<WebElement> cardList=cards.getOptions();

		for (int i=0;i<cardList.size();i++)
		{
			String cardName = cards.getOptions().get(i).getText();
			if(cardName.equals("Visa") )
			{
				cards.selectByVisibleText("Visa");
				String visaCardNumber=(long) ((rand.nextDouble() * 100000000000000L) + 4400000000000000L)+"";
				driver.findElement(By.id("cid")).sendKeys(visaCardNumber);
				break;
			}
			else if (cardName.equals("Master Card") )
			{
				cards.selectByVisibleText("Master Card");
				String masterCardNumber=(long) ((rand.nextDouble() * 100000000000000L) + 5500000000000000L)+"";
				driver.findElement(By.id("cid")).sendKeys(masterCardNumber);
				break;
			}
			else if (cardName.equals("Discover") )
			{
				cards.selectByVisibleText("Discover");
				String discoverCardNumber=(long) ((rand.nextDouble() * 100000000000000L) + 6300000000000000L)+"";
				driver.findElement(By.id("cid")).sendKeys(discoverCardNumber);
				break;
			}

			else if (cardName.equals("American Express") )
			{
				cards.selectByVisibleText("American Express");
				String amxCardNumber=(long) ((rand.nextDouble() * 100000000000000L) + 3200000000000000L)+"";
				driver.findElement(By.id("cid")).sendKeys(amxCardNumber);
				break;
			}

		}
		Select month=new Select(driver.findElement(cardMonth));
		month.selectByValue("04");
		Select year=new Select(driver.findElement(cardYear));
		year.selectByValue("15");
		String cvvValue=rand.nextInt(1000)+"";
		driver.findElement(cvv).sendKeys(cvvValue);
	}

	public void clickOnSubmit()
	{
		driver.findElement(submitB).click();
	}

	/**
	 * Updated by Nithya
	 * The submit button in the app is not working. The app is not giving any such "payment made 
	 * successfully" message. So there's nothing to validate. 
	 * @throws Exception 
	 */
	public void payFee(String fees) throws Exception
	{
		navigateToPay();
		selectPayment(fees);
		inputCardInfo();
		clickOnSubmit();

	}
	/**
	 * Added by Nithya - to validate the create fee action
	 */
	/*public void validateCreateFee(){
		navigateToPay();
		List <WebElement> feeList = driver.findElements(By.xpath("//p[text() = 'Your Outstanding Balance']//following::li"));


	}*/

}
