/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mrge.tradeo.seleniumtask.traderpopup;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.mrge.tradeo.seleniumtask.pagesmodels.TradeoHomePage;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class HomePageTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
      System.out.println("1 Going to " + TradeoHomePage.pageUrl);
    driver = new FirefoxDriver();
//    Thread.sleep(15,000);
    baseUrl = TradeoHomePage.pageUrl;
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  }

  @Test
  public void signInAsCustomerFromHomePage() throws Exception {
      System.out.println(" Going to " + TradeoHomePage.pageUrl);
    driver.get(TradeoHomePage.pageUrl);
    driver.findElement(By.linkText("selenium")).click();
    driver.findElement(By.xpath("//ul[@id='user-links']/li[3]/a/span")).click();
    driver.findElement(By.linkText("Your stars")).click();
    assertEquals("You don’t have any starred repositories yet.", driver.findElement(By.cssSelector("h3")).getText());
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
