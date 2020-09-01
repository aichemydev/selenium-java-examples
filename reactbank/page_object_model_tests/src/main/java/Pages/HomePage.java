package Pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class HomePage extends BasePage{
	final static Logger logger = Logger.getLogger(HomePage.class);
	String landingPageUrl, loginPageURL, landingPgWelcomeTitle,landingPgLoginButton, helpButton;
	boolean testPass;
	Map <String,String> DomsMap = new HashMap<String,String>();
	//constructor

	public HomePage(WebDriver driver, WebDriverWait wait) throws Exception {
		super(driver, wait);
		//First get the RELEVANT DOMS for this Page
		System.out.println("Construct Homepage");
		DomsMap = super.getDomsFromPropFile();
		if(DomsMap.isEmpty() || DomsMap == null){
			logger.error("Empty Map returned. stopping execution..");
			Assert.assertNotNull(DomsMap, "The DOM MAP IS EMPTY OR NULL... Stopping Execution..");
		}
		getPathFromHashMap();
	}
	/*From the MAP, get the path for the DOMS*/
	public void getPathFromHashMap() throws Exception {
		landingPageUrl = DomsMap.get("landingPgURL");
		loginPageURL = DomsMap.get("loginPgURL");
		landingPgWelcomeTitle = DomsMap.get("landingPgWelcomeTitle");
		landingPgLoginButton = DomsMap.get("landingPgLoginButton");
		helpButton = DomsMap.get("helpBtnXPath");
	}

	//############################### PAGE METHODS ##################################
	/*
	 * Visit HomePage and check if we are in the right page by checking URL
	 * */
	public boolean visitHomePage() throws Exception{
		System.out.println("RUNNING METHOD VISIT HOME PAGE");
		String expectedURL = landingPageUrl;
		try {
			if(landingPageUrl == null || landingPageUrl.isEmpty()) {
				return false;
			}
			driver.get(landingPageUrl);
			String actualURL = driver.getCurrentUrl();
			System.out.println("Expected URL is "+ expectedURL+" actual URL is "+ actualURL);
			Assert.assertEquals(expectedURL, actualURL);


		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	/*
	 * Verify the element that has a <title> like text
	 * */
	public boolean verifyWelcomeMessage() throws Exception{
		System.out.println("RUNNING METHOD verifyWelcomeMessage");

		try {
			WebElement welcomeTitle = (new WebDriverWait(driver, 15))
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(landingPgWelcomeTitle)));
			// WebElement welcomeTitle = driver.findElement(By.xpath(landingPgWelcomeTitle));
			boolean welcome = welcomeTitle.isDisplayed();
			if( !welcome) return false;

		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/*Click Login Button and verify if we land in the Login page by verifying
	 * Login page URL
	 * */
	public boolean verifyLoginButtonFunctionality() throws Exception {
		System.out.println("RUNNING METHOD verifyLoginButtonFunctionality");
		try {
			WebElement loginBtn = (new WebDriverWait(driver, 9)).until(ExpectedConditions.elementToBeClickable(By.xpath(landingPgLoginButton)));
			if (loginBtn.isEnabled()) {
				loginBtn.click();
			}
			Thread.sleep(2000);
			String expectedURL = loginPageURL;
			String actualURL = driver.getCurrentUrl();
			if(expectedURL.equalsIgnoreCase(actualURL)) {
				logger.info("Successfully Navigated to login page");
				return true;
			}else {
				return false;
			}


		}catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

// E2E Demo for Givor
	public boolean navigateThroughAllPagesLandInAccountspage() throws Exception {
		System.out.println("RUNNING METHOD navigateThroughAllPagesLandInAccountspage");
		try {
			String loginPgEmailTextBox = DomsMap.get("loginPgEmailTextBox");
			String loginPgPasswordTextBox = DomsMap.get("loginPgPasswordTextBox");
			String loginPgSubmitBtn = DomsMap.get("loginPgSubmitBtn");
			String userHomePgURL = DomsMap.get("userHomePgURL");
			WebElement loginBtn = (new WebDriverWait(driver, 9)).until(ExpectedConditions.elementToBeClickable(By.xpath(landingPgLoginButton)));
			if (loginBtn.isEnabled()) {
				loginBtn.click();
			}
			Thread.sleep(2000);
			String expectedURL = loginPageURL;
			String actualURL = driver.getCurrentUrl();
			if(expectedURL.equalsIgnoreCase(actualURL)) {
				logger.info("Successfully Navigated to login page");

			}else {
				return false;
			}


		}catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}


}
