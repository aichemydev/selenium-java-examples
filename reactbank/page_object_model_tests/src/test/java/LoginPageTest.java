//package test.java;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Pages.LoginPage;

public class LoginPageTest extends BaseTest{
	final static Logger logger = Logger.getLogger(LoginPageTest.class);
	
	// Use DataProvider to designate positive and Negative Tests
		@DataProvider(name = "credentials")
		Object[][] credentials(){
			return new Object[][] {
				{"gmail@sample.com","admin123", false},
				{"email@example.com","dummy", false},
				{"email@example.com","admin123", true},
				
				
			};
		}
	/*
	 * Test Logging in from the Login page. Two negative and one positive Test case
	 * */
	@Test(dataProvider = "credentials")
	public void testLoginMethod(String userName, String password, boolean isPositive) throws Exception{
//		Pages.LoginPage loginPagePg = new Pages.LoginPage(driver, wait);

		System.out.println("RUNNING TEST testLoginMethod with DATA PROVIDER");
		String usrHomPgURL ;
		String expectedURL = "https://demo.testgold.dev/panel";
		try {
			boolean pass ;
			Pages.LoginPage loginPg = new Pages.LoginPage(driver, wait);
			pass = loginPg.visitLoginPage();
			if(pass) {
				usrHomPgURL = loginPg.loginToReactBank(userName, password);
				if(isPositive) {
					Assert.assertEquals(usrHomPgURL, expectedURL);
				}else {
					Assert.assertNotEquals(usrHomPgURL, expectedURL);;
				}
			}
			//Thread.sleep(2000);
			//driver.close(); //DK comments this
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	

}
