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

public class LoginPage extends BasePage{
	final static Logger logger = Logger.getLogger(HomePage.class);
	private String loginPgURL,loginPgEmailTextBox,loginPgPasswordTextBox,loginPgSubmitBtn,userHomePgURL,logoutLink;
	private boolean testPass;
	Map <String,String> DomsMap = new HashMap<String,String>();
	public LoginPage(WebDriver driver, WebDriverWait wait) throws Exception {
		super(driver, wait);
		DomsMap = super.getDomsFromPropFile();
		if(DomsMap.isEmpty() || DomsMap == null){
			logger.error("Empty Map returned. stopping execution..");
			Assert.assertNotNull(DomsMap, "The DOM MAP IS EMPTY OR NULL... Stopping Execution..");	
		}
		getPathFromHashMap();
	}
	public void getPathFromHashMap() throws Exception {
		loginPgURL = DomsMap.get("loginPgURL");
		loginPgEmailTextBox = DomsMap.get("loginPgEmailTextBox");
		loginPgPasswordTextBox = DomsMap.get("loginPgPasswordTextBox");
		loginPgSubmitBtn = DomsMap.get("loginPgSubmitBtn");	
		userHomePgURL = DomsMap.get("userHomePgURL");
		logoutLink = DomsMap.get("logoutLink");
	}
	/*The below Method is Moot. why would one visit login page from login page ? */
	public boolean visitLoginPage() throws Exception{
		System.out.println("waiting for page to load");
		super.waitForPageLoaded();
		System.out.println("RUNNING METHOD visitLoginPage");
		String expectedURL = loginPgURL;
		try {
			if(loginPgURL == null || loginPgURL.isEmpty()) {
				return false;
			}
			System.out.println("Going to sleep for 2 seconds");
			Thread.sleep(2000);
			//	driver.get(loginPgURL);
			driver.get(loginPgURL);
			System.out.println("WAITING FOR PAGE TO LOAD GOD");
			super.waitForPageLoaded();
			String actualURL = driver.getCurrentUrl();
			System.out.println("Expected URL is "+ expectedURL+" actual URL is "+ actualURL);
			if(expectedURL.equalsIgnoreCase(actualURL)) {
				logger.info("Login page launched successfully");
				return true;
			}else {
				return false;
			}

		} catch(Exception ex) {
			ex.printStackTrace();
			return false; 
		}
	}


	/*Method - Log in to react bank and return User home page url
	 * */
	public String loginToReactBank(String userName, String password) throws Exception{
		System.out.println("RUNNING METHOD loginToReactBank");
		String usrHomPgURL ="";
		try {
			// Lets wait for page to load completely //
			System.out.println("Lets wait for page to load completely");
			super.waitForPageLoaded();
			WebElement userNameTextBox = (new WebDriverWait(driver, 9)).
					until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loginPgEmailTextBox)));
			//WebElement userNameTextBox = driver.findElement(By.xpath(loginPgEmailTextBox));
			if (userNameTextBox.isEnabled()) {
				userNameTextBox.sendKeys(userName);
			}
			WebElement passwordTextBox = (new WebDriverWait(driver, 9)).
					until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loginPgPasswordTextBox)));
			//	WebElement passwordTextBox = driver.findElement(By.xpath(loginPgPasswordTextBox));
			if (passwordTextBox.isEnabled()) {
				passwordTextBox.sendKeys(password);
			}
			WebElement submitBtn = (new WebDriverWait(driver, 9)).until(ExpectedConditions.elementToBeClickable(By.xpath(loginPgSubmitBtn)));
			if (submitBtn.isEnabled()) {
				submitBtn.click();
			}
			Thread.sleep(2000);
			usrHomPgURL = driver.getCurrentUrl();
			System.out.println("Actual URL is : "+usrHomPgURL);

		}catch(Exception ex) {  
			ex.printStackTrace(); 
		}
		return usrHomPgURL;	
	}


} // class

