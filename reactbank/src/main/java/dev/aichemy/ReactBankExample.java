package dev.aichemy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ReactBankExample {

  String landingPageUrl = System.getenv("REACTBANK_LANDING_URL");
  String landingLoginButtonXPath = "//a[@class='btn btn-primary btn-lg' and contains(text(),'Click here to log in')]";

  String loginEmailAddr = "email@example.com";
  String loginPassword = "admin123";

  String loginEmailXPath = "//input[@name='email']";
  String loginPasswordXPath = "//input[@name='password']";

  String loginSubmitXPath = "//button[@class='btn btn-primary' and contains(text(),'Log in now')]";

  String logoutLinkXPath = "//a[@href='/logout' and contains(text(),'Logout')]";

  String balanceLinkXPath = "//a//span[contains(text(),'Profile')]";
  String balanceItemXPath = "//span[.='Balance']";

  String helpLinkXPath = "//a//span[contains(text(),'Help')]";
  String helpNameXPath = "//input[@id='name']";
  String helpEmailXPath = "//input[@id='email']";
  String helpHomePhoneXPath = "//*[@id='home_phone1234']";
  String helpOfficePhoneXPath = "//*[@name='office_phone1234']";
  String helpMobilePhoneXPath = "//*[@class='mobilePhoneClass1234']";
  String helpSubjectXPath = "//select[@id='subject']";
  String helpMessageXPath = "//textarea[@id='message']";
  String helpSendButtonXPath = "//button[@type='submit']";

  Map<String, String> helpFormItems = new HashMap<>();


//    public static void main(String[] args) {
//
//    }

  @BeforeClass
  //@BeforeTest  //Enabling this opens two windows
  //@Test
  public void setUp() throws Exception {
    helpFormItems.put("name", "Node User");
    helpFormItems.put("email", "node@test.org");
    helpFormItems.put("homePhone", "1-555-650-5555");
    helpFormItems.put("officePhone", "1-555-440-5555");
    helpFormItems.put("mobilePhone", "1-555-240-5555");
    helpFormItems.put("message", "I'm a node user!");

  }

  /***************************************** test functions *************************************/

  /* This logs into the ReactBank website and returns the driver in the new state */
  public ChromeDriver loginToReactBank(ChromeDriver driver, int timeoutSec) throws InterruptedException {

    TimeUnit.SECONDS.sleep(timeoutSec);
    System.out.println("[TEST] finding the login link");

    /* Click on the login button */
    WebElement loginButton = driver.findElement(By.xpath(landingLoginButtonXPath));
    System.out.println("[TEST] clicking on the login link");
    loginButton.click();

    TimeUnit.SECONDS.sleep(timeoutSec);

    /* Fill in the email address and password  */

    System.out.println("[TEST] finding the login email box and filling it in");
    WebElement emailBox = driver.findElement(By.xpath(loginEmailXPath));
    emailBox.sendKeys(loginEmailAddr);

    System.out.println("[TEST] finding the login password box and filling it in");
    WebElement passBox = driver.findElement(By.xpath(loginPasswordXPath));
    passBox.sendKeys(loginPassword);

    TimeUnit.SECONDS.sleep(timeoutSec);

    /* Click on the submit button  */

    System.out.println("[TEST] finding the login submit button");
    WebElement loginSubmit = driver.findElement(By.xpath(loginSubmitXPath));
    loginSubmit.click();

    TimeUnit.SECONDS.sleep(timeoutSec);

    return driver;
  }

  /* This logs out from the React Bank website and returns the driver in the new state. */
  public ChromeDriver logoutFromReactBank(ChromeDriver driver, int timeoutSec) throws InterruptedException {

    TimeUnit.SECONDS.sleep(timeoutSec);

    /* Find the logout button */
    System.out.println("[TEST] finding the logout link");
    WebElement logoutButton = driver.findElement(By.xpath(logoutLinkXPath));
    System.out.println("[TEST] clicking on the logout link");
    logoutButton.click();

    TimeUnit.SECONDS.sleep(timeoutSec);

    return driver;
  }


  /* Tries to find and click on the balance item on the user page and get the text of the balance item. */
  public ChromeDriver findBalanceOnUserPage(ChromeDriver driver, int timeoutSec) throws InterruptedException {

    TimeUnit.SECONDS.sleep(timeoutSec);

    /* Find the balance item */

    System.out.println("[TEST] finding the balance link");
    WebElement balanceLink = driver.findElement(By.xpath(balanceLinkXPath));
    System.out.println("[TEST] clicking on the balance link");
    balanceLink.click();

    TimeUnit.SECONDS.sleep(timeoutSec);

    /* Get the balance item */

    System.out.println("[TEST] finding the balance item");
    WebElement balanceItem = driver.findElement(By.xpath(balanceItemXPath));
    String balanceText = balanceItem.getText();   /* NOT SURE ABOUT THIS LINE  */
    System.out.println(String.format("[TEST] balance item text is %s: ", balanceText));

    TimeUnit.SECONDS.sleep(timeoutSec);

    return driver;
  }


  /* This tries to find the help page, then fill in the help form, then submit it */
  public ChromeDriver fillOutHelpForm(ChromeDriver driver, int timeoutSec) throws InterruptedException {

    TimeUnit.SECONDS.sleep(timeoutSec);

    System.out.println("[TEST] finding the help page link");
    WebElement helpFormLink = driver.findElement(By.xpath(helpLinkXPath));
    System.out.println("[TEST] clicking on the help page link");
    helpFormLink.click();

    TimeUnit.SECONDS.sleep(timeoutSec);

    /* Get the help form items and fill them in */

    System.out.println("[TEST] filling in the help form name item");
    WebElement helpName = driver.findElement(By.xpath(helpNameXPath));
    helpName.sendKeys(helpFormItems.get("name"));
    TimeUnit.SECONDS.sleep(1);

    System.out.println("[TEST] filling in the help form email item");
    WebElement helpEmail = driver.findElement(By.xpath(helpEmailXPath));
    helpEmail.sendKeys(helpFormItems.get("email"));
    TimeUnit.SECONDS.sleep(1);

    System.out.println("[TEST] filling in the help form home-phone item");
    WebElement helpHomePhone = driver.findElement(By.xpath(helpHomePhoneXPath));
    helpHomePhone.sendKeys(helpFormItems.get("homePhone"));
    TimeUnit.SECONDS.sleep(1);

    System.out.println("[TEST] filling in the help form office-phone item");
    WebElement helpOfficePhone = driver.findElement(By.xpath(helpOfficePhoneXPath));
    helpOfficePhone.sendKeys(helpFormItems.get("officePhone"));
    TimeUnit.SECONDS.sleep(1);

    System.out.println("[TEST] filling in the help form mobile-phone item");
    WebElement helpMobilePhone = driver.findElement(By.xpath(helpMobilePhoneXPath));
    helpMobilePhone.sendKeys(helpFormItems.get("mobilePhone"));
    TimeUnit.SECONDS.sleep(1);

    System.out.println("[TEST] filling in the help form subject item");
    WebElement helpSubject = driver.findElement(By.xpath(helpSubjectXPath));
    String helpSubjectTag = helpSubject.getTagName(); /* NOT SURE ABOUT THIS LINE */
    System.out.println(String.format("[TEST] help form subject tag is %s: ", helpSubjectTag));
    TimeUnit.SECONDS.sleep(1);

    System.out.println("[TEST] filling in the help form message item");
    WebElement helpMessage = driver.findElement(By.xpath(helpMessageXPath));
    helpMessage.sendKeys(helpFormItems.get("message"));
    TimeUnit.SECONDS.sleep(1);

    System.out.println("[TEST] filling in the help form submit button");
    WebElement helpSendButton = driver.findElement(By.xpath(helpSendButtonXPath));
    helpSendButton.click();
    TimeUnit.SECONDS.sleep(1);

    return driver;

  }

  @Test
  /* This runs the test */
  public void runTest() throws InterruptedException {

    //WebDriverManager.chromedriver().clearCache(); //before WebDriverManager.chromedriver().setup();
//				WebDriverManager.chromedriver().driverVersion("83.0.4103.61").setup();
    WebDriverManager.chromedriver().setup();
    System.out.println("LAUNCHING NEW CHROME DRIVER");

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--disable-extensions");
    ChromeDriver driver = new ChromeDriver(options);

    int exitCode = 0;

    int timeoutSec = 2;

    /* WebDriverManager.chromedriver().setup(); */

    try {



      /* Get to the landing page */
      driver.get(landingPageUrl);

      /* Log in */
      TimeUnit.SECONDS.sleep(3);
      System.out.println("[RUNNER] doing the login");
      driver = loginToReactBank(driver, timeoutSec);
      System.out.println("[RUNNER] the login done");

      /****** other tests go here ******/

      TimeUnit.SECONDS.sleep(5);
      /* Balance check */
      System.out.println("[RUNNER] doing the balance check");
      driver = findBalanceOnUserPage(driver, timeoutSec);
      System.out.println("[RUNNER] the balance check done");

      TimeUnit.SECONDS.sleep(3);
      /* Filling in the help form */
      System.out.println("[RUNNER] doing the help form");
      driver = fillOutHelpForm(driver, timeoutSec);
      System.out.println("[RUNNER] the help form done");

      TimeUnit.SECONDS.sleep(3);
      /* Logout */
      System.out.println("[RUNNER] doing the logout");
      driver = logoutFromReactBank(driver, timeoutSec);
      System.out.println("[RUNNER] the logout done");

    } /*catch (Exception ex) {
            System.out.println("Ran into exception when running the test.");
            exitCode = 1;

        } */ finally {
//            if (driver != null) {
      System.out.println("[RUNNER] waiting to close the window");
      TimeUnit.SECONDS.sleep(3);
      driver.close();
      System.out.println("[RUNNER] window close done, waiting for the driver to quit");
      driver.quit();
      System.out.println("[RUNNER] the driver quit done");
//            }

    }
    System.out.println(String.format("[RUNNER] the test run complete. exit code %d: ", exitCode));

  }

}
