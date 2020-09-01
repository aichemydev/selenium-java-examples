package Pages;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HelpPage extends BasePage{
	final static Logger logger = Logger.getLogger(HomePage.class);
	private String loginPgURL,loginPgEmailTextBox,loginPgPasswordTextBox,loginPgSubmitBtn,userHomePgURL,logoutLink;
	private String helpPageURL,
	xpathHelpNameField,
	xpathHelpEmailField,
	xpathHelpHomePhoneField,
	xpathHelpOfficePhoneField,
	xpathHelpMobilePhoneField,
	xpathHelpSubjectField,
	xpathHelpMessageField,
	xpathHelpSendMessageBtn,
	helpBtnXPath;

	private boolean testPass;
	Map <String,String> DomsMap = new HashMap<String,String>();
	public HelpPage(WebDriver driver, WebDriverWait wait) throws Exception {
		super(driver, wait);
		DomsMap = super.getDomsFromPropFile();
		if(DomsMap.isEmpty() || DomsMap == null){
			logger.error("Empty Map returned. stopping execution..");
			Assert.assertNotNull(DomsMap, "The DOM MAP IS EMPTY OR NULL... Stopping Execution..");
		}
		getPathFromHashMap();
	}
	public void getPathFromHashMap() throws Exception {
		helpPageURL = DomsMap.get("helpPageURL");
		xpathHelpNameField = DomsMap.get("xpathHelpNameField");
		xpathHelpEmailField = DomsMap.get("xpathHelpEmailField");
		xpathHelpHomePhoneField = DomsMap.get("xpathHelpHomePhoneField");
		xpathHelpOfficePhoneField = DomsMap.get("xpathHelpOfficePhoneField");
		xpathHelpMobilePhoneField = DomsMap.get("xpathHelpMobilePhoneField");
		xpathHelpSubjectField = DomsMap.get("xpathHelpSubjectField");
		xpathHelpMessageField = DomsMap.get("xpathHelpMessageField");
		xpathHelpSendMessageBtn = DomsMap.get("xpathHelpSendMessageBtn");
		helpBtnXPath = DomsMap.get("helpBtnXPath");
	}

	public String fillContactUsAndClickSubmit(
			String name,
			String email,
			String homePhone,
			String officePhone,
			String mobilePhone,
			String subject,
			String message
			) throws Exception{
		System.out.println("RUNNING METHOD fillContactUsAndClickSubmit");
		String usrHomPgURL ="";
		// Lets wait for page to load completely //
		System.out.println("Lets wait for page to load completely");
		super.waitForPageLoaded();

		// after clicking, then wait, then do this
		WebElement helpButton = (new WebDriverWait(driver, 9)).
				until(ExpectedConditions.elementToBeClickable(By.xpath(helpBtnXPath)));


		if (helpButton.isEnabled()) {
			helpButton.click();
		}

		// after clicking, then wait, then do this
		WebElement nameField = (new WebDriverWait(driver, 9)).
				until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(xpathHelpNameField)));

		if (nameField.isEnabled()) {
			nameField.sendKeys(name);
		}


		WebElement emailField = (new WebDriverWait(driver, 9)).
				until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(xpathHelpEmailField)));

		if (emailField.isEnabled()) {
			emailField.sendKeys(email);
		}


		WebElement homePhoneField = (new WebDriverWait(driver, 9)).
				until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(xpathHelpHomePhoneField)));

		if (homePhoneField.isEnabled()) {
			homePhoneField.sendKeys(homePhone);
		}


		WebElement officePhoneField = (new WebDriverWait(driver, 9)).
				until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(xpathHelpOfficePhoneField)));

		if (officePhoneField.isEnabled()) {
			officePhoneField.sendKeys(officePhone);
		}


		WebElement phoneField = (new WebDriverWait(driver, 9)).
				until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(xpathHelpMobilePhoneField)));

		if (phoneField.isEnabled()) {
			phoneField.sendKeys(mobilePhone);
		}



		WebElement subjectField = (new WebDriverWait(driver, 9)).
				until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(xpathHelpSubjectField)));

		if (subjectField.isEnabled()) {
			subjectField.sendKeys(subject);
		}


		WebElement messageField = (new WebDriverWait(driver, 9)).
				until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(xpathHelpMessageField)));
		if (messageField.isEnabled()) {
			messageField.sendKeys(message);
		}


		WebElement submitBtn = (new WebDriverWait(driver, 9))
				.until(ExpectedConditions.elementToBeClickable(
						By.xpath(xpathHelpSendMessageBtn)));
		if (submitBtn.isEnabled()) {
			submitBtn.click();
		}
		Thread.sleep(2000);
		usrHomPgURL = driver.getCurrentUrl();
//			System.out.println("Actual URL is : "+usrHomPgURL);

		return usrHomPgURL;
	}


} // class

