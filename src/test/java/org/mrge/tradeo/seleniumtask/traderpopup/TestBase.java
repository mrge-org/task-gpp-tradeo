package org.mrge.tradeo.seleniumtask.traderpopup;

import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.mrge.tradeo.seleniumtask.utils.TestConstants.IMPLICIT_WAIT_IN_SEC;
import static org.mrge.tradeo.seleniumtask.utils.TestConstants.WAIT_AFTER_CLICK_IN_SEC;

import org.mrge.tradeo.seleniumtask.pagesmodels.TradeoHomePage;
import org.mrge.tradeo.seleniumtask.pagesmodels.TradeoMyAccountPage;
import org.mrge.tradeo.seleniumtask.pagesmodels.TradeoTestCustomer;
import org.mrge.tradeo.seleniumtask.pagesmodels.TradeoTradersPage;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by gpetrov on 7/23/16.
 */
public abstract class TestBase {

    protected static WebDriver driver;
    protected boolean acceptNextAlert = true;
    protected StringBuffer verificationErrors = new StringBuffer();
    protected WebElement welcomePage;

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    protected static WebDriverWait openTradersPageViaStartCopyingButton(WebDriver driver, WebElement welcomePage) {
        gotoHomePageAndSignIn(TradeoTestCustomer.loginCorrectPassword);
        WebDriverWait wait = new WebDriverWait(driver, WAIT_AFTER_CLICK_IN_SEC);
        wait.until(ExpectedConditions.titleContains(TradeoMyAccountPage.title));
        welcomePage = driver.findElement(By.id(TradeoMyAccountPage.welcomePageDivId));

        WebElement startCopyingButton = welcomePage.findElement(By.xpath(TradeoMyAccountPage.startCopyingButtonXpath));
        WebElement copyingVideo = welcomePage.findElement(By.xpath(TradeoMyAccountPage.copyingVideoXpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", copyingVideo);
        startCopyingButton.click();
        int currentWindowIndex = driver.getWindowHandles().size() -1;

        driver.switchTo().window(driver.getWindowHandles().toArray()[currentWindowIndex].toString());
        return wait;
    }

    protected static void gotoHomePageAndSignIn(String pass) {
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

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected String closeAlertAndGetItsText() {
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
