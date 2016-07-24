package org.mrge.tradeo.seleniumtask.traderpopup;

import org.junit.Before;
import org.junit.Test;
import org.mrge.tradeo.seleniumtask.pagesmodels.TradeoTradersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.SystemClock;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mrge.tradeo.seleniumtask.utils.TestConstants.IMPLICIT_WAIT_IN_SEC;
import static org.mrge.tradeo.seleniumtask.utils.TestConstants.WAIT_AFTER_CLICK_IN_SEC;

/**
 * Created by gpetrov on 7/24/16.
 */
public class TradersPageNewCustomerTourTest extends TestBase {

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_IN_SEC, TimeUnit.SECONDS);
        openTradersPageViaStartCopyingButton(driver, welcomePage);
        WebDriverWait wait = new WebDriverWait(driver, WAIT_AFTER_CLICK_IN_SEC);
        wait.until(ExpectedConditions.titleContains(TradeoTradersPage.title));
    }

    @Test
    public void overlayIsPresent() throws Exception{
        WebElement overlay = driver.findElement(By.id(TradeoTradersPage.overlayId));
        assertNotNull(overlay);
    }

    @Test
    public void traceWelcomeTour() throws Exception {
        int nextButtonIndex = 7;
        findByClassCheckAndClickNext(driver.findElement(By.id(TradeoTradersPage.tourStepId)), TradeoTradersPage.tourStep1TextEnglish, nextButtonIndex);
        findByClassCheckAndClickNext(driver.findElement(By.id(TradeoTradersPage.tourStepId)), TradeoTradersPage.tourStep2TextEnglish, nextButtonIndex);
        findByClassCheckAndClickNext(driver.findElement(By.id(TradeoTradersPage.tourStepId)), TradeoTradersPage.tourStep3TextEnglish, nextButtonIndex);
        findByClassCheckAndClickNext(driver.findElement(By.id(TradeoTradersPage.tourStepId)), TradeoTradersPage.tourStep4TextEnglish, nextButtonIndex);
        findByClassCheckAndClickNext(driver.findElement(By.id(TradeoTradersPage.tourStepId)), TradeoTradersPage.tourStep5TextEnglish, nextButtonIndex);
        findByClassCheckAndClickNext(driver.findElement(By.id(TradeoTradersPage.tourStepId)), TradeoTradersPage.tourStep6TextEnglish, nextButtonIndex);
        findByClassCheckAndClickNext(driver.findElement(By.id(TradeoTradersPage.tourStepId)), TradeoTradersPage.tourStep7TextEnglish, nextButtonIndex);
        findByClassCheckAndCloseBrowser(driver.findElement(By.id(TradeoTradersPage.tourStepId)), TradeoTradersPage.tourStep8TextEnglish);
    }

    private void findByClassCheckAndClickNext(WebElement step, String lookFor, int index) {
        assertNotNull(step);
        assertTrue(step.getText().contains(lookFor));
        driver.findElements(By.className(TradeoTradersPage.tourStepNextButtonClass)).get(index).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void findByClassCheckAndCloseBrowser(WebElement step1, String lookFor) {
        assertNotNull(step1);
        assertTrue(step1.getText().contains(lookFor));
        assertNotNull(driver.findElement(By.className(TradeoTradersPage.tourStepCloseButtonClass)));
    }


}
