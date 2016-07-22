/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mrge.tradeo.seleniumtask.traderpopup;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;

import org.mrge.tradeo.seleniumtask.pagesmodels.TradeoHomePage;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HomePageTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = TradeoHomePage.pageUrl;
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  }

  @Test
  public void signInAsCustomerFromHomePage() throws Exception {
    driver.get(TradeoHomePage.pageUrl);
    WebElement language = driver.findElement(By.xpath(TradeoHomePage.languageXpath));
    assertEquals(TradeoHomePage.languageTextEnglish, language.getText());

    WebElement menu = driver.findElement(By.xpath(TradeoHomePage.menuXpath));
    assertEquals(TradeoHomePage.menuTextEnglish, menu.getText());

    WebElement signinLink = driver.findElement(By.id(TradeoHomePage.signinLinkId));
    assertEquals(TradeoHomePage.signinTextEnglish, signinLink.getText());

    signinLink.click();

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
