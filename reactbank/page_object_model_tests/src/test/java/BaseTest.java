//package test.java;
//package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import Pages.BasePage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;

public class BaseTest {
	private String browser;
	final static Logger logger = Logger.getLogger(BaseTest.class);
	public static WebDriver driver; // changed to static..DK
	public WebDriverWait wait;
	
	@BeforeClass
	//@BeforeTest  //Enabling this opens two windows
	//@Test
	public void setUp() throws Exception{

		// Added line to intercept system.out, later this should only be added at server end, not by tester
		SysOutOverSLF4J.sendSystemOutAndErrToSLF4J();


		System.out.println("SETTING UP BROWSER AND LAUNCHING BASE TEST PAGE");
		try {
		//	final Logger logger = Logger.getLogger(BaseTest.class);
			browser = getBrowserFromProperties();
			if(browser.equalsIgnoreCase("chrome")) {
				//dear god please make this work
				WebDriverManager.chromedriver().clearCache(); //before WebDriverManager.chromedriver().setup();
//				WebDriverManager.chromedriver().driverVersion("83.0.4103.61").setup();
				WebDriverManager.chromedriver().setup();
				System.out.println("LAUNCHING NEW CHROME DRIVER");
//				driver =  new ChromeDriver();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1200","--ignore-certificate-errors");
//				options.addArguments("--window-size=1920,1200","--ignore-certificate-errors");

				driver = new ChromeDriver(options);


			}else if(browser.equalsIgnoreCase("firefox")){
				WebDriverManager.firefoxdriver();
				
			}				 
						
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	/*Get the Browser to run from the properties file*/
	public String getBrowserFromProperties() throws Exception{
		Properties prop = new Properties();
		try{
//			InputStream browStream = new FileInputStream("./src/main/resources/config/browser.properties");
//			InputStream browStream = new FileInputStream("./src/main/resources/config/browser.properties");

//			ClassLoader.getSystemClassLoader().getResource("./myresource.xml");
//			prop.load(ClassLoader.getSystemClassLoader().getResourceAsStream("config/browser.properties"));
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/browser.properties"));


//			prop.load(browStream);
		    browser = prop.getProperty("browser");
		    System.out.println("TESTS WILL RUN IN BROWSER : "+browser);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return browser;
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
			logger.error("Stream is"+ Thread.currentThread().getContextClassLoader().getResourceAsStream("config/PageElements.properties"));
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
	

	
	//@AfterClass
	@AfterTest // changing this DK
	public void teardown () {
		
		System.out.println("RUNNING METHOD TEARDOWN METHODS");
		driver.close();
		
		System.out.println("Closed browser in Base Page");
		driver.quit();
		System.out.println("Quit browser in Base Page");
	}

} // End Class
