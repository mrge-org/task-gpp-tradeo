/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mrge.tradeo.seleniumtask.traderpopup;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mrge.tradeo.seleniumtask.utils.TestConstants.IMPLICIT_WAIT_IN_SEC;
import static org.mrge.tradeo.seleniumtask.utils.TestConstants.WAIT_AFTER_CLICK_IN_SEC;

import org.mrge.tradeo.seleniumtask.pagesmodels.TradeoHomePage;
import org.mrge.tradeo.seleniumtask.pagesmodels.TradeoMyAccountPage;
import org.mrge.tradeo.seleniumtask.pagesmodels.TradeoSignInPage;
import org.mrge.tradeo.seleniumtask.pagesmodels.TradeoTestCustomer;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = TradeoHomePage.pageUrl;
    driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_IN_SEC, TimeUnit.SECONDS);
  }

  @Test
  public void cannotSignInWithWrongPasswordAsCustomerFromHomePage() throws Exception {
    gotoHomePageAndSignIn(TradeoTestCustomer.loginWrongPassword);

    WebDriverWait wait = new WebDriverWait(driver,WAIT_AFTER_CLICK_IN_SEC);
    wait.until(ExpectedConditions.titleIs(TradeoSignInPage.title));

    WebElement alert = driver.findElement(By.xpath(TradeoSignInPage.alertXpath));
    assertEquals(TradeoSignInPage.invalidLoginAlertTextEnglish, alert.getText());
  }

  @Test
  public void signInAsCustomerFromHomePage() throws Exception {
    gotoHomePageAndSignIn(TradeoTestCustomer.loginCorrectPassword);

    WebDriverWait wait = new WebDriverWait(driver,WAIT_AFTER_CLICK_IN_SEC);
    wait.until(ExpectedConditions.titleContains(TradeoMyAccountPage.title));

    WebElement usernameLink = driver.findElement(By.xpath(TradeoHomePage.usernameXpath));
    assertEquals(TradeoTestCustomer.usernameTextEnglish, usernameLink.getText());
  }

  private void gotoHomePageAndSignIn(String pass) {
    driver.get(TradeoHomePage.pageUrl);
    WebElement language = driver.findElement(By.xpath(TradeoHomePage.languageXpath));
    assertEquals(TradeoHomePage.languageTextEnglish, language.getText());

    WebElement menu = driver.findElement(By.xpath(TradeoHomePage.menuXpath));
    assertEquals(TradeoHomePage.menuTextEnglish, menu.getText());

    WebElement signinLink = driver.findElement(By.id(TradeoHomePage.signinLinkId));
    assertEquals(TradeoHomePage.signinTextEnglish, signinLink.getText());

    signinLink.click();
    driver.findElement(By.id(TradeoHomePage.userLoginFieldId)).sendKeys(TradeoTestCustomer.loginName);
    driver.findElement(By.id(TradeoHomePage.userPasswordFieldId)).sendKeys(pass);
    driver.findElement(By.xpath(TradeoHomePage.userLoginButtonXpath)).click();
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
