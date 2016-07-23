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
public class CopyTradersOnMyAccountPageTest {

    private WebDriver driver;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private WebElement welcomePage;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_IN_SEC, TimeUnit.SECONDS);
        gotoHomePageAndSignIn(TradeoTestCustomer.loginCorrectPassword);
        WebDriverWait wait = new WebDriverWait(driver, WAIT_AFTER_CLICK_IN_SEC);
        wait.until(ExpectedConditions.titleContains(TradeoMyAccountPage.title));
        welcomePage = driver.findElement(By.id(TradeoMyAccountPage.welcomePageDivId));
    }

    @Test
    public void copyingHeadingIsPresent() throws Exception {
        WebElement copyingHeading = welcomePage.findElement(By.xpath(TradeoMyAccountPage.headingCopyingXpath));
        assertNotNull(copyingHeading);
        assertTrue(copyingHeading.getText().contains(TradeoMyAccountPage.headingCopyingTextEnglish));
    }

    @Test
    public void copyingVideoIsPresent() throws Exception {
        WebElement copyingVideo = welcomePage.findElement(By.xpath(TradeoMyAccountPage.copyingVideoXpath));
        assertNotNull(copyingVideo);
        assertTrue(copyingVideo.getAttribute("data-video-url").contains(TradeoMyAccountPage.copyingVideoUrl));
    }

    @Test
    public void socialSectionHasTwoTypesOfTraders() throws Exception {
        WebElement socialSection = welcomePage.findElement(By.className(TradeoMyAccountPage.socialSectionClass));
        assertNotNull(socialSection);

        WebElement topTradersCopy = socialSection.findElement(By.className(TradeoMyAccountPage.topTradersCopyClass));
        assertNotNull(topTradersCopy);
        assertEquals( 5, topTradersCopy.findElements(By.tagName("button")).size());

        WebElement topTradersFollow = socialSection.findElement(By.className(TradeoMyAccountPage.topTradersFollowClass));
        assertNotNull(topTradersFollow);
        assertEquals( 5, topTradersFollow.findElements(By.tagName("button")).size());
    }

    @Test
    public void startCopyingOpensNewTab() throws Exception{
        WebElement startCopyingButton = welcomePage.findElement(By.xpath(TradeoMyAccountPage.startCopyingButtonXpath));
        WebElement copyingVideo = welcomePage.findElement(By.xpath(TradeoMyAccountPage.copyingVideoXpath));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", copyingVideo);
        startCopyingButton.click();

        //TODO see the newly opened browser window
        WebDriverWait wait = new WebDriverWait(driver, WAIT_AFTER_CLICK_IN_SEC);
        wait.until(ExpectedConditions.titleContains(TradeoTradersPage.title));
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
