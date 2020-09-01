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

public class HelpPageTest extends BaseTest{
	final static Logger logger = Logger.getLogger(LoginPageTest.class);
	 String userHomePgURL,expAcctPgURL;

	@Test // For the Demo
	public void testContactUsOnPagePage() throws Exception{
		Pages.UserHomePage userHomePg = new Pages.UserHomePage(driver, wait);

		String actURL = "";
		String acctPgURL = "";


		boolean isPass;
		actURL = userHomePg.logintoUserHomePageFromLoginPage();

		Pages.HelpPage helpPg = new Pages.HelpPage(driver, wait);

		String name = "john smith";
		String email = "john@smith.com";
		String homePhone = "123";
		String officePhone = "456";
		String mobilePhone = "789";
		String subject = "Subject 2";
		String message = "Test message for Subject 2";

		helpPg.fillContactUsAndClickSubmit(name, email, homePhone, officePhone, mobilePhone, subject, message);
		driver.navigate().back();
		System.out.println("CURRENT URL AFTER NAVIGATING BACK IS :  "+driver.getCurrentUrl());

	}


}// end class
