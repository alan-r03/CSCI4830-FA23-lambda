package com.tests;

import java.util.regex.Pattern;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SignUpCustomer {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  static final String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  static final Integer len = 8;
  
  public String getRandomString() {
	  StringBuilder sb = new StringBuilder(len);
	  SecureRandom rnd = new SecureRandom();
	  for (int i = 0; i < len; i++) {
		  sb.append(alpha.charAt(rnd.nextInt(alpha.length())));
	  }
	  return sb.toString();
  }

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "lib\\win\\chromedriver.exe");
	driver = new ChromeDriver();    
	baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSignUpCustomer() throws Exception {
	String email = getRandomString();
	Thread.sleep(500);
    driver.get("http://ec2-3-14-143-146.us-east-2.compute.amazonaws.com:8080/ContractOne/Login.jsp");
    Thread.sleep(2000);
    driver.findElement(By.linkText("Sign Up")).click();
    Thread.sleep(2000);
    driver.get("http://ec2-3-14-143-146.us-east-2.compute.amazonaws.com:8080/ContractOne/SignUp.jsp");
    Thread.sleep(2000);
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("SELENIUMTESTCUST");
    Thread.sleep(2000);
    driver.findElement(By.name("address")).clear();
    driver.findElement(By.name("address")).sendKeys("1234 Testing Boulevard");
    Thread.sleep(2000);
    driver.findElement(By.name("phone")).clear();
    driver.findElement(By.name("phone")).sendKeys("123-123-1234");
    Thread.sleep(2000);
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys(email+"@test.org");
    Thread.sleep(2000);
    driver.findElement(By.name("description")).clear();
    driver.findElement(By.name("description")).sendKeys("SELENIUM TESTER!");
    Thread.sleep(2000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("Test@1234");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver.get("http://ec2-3-14-143-146.us-east-2.compute.amazonaws.com:8080/ContractOne/Login.jsp");
    Thread.sleep(2000);
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys(email+"@test.org");
    Thread.sleep(2000);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("Test@1234");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
