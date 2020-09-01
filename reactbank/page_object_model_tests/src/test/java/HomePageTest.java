//package test.java;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Pages.HomePage;

public class HomePageTest extends BaseTest{
	final static Logger logger = Logger.getLogger(HomePageTest.class);
	
	@Test
	public void testHomePage() throws Exception{
		try {
		boolean pass ;
		Pages.HomePage homePg = new Pages.HomePage(driver, wait);
		//DK : Verify if Tests pass in sequence - One way of doing it works for simple pages
		pass = homePg.visitHomePage();
		if(pass) {
			logger.info("TestVisitHomePage -  PASSED" );
		}
		Assert.assertEquals(pass, true);
		
		if(pass) {
			pass = homePg.verifyWelcomeMessage();	
		}
		if(pass) {
			logger.info("verifyWelcomeMessage -  PASSED" );
		}
		Assert.assertEquals(pass, true);
		if(pass) {
			pass = homePg.verifyLoginButtonFunctionality();
		}
		if(pass) {
			logger.info("verifyLoginButtonFunctionality -  PASSED" );
		}
		Assert.assertEquals(pass, true);
		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	

}// End HomePageTestClass
