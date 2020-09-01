//package test.java;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import Pages.UserHomePage;

public class UserHomePageTest extends BaseTest{
	final static Logger logger = Logger.getLogger(LoginPageTest.class);
	 String userHomePgURL,expAcctPgURL;
	
	@Test
	public void TestUserHomePageMethods() throws Exception{
		Map <String,String> DomsMap = new HashMap<String,String>();
		DomsMap = super.getDomsFromPropFile();
		if(DomsMap.isEmpty() || DomsMap == null){
			logger.error("Empty Map returned. stopping execution..");
			Assert.assertNotNull(DomsMap, "The DOM MAP IS EMPTY OR NULL... Stopping Execution..");	
		}
		expAcctPgURL = DomsMap.get("userAccountsPgURL");
		userHomePgURL = DomsMap.get("userHomePgURL");
		
		
		String actURL = "";
		String acctPgURL = "";
		UserHomePage userHomePg = new UserHomePage(driver, wait); 
		try {
			actURL = userHomePg.logintoUserHomePageFromLoginPage();
			//Assert.assertTrue(isPass);
//			Assert.assertEquals(actURL, expURL);
			acctPgURL = userHomePg.clickAccountsButtonReturnAccountsPgURL();
			Assert.assertEquals(acctPgURL, expAcctPgURL);
			//
			// UserHomePgAccountsTxt:      //span[.='Accounts']
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	/*This Test verifies the count of the number of Elements (Home, Accounts..Help ) count is 7
	 * and calls out the name of each of these elements. This is an example of using Axis
	 * finds elements using following-sibling::span
	 * */
	/* Disabling this test as this can change and cause issues */
	public void testMainNavCountAndPrintElementNames() throws Exception{
		Pages.UserHomePage userHomePg = new Pages.UserHomePage(driver, wait);
		String actURL = "";
		boolean isPass;
		actURL = userHomePg.logintoUserHomePageFromLoginPage();
		try {
			isPass = userHomePg.verifyMainNavCount();
			if(isPass) {
				isPass = userHomePg.verifyMainNavElementNames();
			}else {
				Assert.assertTrue(isPass);
			}
			Assert.assertTrue(isPass);	
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	/*
	 * Test to verify Transfer button method this uses CSS instead of XPath
	 * */
	@Test
	public void TestTransferCSSButton() throws Exception{
		Pages.UserHomePage userHomePg = new Pages.UserHomePage(driver, wait);
		String actURL = "";
		boolean isPass;
		actURL = userHomePg.logintoUserHomePageFromLoginPage();
		try {
				isPass = userHomePg.ClickOnTransfersNavigateToTransferPage();	
				if(isPass) {
					driver.navigate().back();  
				    System.out.println(driver.getCurrentUrl());
				    Assert.assertTrue(isPass);
			}else {
				Assert.assertTrue(isPass);
			}
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
		
	}
	
	/*
	 * Test to verify Cards button method this uses CSS instead of XPath
	 * */
	@Test
	public void testCardsCSSButton() throws Exception{
		Pages.UserHomePage userHomePg = new Pages.UserHomePage(driver, wait);
		String actURL = "";
		boolean isPass;
		actURL = userHomePg.logintoUserHomePageFromLoginPage();
		try {
				isPass = userHomePg.clickCardsButtonLandOnCardsPage();	
				if(isPass) {
					driver.navigate().back();  
			        System.out.println("CURRENT URL AFTER NAVIGATING BACK IS :  "+driver.getCurrentUrl());
			}else {
				Assert.assertTrue(isPass);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	/*
	 * Test to verify Profile button method this uses CSS instead of XPath
	 * */
	// We dont need to test CSS Profile button as our healer does not deal with CSS
	public void testProfileCSSButton() throws Exception{
	Pages.UserHomePage userHomePg = new Pages.UserHomePage(driver, wait);
		String actURL = "";
		boolean isPass;
		actURL = userHomePg.logintoUserHomePageFromLoginPage();
		try {
				isPass = userHomePg.clickProfileButtonLandOnProfilesPage();	
				if(isPass) {
					driver.navigate().back();  
					System.out.println("CURRENT URL AFTER NAVIGATING BACK IS :  "+driver.getCurrentUrl());
			}else {
				Assert.assertTrue(isPass);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	@Test // For the Demo
	public void TestBalanceShowsUpOnProfilePage() throws Exception{
		Pages.UserHomePage userHomePg = new Pages.UserHomePage(driver, wait);
		
		String actURL = "";
		String acctPgURL = "";
		
		
		
		boolean isPass;
		actURL = userHomePg.logintoUserHomePageFromLoginPage();
		try {
			isPass = userHomePg.landOnProfilesPageCheckBalanceTextVisible();
			if(isPass) {
				driver.navigate().back();  
				System.out.println("CURRENT URL AFTER NAVIGATING BACK IS :  "+driver.getCurrentUrl());
		}

		else {
			Assert.assertTrue(isPass);
		}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		 
	}
	
	
	  
	
	/*
	 * Test to verify logout functionality from user home page.
	 * */
	@Test
	public void testLogOut() throws Exception{
		Pages.UserHomePage userHomePg = new Pages.UserHomePage(driver, wait);
		String actURL = "";
		boolean isPass;
		actURL = userHomePg.logintoUserHomePageFromLoginPage();
		try {
				isPass = userHomePg.clickLogOutVerifyIfInHomePage();
				Assert.assertTrue(isPass);
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	 

}// end class
