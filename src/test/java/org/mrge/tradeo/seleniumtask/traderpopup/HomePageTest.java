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

public class HomePageTest extends TestBase{

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
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
}
