package Pages;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;



//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.apache.log4j.PropertyConfigurator;



public class BasePage {
	private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

	public WebDriver driver;
	public WebDriverWait wait;
	public static String locale ;
	private HashMap<String,String >locDomsMap = new HashMap<String,String>();
//	final static Logger logger = Logger.getLogger(BasePage.class);

	//Constructor
	public BasePage (WebDriver driver, WebDriverWait wait) throws Exception{
		try{
		logger.info(" BASE PAGE CONSTRUCTOR");
		this.driver = driver;
		Properties props = new Properties();
//		ClassLoader.getSystemClassLoader().getResource("log4j.properties");

//		props.load(new FileInputStream("./src/main/resources/log4j.properties"));
//			System.out.println("Stream: "+ClassLoader.getSystemClassLoader().getResourceAsStream("log4j.properties"));
//		props.load(ClassLoader.getSystemClassLoader().getResourceAsStream("./log4j.properties"));
		props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("log4j.properties"));

//			PropertyConfigurator.configure(props);
		this.wait = wait;
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}


	/*Method to get the Doms by reading the .properties file
	 * Returns - a HashMap of elements and path from the properties file
	 * */
	public Map<String, String> getDomsFromPropFile() throws Exception{
		System.out.println("RUNNING METHOD getDomsFromPropFile");
		Properties prop = new Properties();
		Map<String, String> domMap = new HashMap<String,String>();
		try{
			String domsProp = "./src/main/resources/config/PageElements.properties";
//			String domsProp =  BasePage.class.getResource( "/PageElements.properties" ).toURI();
//			File resourceFile = new File( this.getClass().getResource( "/PageElements.properties" ).toURI() );

//			this.getClass().getClassLoader().getResource("text.txt").getFile();

			logger.info("Locale prop is : "+domsProp);
//			InputStream locStream = new FileInputStream(domsProp);
//			prop.load(ClassLoader.getSystemClassLoader().getResourceAsStream("src/WebUI/config/PageElements.properties"));
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/PageElements.properties"));

			int domSize = prop.size();
			//System.out.println("PRINTING ALL THE DOM ELEMENTS FOR LOCALE : "+ locale+"   ======!");

			for (final String name: prop.stringPropertyNames()){
				domMap.put(name, prop.getProperty(name));
			}
			/*
			 * forLoop is commented only to keep the output clean. a very useful
			 * loop that will Iterate the map and spit out the KV pairs. Run locally
			 * to verify if the test has all hte elements to act on.
			 */
			/* for (Entry e: domMap.entrySet()){
					System.out.println("Key : "+ e.getKey()+ "  Value : "+e.getValue());
				}*/

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return domMap;
	}

	// wait for page to load completely -

	 public void waitForPageLoaded() {
	        ExpectedCondition<Boolean> expectation = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
	                    }
	                };
	        try {
	            Thread.sleep(1000);
	            WebDriverWait wait = new WebDriverWait(driver, 30);
	            wait.until(expectation);
	        } catch (Throwable error) {
	            Assert.fail("Timeout waiting for Page Load Request to complete.");
	        }
	    }




	/*
	 * All Utility methods go here. Feel free to move to a different folder if need be.
	 */

	// Wait for element to be visible giving it 20 seconds, feel free to change this .
		public void waitTillElementVisiable(By elementLocation, String text){
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.textToBePresentInElementValue(elementLocation, text));
		}
   // 	WebElement HeaderTxtElem = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-style-abc']/fieldset/legend[(self::legend) and not (@class='number')]")));

		// wait for page load to complete
		public  void allowPageLoadFully() throws Exception{
			 driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		}


}// end BasePage Class
