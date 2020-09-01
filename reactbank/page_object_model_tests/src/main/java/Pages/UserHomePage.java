package Pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.LoginPage;

public class UserHomePage extends BasePage{
	final static Logger logger = Logger.getLogger(HomePage.class);
	private String userHomePgURL,UserHomePgAccountsTxt,
	userAccountsPgURL,logoutLink,UserHomePgMainNav,
	landingPgURL,profileCSS,transferCSS,cardsCSS, 
	xFerPageURL,cardsPageURL,profilePageURL,profileBtnXPath,
	BalanceTxtXPath,loginPgURL,loginPgURLDemo2;
	
	
	private String helpBtnXPath, helpPageURL;
	

	Map <String,String> DomsMap = new HashMap<String,String>();
	LoginPage lpage = new LoginPage(driver, wait);

	public UserHomePage(WebDriver driver, WebDriverWait wait) throws Exception {
		super(driver, wait);
		DomsMap = super.getDomsFromPropFile();
		if(DomsMap.isEmpty() || DomsMap == null){
			logger.error("Empty Map returned. stopping execution..");
			Assert.assertNotNull(DomsMap, "The DOM MAP IS EMPTY OR NULL... Stopping Execution..");	
		}
		getPathFromHashMap();

	}

	/*log in to Usaer Home page by visiting login and signing in . returns the URL of the current page
	 * */
	public String logintoUserHomePageFromLoginPage() throws Exception {
		boolean bool = false;
		String currURL = "";
		String UserHomeURL = "";
		
		
		
		//DK : Comment this AFTER everything looks Good for Demo site
		String actURL = loginPgURL;

		//DK : UN-Comment this AFTER everything looks Good for Demo site 1, to point to second site.
		//String actURL = loginPgURLDemo2;

		
		
		try {
			//DK : Comment this after everything looks Good for Demo site
			driver.get(loginPgURL);

			//DK : UN-Comment this after everything looks Good for Demo site
			//driver.get(loginPgURLDemo2);

			System.out.println("waiting for page to load");
			super.waitForPageLoaded();
			currURL = driver.getCurrentUrl();
			if (currURL.equalsIgnoreCase(actURL)) {
				System.out.println("We are in the Expected Login page");
				Assert.assertEquals(currURL, actURL);
				bool = true;
			}		
			if(bool) {
				UserHomeURL = lpage.loginToReactBank("email@example.com", "admin123");
			}

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return UserHomeURL;
	}

	public void getPathFromHashMap() throws Exception {
		UserHomePgAccountsTxt = DomsMap.get("UserHomePgAccountsTxt");	
		userHomePgURL = DomsMap.get("userHomePgURL");
		userAccountsPgURL = DomsMap.get("userAccountsPgURL");
		logoutLink = DomsMap.get("logoutLink");
		UserHomePgMainNav = DomsMap.get("UserHomePgMainNav");
		landingPgURL = DomsMap.get("landingPgURL");
		cardsCSS = DomsMap.get("cardsCSS");
		transferCSS = DomsMap.get("transferCSS");
		profileCSS = DomsMap.get("profileCSS");
		xFerPageURL = DomsMap.get("xFerPageURL");
		cardsPageURL = DomsMap.get("cardsPageURL");
		profilePageURL = DomsMap.get("profilePageURL");
		BalanceTxtXPath = DomsMap.get("BalanceTxtXPath");
		profileBtnXPath = DomsMap.get("profileBtnXPath");
		loginPgURL = DomsMap.get("loginPgURL");
		loginPgURLDemo2 = DomsMap.get("loginPgURLDemo2");

		helpBtnXPath = DomsMap.get("helpBtnXPath");
		helpPageURL = DomsMap.get("helpPageURL");

	}

	/*Accounts button method*/
	public String clickAccountsButtonReturnAccountsPgURL() throws Exception {
		String actualURL= "";
		try {
			WebElement UserHomePgAccountTxt = (new WebDriverWait(driver, 9)).until(ExpectedConditions.elementToBeClickable(By.xpath(UserHomePgAccountsTxt)));
			if (UserHomePgAccountTxt.isEnabled()) {
				UserHomePgAccountTxt.click();
			}
			Thread.sleep(2000);
			String expUrl = userAccountsPgURL;
			actualURL = driver.getCurrentUrl();


		}catch(Exception ex) {
			ex.printStackTrace();

		}
		return actualURL;

	}

	/*gets the count of the buttons - accounts, cards.. etc*/
	public boolean verifyMainNavCount() throws Exception{
		try {
			int expectedCnt = 7;
			int  mainNavCount = driver.findElements(By.xpath(UserHomePgMainNav)).size();
			if(mainNavCount == expectedCnt) {
				logger.info("verified the mainNav elements Count "+ expectedCnt);
				return true;
			}else {
				logger.info(" mainNav elements Count "+ expectedCnt);
				return false;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	/*prints the list of main nav buttons */
	public boolean verifyMainNavElementNames() throws Exception{
		try {
			List <WebElement> navList = new ArrayList();
			navList = driver.findElements(By.xpath(UserHomePgMainNav));
			int listSz = navList.size();
			if(navList.isEmpty() || listSz == 0) {
				logger.info("Returned Element List is EMPTY");
				return false;
			}else {
				for (int i = 1 ; i <= listSz ; i++) {
					WebElement elim = driver.findElement(By.xpath(UserHomePgMainNav+"["+i+"]/a/div/div/following-sibling::span"));
					System.out.println(elim.getText());
				}
			}
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * Method to find elements using CSS Selector from home page, navigating to that page and returning
	 * to User Home page using Browser back button
	 * 
	 * */
	public boolean ClickOnTransfersNavigateToTransferPage() throws Exception{
		try {
			WebElement transfer = (new WebDriverWait(driver, 9)).
					until(ExpectedConditions.elementToBeClickable(By.cssSelector(transferCSS)));
			transfer.click();
			Thread.sleep(2000);
			String expPageURL = xFerPageURL;
			String currentPageURL = driver.getCurrentUrl();
			if(expPageURL.equalsIgnoreCase(currentPageURL)) {
				logger.info("Successfully Navigated to the transfer page using CSS");
				return true;
			}else {
				return false;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// CARDS CSS

	public boolean clickCardsButtonLandOnCardsPage() throws Exception{
		try {
			WebElement cards = (new WebDriverWait(driver, 9)).
					until(ExpectedConditions.elementToBeClickable(By.cssSelector(cardsCSS)));
			cards.click();
			Thread.sleep(2000);
			String expPageURL = cardsPageURL;
			String currentPageURL = driver.getCurrentUrl();
			if(expPageURL.equalsIgnoreCase(currentPageURL)) {
				logger.info("Successfully Navigated to the Cards page using CSS");
				return true;
			}else {
				return false;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// PROFILE CSS
	public boolean clickProfileButtonLandOnProfilesPage() throws Exception{
		try {
			WebElement profile = (new WebDriverWait(driver, 9)).
					until(ExpectedConditions.elementToBeClickable(By.cssSelector(profileCSS)));
			profile.click();
			Thread.sleep(2000);
			String expPageURL = profilePageURL;
			String currentPageURL = driver.getCurrentUrl();
			if(expPageURL.equalsIgnoreCase(currentPageURL)) {
				logger.info("Successfully Navigated to the Profile page using CSS");
				return true;
			}else {
				return false;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/* Verify Balance is seen in profile page..
	 * During Demo, This test will PASS for first page and  break for page2 because of the changes made
	 * to the User Home page
	 */
	public boolean landOnProfilesPageCheckBalanceTextVisible() throws Exception{
		try {
			WebElement profile = (new WebDriverWait(driver, 9)).
					until(ExpectedConditions.elementToBeClickable(By.xpath(profileBtnXPath)));
			
			/* This is where Test will break as we are going by the Index :- profileBtnXPath://nav[@class='main-nav']/ul/li[6]
			 * And a Situation specific Temporary Message button has been added which ends up not finding the exact profile button to ckick..
			 * */

			profile.click();
			Thread.sleep(2000); //bad practice .. I know.
			String expPageURL = profilePageURL;
			String currentPageURL = driver.getCurrentUrl();
			if(expPageURL.equalsIgnoreCase(currentPageURL)) {
				System.out.println("Successfully Navigated to the Profile page ");
				// verifying balance text //
				WebElement balTxt = driver.findElement(By.xpath(BalanceTxtXPath));
				if(balTxt.isDisplayed()) {
					System.out.println("BALANCE TEXT IS VISIBLE IN PROFILES PAGE");
					return true;
				}else {
					System.out.println("BALANCE TEXT IS *** NOT VISIBLE *** IN PROFILES PAGE");
					return false;
				}
			}else {
				System.out.println("landOnProfilesPageCheckBalanceTextVisible Method Failed");
				return false;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}


	// PROFILE HELP BTN
	public boolean clickHelpButtonLandOnHelpPage() throws Exception{
		try {
			
			WebElement helpBtn = (new WebDriverWait(driver, 9)).
					until(ExpectedConditions.elementToBeClickable(By.xpath(helpBtnXPath)));
			
			helpBtn.click();
			Thread.sleep(2000);
			String expPageURL = helpPageURL;
			String currentPageURL = driver.getCurrentUrl();
			if(expPageURL.equalsIgnoreCase(currentPageURL)) {
				logger.info("Successfully Navigated to the Help page using XPath");
				return true;
			}else {
				return false;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}


	/*method to Logout from User home page*/
	public boolean clickLogOutVerifyIfInHomePage() throws Exception{
		try {
			WebElement logout = driver.findElement(By.xpath(logoutLink));
			if(logout.isEnabled()) {
				logout.click();
			}
			Thread.sleep(3000);
			//driver.wait().until(driver.getCurrentUrl().equalsIgnoreCase(landingPgURL));
			String currUrl = driver.getCurrentUrl();
			if(landingPgURL.equalsIgnoreCase(currUrl)) {
				logger.info("Landed in Home page Successfully "+ currUrl);
				return true;
			}else {
				logger.info("Did NOT get anavigatyed to landing page . Test fails");
				return false;
			}			

		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		} 
	}



}// end class 
/*userHomePgURL:https://demosites.testgold.dev/panel
UserHomePgAccountsTxt://span[.='Accounts']*/
